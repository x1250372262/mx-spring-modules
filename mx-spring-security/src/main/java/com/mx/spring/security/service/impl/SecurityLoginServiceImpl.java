package com.mx.spring.security.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.constants.Constants;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.View;
import com.mx.spring.dev.result.Result;
import com.mx.spring.dev.util.BeanUtil;
import com.mx.spring.dev.util.TimeUtil;
import com.mx.spring.dev.util.WebUtil;
import com.mx.spring.jdbc.mybatis.plus.Mp;
import com.mx.spring.redis.api.IRedisApi;
import com.mx.spring.security.SaUtils;
import com.mx.spring.security.base.bean.LoginResult;
import com.mx.spring.security.base.bean.SecurityLoginInfoBean;
import com.mx.spring.security.base.config.MxSecurityConfig;
import com.mx.spring.security.base.model.SecurityUser;
import com.mx.spring.security.base.vo.SecurityLoginVO;
import com.mx.spring.security.handler.Handler;
import com.mx.spring.security.handler.ILoginHandler;
import com.mx.spring.security.mapper.ISecurityUserMapper;
import com.mx.spring.security.service.ISecurityLoginService;
import com.mx.spring.security.service.ISecurityUserRoleService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.mx.spring.security.SaUtils.PERMISSION_LIST;
import static com.mx.spring.security.SaUtils.USER_INFO;
import static com.mx.spring.security.base.code.SecurityCode.*;

/**
 * @Author: mengxiang.
 * @create: 2021-09-03 15:21
 * @Description:
 */
@Service
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class SecurityLoginServiceImpl implements ISecurityLoginService {

    private final SecurityUser bean = SecurityUser.init();
    @Autowired
    private ISecurityUserMapper iSecurityUserMapper;
    @Autowired
    private ISecurityUserRoleService iSecurityUserRoleService;
    @Autowired
    private IRedisApi iRedisApi;
    @Autowired
    private SaUtils saUtils;
    @Autowired
    private MxSecurityConfig config;

    @Override
    public View<LoginResult> login(String userName, String password) throws MxException {
        Map<String, String> params = ServletUtil.getParamMap(WebUtil.request());
        ILoginHandler loginHandler = Handler.loginHandler();
        Result r = loginHandler.loginBefore(params);
        if (Handler.check(r)) {
            return Handler.toM(r);
        }
        SecurityUser securityUser = iSecurityUserMapper.selectOne(Mp.lqw(bean).eq(SecurityUser::getUserName, userName).eq(SecurityUser::getClient, config.getClient()));
        if (securityUser == null) {
            return View.fail(SECURITY_LOGIN_USER_NAME_NOT_EXIST.getCode(), SECURITY_LOGIN_USER_NAME_NOT_EXIST.getMsg());
        }
        if (Objects.equals(Constants.BOOL_TRUE,securityUser.getDisableStatus())) {
            return View.fail(SECURITY_LOGIN_USER_DISABLE.getCode(), SECURITY_LOGIN_USER_DISABLE.getMsg());
        }
        //锁住了
        if (Objects.equals(Constants.BOOL_TRUE, securityUser.getLoginLockStatus()) && System.currentTimeMillis() < securityUser.getLoginLockEndTime()) {
            String msg = SECURITY_LOGIN_USER_LOCKED.getMsg();
            msg = StrUtil.format(msg, TimeUtil.formatTime(securityUser.getLoginLockEndTime()));
            return View.fail(SECURITY_LOGIN_USER_LOCKED.getCode(), msg);
        } else if (Objects.equals(Constants.BOOL_TRUE, securityUser.getLoginLockStatus()) && System.currentTimeMillis() > securityUser.getLoginLockEndTime()) {
            securityUser.setLoginLockStatus(Constants.BOOL_FALSE);
            securityUser.setLoginErrorCount(0);
            securityUser.setLoginLockStartTime(0L);
            securityUser.setLoginLockEndTime(0L);
        }
        password = DigestUtils.md5DigestAsHex(Base64.encodeBase64((password + securityUser.getSalt()).getBytes(StandardCharsets.UTF_8)));
        if (!password.equals(securityUser.getPassword())) {
            r = loginHandler.loginFail(params, securityUser);
            if (Handler.check(r)) {
                return Handler.toM(r);
            }
            //次数+1 到数之后直接冻结
            securityUser.setLoginErrorCount(securityUser.getLoginErrorCount() + 1);
            //冻结了 配置设置了错误次数 并且实际错误次数大于等于设置的错误次数
            if(config.getErrorCount() > 0 && securityUser.getLoginErrorCount() >= config.getErrorCount()){
                securityUser.setLoginLockStatus(Constants.BOOL_TRUE);
                securityUser.setLoginLockStartTime(System.currentTimeMillis());
                securityUser.setLoginLockEndTime(System.currentTimeMillis() + TimeUtil.DAY);
            }
            iSecurityUserMapper.updateById(securityUser);
            return View.fail(SECURITY_LOGIN_USER_NAME_OR_PASSWORD_ERROR.getCode(), SECURITY_LOGIN_USER_NAME_OR_PASSWORD_ERROR.getMsg());
        } else {
            r = loginHandler.loginSuccess(params, securityUser);
            if (Handler.check(r)) {
                return Handler.toM(r);
            }

            //重置时间和次数
            securityUser.setLoginLockStatus(Constants.BOOL_FALSE);
            securityUser.setLoginErrorCount(0);
            securityUser.setLoginLockStartTime(0L);
            securityUser.setLoginLockEndTime(0L);
            iSecurityUserMapper.updateById(securityUser);
        }
        StpUtil.login(securityUser.getId());
        SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();

        //设置用户到缓存
        cacheUser(securityUser, saTokenInfo);
        LoginResult loginResult = BeanUtil.copy(saTokenInfo, LoginResult::new);
        if (r != null) {
            loginResult.setAttrs(r.attrs());
        }
        return View.ok(loginResult);
    }

    @Override
    public Result logout() throws MxException {
        Map<String, String> params = ServletUtil.getParamMap(WebUtil.request());
        ILoginHandler loginHandler = Handler.loginHandler();
        Result r = loginHandler.logoutBefore(params);
        if (Handler.check(r)) {
            return r;
        }
        StpUtil.logout();
        r = loginHandler.logoutAfter(params);
        if (Handler.check(r)) {
            return r;
        }
        return Result.ok();
    }

    public void cacheUser(SecurityUser securityUser, SaTokenInfo saTokenInfo) throws MxException {
        //设置用户信息到redis
        String userKey = StrUtil.format(USER_INFO, securityUser.getClient(), securityUser.getId());
        StpUtil.getTokenSessionByToken(saTokenInfo.getTokenValue()).set(userKey, securityUser);

        //设置权限到redis
        //先删除redis的数据
        String permissionKey = StrUtil.format(PERMISSION_LIST, securityUser.getClient(), saUtils.getToken(), StpUtil.getLoginType(), securityUser.getId());
        List<Object> redisPermissionList = JSONObject.parseArray(Convert.toStr(iRedisApi.strGet(permissionKey)));
        if (CollUtil.isNotEmpty(redisPermissionList)) {
            iRedisApi.delete(permissionKey);
        }
        List<String> permissionList = iSecurityUserRoleService.securityUserPermissionList(securityUser.getId(), saTokenInfo.getTokenValue());
        iRedisApi.strSet(permissionKey, JSONObject.toJSONString(permissionList));
    }

    @Override
    public SecurityLoginVO info() throws MxException {
        SecurityUser securityUser = iSecurityUserMapper.selectOne(Mp.lqw(bean).eq(SecurityUser::getId, saUtils.loginId()));
        SecurityLoginVO securityUserVO = BeanUtil.copy(securityUser, SecurityLoginVO::new);
        return BeanUtil.copy(securityUserVO, SecurityLoginVO::new);
    }

    @Override
    public Result update(SecurityLoginInfoBean securityLoginInfoBean) throws MxException {
        SecurityUser securityUser = iSecurityUserMapper.selectOne(Mp.lqw(bean).eq(SecurityUser::getId, saUtils.loginId()));
        if (securityUser == null) {
            return Result.noData();
        }
        securityUser = BeanUtil.duplicate(securityLoginInfoBean, securityUser);
        int result = iSecurityUserMapper.updateById(securityUser);
        return Result.result(result);
    }

    @Override
    public Result password(String oldPassword, String newPassword, String rePassword) throws MxException {
        if (!newPassword.equals(rePassword)) {
            return Result.create(SECURITY_USER_PASSWORD_NOT_SAME.getCode()).msg(SECURITY_USER_PASSWORD_NOT_SAME.getMsg());
        }
        SecurityUser securityUser = iSecurityUserMapper.selectOne(Mp.lqw(bean).eq(SecurityUser::getId, saUtils.loginId()));
        if (securityUser == null) {
            return Result.noData();
        }
        oldPassword = DigestUtils.md5DigestAsHex(Base64.encodeBase64((oldPassword + securityUser.getSalt()).getBytes(StandardCharsets.UTF_8)));
        if (!oldPassword.equals(securityUser.getPassword())) {
            return Result.create(SECURITY_USER_OLD_PASSWORD_ERROR.getCode()).msg(SECURITY_USER_OLD_PASSWORD_ERROR.getMsg());
        }
        newPassword = DigestUtils.md5DigestAsHex(Base64.encodeBase64((newPassword + securityUser.getSalt()).getBytes(StandardCharsets.UTF_8)));
        if (newPassword.equals(securityUser.getPassword())) {
            return Result.create(SECURITY_USER_NEW_PASSWORD_NOT_SAME_OLD_PASSWORD.getCode()).msg(SECURITY_USER_NEW_PASSWORD_NOT_SAME_OLD_PASSWORD.getMsg());
        }
        securityUser.setPassword(newPassword);
        securityUser.setLastModifyTime(System.currentTimeMillis());
        securityUser.setLastModifyUser(securityUser.getId());
        int result = iSecurityUserMapper.updateById(securityUser);
        return Result.result(result);
    }
}

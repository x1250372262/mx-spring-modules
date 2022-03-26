package com.mx.spring.security.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.constants.Constants;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.mybatisplus.MMP;
import com.mx.spring.dev.support.redis.api.IRedisService;
import com.mx.spring.dev.support.security.SaUtils;
import com.mx.spring.dev.support.security.model.SecurityUser;
import com.mx.spring.dev.util.BeanUtils;
import com.mx.spring.dev.util.ListUtils;
import com.mx.spring.dev.util.TimeHelper;
import com.mx.spring.dev.util.WebUtils;
import com.mx.spring.security.bean.LoginResult;
import com.mx.spring.security.bean.SecurityLoginInfoBean;
import com.mx.spring.security.config.MxSecurityConfig;
import com.mx.spring.security.handler.Handler;
import com.mx.spring.security.handler.ILoginHandler;
import com.mx.spring.security.mapper.ISecurityUserMapper;
import com.mx.spring.security.service.ISecurityLoginService;
import com.mx.spring.security.service.ISecurityUserRoleService;
import com.mx.spring.security.vo.SecurityLoginVO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.mx.spring.dev.support.security.SaUtils.PERMISSION_LIST;
import static com.mx.spring.dev.support.security.SaUtils.USER_INFO;
import static com.mx.spring.security.code.SecurityCode.*;

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
    private IRedisService iRedisService;
    @Autowired
    private SaUtils saUtils;
    @Autowired
    private MxSecurityConfig config;

    @Override
    public M<LoginResult> login(String userName, String password) throws MxException {
        Map<String, Object> attrs = new HashMap<>();
        Map<String, String> params = ServletUtil.getParamMap(WebUtils.request());
        ILoginHandler loginHandler = Handler.loginHandler();
        R r = loginHandler.loginBefore(params);
        if (Handler.check(r)) {
            return Handler.toM(r);
        }
        SecurityUser securityUser = iSecurityUserMapper.selectOne(MMP.lqw(bean).eq(SecurityUser::getUserName, userName).eq(SecurityUser::getClient, config.getClient()));
        if (securityUser == null) {
            return M.fail(SECURITY_LOGIN_USER_NAME_NOT_EXIST.getCode(), SECURITY_LOGIN_USER_NAME_NOT_EXIST.getMsg());
        }
        //锁住了
        if (Objects.equals(Constants.BOOL_TRUE, securityUser.getLoginLockStatus()) && System.currentTimeMillis() < securityUser.getLoginLockEndTime()) {
            String msg = SECURITY_LOGIN_USER_LOCKED.getMsg();
            msg = StrUtil.format(msg, TimeHelper.formatTime(securityUser.getLoginLockEndTime()));
            return M.fail(SECURITY_LOGIN_USER_LOCKED.getCode(), msg);
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
            //冻结了
            if (securityUser.getLoginErrorCount() >= 5) {
                securityUser.setLoginLockStatus(Constants.BOOL_TRUE);
                securityUser.setLoginLockStartTime(System.currentTimeMillis());
                securityUser.setLoginLockEndTime(System.currentTimeMillis() + TimeHelper.DAY);
            }
            iSecurityUserMapper.updateById(securityUser);
            return M.fail(SECURITY_LOGIN_USER_NAME_OR_PASSWORD_ERROR.getCode(), SECURITY_LOGIN_USER_NAME_OR_PASSWORD_ERROR.getMsg());
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
        //设置用户信息到redis
        StpUtil.getTokenSessionByToken(saTokenInfo.getTokenValue()).set(USER_INFO + securityUser.getId(), securityUser);
        //设置权限到redis
        setLoginSecurityUserPermissionToRedis(securityUser.getId(), saTokenInfo.getTokenValue());
        LoginResult loginResult = BeanUtils.copy(saTokenInfo, LoginResult::new);
        if (r != null) {
            loginResult.setAttrs(r.attrs());
        }
        return M.ok(loginResult);
    }

    @Override
    public R logout() throws MxException {
        Map<String, String> params = ServletUtil.getParamMap(WebUtils.request());
        ILoginHandler loginHandler = Handler.loginHandler();
        R r = loginHandler.logoutBefore(params);
        if (Handler.check(r)) {
            return r;
        }
        StpUtil.logout();
        r = loginHandler.logoutAfter(params);
        if (Handler.check(r)) {
            return r;
        }
        return R.ok();
    }

    public void setLoginSecurityUserPermissionToRedis(String securityUserId, String token) throws MxException {
        //设置权限角色到redis
        //先删除redis的数据
        String permissionKey = StrUtil.format(PERMISSION_LIST, saUtils.getToken(), StpUtil.getLoginType(), securityUserId);
        List<Object> redisPermissionList = JSONObject.parseArray(Convert.toStr(iRedisService.strGet(permissionKey)));
        if (ListUtils.isNotEmpty(redisPermissionList)) {
            iRedisService.delete(permissionKey);
        }
        List<String> permissionList = iSecurityUserRoleService.securityUserPermissionList(securityUserId, token);
        iRedisService.strSet(permissionKey, JSONObject.toJSONString(permissionList));
    }

    @Override
    public SecurityLoginVO info() throws MxException {
        SecurityUser securityUser = iSecurityUserMapper.selectOne(MMP.lqw(bean).eq(SecurityUser::getId, saUtils.loginId()));
        SecurityLoginVO securityUserVO = BeanUtils.copy(securityUser, SecurityLoginVO::new);
        return BeanUtils.copy(securityUserVO, SecurityLoginVO::new);
    }

    @Override
    public R update(SecurityLoginInfoBean securityLoginInfoBean) throws MxException {
        SecurityUser securityUser = iSecurityUserMapper.selectOne(MMP.lqw(bean).eq(SecurityUser::getId, saUtils.loginId()));
        if (securityUser == null) {
            return R.noData();
        }
        securityUser = BeanUtils.duplicate(securityLoginInfoBean, securityUser);
        int result = iSecurityUserMapper.updateById(securityUser);
        return R.result(result);
    }

    @Override
    public R password(String oldPassword, String newPassword, String rePassword) throws MxException {
        if (!newPassword.equals(rePassword)) {
            return R.create(SECURITY_USER_PASSWORD_NOT_SAME.getCode()).msg(SECURITY_USER_PASSWORD_NOT_SAME.getMsg());
        }
        SecurityUser securityUser = iSecurityUserMapper.selectOne(MMP.lqw(bean).eq(SecurityUser::getId, saUtils.loginId()));
        if (securityUser == null) {
            return R.noData();
        }
        oldPassword = DigestUtils.md5DigestAsHex(Base64.encodeBase64((oldPassword + securityUser.getSalt()).getBytes(StandardCharsets.UTF_8)));
        if (!oldPassword.equals(securityUser.getPassword())) {
            return R.create(SECURITY_USER_OLD_PASSWORD_ERROR.getCode()).msg(SECURITY_USER_OLD_PASSWORD_ERROR.getMsg());
        }
        newPassword = DigestUtils.md5DigestAsHex(Base64.encodeBase64((newPassword + securityUser.getSalt()).getBytes(StandardCharsets.UTF_8)));
        if (newPassword.equals(securityUser.getPassword())) {
            return R.create(SECURITY_USER_NEW_PASSWORD_NOT_SAME_OLD_PASSWORD.getCode()).msg(SECURITY_USER_NEW_PASSWORD_NOT_SAME_OLD_PASSWORD.getMsg());
        }
        securityUser.setPassword(newPassword);
        securityUser.setLastModifyTime(System.currentTimeMillis());
        securityUser.setLastModifyUser(securityUser.getId());
        int result = iSecurityUserMapper.updateById(securityUser);
        return R.result(result);
    }
}

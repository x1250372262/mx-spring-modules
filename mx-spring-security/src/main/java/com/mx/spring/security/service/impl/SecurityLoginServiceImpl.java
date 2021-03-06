package com.mx.spring.security.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.constants.Constants;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;
import com.mx.spring.dev.result.MxView;
import com.mx.spring.dev.util.BeanUtil;
import com.mx.spring.dev.util.TimeUtil;
import com.mx.spring.dev.util.WebUtil;
import com.mx.spring.jdbc.mybatis.plus.Mp;
import com.mx.spring.redis.api.IRedisApi;
import com.mx.spring.security.SaUtil;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.mx.spring.security.SaUtil.PERMISSION_LIST;
import static com.mx.spring.security.SaUtil.USER_INFO;
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
    private SaUtil saUtils;
    @Autowired
    private MxSecurityConfig config;

    @Override
    public MxView<LoginResult> login(String userName, String password) throws MxException {
        Map<String, String> params = ServletUtil.getParamMap(WebUtil.request());
        ILoginHandler loginHandler = Handler.loginHandler();
        MxResult r = loginHandler.loginBefore(params);
        if (Handler.error(r)) {
            return Handler.toM(r);
        }
        SecurityUser securityUser = r.attr("securityUser");
        if(securityUser == null){
            securityUser = iSecurityUserMapper.selectOne(Mp.lqw(bean)
                    .eq(SecurityUser::getUserName, userName)
                    .eq(SecurityUser::getClient, config.getClient()));
        }
        if (securityUser == null) {
            return MxView.fail(SECURITY_LOGIN_USER_NAME_NOT_EXIST.getCode(), SECURITY_LOGIN_USER_NAME_NOT_EXIST.getMsg());
        }
        if (Objects.equals(Constants.BOOL_TRUE, securityUser.getDisableStatus())) {
            return MxView.fail(SECURITY_LOGIN_USER_DISABLE.getCode(), SECURITY_LOGIN_USER_DISABLE.getMsg());
        }
        //?????????
        if (Objects.equals(Constants.BOOL_TRUE, securityUser.getLoginLockStatus()) && System.currentTimeMillis() < securityUser.getLoginLockEndTime()) {
            String msg = SECURITY_LOGIN_USER_LOCKED.getMsg();
            msg = StrUtil.format(msg, TimeUtil.formatTime(securityUser.getLoginLockEndTime()));
            return MxView.fail(SECURITY_LOGIN_USER_LOCKED.getCode(), msg);
        } else if (Objects.equals(Constants.BOOL_TRUE, securityUser.getLoginLockStatus()) && System.currentTimeMillis() > securityUser.getLoginLockEndTime()) {
            securityUser.setLoginLockStatus(Constants.BOOL_FALSE);
            securityUser.setLoginErrorCount(0);
            securityUser.setLoginLockStartTime(0L);
            securityUser.setLoginLockEndTime(0L);
        }
        password = DigestUtils.md5DigestAsHex(Base64.encodeBase64((password + securityUser.getSalt()).getBytes(StandardCharsets.UTF_8)));
        if (!password.equals(securityUser.getPassword())) {
            r = loginHandler.loginFail(params, securityUser);
            if (Handler.error(r)) {
                return Handler.toM(r);
            }
            //??????+1 ????????????????????????
            securityUser.setLoginErrorCount(securityUser.getLoginErrorCount() + 1);
            //????????? ??????????????????????????? ?????????????????????????????????????????????????????????
            if (config.getErrorCount() > 0 && securityUser.getLoginErrorCount() >= config.getErrorCount()) {
                securityUser.setLoginLockStatus(Constants.BOOL_TRUE);
                securityUser.setLoginLockStartTime(System.currentTimeMillis());
                securityUser.setLoginLockEndTime(System.currentTimeMillis() + TimeUtil.DAY);
            }
            iSecurityUserMapper.updateById(securityUser);
            return MxView.fail(SECURITY_LOGIN_USER_NAME_OR_PASSWORD_ERROR.getCode(), SECURITY_LOGIN_USER_NAME_OR_PASSWORD_ERROR.getMsg());
        }
        r = loginHandler.loginSuccess(params, securityUser);
        if (Handler.error(r)) {
            return Handler.toM(r);
        }

        //?????????????????????
        securityUser.setLoginLockStatus(Constants.BOOL_FALSE);
        securityUser.setLoginErrorCount(0);
        securityUser.setLoginLockStartTime(0L);
        securityUser.setLoginLockEndTime(0L);
        iSecurityUserMapper.updateById(securityUser);
        StpUtil.login(securityUser.getId());
        SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();

        //?????????????????????
        cacheUser(securityUser, saTokenInfo);
        LoginResult loginResult = BeanUtil.copy(saTokenInfo, LoginResult::new);
        if (r != null) {
            loginResult.setAttrs(r.attrs());
        }

        //?????????????????????  ?????????????????????
        loginHandler.loginComplete(params, securityUser, saTokenInfo);
        return MxView.ok(loginResult);
    }

    @Override
    public MxResult logout() throws MxException {
        Map<String, String> params = ServletUtil.getParamMap(WebUtil.request());
        ILoginHandler loginHandler = Handler.loginHandler();
        MxResult r = loginHandler.logoutBefore(params);
        if (Handler.error(r)) {
            return r;
        }
        StpUtil.logout();
        r = loginHandler.logoutAfter(params);
        if (Handler.error(r)) {
            return r;
        }
        return MxResult.ok();
    }

    public void cacheUser(SecurityUser securityUser, SaTokenInfo saTokenInfo) throws MxException {
        //?????????????????????redis
        String userKey = StrUtil.format(USER_INFO, securityUser.getClient(), securityUser.getId());
        StpUtil.getTokenSessionByToken(saTokenInfo.getTokenValue()).set(userKey, securityUser);

        //???????????????redis
        //?????????redis?????????
        String permissionKey = StrUtil.format(PERMISSION_LIST, securityUser.getClient(), saUtils.getToken(), StpUtil.getLoginType(), securityUser.getId());
        String permissionStr = Convert.toStr(iRedisApi.strGet(permissionKey));
        if (StringUtils.isNotBlank(permissionStr)) {
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
    public MxResult update(SecurityLoginInfoBean securityLoginInfoBean) throws MxException {
        SecurityUser securityUser = iSecurityUserMapper.selectOne(Mp.lqw(bean).eq(SecurityUser::getId, saUtils.loginId()));
        if (securityUser == null) {
            return MxResult.noData();
        }
        securityUser = BeanUtil.duplicate(securityLoginInfoBean, securityUser);
        int result = iSecurityUserMapper.updateById(securityUser);
        return MxResult.result(result);
    }

    @Override
    public MxResult password(String oldPassword, String newPassword, String rePassword) throws MxException {
        if (!newPassword.equals(rePassword)) {
            return MxResult.create(SECURITY_USER_PASSWORD_NOT_SAME.getCode()).msg(SECURITY_USER_PASSWORD_NOT_SAME.getMsg());
        }
        SecurityUser securityUser = iSecurityUserMapper.selectOne(Mp.lqw(bean).eq(SecurityUser::getId, saUtils.loginId()));
        if (securityUser == null) {
            return MxResult.noData();
        }
        oldPassword = DigestUtils.md5DigestAsHex(Base64.encodeBase64((oldPassword + securityUser.getSalt()).getBytes(StandardCharsets.UTF_8)));
        if (!oldPassword.equals(securityUser.getPassword())) {
            return MxResult.create(SECURITY_USER_OLD_PASSWORD_ERROR.getCode()).msg(SECURITY_USER_OLD_PASSWORD_ERROR.getMsg());
        }
        newPassword = DigestUtils.md5DigestAsHex(Base64.encodeBase64((newPassword + securityUser.getSalt()).getBytes(StandardCharsets.UTF_8)));
        if (newPassword.equals(securityUser.getPassword())) {
            return MxResult.create(SECURITY_USER_NEW_PASSWORD_NOT_SAME_OLD_PASSWORD.getCode()).msg(SECURITY_USER_NEW_PASSWORD_NOT_SAME_OLD_PASSWORD.getMsg());
        }
        securityUser.setPassword(newPassword);
        securityUser.setLastModifyTime(System.currentTimeMillis());
        securityUser.setLastModifyUser(securityUser.getId());
        int result = iSecurityUserMapper.updateById(securityUser);
        return MxResult.result(result);
    }
}

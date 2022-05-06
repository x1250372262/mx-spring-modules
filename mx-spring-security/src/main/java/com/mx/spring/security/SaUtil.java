package com.mx.spring.security;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.mx.spring.dev.constants.Constants;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.util.WebUtil;
import com.mx.spring.security.base.config.MxSecurityConfig;
import com.mx.spring.security.base.model.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: mengxiang.
 * @create: 2021-09-04 11:59
 * @Description:
 */
@Component
public class SaUtil {

    /**
     * redis缓存中key user-info:客户端:用户id
     */
    public final static String USER_INFO = "user-info:{}:{}";
    /**
     * redis缓存权限列表 permission:客户端:token:类型:用户id
     */
    public final static String PERMISSION_LIST = "permission:{}:{}:{}:{}";
    @Value("${sa-token.token-name}")
    private String tokenName;
    @Value("${sa-token.token-name}")
    private String token;

    @Autowired
    private MxSecurityConfig mxSecurityConfig;

    public boolean isFounder(String loginId, String token) throws MxException {
        if (StringUtils.isBlank(token)) {
            token = token();
        }
        return Objects.equals(user(loginId, token).getFounder(), Constants.BOOL_TRUE);
    }

    public boolean isFounder(String loginId) throws MxException {
        if (StringUtils.isBlank(loginId)) {
            loginId = loginId();
        }
        return Objects.equals(user(loginId).getFounder(), Constants.BOOL_TRUE);
    }

    public boolean isFounder() throws MxException {
        return isFounder(loginId());
    }

    public String loginId() throws MxException {
        return loginId(token());
    }

    public String loginId(String token) throws MxException {
        return (String) StpUtil.getLoginIdByToken(token);
    }

    public String token() throws MxException {
        return WebUtil.request().getHeader(tokenName);
    }

    public SecurityUser user(String loginId, String token) throws MxException {
        String userKey = StrUtil.format(USER_INFO, mxSecurityConfig.getClient(), loginId);
        return (SecurityUser) StpUtil.getTokenSessionByToken(token).get(userKey);
    }

    public SecurityUser user(String loginId) throws MxException {
        return user(loginId, token());
    }

    public SecurityUser user() throws MxException {
        return user(loginId());
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

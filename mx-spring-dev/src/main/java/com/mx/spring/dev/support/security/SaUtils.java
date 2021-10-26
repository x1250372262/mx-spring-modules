package com.mx.spring.dev.support.security;

import cn.dev33.satoken.stp.StpUtil;
import com.mx.spring.dev.core.Constants;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.security.model.SecurityUser;
import com.mx.spring.dev.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: mengxiang.
 * @create: 2021-09-04 11:59
 * @Description:
 */
@Component
public class SaUtils {

    public final static String USER_INFO = "user-info";
    public final static String PERMISSION_LIST = "{}:{}:permission:{}";
    @Value("${sa-token.token-name}")
    private String tokenName;
    @Value("${sa-token.token-name}")
    private String token;

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
        return WebUtils.request().getHeader(tokenName);
    }

    public SecurityUser user(String loginId, String token) throws MxException {
        return (SecurityUser) StpUtil.getTokenSessionByToken(token).get(USER_INFO + loginId);
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

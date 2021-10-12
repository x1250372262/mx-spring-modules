package com.mx.spring.security;

import cn.dev33.satoken.stp.StpUtil;
import com.mx.spring.dev.core.Constants;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.util.WebUtils;
import com.mx.spring.security.bean.SaUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * @Author: mengxiang.
 * @create: 2021-09-04 11:59
 * @Description:
 */
@Component
public class SaUtils {

    public final static String SESSION_ADMIN_INFO_KEY = "session-user-info-";
    public final static String ADMIN_ROLE_LIST = "admin_role_list";
    public final static String ADMIN_PERMISSION_LIST = "admin_permission_list";
    @Value("${sa-token.token-name}")
    private static String tokenName;
    @Value("${sa-token.token-name}")
    private String token;

    public static boolean isFounder(String loginId, String token) throws MxException {
        if (StringUtils.isBlank(token)) {
            token = token();
        }
        return Objects.equals(user(loginId, token).getFounder(), Constants.BOOL_TRUE);
    }

    public static boolean isFounder(String loginId) throws MxException {
        if (StringUtils.isBlank(loginId)) {
            loginId = loginId();
        }
        return Objects.equals(user(loginId).getFounder(), Constants.BOOL_TRUE);
    }

    public static boolean isFounder() throws MxException {
        return isFounder(loginId());
    }

    public static String loginId() throws MxException {
        return loginId(token());
    }

    public static String loginId(String token) throws MxException {
        return (String) StpUtil.getLoginIdByToken(token);
    }

    public static String token() throws MxException {
        return WebUtils.request().getHeader(tokenName);
    }

    public static SaUser user(String loginId, String token) throws MxException {
        return (SaUser) StpUtil.getTokenSessionByToken(token).get(SESSION_ADMIN_INFO_KEY + loginId);
    }

    public static SaUser user(String loginId) throws MxException {
        return user(loginId, SaUtils.token());
    }

    public static SaUser user() throws MxException {
        return user(loginId());
    }

    @PostConstruct
    public void init() {
        tokenName = token;
    }
}

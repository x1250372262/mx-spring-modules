package com.mx.spring.security;

import cn.dev33.satoken.stp.StpUtil;
import com.mx.spring.dev.core.Constants;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.util.WebUtils;
import com.mx.spring.security.bean.SaUser;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: mengxiang.
 * @create: 2021-09-04 11:59
 * @Description:
 */
@Component
public class SaUtils {

    public final static String ADMIN_SA_TOKEN_HEADER = "mxToken";
    public final static String SESSION_ADMIN_INFO_KEY = "session-user-info-";
    public final static String ADMIN_ROLE_LIST = "admin_role_list";
    public final static String ADMIN_PERMISSION_LIST = "admin_permission_list";

    public static boolean isFounder() throws MxException {
        return Objects.equals(user().getFounder(), Constants.BOOL_TRUE);
    }

    public static String loginId() throws MxException {
        String token = token();
        return (String) StpUtil.getLoginIdByToken(token);
    }

    public static String token() throws MxException {
        return WebUtils.request().getHeader(ADMIN_SA_TOKEN_HEADER);
    }

    public static SaUser user() throws MxException {
        return (SaUser) StpUtil.getTokenSessionByToken(SaUtils.token()).get(SESSION_ADMIN_INFO_KEY + loginId());
    }
}

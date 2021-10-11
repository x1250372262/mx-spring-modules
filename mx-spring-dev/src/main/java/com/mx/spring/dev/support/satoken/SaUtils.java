package com.mx.spring.dev.support.satoken;

import cn.dev33.satoken.stp.StpUtil;
import com.mx.spring.dev.core.Constants;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.satoken.bean.SaUser;
import com.mx.spring.dev.util.WebUtils;
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
    public final static String MEMBER_SA_TOKEN_HEADER = "mxToken";
    public final static String SESSION_ADMIN_INFO_KEY = "session-user-info-";

    public static boolean isFounder() throws MxException {
        return Objects.equals(user().getFounder(), Constants.BOOL_TRUE);
    }

    public static String adminLoginId() throws MxException {
        return loginId(ClientType.ADMIN);
    }

    public static String memberLoginId() throws MxException {
        return loginId(ClientType.MEMBER);
    }

    public static String adminToken() throws MxException {
        return token(ClientType.ADMIN);
    }

    public static String memberToken() throws MxException {
        return token(ClientType.MEMBER);
    }

    private static String loginId(ClientType clientType) throws MxException {
        String token = token(clientType);
        return (String) StpUtil.getLoginIdByToken(token);
    }

    private static String token(ClientType clientType) throws MxException {
        if (clientType == null) {
            throw new MxException("clientType不能为空");
        }
        String token;
        if (ClientType.ADMIN.equals(clientType)) {
            token = WebUtils.request().getHeader(ADMIN_SA_TOKEN_HEADER);
        } else if (ClientType.MEMBER.equals(clientType)) {
            token = WebUtils.request().getHeader(MEMBER_SA_TOKEN_HEADER);
        } else {
            throw new MxException("clientType无效");
        }
        return token;
    }

    public static SaUser user() throws MxException {
        return (SaUser) StpUtil.getTokenSessionByToken(SaUtils.adminToken()).get(SESSION_ADMIN_INFO_KEY + adminLoginId());
    }

    public enum ClientType {
        ADMIN, MEMBER
    }
}

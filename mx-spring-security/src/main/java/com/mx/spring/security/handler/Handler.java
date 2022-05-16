package com.mx.spring.security.handler;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mx.spring.dev.result.MxResult;
import com.mx.spring.dev.result.MxView;
import com.mx.spring.security.base.code.SecurityCode;
import com.mx.spring.security.base.config.MxSecurityConfig;
import org.springframework.context.annotation.Import;

/**
 * @Author: mengxiang.
 * @create: 2021-10-27 15:11
 * @Description:
 */
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class Handler {

    public static MxSecurityConfig config = SpringUtil.getBean(MxSecurityConfig.class);

    public static boolean error(MxResult result) {
        return result != null && SecurityCode.SECURITY_CHECK_ERROR.getCode().equals(result.code());
    }

    public static <T> MxView<T> toM(MxResult result) {
        return MxView.fail(result.getCode(), result.getMsg());
    }

    public static ILoginHandler loginHandler() {
        ILoginHandler loginHandler = SpringUtil.getBean(ClassUtil.loadClass(config.getLoginHandlerClass()));
        if (loginHandler == null) {
            loginHandler = SpringUtil.getBean(ILoginHandler.DefaultLoginHandler.class);
        }
        return loginHandler;
    }

    public static IUserHandler userHandler() {
        IUserHandler userHandler = SpringUtil.getBean(ClassUtil.loadClass(config.getUserHandlerClass()));
        if (userHandler == null) {
            userHandler = SpringUtil.getBean(IUserHandler.DefaultUserHandler.class);
        }
        return userHandler;
    }
}

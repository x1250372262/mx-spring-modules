package com.mx.spring.security.handler;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.R;
import com.mx.spring.security.config.MxSecurityConfig;
import org.springframework.context.annotation.Import;

/**
 * @Author: mengxiang.
 * @create: 2021-10-27 15:11
 * @Description:
 */
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class Handler {

    public static MxSecurityConfig config = SpringUtil.getBean(MxSecurityConfig.class);

    public static boolean check(R result) {
        return result != null && result.code() != -10;
    }

    public static <T> M<T> toM(R result) {
        return M.fail(result.getCode(), result.getMsg());
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

package com.mx.spring.dev.support.log;

/**
 * @Author: 徐建鹏.
 * @create: 2022-03-25 17:57
 * @Description:
 */
public final class MxSecurityManager extends SecurityManager{

    public Class<?> getCallerClass() {
        return super.getClassContext()[3];
    }
}

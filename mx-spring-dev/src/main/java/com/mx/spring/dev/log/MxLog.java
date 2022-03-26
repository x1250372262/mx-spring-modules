package com.mx.spring.dev.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: 徐建鹏.
 * @create: 2022-03-25 16:43
 * @Description:
 */
public class MxLog {

    private static final MxSecurityManager MX_SECURITY_MANAGER = new MxSecurityManager();

    private MxLog() {
    }

    private static Logger create() {
        return LoggerFactory.getLogger(MX_SECURITY_MANAGER.getCallerClass().getName());
    }

    public static Logger create(String name) {
        return LoggerFactory.getLogger(name);
    }

    public static void trace(String message) {
        create().trace(message);
    }

    public static void trace(String message, Object... objects) {
        create().trace(message, objects);
    }

    public static void trace(String message, Object obj) {
        create().trace(message, obj);
    }

    public static void trace(String message, Throwable throwable) {
        create().trace(message, throwable);
    }


    public static void debug(String message) {
        create().debug(message);
    }

    public static void debug(String message, Object... objects) {
        create().debug(message, objects);
    }

    public static void debug(String message, Object obj) {
        create().debug(message, obj);
    }

    public static void debug(String message, Throwable throwable) {
        create().debug(message, throwable);
    }

    public static void info(String message) {
        create().info(message);
    }

    public static void info(String message, Object... objects) {
        create().info(message, objects);
    }

    public static void info(String message, Object obj) {
        create().info(message, obj);
    }

    public static void info(String message, Throwable throwable) {
        create().info(message, throwable);
    }

    public static void warn(String message) {
        create().warn(message);
    }

    public static void warn(String message, Object... objects) {
        create().warn(message, objects);
    }

    public static void warn(String message, Object obj) {
        create().warn(message, obj);
    }

    public static void warn(String message, Throwable throwable) {
        create().warn(message, throwable);
    }

    public static void error(String message) {
        create().error(message);
    }

    public static void error(String message, Object... objects) {
        create().error(message, objects);
    }

    public static void error(String message, Object obj) {
        create().error(message, obj);
    }

    public static void error(String message, Throwable throwable) {
        create().error(message, throwable);
    }

}

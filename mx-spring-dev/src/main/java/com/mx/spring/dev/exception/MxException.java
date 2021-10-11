package com.mx.spring.dev.exception;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
public class MxException extends Exception {

    protected final String message;

    public MxException(String message) {
        this.message = message;
    }

    public MxException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

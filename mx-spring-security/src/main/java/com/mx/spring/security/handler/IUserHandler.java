package com.mx.spring.security.handler;

import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: mengxiang.
 * @create: 2021-10-27 15:03
 * @Description:
 */
public interface IUserHandler {

    /**
     * 添加用户之前
     *
     * @param params
     * @return
     * @throws MxException
     */
    R createBefore(Map<String, String> params) throws MxException;

    /**
     * 添加用户之后
     *
     * @param params
     * @return
     * @throws MxException
     */
    R createAfter(Map<String, String> params) throws MxException;

    @Component
    class DefaultUserHandler implements IUserHandler {

        @Override
        public R createBefore(Map<String, String> params) throws MxException {
            return null;
        }

        @Override
        public R createAfter(Map<String, String> params) throws MxException {
            return null;
        }
    }

}

package com.mx.spring.security.handler;

import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;
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
    MxResult createBefore(Map<String, String> params) throws MxException;

    /**
     * 添加用户之后
     *
     * @param params
     * @return
     * @throws MxException
     */
    MxResult createAfter(Map<String, String> params) throws MxException;

    @Component
    class DefaultUserHandler implements IUserHandler {


        @Override
        public MxResult createBefore(Map<String, String> params) throws MxException {
            return null;
        }

        @Override
        public MxResult createAfter(Map<String, String> params) throws MxException {
            return null;
        }
    }

}

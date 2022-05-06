package com.mx.spring.security.handler;

import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.Result;
import com.mx.spring.security.base.model.SecurityUser;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: mengxiang.
 * @create: 2021-10-27 15:03
 * @Description:
 */
public interface ILoginHandler {

    /**
     * 登录之前
     *
     * @param params
     * @return
     * @throws MxException
     */
    Result loginBefore(Map<String, String> params) throws MxException;

    /**
     * 登录成功
     *
     * @param params
     * @param securityUser
     * @return
     * @throws MxException
     */
    Result loginSuccess(Map<String, String> params, SecurityUser securityUser) throws MxException;

    /**
     * 登录失败
     *
     * @param params
     * @param securityUser
     * @return
     * @throws MxException
     */
    Result loginFail(Map<String, String> params, SecurityUser securityUser) throws MxException;

    /**
     * 退出之前
     *
     * @param params
     * @return
     * @throws MxException
     */
    Result logoutBefore(Map<String, String> params) throws MxException;

    /**
     * 退出之后
     *
     * @param params
     * @return
     * @throws MxException
     */
    Result logoutAfter(Map<String, String> params) throws MxException;


    @Component
    class DefaultLoginHandler implements ILoginHandler {

        @Override
        public Result loginBefore(Map<String, String> params) throws MxException {
            return null;
        }

        @Override
        public Result loginSuccess(Map<String, String> params, SecurityUser securityUser) throws MxException {
            return null;
        }

        @Override
        public Result loginFail(Map<String, String> params, SecurityUser securityUser) throws MxException {
            return null;
        }

        @Override
        public Result logoutBefore(Map<String, String> params) throws MxException {
            return null;
        }

        @Override
        public Result logoutAfter(Map<String, String> params) throws MxException {
            return null;
        }
    }
}

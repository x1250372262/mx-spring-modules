package com.mx.spring.security.handler;

import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;
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
    MxResult loginBefore(Map<String, String> params) throws MxException;

    /**
     * 登录成功
     *
     * @param params
     * @param securityUser
     * @return
     * @throws MxException
     */
    MxResult loginSuccess(Map<String, String> params, SecurityUser securityUser) throws MxException;

    /**
     * 登录失败
     *
     * @param params
     * @param securityUser
     * @return
     * @throws MxException
     */
    MxResult loginFail(Map<String, String> params, SecurityUser securityUser) throws MxException;

    /**
     * 退出之前
     *
     * @param params
     * @return
     * @throws MxException
     */
    MxResult logoutBefore(Map<String, String> params) throws MxException;

    /**
     * 退出之后
     *
     * @param params
     * @return
     * @throws MxException
     */
    MxResult logoutAfter(Map<String, String> params) throws MxException;


    @Component
    class DefaultLoginHandler implements ILoginHandler {

        @Override
        public MxResult loginBefore(Map<String, String> params) throws MxException {
            return null;
        }

        @Override
        public MxResult loginSuccess(Map<String, String> params, SecurityUser securityUser) throws MxException {
            return null;
        }

        @Override
        public MxResult loginFail(Map<String, String> params, SecurityUser securityUser) throws MxException {
            return null;
        }

        @Override
        public MxResult logoutBefore(Map<String, String> params) throws MxException {
            return null;
        }

        @Override
        public MxResult logoutAfter(Map<String, String> params) throws MxException {
            return null;
        }
    }
}

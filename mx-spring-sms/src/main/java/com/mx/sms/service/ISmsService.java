package com.mx.sms.service;

import com.mx.sms.handler.ISmsHandler;
import com.mx.sms.provider.ISmsProvider;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;

import java.util.Map;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:49
 * @Description:
 */
public interface ISmsService {

    /**
     * 发送短信
     *
     * @param mobile
     * @return
     * @throws MxException
     */
    MxResult send(String mobile, Map<String, String> params) throws MxException;


    /**
     * 群发
     *
     * @param mobileJson
     * @return
     * @throws MxException
     */
    MxResult sendMultiple(String mobileJson, Map<String, String> params) throws MxException;


    default ISmsHandler getHander(Class<? extends ISmsHandler> handlerClass) {
        ISmsHandler iSmsHandler;
        try {
            iSmsHandler = handlerClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new MxException("初始化处理类失败", e);
        }
        return iSmsHandler;
    }

    default ISmsProvider getProvider(Class<? extends ISmsProvider> providerClass) {
        ISmsProvider iSmsProvider = null;
        try {
            iSmsProvider = providerClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new MxException("初始化提供者失败", e);
        }
        return iSmsProvider;
    }

}

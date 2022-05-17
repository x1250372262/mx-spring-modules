package com.mx.sms.handler;

import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;

import java.util.Map;

/**
 * @Author: mengxiang.
 * @create: 2022-03-30 11:40
 * @Description:
 */
public interface ISmsHandler {

    /**
     * 发送之前
     *
     * @param mobile
     * @return
     * @throws MxException
     */
    MxResult sendBefore(String mobile, Map<String, String> params) throws MxException;

    /**
     * 存储内容 主要用于验证码
     *
     * @param mobile
     * @return
     * @throws MxException
     */
    void storeContent(String mobile, Map<String, String> params) throws MxException;

    /**
     * 发送之后
     *
     * @param mobile
     * @return
     * @throws MxException
     */
    void sendAfter(String mobile, Map<String, String> params) throws MxException;


}

package com.mx.sms.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.mx.sms.config.SmsConfig;
import com.mx.sms.handler.ISmsHandler;
import com.mx.sms.provider.ISmsProvider;
import com.mx.sms.service.ISmsService;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.mx.sms.code.SmsCode.*;
import static com.mx.sms.constant.SmsConstants.TEMPLATE_PARAMS;
import static com.mx.spring.dev.code.Code.SUCCESS;


/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:52
 * @Description:
 */
@Service
public class SmsServiceImpl implements ISmsService {

    @Autowired
    private SmsConfig smsConfig;

    @Override
    public MxResult send(String mobile, Map<String, String> params) throws MxException {
        ISmsHandler iSmsHandler = getHander(smsConfig.getHandlerClass());
        ISmsProvider iSmsProvider = getProvider(smsConfig.getProviderClass());
        if (iSmsHandler == null) {
            return MxResult.create(SMS_HANDLER_NOT_EXIST.getCode()).msg(SMS_HANDLER_NOT_EXIST.getMsg());
        }
        if (iSmsProvider == null) {
            return MxResult.create(SMS_PROVIDER_NOT_EXIST.getCode()).msg(SMS_PROVIDER_NOT_EXIST.getMsg());
        }
        MxResult handlerResult = iSmsHandler.sendBefore(mobile, params);
        if (handlerResult == null) {
            throw new MxException("sendBefore 返回值不能为空");
        }
        if (SMS_CHECK_ERROR.getCode().equals(handlerResult.getCode())) {
            return handlerResult;
        }
        List<String> templateParams = handlerResult.attr(TEMPLATE_PARAMS);
        MxResult sendResult = iSmsProvider.send(ListUtil.toList(mobile), templateParams);
        if (SUCCESS.getCode().equals(sendResult.getCode())) {
            iSmsHandler.storeContent(mobile, params);
        }
        iSmsHandler.sendAfter(mobile, params);
        return sendResult;
    }

    @Override
    public MxResult sendMultiple(String mobileJson, Map<String, String> params) throws MxException {
        List<String> mobieList = Arrays.asList(mobileJson.split(","));
        ISmsHandler iSmsHandler = getHander(smsConfig.getHandlerClass());
        ISmsProvider iSmsProvider = getProvider(smsConfig.getProviderClass());
        if (iSmsHandler == null) {
            return MxResult.create(SMS_HANDLER_NOT_EXIST.getCode()).msg(SMS_HANDLER_NOT_EXIST.getMsg());
        }
        if (iSmsProvider == null) {
            return MxResult.create(SMS_PROVIDER_NOT_EXIST.getCode()).msg(SMS_PROVIDER_NOT_EXIST.getMsg());
        }
        MxResult handlerResult = iSmsHandler.sendBefore(mobileJson, params);
        if (handlerResult == null) {
            throw new MxException("sendBefore 返回值不能为空");
        }
        if (SMS_CHECK_ERROR.getCode().equals(handlerResult.getCode())) {
            return handlerResult;
        }
        List<String> templateParams = handlerResult.attr(TEMPLATE_PARAMS);
        MxResult sendResult = iSmsProvider.send(mobieList, templateParams);
        if (SUCCESS.getCode().equals(sendResult.getCode())) {
            iSmsHandler.storeContent(mobileJson, params);
        }
        iSmsHandler.sendAfter(mobileJson, params);
        return sendResult;
    }
}

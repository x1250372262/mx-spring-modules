package com.mx.sms.provider.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mx.sms.config.SmsConfig;
import com.mx.sms.provider.ISmsProvider;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;

import java.util.ArrayList;
import java.util.List;

import static com.mx.sms.code.SmsCode.SMS_MOBILE_LIST_NOT_EMPTY;
import static com.mx.sms.constant.SmsConstants.*;

/**
 * @Author: mengxiang.
 * @create: 2022-05-16 11:06
 * @Description:
 */
public class TxSmsProvider implements ISmsProvider {

    private final SmsConfig smsConfig = SpringUtil.getBean(SmsConfig.class);
    private final Credential cred = new Credential(smsConfig.getSecretId(), smsConfig.getSecretKey());


    @Override
    public MxResult send(List<String> mobileList, List<String> params) throws MxException {
        if (CollUtil.isEmpty(mobileList)) {
            return MxResult.create(SMS_MOBILE_LIST_NOT_EMPTY.getCode()).msg(SMS_MOBILE_LIST_NOT_EMPTY.getMsg());
        }
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setReqMethod("POST");
        SmsClient client = new SmsClient(cred, smsConfig.getRegion());
        SendSmsRequest req = new SendSmsRequest();

        req.setSmsSdkAppid(smsConfig.getAppId());
        req.setSign(smsConfig.getSignText());
        req.setTemplateID(smsConfig.getTemplateId());

        /* 模板参数: 模板参数的个数需要与 TemplateId 对应模板的变量个数保持一致，若无模板参数，则设置为空 */
        if (CollUtil.isNotEmpty(params)) {
            req.setTemplateParamSet(params.toArray(new String[0]));
        } else {
            req.setTemplateParamSet(null);
        }
        req.setPhoneNumberSet(mobileList.toArray(new String[0]));
        SendSmsResponse result;
        try {
            result = client.SendSms(req);
        } catch (TencentCloudSDKException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> successList = new ArrayList<>(result.getSendStatusSet().length);
        ArrayList<String> failList = new ArrayList<>(result.getSendStatusSet().length);
        boolean isSuccess = true;
        for (SendStatus sendStatus : result.getSendStatusSet()) {
            if (!SUCCESS_CODE.equals(sendStatus.getCode())) {
                failList.add(sendStatus.getPhoneNumber());
                isSuccess = false;
            } else {
                successList.add(sendStatus.getPhoneNumber());
            }
        }
        if (isSuccess) {
            return MxResult.ok().msg(SUCCESS_MSG)
                    .attr(SUCCESS_LIST, successList)
                    .attr(FAIL_LIST, failList);
        }
        return MxResult.fail().msg(FAIL_MSG)
                .attr(SUCCESS_LIST, successList)
                .attr(FAIL_LIST, failList);
    }
}

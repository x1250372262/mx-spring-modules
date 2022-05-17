package com.mx.sms.config;

import com.mx.sms.handler.ISmsHandler;
import com.mx.sms.provider.ISmsProvider;
import com.mx.spring.dev.exception.MxException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:53
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "mx.sms")
public class SmsConfig {

    /**
     * SecretID.
     */
    private String secretId;

    /**
     * SecretKey.
     */
    private String secretKey;

    /**
     * 地域.
     */
    private String region;

    /**
     * 短信应用ID.
     */
    private String appId;

    /**
     * 模板id.
     */
    private String templateId;

    /**
     * 短信签名内容.
     */
    private String signText;

    /**
     * 短信发送处理类
     */
    private Class<? extends ISmsHandler> handlerClass;

    /**
     * 短信发送提供者
     */
    private Class<? extends ISmsProvider> providerClass;



    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getSignText() {
        return signText;
    }

    public void setSignText(String signText) {
        this.signText = signText;
    }

    public Class<? extends ISmsHandler> getHandlerClass() {
        return handlerClass;
    }

    public void setHandlerClass(Class<? extends ISmsHandler> handlerClass) {
        if(handlerClass == null){
            throw new MxException("处理类不能为空");
        }
        this.handlerClass = handlerClass;
    }

    public Class<? extends ISmsProvider> getProviderClass() {
        return providerClass;
    }

    public void setProviderClass(Class<? extends ISmsProvider> providerClass) {
        if(providerClass == null){
            throw new MxException("短信发送提供者不能为空");
        }
        this.providerClass = providerClass;
    }
}

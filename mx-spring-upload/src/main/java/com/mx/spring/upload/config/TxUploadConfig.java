package com.mx.spring.upload.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:53
 * @Description: 腾讯云配置
 */
@Component
@ConfigurationProperties(prefix = "mx.upload.tx")
public class TxUploadConfig {


    /**
     * 外链地址
     */
    private String url;

    /**
     * 腾讯云secretId
     */
    private String secretId;

    /**
     * 腾讯云secretKey
     */
    private String secretKey;

    /**
     * 存储桶名称
     */
    private String bucket;

    /**
     * 地域
     */
    private String region;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
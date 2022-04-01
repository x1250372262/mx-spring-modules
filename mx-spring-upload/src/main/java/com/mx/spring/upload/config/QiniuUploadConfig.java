package com.mx.spring.upload.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:53
 * @Description: 七牛云配置
 */
@Component
@ConfigurationProperties(prefix = "mx.upload.qiniu")
public class QiniuUploadConfig {


    /**
     * 外链地址
     */
    private String url;

    /**
     * 七牛公钥ak
     */
    private String accessKey;

    /**
     * 七牛私钥sk
     */
    private String secretKey;

    /**
     * 要上传的空间名称
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

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
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
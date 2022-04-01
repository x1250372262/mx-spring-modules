package com.mx.spring.upload.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:53
 * @Description: 阿里云配置
 */
@Component
@ConfigurationProperties(prefix = "mx.upload.ali")
public class AliUploadConfig {


    /**
     * 地址
     */
    private String url;

    /**
     * accessKey
     */
    private String accessKeyId;

    /**
     * secretKey
     */
    private String accessKeySecret;

    /**
     * 桶名称
     */
    private String bucket;

    /**
     * 文件上传地址
     */
    private String endpoint;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
package com.mx.spring.upload.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:53
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "mx.upload.minio")
public class MinIoUploadConfig {


    /**
     * Minio地址
     */
    private String url;

    /**
     * Minio公钥
     */
    private String accessKey;

    /**
     * Minio私钥
     */
    private String secretKey;

    /**
     * Minio桶
     */
    private String bucket;


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
}
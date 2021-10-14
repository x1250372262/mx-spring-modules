package com.mx.spring.dev.support.upload.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:53
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "mx-upload")
public class UploadConfig {

    /**
     * 允许上传的contentType列表  用|分割 默认不限制
     */
    private String contentType;

    /**
     * 上传文件大小最大值(M) 默认10M
     */
    private Integer maxSize;

    /**
     * 文件存储位置 不能为空
     */
    private String fileStoragePath;

    /**
     * 请求域名 不能为空
     */
    private String baseUrl;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getMaxSize() {
        if(Objects.isNull(maxSize)){
            maxSize = 10;
        }
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public String getFileStoragePath() {
        return fileStoragePath;
    }

    public void setFileStoragePath(String fileStoragePath) {
        this.fileStoragePath = fileStoragePath;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}

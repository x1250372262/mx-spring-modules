package com.mx.spring.upload.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:53
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "mx.upload.local")
public class LocalUploadConfig {


    /**
     * 文件存储位置
     */
    private String fileStoragePath;

    /**
     * 请求域名
     */
    private String url;


    public String getFileStoragePath() {
        return fileStoragePath;
    }

    public void setFileStoragePath(String fileStoragePath) {
        this.fileStoragePath = fileStoragePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

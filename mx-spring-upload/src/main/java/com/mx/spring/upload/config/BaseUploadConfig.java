package com.mx.spring.upload.config;

import com.mx.spring.upload.handler.IUploadHandler;
import com.mx.spring.upload.handler.impl.LocalUploadHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:53
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "mx.upload")
public class BaseUploadConfig {

    /**
     * 允许上传的contentType列表  用|分割 默认不限制
     */
    private String contentType;

    /**
     * 上传文件大小最大值(M) 默认10M
     */
    private Integer maxSize;

    /**
     * 文件上传处理类
     */
    private Class<? extends IUploadHandler> handlerClass;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getMaxSize() {
        if (Objects.isNull(maxSize)) {
            maxSize = 10;
        }
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Class<? extends IUploadHandler> getHandlerClass() {
        return handlerClass;
    }

    public void setHandlerClass(Class<? extends IUploadHandler> handlerClass) {
        if (handlerClass == null) {
            handlerClass = LocalUploadHandler.class;
        }
        this.handlerClass = handlerClass;
    }
}

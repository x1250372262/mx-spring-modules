package com.mx.spring.dev.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: mengxiang.
 * @create: 2022-03-25 20:28
 * @Description: 系统配置
 */
@Component
@ConfigurationProperties(prefix = "mx.config")
public class MxConfig {

    /**
     * 是否输出resturl
     */
    private boolean printRestUrl;

    /**
     * 是否格式化请求
     */
    private boolean formatRequest;


    public boolean isPrintRestUrl() {
        return printRestUrl;
    }

    public void setPrintRestUrl(boolean printRestUrl) {
        this.printRestUrl = printRestUrl;
    }

    public boolean isFormatRequest() {
        return formatRequest;
    }

    public void setFormatRequest(boolean formatRequest) {
        this.formatRequest = formatRequest;
    }
}

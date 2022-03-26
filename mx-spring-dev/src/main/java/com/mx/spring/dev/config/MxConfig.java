package com.mx.spring.dev.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐建鹏.
 * @create: 2022-03-25 20:28
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "mx.config")
public class MxConfig {

    /**
     * 是否输出resturl
     */
    private boolean printRestUrl;


    public boolean isPrintRestUrl() {
        return printRestUrl;
    }

    public void setPrintRestUrl(boolean printRestUrl) {
        this.printRestUrl = printRestUrl;
    }

}

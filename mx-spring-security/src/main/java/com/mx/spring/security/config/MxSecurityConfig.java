package com.mx.spring.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: mengxiang.
 * @create: 2021-09-07 16:36
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "mx-security")
public class MxSecurityConfig {

    /**
     * 是否单独设置跨域
     */
    private boolean cross;

    public boolean isCross() {
        return cross;
    }

    public void setCross(boolean cross) {
        this.cross = cross;
    }
}

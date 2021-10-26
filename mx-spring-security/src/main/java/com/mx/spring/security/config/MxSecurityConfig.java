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
     * 客户端名称
     */
    private String clientName;



}

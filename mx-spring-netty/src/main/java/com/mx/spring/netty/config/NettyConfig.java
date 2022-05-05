package com.mx.spring.netty.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐建鹏.
 * @create: 2022-04-22 14:21
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "mx.netty")
public class NettyConfig {


    public final static String SERVER_CLIENT_ALL = "all";
    public final static String SERVER_CLIENT_SERVER = "server";
    public final static String SERVER_CLIENT_CLIENT = "client";

    /**
     * 启动的客户端 all全部 server服务端  client 客户端  默认all
     */
    private String serverClient;

    public String getServerClient() {
        return StringUtils.defaultIfBlank(serverClient,SERVER_CLIENT_ALL);
    }

    public void setServerClient(String serverClient) {
        this.serverClient = serverClient;
    }
}

package com.mx.spring.netty;

import com.mx.spring.netty.config.NettyConfig;
import com.mx.spring.netty.impl.NettyClient;
import com.mx.spring.netty.impl.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.mx.spring.netty.config.NettyConfig.SERVER_CLIENT_CLIENT;
import static com.mx.spring.netty.config.NettyConfig.SERVER_CLIENT_SERVER;

/**
 * @Author: 徐建鹏.
 * @create: 2022-04-22 15:56
 * @Description:
 */
@Component
public class NettyStart {

    @Autowired
    private NettyConfig nettyConfig;

    @Autowired
    private NettyServer nettyServer;
    @Autowired
    private NettyClient nettyClient;

    @PostConstruct
    private void init() throws Exception {
        if (SERVER_CLIENT_SERVER.equals(nettyConfig.getServerClient())) {
            nettyServer.run();
        } else if (SERVER_CLIENT_CLIENT.equals(nettyConfig.getServerClient())) {
            nettyClient.run();
        } else {
            nettyServer.run();
            nettyClient.run();
        }
    }

    @PreDestroy
    private void destroy() {
        if (SERVER_CLIENT_SERVER.equals(nettyConfig.getServerClient())) {
            nettyServer.stop();
        } else if (SERVER_CLIENT_CLIENT.equals(nettyConfig.getServerClient())) {
            nettyClient.stop();
        } else {
            nettyServer.stop();
            nettyClient.stop();
        }

    }
}

package com.mx.spring.netty.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐建鹏.
 * @create: 2022-04-22 14:21
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "mx.netty.client")
public class NettyClientConfig {

    /**
     * 远程连接地址 ip:port  多个用逗号分割
     */
    private String remoteAddress;


    /**
     * 心跳维护时间 默认不维护
     */
    private Integer heartBeatTime;


    /**
     * 处理器名称 可以指定多个用,号分割 按顺序添加
     */
    private String handlerClassName;

    /**
     * 编解码名称 只能指定一个
     */
    private String decoderClassName;


    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public Integer getHeartBeatTime() {
        return heartBeatTime;
    }

    public void setHeartBeatTime(Integer heartBeatTime) {
        this.heartBeatTime = heartBeatTime;
    }

    public String getHandlerClassName() {
        return handlerClassName;
    }

    public void setHandlerClassName(String handlerClassName) {
        this.handlerClassName = handlerClassName;
    }

    public String getDecoderClassName() {
        return decoderClassName;
    }

    public void setDecoderClassName(String decoderClassName) {
        this.decoderClassName = decoderClassName;
    }
}

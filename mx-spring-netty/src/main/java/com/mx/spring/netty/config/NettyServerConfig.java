package com.mx.spring.netty.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: mengxiang.
 * @create: 2022-04-22 14:21
 * @Description: 服务端配置
 */
@Component
@ConfigurationProperties(prefix = "mx.netty.server")
public class NettyServerConfig {

    /**
     * 服务端端口 优先级最高
     */
    private Integer port;

    /**
     * 服务端开始端口 指定port了 以port为准
     */
    private Integer startPort;

    /**
     * 服务端结束端口 指定port了 以port为准
     */
    private Integer endPort;

    /**
     * 心跳维护时间 默认不维护
     */
    private Integer heartBeatTime;

    /**
     * 排除端口 用,号分割 只针对startPort endPort有效
     */
    private String excludePort;

    /**
     * 处理器名称 可以指定多个用,号分割 按顺序添加
     */
    private String handlerClassName;

    /**
     * 编解码名称 只能指定一个
     */
    private String decoderClassName;


    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getStartPort() {
        return startPort;
    }

    public void setStartPort(Integer startPort) {
        this.startPort = startPort;
    }

    public Integer getEndPort() {
        return endPort;
    }

    public void setEndPort(Integer endPort) {
        this.endPort = endPort;
    }

    public String getExcludePort() {
        return excludePort;
    }

    public void setExcludePort(String excludePort) {
        this.excludePort = excludePort;
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

    public Integer getHeartBeatTime() {
        return heartBeatTime;
    }

    public void setHeartBeatTime(Integer heartBeatTime) {
        this.heartBeatTime = heartBeatTime;
    }
}

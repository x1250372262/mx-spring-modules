package com.mx.spring.netty.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.netty.INetty;
import com.mx.spring.netty.config.NettyClientConfig;
import com.mx.spring.netty.handler.HeartBeatServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: mengxiang.
 * @create: 2022-04-22 15:27
 * @Description: netty服务类
 */
@Component
public class NettyClient implements INetty {

    @Autowired
    private NettyClientConfig config;

    private final EventLoopGroup WORK_GROUP = new NioEventLoopGroup();


    private List<RemoteAddress> getRemoteAddressList(List<String> remoteAddressStrList) {
        List<RemoteAddress> list = new ArrayList<>();
        for (String remoteAddressStr : remoteAddressStrList) {
            String[] remoteAddressArr = remoteAddressStr.split(":");
            if (remoteAddressArr.length == 2) {
                list.add(new RemoteAddress(remoteAddressArr[0], Convert.toInt(remoteAddressArr[1])));
            }
        }
        return list;
    }


    public void run() throws Exception {
        if (StringUtils.isBlank(config.getRemoteAddress())) {
            throw new MxException("请指定需要连接的服务地址");
        }
        List<String> remoteAddressStrList = Arrays.asList(config.getRemoteAddress().split(","));
        if (CollUtil.isEmpty(remoteAddressStrList)) {
            throw new MxException("请指定需要连接的服务地址");
        }
        List<RemoteAddress> remoteAddressList = getRemoteAddressList(remoteAddressStrList);
        if (CollUtil.isEmpty(remoteAddressList)) {
            throw new MxException("请指定需要连接的服务地址");
        }
        if (StringUtils.isBlank(config.getHandlerClassName())) {
            throw new MxException("请指定handler处理类");
        }
        List<Class<? extends ChannelInboundHandlerAdapter>> handlerAdapterList = getHandlerList(config.getHandlerClassName());
        if (CollUtil.isEmpty(handlerAdapterList)) {
            throw new MxException("请指定handler处理类");
        }
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(WORK_GROUP)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline channelPipeline = ch.pipeline();
                        channelPipeline.addLast(new LoggingHandler(LogLevel.INFO));
                        if (Objects.nonNull(config.getHeartBeatTime())) {
                            channelPipeline.addLast(new IdleStateHandler(config.getHeartBeatTime(), 0, 0, TimeUnit.SECONDS));
                        }
                        if (StringUtils.isNotBlank(config.getDecoderClassName())) {
                            channelPipeline.addLast(getDecoder(config.getDecoderClassName()));
                        }
                        for (Class<? extends ChannelInboundHandlerAdapter> clazz : handlerAdapterList) {
                            channelPipeline.addLast(clazz.newInstance());
                        }
                        if (Objects.nonNull(config.getHeartBeatTime())) {
                            channelPipeline.addLast(new HeartBeatServerHandler());
                        }
                    }
                });
        for (RemoteAddress remoteAddress : remoteAddressList) {
            bootstrap.connect(remoteAddress.getHost(), remoteAddress.getPort()).sync();
//            bootstrap.connect(remoteAddress.getHost(), remoteAddress.getPort()).addListener((ChannelFutureListener) future -> {
//                if (!future.isSuccess()) {
//                    MxLog.error("连接创建失败");
//                }
//            }).get();
        }

    }

    public void stop() {
        //优雅退出，释放线程池
        WORK_GROUP.shutdownGracefully();
    }


    public class RemoteAddress {

        public RemoteAddress(String host, int port) {
            this.host = host;
            this.port = port;
        }

        private String host;

        private int port;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }

}

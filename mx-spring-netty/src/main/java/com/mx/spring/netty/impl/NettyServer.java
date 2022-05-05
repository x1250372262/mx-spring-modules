package com.mx.spring.netty.impl;

import cn.hutool.core.collection.CollUtil;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.netty.INetty;
import com.mx.spring.netty.config.NettyServerConfig;
import com.mx.spring.netty.handler.HeartBeatServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: mengxiang.
 * @create: 2022-04-22 15:27
 * @Description: netty服务类
 */
@Component
public class NettyServer implements INetty {

    @Autowired
    private NettyServerConfig config;

    private final EventLoopGroup BOSS_GROUP = new NioEventLoopGroup();
    private final EventLoopGroup WORK_GROUP = new NioEventLoopGroup();

    public void run() throws Exception {
        if (StringUtils.isBlank(config.getHandlerClassName())) {
            throw new MxException("请指定handler处理类");
        }
        List<Class<? extends ChannelInboundHandlerAdapter>> handlerAdapterList = getHanderList(config.getHandlerClassName());
        if (CollUtil.isEmpty(handlerAdapterList)) {
            throw new MxException("请指定handler处理类");
        }
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(BOSS_GROUP, WORK_GROUP).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100).childOption(ChannelOption.SO_REUSEADDR, true).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline channelPipeline = ch.pipeline();
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
        if (Objects.nonNull(config.getPort())) {
//            //绑定端口，同步等待成功
            serverBootstrap.bind(config.getPort()).sync();
        } else if (Objects.nonNull(config.getStartPort()) && Objects.nonNull(config.getEndPort())) {
            int startPort = config.getStartPort();
            int endPort = config.getEndPort();
            for (; startPort < endPort; startPort++) {
                //绑定端口，同步等待成功
                serverBootstrap.bind(startPort).sync();
            }
        }

    }

    public void stop() {
        //优雅退出，释放线程池
        BOSS_GROUP.shutdownGracefully();
        WORK_GROUP.shutdownGracefully();
    }

}

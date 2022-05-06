package com.mx.spring.netty;

import cn.hutool.core.util.ClassUtil;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2022-04-22 15:47
 * @Description:
 */
public interface INetty {

    /**
     * 获取编码器
     *
     * @param decoderClassName
     * @return
     */
    default ChannelInboundHandlerAdapter getDecoder(String decoderClassName) {
        try {
            return (ChannelInboundHandlerAdapter) ClassUtil.loadClass(decoderClassName).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取hander集合
     *
     * @param handlerClassName
     * @return
     */
    default List<Class<? extends ChannelInboundHandlerAdapter>> getHandlerList(String handlerClassName) {
        String[] handlerArray = handlerClassName.split(",");
        List<Class<? extends ChannelInboundHandlerAdapter>> list = new ArrayList<>();
        for (String handler : handlerArray) {
            list.add(ClassUtil.loadClass(handler, false));
        }
        return list;
    }
}

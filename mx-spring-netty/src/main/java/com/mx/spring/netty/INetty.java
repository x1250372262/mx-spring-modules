package com.mx.spring.netty;

import cn.hutool.core.util.ClassUtil;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 徐建鹏.
 * @create: 2022-04-22 15:47
 * @Description:
 */
public interface INetty {

    default ChannelInboundHandlerAdapter getDecoder(String decoderClassName) {
        try {
            return (ChannelInboundHandlerAdapter) ClassUtil.loadClass(decoderClassName).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    default List<Class<? extends ChannelInboundHandlerAdapter>> getHanderList(String handerClassName) {
        String[] handerArray = handerClassName.split(",");
        List<Class<? extends ChannelInboundHandlerAdapter>> list = new ArrayList<>();
        for (String handler : handerArray) {
            list.add(ClassUtil.loadClass(handler,false));
        }
        return list;
    }
}

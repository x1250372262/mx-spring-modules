package com.mx.spring.dev.support.log.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: mengxiang.
 * @create: 2021-09-06 17:20
 * @Description:
 */
public class OperationLogEvent extends ApplicationEvent {
    public OperationLogEvent(Object object) {
        super(object);
    }
}

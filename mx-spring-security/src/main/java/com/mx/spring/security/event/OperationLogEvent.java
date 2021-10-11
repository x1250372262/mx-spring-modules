package com.mx.spring.security.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: 徐建鹏.
 * @create: 2021-09-06 17:20
 * @Description:
 */
public class OperationLogEvent extends ApplicationEvent {
    public OperationLogEvent(Object object) {
        super(object);
    }
}

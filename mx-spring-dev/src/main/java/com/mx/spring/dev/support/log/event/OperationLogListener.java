package com.mx.spring.dev.support.log.event;

import com.mx.spring.dev.support.log.mapper.IOperationLogMapper;
import com.mx.spring.dev.support.log.model.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: mengxiang.
 * @create: 2021-09-06 17:20
 * @Description:
 */
@Component
public class OperationLogListener {

    @Autowired
    private IOperationLogMapper iOperationLogMapper;

    @Async
    @Order
    @EventListener(OperationLogEvent.class)
    public void saveSysLog(OperationLogEvent event) {
        OperationLog operationLog = (OperationLog) event.getSource();
        iOperationLogMapper.insert(operationLog);
    }
}

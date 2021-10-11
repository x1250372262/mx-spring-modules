package com.mx.spring.security.event;

import com.mx.spring.security.model.OperationLog;
import com.mx.spring.security.mapper.IOperationLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐建鹏.
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

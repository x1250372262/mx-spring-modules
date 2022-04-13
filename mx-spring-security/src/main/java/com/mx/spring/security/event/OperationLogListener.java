package com.mx.spring.security.event;

import com.mx.spring.security.base.model.SecurityOperationLog;
import com.mx.spring.security.mapper.ISecurityOperationLogMapper;
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
    private ISecurityOperationLogMapper iSecurityOperationLogMapper;

    @Async
    @Order
    @EventListener(OperationLogEvent.class)
    public void saveSysLog(OperationLogEvent event) {
        SecurityOperationLog securityOperationLog = (SecurityOperationLog) event.getSource();
        iSecurityOperationLogMapper.insert(securityOperationLog);
    }
}

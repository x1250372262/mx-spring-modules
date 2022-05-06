package com.mx.spring.security.annotation;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.result.View;
import com.mx.spring.dev.result.Result;
import com.mx.spring.dev.support.log.MxLog;
import com.mx.spring.dev.util.WebUtil;
import com.mx.spring.security.SaUtils;
import com.mx.spring.security.base.config.MxSecurityConfig;
import com.mx.spring.security.base.enums.OperationType;
import com.mx.spring.security.base.model.SecurityOperationLog;
import com.mx.spring.security.base.model.SecurityUser;
import com.mx.spring.security.event.OperationLogEvent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
@Aspect
@Component
public class OperationLogAspect {


    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SaUtils saUtils;

    @Autowired
    private MxSecurityConfig config;

    @Pointcut(value = "@annotation(com.mx.spring.security.annotation.OperationLog)")
    public void operationLog() {
    }

    /**
     * 在切点之后织入
     */
    @AfterReturning(value = "operationLog()", returning = "ret")
    public void doAfter(JoinPoint joinPoint, Object ret) {
        handlerLog(joinPoint, ret, null);
    }

    /**
     * 异常
     */
    @AfterThrowing(value = "operationLog()", throwing = "e")
    public void doAfterThrowable(JoinPoint joinPoint, Throwable e) {
        handlerLog(joinPoint, null, e);
    }

    private void handlerLog(JoinPoint joinPoint, Object ret, Throwable e) {
        try {
            // 获得注解
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            if (method == null) {
                return;
            }
            OperationLog operationLog = method.getAnnotation(OperationLog.class);
            if (operationLog == null) {
                return;
            }
            SecurityUser securityUser = saUtils.user();
            if (securityUser == null) {
                return;
            }
            int code = 10000;
            String msg = OperationType.UNKNOWN.name();
            if (ret instanceof Result) {
                code = ((Result) ret).getCode();
                msg = ((Result) ret).getMsg();
            } else if (ret instanceof View) {
                code = ((View<?>) ret).getCode();
                msg = ((View<?>) ret).getMsg();
            }
            if (e != null) {
                code = -50;
                msg = e.getMessage();
            }
            HttpServletRequest request = WebUtil.request();
            String userAgentStr = request.getHeader("user-agent");
            // *========数据库日志=========*//
            SecurityOperationLog securityOperationLog = SecurityOperationLog.builder()
                    .id(IdUtil.fastSimpleUUID())
                    .title(operationLog.title())
                    .type(operationLog.operationType().name())
                    .typeName(operationLog.operationType().value())
                    .userId(securityUser.getId())
                    .userName(securityUser.getUserName())
                    .createTime(System.currentTimeMillis())
                    .requestUrl(request.getRequestURI())
                    .requestParam(JSONObject.toJSONString(ServletUtil.getParams(request)))
                    .returnCode(Convert.toStr(code)).returnMessage(msg)
                    .returnResult(JSONObject.toJSONString(ret))
                    .className(joinPoint.getTarget().getClass().getName())
                    .methodName(joinPoint.getSignature().getName())
                    .ip(ServletUtil.getClientIP(request))
                    //位置之后再说把
                    .location("")
                    .os(UserAgentUtil.parse(userAgentStr).getOs().toString())
                    .browser(UserAgentUtil.parse(userAgentStr).getBrowser().toString())
                    .client(config.getClient())
                    .build();
            // 保存数据库
            applicationContext.publishEvent(new OperationLogEvent(securityOperationLog));
        } catch (Exception exp) {
            // 记录本地异常日志
            MxLog.error("==日志记录异常==");
            MxLog.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

}

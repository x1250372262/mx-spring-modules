package com.mx.spring.security.aspect;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.enums.OperationType;
import com.mx.spring.dev.util.BeanUtils;
import com.mx.spring.dev.util.WebUtils;
import com.mx.spring.security.SaUtils;
import com.mx.spring.security.annotation.Log;
import com.mx.spring.security.bean.SaUser;
import com.mx.spring.security.event.OperationLogEvent;
import com.mx.spring.security.model.OperationLog;
import com.mx.spring.security.service.IAdminService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class LogAspect {


    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private IAdminService iAdminService;

    @Pointcut(value = "@annotation(com.mx.spring.security.annotation.Log)")
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
            Log log = method.getAnnotation(Log.class);
            if (log == null) {
                return;
            }
            SaUser saUser = SaUtils.user();
            int code = 10000;
            String adminId = "";
            String msg = OperationType.UNKNOWN.name();
            if (ret instanceof R) {
                code = ((R) ret).getCode();
                msg = ((R) ret).getMsg();
                if (saUser == null) {
                    adminId = ((R) ret).attr("adminId");
                }
            } else if (ret instanceof M) {
                code = ((M<?>) ret).getCode();
                msg = ((M<?>) ret).getMsg();
                if (saUser == null) {
                    SaTokenInfo token = (SaTokenInfo) ((M<?>) ret).getData();
                    if (token != null) {
                        adminId = Convert.toStr(token.getLoginId());
                    }
                }
            }
            if (e != null) {
                code = -50;
                msg = e.getMessage();
            }

            if (saUser == null && StringUtils.isBlank(adminId)) {
                return;
            } else if (saUser == null && StringUtils.isNotBlank(adminId)) {
                saUser = BeanUtils.copy(iAdminService.detail(adminId), SaUser::new);
            }
            if (saUser == null) {
                return;
            }
            HttpServletRequest request = WebUtils.request();
            String userAgentStr = request.getHeader("user-agent");
            // *========数据库日志=========*//
            OperationLog operationLog = OperationLog.builder()
                    .id(IdUtil.fastSimpleUUID())
                    .title(log.title())
                    .type(log.operationType().name())
                    .typeName(log.operationType().value())
                    .userId(saUser.getId())
                    .userName(saUser.getUserName())
                    .createTime(System.currentTimeMillis())
                    .requestUrl(request.getRequestURI())
                    .requestParam(JSONObject.toJSONString(ServletUtil.getParams(request)))
                    .returnCode(Convert.toStr(code))
                    .returnMessage(msg)
                    .returnResult(JSONObject.toJSONString(ret))
                    .className(joinPoint.getTarget().getClass().getName())
                    .methodName(joinPoint.getSignature().getName())
                    .ip(ServletUtil.getClientIP(request))
                    //位置之后再说把
                    .location("")
                    .os(UserAgentUtil.parse(userAgentStr).getOs().toString())
                    .browser(UserAgentUtil.parse(userAgentStr).getBrowser().toString())
                    .build();
            // 保存数据库
            applicationContext.publishEvent(new OperationLogEvent(operationLog));
        } catch (Exception exp) {
            // 记录本地异常日志
            logger.error("==日志记录异常==");
            logger.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

}

package com.mx.spring.dev.aspect;

import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.log.MxLog;
import com.mx.spring.dev.util.WebUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
@Aspect
@Component
public class FormatRequestAspect {

    @Pointcut(value = "@annotation(com.mx.spring.dev.annotation.FormatRequest)")
    public void formatRequest() {
    }

    @Around("formatRequest()")
    public Object around(ProceedingJoinPoint pj) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pj.proceed();
        //打印出参
        MxLog.info("返回参数\t\t\t\t\t\t: {}", JSONObject.toJSONString(result, true));
        //执行耗时
        MxLog.info("执行耗时\t\t\t\t\t\t: {} ms", System.currentTimeMillis() - startTime);
        //结束打印日志
        MxLog.info("=============== End ===============");
        return result;
    }

    /**
     * 在切点前织入
     */
    @Before("formatRequest()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        //开始打印请求日志
        HttpServletRequest request = WebUtils.request();
        //打印相关请求参数
        MxLog.info("=============== Start ===============");
        // 打印请求 url
        MxLog.info("请求URL\t\t\t\t\t\t: {}", request.getMethod() + ":" + request.getRequestURI());
        // 打印 Http method
        MxLog.info("请求方法\t\t\t\t\t\t: {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        MxLog.info("执行方法\t\t\t\t\t\t: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        MxLog.info("请求IP\t\t\t\t\t\t: {}", request.getRemoteAddr());
        // 打印请求入参
        MxLog.info("请求参数\t\t\t\t\t\t: {}", JSONObject.toJSONString(request.getParameterMap(), true));
    }

}

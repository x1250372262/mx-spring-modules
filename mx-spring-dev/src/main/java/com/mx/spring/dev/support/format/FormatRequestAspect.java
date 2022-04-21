package com.mx.spring.dev.support.format;

import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.config.MxConfig;
import com.mx.spring.dev.support.log.MxLog;
import com.mx.spring.dev.util.WebUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MxConfig mxConfig;

    @Pointcut(value = "@annotation(com.mx.spring.dev.support.format.FormatRequest)")
    public void formatRequest() {
    }

    @Around("formatRequest()")
    public Object around(ProceedingJoinPoint pj) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object result = pj.proceed();
        if(mxConfig.isFormatRequest()){
            //打印出参
            MxLog.info("返回参数: {}", JSONObject.toJSONString(result, true));
            //执行耗时
            MxLog.info("执行耗时: {} ms", System.currentTimeMillis() - startTime);
            //结束打印日志
            MxLog.info("=============== End ===============");
        }
        return result;
    }

    /**
     * 在切点前织入
     */
    @Before("formatRequest()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        if(mxConfig.isFormatRequest()){
            //开始打印请求日志
            HttpServletRequest request = WebUtil.request();
            //打印相关请求参数
            MxLog.info("=============== Start ===============");
            // 打印请求 url
            MxLog.info("请求URL: {}", request.getMethod() + ":" + request.getRequestURI());
            // 打印 Http method
            MxLog.info("请求方法: {}", request.getMethod());
            // 打印调用 controller 的全路径以及执行方法
            MxLog.info("执行方法: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            // 打印请求的 IP
            MxLog.info("请求 IP: {}", request.getRemoteAddr());
            // 打印请求入参
            MxLog.info("请求参数: {}", JSONObject.toJSONString(request.getParameterMap(), true));
        }
    }

}

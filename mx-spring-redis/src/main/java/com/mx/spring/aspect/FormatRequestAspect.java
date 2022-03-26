package com.mx.spring.aspect;

import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.util.WebUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    private final static Logger logger = LoggerFactory.getLogger(FormatRequestAspect.class);
    //换行符
    private final static String LINE_SEPARATOR = System.lineSeparator();


    @Pointcut(value = "@annotation(com.mx.spring.dev.annotation.FormatRequest)")
    public void formatRequest() {
    }

    @Around("formatRequest()")
    public Object around(ProceedingJoinPoint pj) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pj.proceed();
        //打印出参
        logger.info("Response Args : {}", JSONObject.toJSONString(result, true));
        //执行耗时
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
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
        logger.info("=============== Start ===============");
        // 打印请求 url
        logger.info("URL            : {}", request.getMethod() + ":" + request.getRequestURI());
        // 打印 Http method
        logger.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        logger.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        logger.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        logger.info("Request Parameters   : {}", JSONObject.toJSONString(request.getParameterMap(), true));
    }

    /**
     * 在切点之后织入
     */
    @After("formatRequest()")
    public void doAfter() throws Throwable {
        logger.info("=============== End ===============" + LINE_SEPARATOR);
    }

}

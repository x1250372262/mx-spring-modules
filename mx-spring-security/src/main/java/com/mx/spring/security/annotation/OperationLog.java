package com.mx.spring.security.annotation;


import com.mx.spring.security.base.enums.OperationType;

import java.lang.annotation.*;

/**
 * @Author: mengxiang.
 * @create: 2022-04-20 00:00
 * @Description: 操作日志注解
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {

    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    OperationType operationType() default OperationType.UNKNOWN;

}

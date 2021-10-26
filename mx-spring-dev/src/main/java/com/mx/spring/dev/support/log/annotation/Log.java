package com.mx.spring.dev.support.log.annotation;


import com.mx.spring.dev.enums.OperationType;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    OperationType operationType() default OperationType.UNKNOWN;

}

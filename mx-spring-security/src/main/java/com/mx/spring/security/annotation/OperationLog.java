package com.mx.spring.security.annotation;



import com.mx.spring.security.base.enums.OperationType;

import java.lang.annotation.*;

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

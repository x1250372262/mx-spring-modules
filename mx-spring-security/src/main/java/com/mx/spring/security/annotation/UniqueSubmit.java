package com.mx.spring.security.annotation;


import com.mx.spring.dev.util.TimeHelper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueSubmit {

    long value() default TimeHelper.SECOND * 10;

    RetType retType();


    enum RetType {
        R,
        M
    }
}

package com.mx.spring.jdbc.mybatis.plus.query.annotation;

import com.mx.spring.jdbc.mybatis.plus.query.Query;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cond {

    Query.OPT opt();

    String field();

    boolean notEmpty() default true;
}

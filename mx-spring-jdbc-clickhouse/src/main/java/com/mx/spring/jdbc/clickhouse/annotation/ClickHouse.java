package com.mx.spring.jdbc.clickhouse.annotation;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DS("clickhouse")
public @interface ClickHouse {
}

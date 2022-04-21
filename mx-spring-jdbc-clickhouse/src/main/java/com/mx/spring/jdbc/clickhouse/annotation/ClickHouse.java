package com.mx.spring.jdbc.clickhouse.annotation;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.lang.annotation.*;


/**
 * @Author: mengxiang.
 * @create: 2022-04-20 00:00
 * @Description: clickhouse数据源注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DS("clickhouse")
public @interface ClickHouse {
}

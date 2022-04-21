package com.mx.spring.dev.support.generator.annotation;

import java.lang.annotation.*;

/**
 * @Author: mengxiang.
 * @create: 2022-04-20 00:00
 * @Description: 实体类信息用来生成代码用的
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldInfo {

    /**
     * 描述
     * @return
     */
    String comment() default "";

    /**
     * 是否为空 默认true
     * @return
     */
    boolean nullable() default true;

}

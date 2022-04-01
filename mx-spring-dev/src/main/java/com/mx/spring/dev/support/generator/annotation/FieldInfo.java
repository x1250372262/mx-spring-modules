package com.mx.spring.dev.support.generator.annotation;

import java.lang.annotation.*;

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

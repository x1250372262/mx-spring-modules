package com.mx.spring.dev.support.format;

import java.lang.annotation.*;

/**
 * @Author: mengxiang.
 * @create: 2022-04-20 00:00
 * @Description: 格式化request请求
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FormatRequest {

}

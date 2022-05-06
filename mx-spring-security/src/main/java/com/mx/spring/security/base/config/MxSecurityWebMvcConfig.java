package com.mx.spring.security.base.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: mengxiang.
 * @create: 2021-09-05 07:01
 * @Description:
 */
@Configuration
public class MxSecurityWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private MxSecurityConfig mxSecurityConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址
        registry.addInterceptor(new SaAnnotationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/webjars/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (mxSecurityConfig.isCross()) {
            //添加映射路径
            registry.addMapping("/**")
                    //放行哪些原始域
                    .allowedOriginPatterns("*")
                    //是否发送Cookie信息
                    .allowCredentials(true)
                    //放行哪些原始域(请求方式)
                    .allowedMethods("*")
                    //放行哪些原始域(头部信息)
                    .allowedHeaders("*");
        }
    }
}

package com.mx.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: mengxiang.
 * @create: 2021-10-12 09:41
 * @Description:
 */
@SpringBootConfiguration
public class WebGlobalConfig {

    @Autowired
    private MxSecurityConfig mxSecurityConfig;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            //重写父类提供的跨域请求处理的接口
            public void addCorsMappings(CorsRegistry registry) {
                if(mxSecurityConfig.isCross()){
                    //添加映射路径
                    registry.addMapping("/**")
                            //放行哪些原始域
                            .allowedOrigins("*")
                            //是否发送Cookie信息
                            .allowCredentials(true)
                            //放行哪些原始域(请求方式)
                            .allowedMethods("*")
                            //放行哪些原始域(头部信息)
                            .allowedHeaders("*");
                }
            }
        };
    }

}

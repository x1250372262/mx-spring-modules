package com.mx.spring.security.base.config;

import cn.hutool.core.collection.ListUtil;
import com.mx.spring.security.interceptor.MxSaRouteInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;
import java.util.List;

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

    private List<String> excludePathPatterns() {
        List<String> excludePathPatternList = ListUtil.toList("/mx/security/login/login",
                "/mx/security/login/logout",
                "/doc.html",
                "/error",
                "/webjars/**");
        String excludePathPatterns = mxSecurityConfig.getExcludePathPatterns();
        if (StringUtils.isNotBlank(excludePathPatterns)) {
            excludePathPatternList.addAll(Arrays.asList(excludePathPatterns.split("\\|")));
        }
        return excludePathPatternList;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new MxSaRouteInterceptor());
        interceptorRegistration.addPathPatterns("/**")
                .excludePathPatterns("/mx/security/login/login")
                .excludePathPatterns("/mx/security/login/logout")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/error")
                .excludePathPatterns("/webjars/**");
        interceptorRegistration.addPathPatterns(excludePathPatterns());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (mxSecurityConfig.isCross()) {
            //添加映射路径
            registry.addMapping("/**");
        }
    }


    //    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        if (mxSecurityConfig.isCross()) {
//            //添加映射路径
//            registry.addMapping("/**")
//                    //放行哪些原始域
//                    .allowedOriginPatterns("*")
//                    //是否发送Cookie信息
//                    .allowCredentials(true)
//                    //放行哪些原始域(请求方式)
//                    .allowedMethods("*")
//                    //放行哪些原始域(头部信息)
//                    .allowedHeaders("*");
//        }
//    }
}

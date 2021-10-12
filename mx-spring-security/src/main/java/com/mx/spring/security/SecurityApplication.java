package com.mx.spring.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author: 徐建鹏.
 * @create: 2021-09-03 15:26
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.mx.spring.security.mapper")
@ComponentScan("com.mx.spring.*")
//开启服务发现
//@EnableDiscoveryClient
@EnableWebMvc
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}

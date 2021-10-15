package com.mx.spring.security;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.mx.spring.dev.util.ListUtils;
import com.mx.spring.security.config.SPConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

/**
 * @Author: mengxiang.
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

        List<SPConfig.Permission> permissionList = SPConfig.permissionList();
        if (ListUtils.isNotEmpty(permissionList)) {
            String sql = "INSERT INTO `mx-bdm`.`mx_permission` (`id`, `group_name`, `permission_name`, `permission_code`, `create_user`, `create_time`, `last_modify_user`, `last_modify_time`) " +
                    "VALUES ('{}', '{}', '{}', '{}', '{}', {}, '{}', {});";
            permissionList.forEach(permission -> System.out.println(StrUtil.format(sql, IdUtil.fastSimpleUUID(), permission.getGroupName(), permission.getName(), permission.getCode(), "1", System.currentTimeMillis(), "1", System.currentTimeMillis())));
        }
//        System.out.println(StrUtil.format("INSERT INTO `mx-bdm`.`mx_role` (`id`, `name`, `remark`, `create_user`, `create_time`, `last_modify_user`, `last_modify_time`) " +
//                "VALUES ('{}', '{}', '{}', '{}', {}, '{}', {});",IdUtil.fastSimpleUUID(),SPConfig.SECURITY_ROLE_NAME,SPConfig.SECURITY_ROLE_NAME,"1",System.currentTimeMillis(),"1",System.currentTimeMillis()));
    }
}

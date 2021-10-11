package com.mx.spring.security.mapper;

import com.mx.spring.dev.support.mybatisplus.MxBaseMapper;
import com.mx.spring.security.model.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/05.
 * @Time: 14:37:38.
 * @Description: 2021/10/05 14:37:38 生成 RolePermissionMapper
 */
@Mapper
public interface IRolePermissionMapper extends MxBaseMapper<RolePermission> {
    int insetAll(List<RolePermission> rolePermissionList);
}
package com.mx.spring.security.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.jdbc.mybatis.plus.MxBaseMapper;
import com.mx.spring.security.base.model.SecurityUserRole;
import com.mx.spring.security.base.vo.SecurityUserPermissionVO;
import com.mx.spring.security.base.vo.SecurityUserRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/22.
 * @Time: 15:14:05.
 * @Description: 2021/10/22 15:14:05 生成 SecurityUserRoleMapper
 */
@Mapper
public interface ISecurityUserRoleMapper extends MxBaseMapper<SecurityUserRole> {

    Page<SecurityUserRoleVO> roleList(@Param("userId") String userId, Page<SecurityUserRoleVO> page) throws MxException;

    List<SecurityUserPermissionVO> permissionList(@Param("userId") String userId) throws MxException;
}
package com.mx.spring.security.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.mybatisplus.MxBaseMapper;
import com.mx.spring.security.model.AdminRole;
import com.mx.spring.security.vo.AdminPermissionVO;
import com.mx.spring.security.vo.AdminRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/05.
 * @Time: 14:37:38.
 * @Description: 2021/10/05 14:37:38 生成 AdminRoleMapper
 */
@Mapper
public interface IAdminRoleMapper extends MxBaseMapper<AdminRole> {
    Page<AdminRoleVO> roleList(@Param("adminId") String adminId, Page<AdminRoleVO> page) throws MxException;

    List<AdminPermissionVO> permissionList(@Param("adminId") String adminId) throws MxException;
}
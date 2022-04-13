package com.mx.spring.security.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.jdbc.mybatis.plus.MxBaseMapper;
import com.mx.spring.security.base.model.SecurityMenuRole;
import com.mx.spring.security.base.vo.SecurityMenuRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/22.
 * @Time: 15:14:05.
 * @Description: 2021/10/22 15:14:05 生成 SecurityMenuRoleMapper
 */
@Mapper
public interface ISecurityMenuRoleMapper extends MxBaseMapper<SecurityMenuRole> {

    Page<SecurityMenuRoleVO> findAll(@Param("menuId") String menuId, @Param("name") String name, Page<SecurityMenuRoleVO> page);

}
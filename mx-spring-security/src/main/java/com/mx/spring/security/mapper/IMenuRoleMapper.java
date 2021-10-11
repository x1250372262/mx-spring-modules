package com.mx.spring.security.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.support.mybatisplus.MxBaseMapper;
import com.mx.spring.security.model.MenuRole;
import com.mx.spring.security.vo.MenuRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/05.
 * @Time: 14:37:38.
 * @Description: 2021/10/05 14:37:38 生成 MenuRoleMapper
 */
@Mapper
public interface IMenuRoleMapper extends MxBaseMapper<MenuRole> {
    Page<MenuRoleVO> findAll(@Param("menuId") String menuId, @Param("name") String name, Page<MenuRoleVO> page);
}
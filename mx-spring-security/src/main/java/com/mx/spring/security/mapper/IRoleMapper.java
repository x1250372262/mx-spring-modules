package com.mx.spring.security.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.support.mybatisplus.MxBaseMapper;
import com.mx.spring.security.model.Role;
import com.mx.spring.security.vo.RoleListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/05.
 * @Time: 14:37:38.
 * @Description: 2021/10/05 14:37:38 生成 RoleMapper
 */
@Mapper
public interface IRoleMapper extends MxBaseMapper<Role> {
    Page<RoleListVO> findAll(@Param("name") String name, Page<Role> page);
}
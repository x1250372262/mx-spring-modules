package com.mx.spring.security.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.support.mybatisplus.MxBaseMapper;
import com.mx.spring.security.model.Admin;
import com.mx.spring.security.vo.UserListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/05.
 * @Time: 14:37:37.
 * @Description: 2021/10/05 14:37:37 生成 AdminMapper
 */
@Mapper
public interface IAdminMapper extends MxBaseMapper<Admin> {
    Page<UserListVO> findAll(@Param("userName") String userName, @Param("realName") String realName, @Param("disableStatus") Integer disableStatus, Page<UserListVO> page);
}
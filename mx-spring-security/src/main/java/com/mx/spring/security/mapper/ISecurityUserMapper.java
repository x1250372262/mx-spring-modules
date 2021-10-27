package com.mx.spring.security.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.support.mybatisplus.MxBaseMapper;
import com.mx.spring.dev.support.security.model.SecurityUser;
import com.mx.spring.security.vo.SecurityUserListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/22.
 * @Time: 15:14:05.
 * @Description: 2021/10/22 15:14:05 生成 SecurityUserMapper
 */
@Mapper
public interface ISecurityUserMapper extends MxBaseMapper<SecurityUser> {

    Page<SecurityUserListVO> findAll(@Param("userName") String userName, @Param("realName") String realName, @Param("disableStatus") Integer disableStatus,@Param("client") String client, Page<SecurityUserListVO> page);

}
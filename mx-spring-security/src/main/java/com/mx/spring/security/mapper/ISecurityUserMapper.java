package com.mx.spring.security.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.jdbc.mybatis.plus.MxBaseMapper;
import com.mx.spring.security.base.model.SecurityUser;
import com.mx.spring.security.base.vo.SecurityUserListVO;
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

    /**
     * 获取用户列表
     * @param userName
     * @param realName
     * @param disableStatus
     * @param client
     * @param page
     * @return
     */
    Page<SecurityUserListVO> findAll(@Param("userName") String userName, @Param("realName") String realName, @Param("disableStatus") Integer disableStatus, @Param("client") String client, Page<SecurityUserListVO> page);

}
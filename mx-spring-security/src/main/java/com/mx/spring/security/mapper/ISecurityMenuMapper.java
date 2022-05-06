package com.mx.spring.security.mapper;

import com.mx.spring.jdbc.mybatis.plus.MxBaseMapper;
import com.mx.spring.security.base.model.SecurityMenu;
import com.mx.spring.security.base.vo.SecurityMenuNavVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/22.
 * @Time: 15:14:05.
 * @Description: 2021/10/22 15:14:05 生成 SecurityMenuMapper
 */
@Mapper
public interface ISecurityMenuMapper extends MxBaseMapper<SecurityMenu> {


    /**
     * 根据类型和客户端获取
     *
     * @param type
     * @param client
     * @return
     */
    List<SecurityMenuNavVO> findAllByType(@Param("type") Integer type, @Param("client") String client);

    /**
     * 根据用户id和客户端获取
     *
     * @param userId
     * @param client
     * @return
     */
    List<SecurityMenuNavVO> findAll(@Param("userId") String userId, @Param("client") String client);
}
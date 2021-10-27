package com.mx.spring.security.mapper;

import com.mx.spring.dev.support.mybatisplus.MxBaseMapper;
import com.mx.spring.dev.support.security.model.SecurityMenu;
import com.mx.spring.security.vo.SecurityMenuNavVO;
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


    List<SecurityMenuNavVO> findAllByType(@Param("type") Integer type, @Param("client") String client);

    List<SecurityMenuNavVO> findAll(@Param("userId") String userId, @Param("client") String client);
}
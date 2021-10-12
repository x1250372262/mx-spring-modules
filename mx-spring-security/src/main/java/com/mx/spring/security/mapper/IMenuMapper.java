package com.mx.spring.security.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.support.mybatisplus.MxBaseMapper;
import com.mx.spring.security.model.Menu;
import com.mx.spring.security.vo.MenuNavVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/05.
 * @Time: 14:37:38.
 * @Description: 2021/10/05 14:37:38 生成 MenuMapper
 */
@Mapper
public interface IMenuMapper extends MxBaseMapper<Menu> {

    List<MenuNavVO> findAllByType(@Param("type") Integer type);

    List<MenuNavVO> findAll(@Param("adminId") String adminId);
}
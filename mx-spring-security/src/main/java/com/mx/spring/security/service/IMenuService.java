package com.mx.spring.security.service;

import com.mx.spring.dev.bean.PageBean;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.security.bean.MenuBean;
import com.mx.spring.security.vo.MenuListVO;
import com.mx.spring.security.vo.MenuNavVO;
import com.mx.spring.security.vo.MenuRoleVO;
import com.mx.spring.security.vo.MenuVO;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 15:41
 * @Description:
 */
public interface IMenuService {

    /**
     * 左侧导航栏
     *
     * @return
     * @throws MxException
     */
    M<List<MenuNavVO>> nav() throws MxException;

    /**
     * 菜单列表
     *
     * @return
     * @throws MxException
     */
    M<List<MenuListVO>> list() throws MxException;

    /**
     * 添加菜单
     *
     * @param menuBean
     * @return
     * @throws MxException
     */
    R create(MenuBean menuBean) throws MxException;

    /**
     * 修改菜单
     *
     * @param id
     * @param menuBean
     * @return
     * @throws MxException
     */
    R update(String id, MenuBean menuBean) throws MxException;

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    M<MenuVO> detail(String id) throws MxException;

    /**
     * 删除菜单
     *
     * @param id
     * @return
     * @throws MxException
     */
    R delete(String id) throws MxException;

    /**
     * 菜单角色列表
     *
     * @param menuId
     * @param name
     * @param pageBean
     * @return
     * @throws MxException
     */
    M<Pages<MenuRoleVO>> roleList(String menuId, String name, PageBean<MenuRoleVO> pageBean) throws MxException;

    /**
     * 添加菜单角色
     *
     * @param menuId
     * @param roleId
     * @return
     * @throws MxException
     */
    R roleCreate(String menuId, String roleId) throws MxException;

    /**
     * 删除菜单角色
     *
     * @param ids
     * @return
     * @throws MxException
     */
    R roleDelete(String[] ids) throws MxException;
}

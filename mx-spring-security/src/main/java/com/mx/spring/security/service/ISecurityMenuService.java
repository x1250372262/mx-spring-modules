package com.mx.spring.security.service;

import com.mx.spring.dev.support.page.PageBean;
import com.mx.spring.dev.result.View;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.dev.result.Result;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.security.base.bean.SecurityMenuBean;
import com.mx.spring.security.base.vo.SecurityMenuListVO;
import com.mx.spring.security.base.vo.SecurityMenuNavVO;
import com.mx.spring.security.base.vo.SecurityMenuRoleVO;
import com.mx.spring.security.base.vo.SecurityMenuVO;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 15:41
 * @Description:
 */
public interface ISecurityMenuService {

    /**
     * 左侧导航栏
     *
     * @return
     * @throws MxException
     */
    View<List<SecurityMenuNavVO>> nav() throws MxException;

    /**
     * 菜单列表
     *
     * @return
     * @throws MxException
     */
    View<List<SecurityMenuListVO>> list() throws MxException;

    /**
     * 添加菜单
     *
     * @param menuBean
     * @return
     * @throws MxException
     */
    Result create(SecurityMenuBean menuBean) throws MxException;

    /**
     * 修改菜单
     *
     * @param id
     * @param menuBean
     * @return
     * @throws MxException
     */
    Result update(String id, SecurityMenuBean menuBean) throws MxException;

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    View<SecurityMenuVO> detail(String id) throws MxException;

    /**
     * 删除菜单
     *
     * @param id
     * @return
     * @throws MxException
     */
    Result delete(String id) throws MxException;

    /**
     * 菜单角色列表
     *
     * @param menuId
     * @param name
     * @param pageBean
     * @return
     * @throws MxException
     */
    View<Pages<SecurityMenuRoleVO>> roleList(String menuId, String name, PageBean<SecurityMenuRoleVO> pageBean) throws MxException;

    /**
     * 添加菜单角色
     *
     * @param menuId
     * @param roleId
     * @return
     * @throws MxException
     */
    Result roleCreate(String menuId, String roleId) throws MxException;

    /**
     * 删除菜单角色
     *
     * @param ids
     * @return
     * @throws MxException
     */
    Result roleDelete(String[] ids) throws MxException;
}

package com.mx.spring.security.service;

import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;
import com.mx.spring.dev.result.MxView;
import com.mx.spring.dev.support.page.PageBean;
import com.mx.spring.dev.support.page.Pages;
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
    MxView<List<SecurityMenuNavVO>> nav() throws MxException;

    /**
     * 菜单列表
     *
     * @return
     * @throws MxException
     */
    MxView<List<SecurityMenuListVO>> list() throws MxException;

    /**
     * 添加菜单
     *
     * @param menuBean
     * @return
     * @throws MxException
     */
    MxResult create(SecurityMenuBean menuBean) throws MxException;

    /**
     * 修改菜单
     *
     * @param id
     * @param menuBean
     * @return
     * @throws MxException
     */
    MxResult update(String id, SecurityMenuBean menuBean) throws MxException;

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    MxView<SecurityMenuVO> detail(String id) throws MxException;

    /**
     * 删除菜单
     *
     * @param id
     * @return
     * @throws MxException
     */
    MxResult delete(String id) throws MxException;

    /**
     * 菜单角色列表
     *
     * @param menuId
     * @param name
     * @param pageBean
     * @return
     * @throws MxException
     */
    MxView<Pages<SecurityMenuRoleVO>> roleList(String menuId, String name, PageBean<SecurityMenuRoleVO> pageBean) throws MxException;

    /**
     * 添加菜单角色
     *
     * @param menuId
     * @param roleId
     * @return
     * @throws MxException
     */
    MxResult roleCreate(String menuId, String roleId) throws MxException;

    /**
     * 删除菜单角色
     *
     * @param ids
     * @return
     * @throws MxException
     */
    MxResult roleDelete(String[] ids) throws MxException;
}

package com.mx.spring.security.service;

import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.Result;
import com.mx.spring.dev.result.View;
import com.mx.spring.dev.support.page.PageBean;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.security.base.bean.SecurityRoleBean;
import com.mx.spring.security.base.model.SecurityRole;
import com.mx.spring.security.base.vo.SecurityRoleListVO;
import com.mx.spring.security.base.vo.SecurityRoleVO;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 15:41
 * @Description:
 */
public interface ISecurityRoleService {


    /**
     * 角色列表
     *
     * @param name
     * @param pageBean
     * @return
     * @throws MxException
     */
    View<Pages<SecurityRoleListVO>> list(String name, PageBean<SecurityRole> pageBean) throws MxException;

    /**
     * 添加角色
     *
     * @param roleBean
     * @return
     * @throws MxException
     */
    Result create(SecurityRoleBean roleBean) throws MxException;

    /**
     * 修改角色
     *
     * @param id
     * @param lastModifyTime
     * @param roleBean
     * @return
     * @throws MxException
     */
    Result update(String id, Long lastModifyTime, SecurityRoleBean roleBean) throws MxException;

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    View<SecurityRoleVO> detail(String id) throws MxException;

    /**
     * 删除角色
     *
     * @param ids
     * @return
     * @throws MxException
     */
    Result delete(String[] ids) throws MxException;

    /**
     * 角色权限列表
     *
     * @param id
     * @return
     * @throws MxException
     */
    Result permissionList(String id) throws MxException;

    /**
     * 角色权限绑定
     *
     * @param id
     * @param permissions
     * @return
     * @throws MxException
     */
    Result permissionBind(String id, String[] permissions) throws MxException;
}

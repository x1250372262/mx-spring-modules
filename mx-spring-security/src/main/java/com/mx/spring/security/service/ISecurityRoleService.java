package com.mx.spring.security.service;

import com.mx.spring.dev.page.PageBean;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.page.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.security.model.SecurityRole;
import com.mx.spring.security.bean.SecurityRoleBean;
import com.mx.spring.security.vo.SecurityRoleListVO;
import com.mx.spring.security.vo.SecurityRoleVO;

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
    M<Pages<SecurityRoleListVO>> list(String name, PageBean<SecurityRole> pageBean) throws MxException;

    /**
     * 添加角色
     *
     * @param roleBean
     * @return
     * @throws MxException
     */
    R create(SecurityRoleBean roleBean) throws MxException;

    /**
     * 修改角色
     *
     * @param id
     * @param lastModifyTime
     * @param roleBean
     * @return
     * @throws MxException
     */
    R update(String id, Long lastModifyTime, SecurityRoleBean roleBean) throws MxException;

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    M<SecurityRoleVO> detail(String id) throws MxException;

    /**
     * 删除角色
     *
     * @param ids
     * @return
     * @throws MxException
     */
    R delete(String[] ids) throws MxException;

    /**
     * 角色权限列表
     *
     * @param id
     * @return
     * @throws MxException
     */
    R permissionList(String id) throws MxException;

    /**
     * 角色权限绑定
     *
     * @param id
     * @param permissions
     * @return
     * @throws Exception
     */
    R permissionBind(String id, String[] permissions) throws MxException;
}

package com.mx.spring.security.service;

import com.mx.spring.dev.exception.MxException;

import java.util.List;

/**
 * @Author: 徐建鹏.
 * @create: 2021-09-04 16:02
 * @Description:
 */
public interface IAdminRoleService {

    /**
     * 管理员所有角色
     * @param adminId
     * @return
     * @throws MxException
     */
    List<String> adminRoleList(String adminId) throws MxException;

    /**
     * 获取管理员所有权限
     * @param adminId
     * @return
     * @throws MxException
     */
    List<String> adminPermissionList(String adminId) throws MxException;


}
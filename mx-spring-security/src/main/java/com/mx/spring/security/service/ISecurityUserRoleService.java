package com.mx.spring.security.service;

import com.mx.spring.dev.exception.MxException;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-09-04 16:02
 * @Description:
 */
public interface ISecurityUserRoleService {

    /**
     * 获取所有权限
     * @param securityUserId
     * @param token
     * @return
     * @throws MxException
     */
    List<String> securityUserPermissionList(String securityUserId, String token) throws MxException;


}

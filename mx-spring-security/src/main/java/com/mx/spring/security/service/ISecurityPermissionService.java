package com.mx.spring.security.service;

import com.mx.spring.dev.core.M;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.security.vo.SecurityPermissionSelectVO;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-09-24 14:45
 * @Description:
 */
public interface ISecurityPermissionService {

    /**
     * 权限列表
     *
     * @return
     * @throws MxException
     */
    M<List<SecurityPermissionSelectVO>> list() throws MxException;
}

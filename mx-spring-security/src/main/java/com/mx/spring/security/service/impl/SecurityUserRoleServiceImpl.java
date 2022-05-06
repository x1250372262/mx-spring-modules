package com.mx.spring.security.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.security.SaUtil;
import com.mx.spring.security.base.model.SecurityPermission;
import com.mx.spring.security.base.vo.SecurityUserPermissionVO;
import com.mx.spring.security.mapper.ISecurityPermissionMapper;
import com.mx.spring.security.mapper.ISecurityUserRoleMapper;
import com.mx.spring.security.service.ISecurityUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-09-04 16:02
 * @Description:
 */
@Service
public class SecurityUserRoleServiceImpl implements ISecurityUserRoleService {

    @Autowired
    private ISecurityUserRoleMapper iSecurityUserRoleMapper;
    @Autowired
    private ISecurityPermissionMapper iSecurityPermissionMapper;
    @Autowired
    private SaUtil saUtils;

    @Override
    public List<String> securityUserPermissionList(String securityUserId, String token) throws MxException {
        List<String> permissionList = new ArrayList<>();
        if (saUtils.isFounder(securityUserId, token)) {
            List<SecurityPermission> permissions = iSecurityPermissionMapper.selectList(null);
            if (CollUtil.isNotEmpty(permissions)) {
                permissions.forEach(permission -> permissionList.add(permission.getPermissionCode()));
            }
        } else {
            List<SecurityUserPermissionVO> securityUserPermissionVOList = iSecurityUserRoleMapper.permissionList(securityUserId);
            if (CollUtil.isNotEmpty(securityUserPermissionVOList)) {
                securityUserPermissionVOList.forEach(securityUserPermissionVO -> permissionList.add(securityUserPermissionVO.getPermissionCode()));
            }
        }
        return permissionList;
    }
}

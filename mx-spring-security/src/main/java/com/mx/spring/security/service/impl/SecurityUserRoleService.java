package com.mx.spring.security.service.impl;

import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.security.SaUtils;
import com.mx.spring.dev.support.security.model.SecurityPermission;
import com.mx.spring.dev.util.ListUtils;
import com.mx.spring.security.mapper.ISecurityPermissionMapper;
import com.mx.spring.security.mapper.ISecurityUserRoleMapper;
import com.mx.spring.security.service.ISecurityUserRoleService;
import com.mx.spring.security.vo.SecurityUserPermissionVO;
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
public class SecurityUserRoleService implements ISecurityUserRoleService {

    @Autowired
    private ISecurityUserRoleMapper iSecurityUserRoleMapper;
    @Autowired
    private ISecurityPermissionMapper iSecurityPermissionMapper;
    @Autowired
    private SaUtils saUtils;

    @Override
    public List<String> securityUserPermissionList(String securityUserId, String token) throws MxException {
        List<String> permissionList = new ArrayList<>();
        if (saUtils.isFounder(securityUserId, token)) {
            List<SecurityPermission> permissions = iSecurityPermissionMapper.selectList(null);
            if (ListUtils.isNotEmpty(permissions)) {
                permissions.forEach(permission -> permissionList.add(permission.getPermissionCode()));
            }
        } else {
            List<SecurityUserPermissionVO> securityUserPermissionVOList = iSecurityUserRoleMapper.permissionList(securityUserId);
            if (ListUtils.isNotEmpty(securityUserPermissionVOList)) {
                securityUserPermissionVOList.forEach(securityUserPermissionVO -> permissionList.add(securityUserPermissionVO.getPermissionCode()));
            }
        }
        return permissionList;
    }
}

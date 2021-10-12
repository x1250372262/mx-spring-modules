package com.mx.spring.security.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.bean.PageBean;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.util.ListUtils;
import com.mx.spring.security.SaUtils;
import com.mx.spring.security.mapper.IAdminRoleMapper;
import com.mx.spring.security.mapper.IPermissionMapper;
import com.mx.spring.security.mapper.IRoleMapper;
import com.mx.spring.security.model.Permission;
import com.mx.spring.security.model.Role;
import com.mx.spring.security.service.IAdminRoleService;
import com.mx.spring.security.vo.AdminPermissionVO;
import com.mx.spring.security.vo.AdminRoleVO;
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
public class AdminRoleService implements IAdminRoleService {

    @Autowired
    private IAdminRoleMapper iAdminRoleMapper;
    @Autowired
    private IRoleMapper iRoleMapper;
    @Autowired
    private IPermissionMapper iPermissionMapper;

    @Override
    public List<String> adminRoleList(String adminId,String token) throws MxException {
        List<String> roleList = new ArrayList<>();
        if (SaUtils.isFounder(adminId,token)) {
            List<Role> roles = iRoleMapper.selectList(null);
            if (ListUtils.isNotEmpty(roles)) {
                roles.forEach(role -> roleList.add(role.getName()));
            }
        } else {
            Page<AdminRoleVO> adminRoleVOList = iAdminRoleMapper.roleList(adminId, new PageBean<AdminRoleVO>(1, -1).toPage());
            if (ListUtils.isNotEmpty(adminRoleVOList.getRecords())) {
                adminRoleVOList.getRecords().forEach(adminRoleVO -> roleList.add(adminRoleVO.getRoleName()));
            }
        }
        return roleList;
    }

    @Override
    public List<String> adminPermissionList(String adminId,String token) throws MxException {
        List<String> permissionList = new ArrayList<>();
        if (SaUtils.isFounder(adminId,token)) {
            List<Permission> permissions = iPermissionMapper.selectList(null);
            if (ListUtils.isNotEmpty(permissions)) {
                permissions.forEach(permission -> permissionList.add(permission.getPermissionCode()));
            }
        } else {
            List<AdminPermissionVO> adminPermissionVOList = iAdminRoleMapper.permissionList(adminId);
            if (ListUtils.isNotEmpty(adminPermissionVOList)) {
                adminPermissionVOList.forEach(adminPermissionVO -> permissionList.add(adminPermissionVO.getPermissionCode()));
            }
        }
        return permissionList;
    }
}

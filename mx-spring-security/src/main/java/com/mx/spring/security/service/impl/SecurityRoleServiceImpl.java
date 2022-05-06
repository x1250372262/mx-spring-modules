package com.mx.spring.security.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.Result;
import com.mx.spring.dev.result.View;
import com.mx.spring.dev.support.page.PageBean;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.dev.util.BeanUtil;
import com.mx.spring.jdbc.mybatis.plus.Mp;
import com.mx.spring.jdbc.mybatis.plus.page.PageHelper;
import com.mx.spring.jdbc.mybatis.plus.util.MpBeanUtils;
import com.mx.spring.security.SaUtil;
import com.mx.spring.security.base.bean.SecurityRoleBean;
import com.mx.spring.security.base.config.MxSecurityConfig;
import com.mx.spring.security.base.model.SecurityPermission;
import com.mx.spring.security.base.model.SecurityRole;
import com.mx.spring.security.base.model.SecurityRolePermission;
import com.mx.spring.security.base.model.SecurityUserRole;
import com.mx.spring.security.base.vo.SecurityRoleListVO;
import com.mx.spring.security.base.vo.SecurityRoleVO;
import com.mx.spring.security.mapper.ISecurityPermissionMapper;
import com.mx.spring.security.mapper.ISecurityRoleMapper;
import com.mx.spring.security.mapper.ISecurityRolePermissionMapper;
import com.mx.spring.security.mapper.ISecurityUserRoleMapper;
import com.mx.spring.security.service.ISecurityRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-09-24 10:48
 * @Description:
 */
@Service
public class SecurityRoleServiceImpl implements ISecurityRoleService {

    private final SecurityRole findRole = SecurityRole.init();
    @Autowired
    private ISecurityRoleMapper iRoleMapper;
    @Autowired
    private ISecurityUserRoleMapper iSecurityUserRoleMapper;
    @Autowired
    private ISecurityRolePermissionMapper iRolePermissionMapper;
    @Autowired
    private ISecurityPermissionMapper iPermissionMapper;
    @Autowired
    private SaUtil saUtils;
    @Autowired
    private MxSecurityConfig config;

    @Override
    public View<Pages<SecurityRoleListVO>> list(String name, PageBean<SecurityRole> pageBean) throws MxException {
        LambdaQueryWrapper<SecurityRole> lambdaQueryWrapper = Mp.lqw(findRole)
                .like(StringUtils.isNotBlank(name), SecurityRole::getName, name)
                .eq(SecurityRole::getClient, config.getClient());
        return View.list(MpBeanUtils.copyPage(iRoleMapper.selectPage(PageHelper.in(pageBean), lambdaQueryWrapper), SecurityRoleListVO::new));
    }

    @Override
    public Result create(SecurityRoleBean roleBean) throws MxException {
        SecurityRole role = iRoleMapper.selectOne(Mp.lqw(findRole).eq(SecurityRole::getName, roleBean.getName()));
        if (role != null) {
            return Result.sameName();
        }
        role = BeanUtil.copy(roleBean, SecurityRole::new, (s, t) ->
                t.bind()
                        .id(IdUtil.fastSimpleUUID())
                        .client(config.getClient())
                        .createTime(System.currentTimeMillis())
                        .createUser(saUtils.loginId())
                        .lastModifyTime(System.currentTimeMillis())
                        .lastModifyUser(saUtils.loginId())
        );
        return Result.result(iRoleMapper.insert(role));
    }

    @Override
    public Result update(String id, Long lastModifyTime, SecurityRoleBean roleBean) throws MxException {
        SecurityRole role = iRoleMapper.selectOne(Mp.lqw(findRole).eq(SecurityRole::getName, roleBean.getName()).ne(SecurityRole::getId, id));
        if (role != null) {
            return Result.sameName();
        }
        role = iRoleMapper.selectById(id);
        if (role == null) {
            return Result.noData();
        }
        if (Result.checkVersion(role.getLastModifyTime(), lastModifyTime)) {
            return Result.noVersion();
        }
        role = BeanUtil.duplicate(roleBean, role, (s, t) -> {
            t.bind()
                    .lastModifyTime(System.currentTimeMillis())
                    .lastModifyUser(saUtils.loginId());
        });
        return Result.result(iRoleMapper.updateById(role));
    }

    @Override
    public View<SecurityRoleVO> detail(String id) throws MxException {
        return View.ok(BeanUtil.copy(iRoleMapper.selectById(id), SecurityRoleVO::new));
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {MxException.class, Exception.class})
    public Result delete(String[] ids) throws MxException {
        iSecurityUserRoleMapper.delete(Mp.lqw(SecurityUserRole.init()).in(SecurityUserRole::getRoleId, Arrays.asList(ids)));
        return Result.result(iRoleMapper.deleteBatchIds(Arrays.asList(ids)));
    }

    @Override
    public Result permissionList(String id) throws MxException {
        JSONObject jsonObject = new JSONObject();
        SecurityRole role = iRoleMapper.selectById(id);
        if (role == null) {
            return Result.noData();
        }
        List<SecurityRolePermission> rolePermissionList = iRolePermissionMapper.selectList(Mp.lqw(SecurityRolePermission.init())
                .eq(SecurityRolePermission::getClient, config.getClient())
                .eq(SecurityRolePermission::getRoleId, id));
        if (CollUtil.isNotEmpty(rolePermissionList)) {
            String[] selectRoles = new String[rolePermissionList.size()];
            for (int i = 0; i < rolePermissionList.size(); i++) {
                selectRoles[i] = rolePermissionList.get(i).getPermissionCode();
            }
            jsonObject.put("permissions", selectRoles);
        }
        return Result.ok().data(jsonObject);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {MxException.class, Exception.class})
    public Result permissionBind(String id, String[] permissions) throws MxException {
        SecurityRole role = iRoleMapper.selectById(id);
        if (role == null) {
            return Result.noData();
        }

        iRolePermissionMapper.delete(Mp.lqw(SecurityRolePermission.init())
                .eq(SecurityRolePermission::getClient, config.getClient())
                .eq(SecurityRolePermission::getRoleId, id));
        List<SecurityPermission> permissionList = iPermissionMapper.selectList(null);
        if (permissions != null && permissions.length > 0) {
            List<SecurityRolePermission> rolePermissions = new ArrayList<>();
            for (String permissionCode : permissions) {
                SecurityPermission permission = permissionList.stream().filter(p -> p.getPermissionCode().equals(permissionCode)).findFirst().get();
                SecurityRolePermission rolePermission = SecurityRolePermission.builder()
                        .id(IdUtil.fastSimpleUUID())
                        .roleId(id)
                        .client(config.getClient())
                        .permissonId(permission.getId())
                        .createTime(System.currentTimeMillis())
                        .groupName(permission.getGroupName())
                        .permissionName(permission.getPermissionName())
                        .permissionCode(permission.getPermissionCode())
                        .build();
                rolePermissions.add(rolePermission);
            }
            if (CollUtil.isNotEmpty(rolePermissions)) {
                iRolePermissionMapper.insertBatch(rolePermissions);
            }
        }
        return Result.ok();
    }
}

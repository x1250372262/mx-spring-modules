package com.mx.spring.security.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.bean.PageBean;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.mybatisplus.MMP;
import com.mx.spring.dev.support.security.SaUtils;
import com.mx.spring.dev.support.security.model.SecurityPermission;
import com.mx.spring.dev.support.security.model.SecurityRole;
import com.mx.spring.dev.support.security.model.SecurityRolePermission;
import com.mx.spring.dev.support.security.model.SecurityUserRole;
import com.mx.spring.dev.util.BeanUtils;
import com.mx.spring.dev.util.ListUtils;
import com.mx.spring.security.bean.SecurityRoleBean;
import com.mx.spring.security.mapper.ISecurityPermissionMapper;
import com.mx.spring.security.mapper.ISecurityRoleMapper;
import com.mx.spring.security.mapper.ISecurityRolePermissionMapper;
import com.mx.spring.security.mapper.ISecurityUserRoleMapper;
import com.mx.spring.security.service.ISecurityRoleService;
import com.mx.spring.security.vo.SecurityRoleListVO;
import com.mx.spring.security.vo.SecurityRoleVO;
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
    private SaUtils saUtils;

    @Override
    public M<Pages<SecurityRoleListVO>> list(String name, PageBean<SecurityRole> pageBean) throws MxException {
        return M.list(BeanUtils.copyPage(iRoleMapper.selectPage(pageBean.toPage(), MMP.lqw(findRole).like(StringUtils.isNotBlank(name), SecurityRole::getName, name)), SecurityRoleListVO::new));
    }

    @Override
    public R create(SecurityRoleBean roleBean) throws MxException {
        SecurityRole role = iRoleMapper.selectOne(MMP.lqw(findRole).eq(SecurityRole::getName, roleBean.getName()));
        if (role != null) {
            return R.sameName();
        }
        role = BeanUtils.copy(roleBean, SecurityRole::new, (s, t) ->
                t.bind()
                        .id(IdUtil.fastSimpleUUID())
                        .createTime(System.currentTimeMillis())
                        .createUser(saUtils.loginId())
                        .lastModifyTime(System.currentTimeMillis())
                        .lastModifyUser(saUtils.loginId())
        );
        return R.result(iRoleMapper.insert(role));
    }

    @Override
    public R update(String id, Long lastModifyTime, SecurityRoleBean roleBean) throws MxException {
        SecurityRole role = iRoleMapper.selectOne(MMP.lqw(findRole).eq(SecurityRole::getName, roleBean.getName()).ne(SecurityRole::getId, id));
        if (role != null) {
            return R.sameName();
        }
        role = iRoleMapper.selectById(id);
        if (role == null) {
            return R.noData();
        }
        if (!R.checkVersion(role.getLastModifyTime(), lastModifyTime)) {
            return R.noVersion();
        }
        role = BeanUtils.duplicate(roleBean, role, (s, t) -> {
            t.bind()
                    .lastModifyTime(System.currentTimeMillis())
                    .lastModifyUser(saUtils.loginId());
        });
        return R.result(iRoleMapper.updateById(role));
    }

    @Override
    public M<SecurityRoleVO> detail(String id) throws MxException {
        return M.ok(BeanUtils.copy(iRoleMapper.selectById(id), SecurityRoleVO::new));
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {MxException.class, Exception.class})
    public R delete(String[] ids) throws MxException {
        iSecurityUserRoleMapper.delete(MMP.lqw(SecurityUserRole.init()).in(SecurityUserRole::getRoleId, Arrays.asList(ids)));
        return R.result(iRoleMapper.deleteBatchIds(Arrays.asList(ids)));
    }

    @Override
    public R permissionList(String id) throws MxException {
        JSONObject jsonObject = new JSONObject();
        SecurityRole role = iRoleMapper.selectById(id);
        if (role == null) {
            return R.noData();
        }
        List<SecurityRolePermission> rolePermissionList = iRolePermissionMapper.selectList(MMP.lqw(SecurityRolePermission.init()).eq(SecurityRolePermission::getRoleId, id));
        if (ListUtils.isNotEmpty(rolePermissionList)) {
            String[] selectRoles = new String[rolePermissionList.size()];
            for (int i = 0; i < rolePermissionList.size(); i++) {
                selectRoles[i] = rolePermissionList.get(i).getPermissionCode();
            }
            jsonObject.put("permissions", selectRoles);
        }
        return R.ok().data(jsonObject);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {MxException.class, Exception.class})
    public R permissionBind(String id, String[] permissions) throws MxException {
        SecurityRole role = iRoleMapper.selectById(id);
        if (role == null) {
            return R.noData();
        }

        iRolePermissionMapper.delete(MMP.lqw(SecurityRolePermission.init()).eq(SecurityRolePermission::getRoleId, id));
        List<SecurityPermission> permissionList = iPermissionMapper.selectList(null);
        if (permissions != null && permissions.length > 0) {
            List<SecurityRolePermission> rolePermissions = new ArrayList<>();
            for (String permissionCode : permissions) {
                SecurityPermission permission = permissionList.stream().filter(p -> p.getPermissionCode().equals(permissionCode)).findFirst().get();
                SecurityRolePermission rolePermission = SecurityRolePermission.builder()
                        .id(IdUtil.fastSimpleUUID())
                        .roleId(id)
                        .permissonId(permission.getId())
                        .createTime(System.currentTimeMillis())
                        .groupName(permission.getGroupName())
                        .permissionName(permission.getPermissionName())
                        .permissionCode(permission.getPermissionCode())
                        .build();
                rolePermissions.add(rolePermission);
            }
            if (ListUtils.isNotEmpty(rolePermissions)) {
                iRolePermissionMapper.insertBatch(rolePermissions);
            }
        }
        return R.ok();
    }
}

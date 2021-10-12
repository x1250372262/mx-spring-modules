package com.mx.spring.security.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.bean.PageBean;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.mybatisplus.MMP;
import com.mx.spring.dev.util.BeanUtils;
import com.mx.spring.dev.util.ListUtils;
import com.mx.spring.security.SaUtils;
import com.mx.spring.security.bean.RoleBean;
import com.mx.spring.security.mapper.IAdminRoleMapper;
import com.mx.spring.security.mapper.IPermissionMapper;
import com.mx.spring.security.mapper.IRoleMapper;
import com.mx.spring.security.mapper.IRolePermissionMapper;
import com.mx.spring.security.model.AdminRole;
import com.mx.spring.security.model.Permission;
import com.mx.spring.security.model.Role;
import com.mx.spring.security.model.RolePermission;
import com.mx.spring.security.service.IRoleService;
import com.mx.spring.security.vo.RoleListVO;
import com.mx.spring.security.vo.RoleVO;
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
public class RoleServiceImpl implements IRoleService {

    private final Role findRole = Role.init();
    @Autowired
    private IRoleMapper iRoleMapper;
    @Autowired
    private IAdminRoleMapper iAdminRoleMapper;
    @Autowired
    private IRolePermissionMapper iRolePermissionMapper;
    @Autowired
    private IPermissionMapper iPermissionMapper;

    @Override
    public M<Pages<RoleListVO>> list(String name, PageBean<Role> pageBean) throws MxException {
        return M.list(iRoleMapper.findAll(name, pageBean.toPage()));
    }

    @Override
    public R create(RoleBean roleBean) throws MxException {
        Role role = iRoleMapper.selectOne(MMP.lqw(findRole).eq(Role::getName, roleBean.getName()));
        if (role != null) {
            return R.sameName();
        }
        role = BeanUtils.copy(roleBean, Role::new, (s, t) ->
                t.bind()
                        .id(IdUtil.fastSimpleUUID())
                        .createTime(System.currentTimeMillis())
                        .createUser(SaUtils.loginId())
                        .lastModifyTime(System.currentTimeMillis())
                        .lastModifyUser(SaUtils.loginId())
        );
        return R.result(iRoleMapper.insert(role));
    }

    @Override
    public R update(String id, Long lastModifyTime, RoleBean roleBean) throws MxException {
        Role role = iRoleMapper.selectOne(MMP.lqw(findRole).eq(Role::getName, roleBean.getName()).ne(Role::getId, id));
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
                    .lastModifyUser(SaUtils.loginId());
        });
        return R.result(iRoleMapper.updateById(role));
    }

    @Override
    public M<RoleVO> detail(String id) throws MxException {
        return M.ok(BeanUtils.copy(iRoleMapper.selectById(id), RoleVO::new));
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {MxException.class, Exception.class})
    public R delete(String[] ids) throws MxException {
        iAdminRoleMapper.delete(MMP.lqw(AdminRole.init()).in(AdminRole::getRoleId, Arrays.asList(ids)));
        return R.result(iRoleMapper.deleteBatchIds(Arrays.asList(ids)));
    }

    @Override
    public R permissionList(String id) throws MxException {
        JSONObject jsonObject = new JSONObject();
        Role role = iRoleMapper.selectById(id);
        if (role == null) {
            return R.noData();
        }
        List<RolePermission> rolePermissionList = iRolePermissionMapper.selectList(MMP.lqw(RolePermission.init()).eq(RolePermission::getRoleId, id));
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
        Role role = iRoleMapper.selectById(id);
        if (role == null) {
            return R.noData();
        }

        iRolePermissionMapper.delete(MMP.lqw(RolePermission.init()).eq(RolePermission::getRoleId, id));
        List<Permission> permissionList = iPermissionMapper.selectList(null);
        if (permissions != null && permissions.length > 0) {
            List<RolePermission> rolePermissions = new ArrayList<>();
            for (String permissionCode : permissions) {
                Permission permission = permissionList.stream().filter(p -> p.getPermissionCode().equals(permissionCode)).findFirst().get();
                RolePermission rolePermission = RolePermission.builder()
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
                iRolePermissionMapper.insetAll(rolePermissions);
            }
        }
        return R.ok();
    }
}

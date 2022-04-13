package com.mx.spring.security.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.M;
import com.mx.spring.dev.util.BeanUtil;
import com.mx.spring.jdbc.mybatis.plus.MP;
import com.mx.spring.security.base.config.MxSecurityConfig;
import com.mx.spring.security.base.model.SecurityPermission;
import com.mx.spring.security.base.vo.SecurityPermissionSelectVO;
import com.mx.spring.security.base.vo.SecurityPermissionVO;
import com.mx.spring.security.mapper.ISecurityPermissionMapper;
import com.mx.spring.security.service.ISecurityPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: mengxiang.
 * @create: 2021-09-24 14:45
 * @Description:
 */
@Service
public class SecurityPermissionServiceImpl implements ISecurityPermissionService {

    @Autowired
    private ISecurityPermissionMapper iPermissionMapper;
    @Autowired
    private MxSecurityConfig config;

    @Override
    public M<List<SecurityPermissionSelectVO>> list() throws MxException {
        List<SecurityPermissionSelectVO> permissionSelectVOList = new ArrayList<>();
        List<SecurityPermission> permissionList = iPermissionMapper.selectList(MP.lqw(SecurityPermission.init()).eq(SecurityPermission::getClient, config.getClient()));
        if (CollUtil.isEmpty(permissionList)) {
            return M.ok(permissionSelectVOList);
        }
        Map<String, List<SecurityPermission>> permissionMap = permissionList.stream().collect(Collectors.groupingBy(SecurityPermission::getGroupName));
        for (Map.Entry<String, List<SecurityPermission>> entry : permissionMap.entrySet()) {
            SecurityPermissionSelectVO permissionGroupVO = new SecurityPermissionSelectVO();
            permissionGroupVO.setGroupName(entry.getKey());
            List<SecurityPermissionVO> permissionVOList = BeanUtil.copyList(entry.getValue(), SecurityPermissionVO::new, (s, t) -> {
                t.setCode(s.getPermissionCode());
                t.setName(s.getPermissionName());
            });
            permissionGroupVO.setPermissionVOList(permissionVOList);
            permissionSelectVOList.add(permissionGroupVO);
        }
        return M.ok(permissionSelectVOList);
    }
}

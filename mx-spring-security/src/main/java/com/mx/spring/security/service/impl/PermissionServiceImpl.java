package com.mx.spring.security.service.impl;

import com.mx.spring.dev.core.M;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.util.BeanUtils;
import com.mx.spring.dev.util.ListUtils;
import com.mx.spring.security.mapper.IPermissionMapper;
import com.mx.spring.security.model.Permission;
import com.mx.spring.security.service.IPermissionService;
import com.mx.spring.security.vo.PermissionSelectVO;
import com.mx.spring.security.vo.PermissionVO;
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
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionMapper iPermissionMapper;

    @Override
    public M<List<PermissionSelectVO>> list() throws MxException {
        List<PermissionSelectVO> permissionSelectVOList = new ArrayList<>();
        List<Permission> permissionList = iPermissionMapper.selectList(null);
        if (ListUtils.isEmpty(permissionList)) {
            return M.ok(permissionSelectVOList);
        }
        Map<String, List<Permission>> permissionMap = permissionList.stream().collect(Collectors.groupingBy(Permission::getGroupName));
        for (Map.Entry<String, List<Permission>> entry : permissionMap.entrySet()) {
            PermissionSelectVO permissionGroupVO = new PermissionSelectVO();
            permissionGroupVO.setGroupName(entry.getKey());
            List<PermissionVO> permissionVOList = BeanUtils.copyList(entry.getValue(), PermissionVO::new, (s, t) -> {
                t.setCode(s.getPermissionCode());
                t.setName(s.getPermissionName());
            });
            permissionGroupVO.setPermissionVOList(permissionVOList);
            permissionSelectVOList.add(permissionGroupVO);
        }
        return M.ok(permissionSelectVOList);
    }
}

package com.mx.spring.security.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.redis.api.IRedisService;
import com.mx.spring.dev.support.security.SaUtils;
import com.mx.spring.dev.util.ListUtils;
import com.mx.spring.security.service.ISecurityUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.mx.spring.dev.support.security.SaUtils.PERMISSION_LIST;

/**
 * @Author: mengxiang.
 * @create: 2021-09-04 15:43
 * @Description: 权限角色来源配置
 */
@Component
public class SecurityStpImpl implements StpInterface {

    @Autowired
    private ISecurityUserRoleService iSecurityUserRoleService;
    @Autowired
    private IRedisService iRedisService;
    @Autowired
    private SaUtils saUtils;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        String permissionKey = null;
        try {
            permissionKey = StrUtil.format(PERMISSION_LIST, saUtils.getToken(), StpUtil.getLoginType(), saUtils.loginId());
        } catch (MxException e) {
            e.printStackTrace();
        }
        //先从redis获取role集合  没有再去数据库获取
        List<Object> redisPermissionList = null;
        try {
            redisPermissionList = JSONObject.parseArray(Convert.toStr(iRedisService.strGet(permissionKey)));
        } catch (MxException e) {
            e.printStackTrace();
        }
        List<String> permissionList = new ArrayList<>();
        if (ListUtils.isNotEmpty(redisPermissionList)) {
            for (Object redisRole : redisPermissionList) {
                permissionList.add(Convert.toStr(redisRole));
            }
        } else {
            try {
                permissionList = iSecurityUserRoleService.securityUserPermissionList((String) loginId, null);
            } catch (MxException e) {
                e.printStackTrace();
            }
            try {
                iRedisService.strSet(permissionKey, JSONObject.toJSONString(permissionList));
            } catch (MxException e) {
                e.printStackTrace();
            }
        }
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }
}

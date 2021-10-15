package com.mx.spring.security.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.redis.api.IRedisService;
import com.mx.spring.dev.util.ListUtils;
import com.mx.spring.security.SaUtils;
import com.mx.spring.security.service.IAdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-09-04 15:43
 * @Description: 权限角色来源配置
 */
@Component
public class SecurityStpImpl implements StpInterface {

    public final static String PERMISSION_LIST = "admin_permission_list";
    @Autowired
    private IAdminRoleService iAdminRoleService;
    @Autowired
    private IRedisService iRedisService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        String redisPermissionkey = null;
        try {
            redisPermissionkey = PERMISSION_LIST + "-" + SaUtils.loginId();
        } catch (MxException e) {
            e.printStackTrace();
        }
        //先从redis获取role集合  没有再去数据库获取
        List<Object> redisPermissionList = null;
        try {
            redisPermissionList = JSONObject.parseArray(Convert.toStr(iRedisService.strGet(redisPermissionkey)));
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
                permissionList = iAdminRoleService.adminPermissionList((String) loginId,null);
            } catch (MxException e) {
                e.printStackTrace();
            }
            try {
                iRedisService.strSet(redisPermissionkey, JSONObject.toJSONString(permissionList));
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

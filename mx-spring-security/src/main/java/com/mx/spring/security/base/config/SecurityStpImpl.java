package com.mx.spring.security.base.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.redis.api.IRedisApi;
import com.mx.spring.security.SaUtil;
import com.mx.spring.security.service.ISecurityUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.mx.spring.security.SaUtil.PERMISSION_LIST;


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
    private IRedisApi iRedisApi;
    @Autowired
    private SaUtil saUtils;
    @Autowired
    private MxSecurityConfig mxSecurityConfig;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> permissionList = new ArrayList<>();
        try {
            String permissionKey = StrUtil.format(PERMISSION_LIST, mxSecurityConfig.getClient(), saUtils.getToken(), StpUtil.getLoginType(), saUtils.loginId());
            String permissionStr = Convert.toStr(iRedisApi.strGet(permissionKey));
            if (StringUtils.isNotBlank(permissionStr)) {
                JSONArray redisPermissionList = JSONObject.parseArray(permissionStr);
                for (Object redisRole : redisPermissionList) {
                    permissionList.add(Convert.toStr(redisRole));
                }
            } else {
                permissionList = iSecurityUserRoleService.securityUserPermissionList((String) loginId, null);
                iRedisApi.strSet(permissionKey, JSONObject.toJSONString(permissionList));
            }
        } catch (MxException e) {
            e.printStackTrace();
        }
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }
}

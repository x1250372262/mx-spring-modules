package com.mx.spring.security.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.mx.spring.dev.core.Constants;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.mybatisplus.MMP;
import com.mx.spring.dev.support.redis.api.IRedisService;
import com.mx.spring.dev.util.BeanUtils;
import com.mx.spring.dev.util.ListUtils;
import com.mx.spring.dev.util.TimeHelper;
import com.mx.spring.security.SaUtils;
import com.mx.spring.security.bean.AdminBean;
import com.mx.spring.security.bean.SaUser;
import com.mx.spring.security.mapper.IAdminMapper;
import com.mx.spring.security.model.Admin;
import com.mx.spring.security.service.IAdminRoleService;
import com.mx.spring.security.service.IAdminService;
import com.mx.spring.security.vo.AdminVO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static com.mx.spring.security.SaUtils.ADMIN_PERMISSION_LIST;
import static com.mx.spring.security.SaUtils.SESSION_ADMIN_INFO_KEY;
import static com.mx.spring.security.code.SecurityCode.*;

/**
 * @Author: mengxiang.
 * @create: 2021-09-03 15:21
 * @Description:
 */
@Service
public class AdminServiceImpl implements IAdminService {

    private final Admin finalAdmin = Admin.init();
    @Autowired
    private IAdminMapper iAdminMapper;
    @Autowired
    private IAdminRoleService iAdminRoleService;
    @Autowired
    private IRedisService iRedisService;

    @Override
    public M<SaTokenInfo> login(String userName, String password) throws MxException {
        Admin admin = iAdminMapper.selectOne(MMP.lqw(finalAdmin).eq(Admin::getUserName, userName));
        if (admin == null) {
            return M.fail(SECURITY_LOGIN_USER_NAME_NOT_EXIST.getCode(), SECURITY_LOGIN_USER_NAME_NOT_EXIST.getMsg());
        }
        //锁住了
        if (Objects.equals(Constants.BOOL_TRUE, admin.getLoginLockStatus()) && System.currentTimeMillis() < admin.getLoginLockEndTime()) {
            String msg = SECURITY_LOGIN_USER_LOCKED.getMsg();
            msg = StrUtil.format(msg, TimeHelper.formatTime(admin.getLoginLockEndTime()));
            return M.fail(SECURITY_LOGIN_USER_LOCKED.getCode(), msg);
        } else if (Objects.equals(Constants.BOOL_TRUE, admin.getLoginLockStatus()) && System.currentTimeMillis() > admin.getLoginLockEndTime()) {
            admin.setLoginLockStatus(Constants.BOOL_FALSE);
            admin.setLoginErrorCount(0);
            admin.setLoginLockStartTime(0L);
            admin.setLoginLockEndTime(0L);
        }
        password = DigestUtils.md5DigestAsHex(Base64.encodeBase64((password + admin.getSalt()).getBytes(StandardCharsets.UTF_8)));
        if (!password.equals(admin.getPassword())) {
            //次数+1 到数之后直接冻结
            admin.setLoginErrorCount(admin.getLoginErrorCount() + 1);
            //冻结了
            if (admin.getLoginErrorCount() >= 5) {
                admin.setLoginLockStatus(Constants.BOOL_TRUE);
                admin.setLoginLockStartTime(System.currentTimeMillis());
                admin.setLoginLockEndTime(System.currentTimeMillis() + TimeHelper.DAY);
            }
            iAdminMapper.updateById(admin);
            return M.fail(SECURITY_LOGIN_USER_NAME_OR_PASSWORD_ERROR.getCode(), SECURITY_LOGIN_USER_NAME_OR_PASSWORD_ERROR.getMsg());
        } else {
            //重置时间和次数
            admin.setLoginLockStatus(Constants.BOOL_FALSE);
            admin.setLoginErrorCount(0);
            admin.setLoginLockStartTime(0L);
            admin.setLoginLockEndTime(0L);
            iAdminMapper.updateById(admin);
        }
        StpUtil.login(admin.getId());
        SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
        //设置用户信息到redis
        SaUser saUser = BeanUtils.copy(admin, SaUser::new);
        StpUtil.getTokenSessionByToken(saTokenInfo.getTokenValue()).set(SESSION_ADMIN_INFO_KEY + admin.getId(), saUser);
        //设置权限到redis
        setLoginAdminPermissionToRedis(admin.getId(), saTokenInfo.getTokenValue());
        return M.ok(saTokenInfo);
    }

    public void setLoginAdminPermissionToRedis(String adminId, String token) throws MxException {
        //设置权限角色到redis
        //先删除redis的数据
        String redisPermissionkey = ADMIN_PERMISSION_LIST + "-" + adminId;
        List<Object> redisPermissionList = JSONObject.parseArray(Convert.toStr(iRedisService.strGet(redisPermissionkey)));
        if (ListUtils.isNotEmpty(redisPermissionList)) {
            iRedisService.delete(redisPermissionkey);
        }
        List<String> permissionList = iAdminRoleService.adminPermissionList(adminId, token);
        iRedisService.strSet(redisPermissionkey, JSONObject.toJSONString(permissionList));
    }

    @Override
    public AdminVO info() throws MxException {
        return detail(SaUtils.loginId());
    }

    @Override
    public AdminVO detail(String id) throws MxException {
        Admin admin = iAdminMapper.selectOne(MMP.lqw(finalAdmin).eq(Admin::getId, id));
        AdminVO adminVO = BeanUtils.copy(admin, AdminVO::new);
        return BeanUtils.copy(adminVO, AdminVO::new);
    }

    @Override
    public R update(AdminBean adminBean) throws MxException {
        Admin admin = iAdminMapper.selectOne(MMP.lqw(finalAdmin).eq(Admin::getId, SaUtils.loginId()));
        if (admin == null) {
            return R.noData();
        }
        admin = BeanUtils.duplicate(adminBean, admin);
        int result = iAdminMapper.updateById(admin);
        return R.result(result);
    }

    @Override
    public R password(String oldPassword, String newPassword, String rePassword) throws MxException {
        if (!newPassword.equals(rePassword)) {
            return R.create(SECURITY_ADMIN_PASSWORD_NOT_SAME.getCode()).msg(SECURITY_ADMIN_PASSWORD_NOT_SAME.getMsg());
        }
        Admin admin = iAdminMapper.selectOne(MMP.lqw(finalAdmin).eq(Admin::getId, SaUtils.loginId()));
        if (admin == null) {
            return R.noData();
        }
        oldPassword = DigestUtils.md5DigestAsHex(Base64.encodeBase64((oldPassword + admin.getSalt()).getBytes(StandardCharsets.UTF_8)));
        if (!oldPassword.equals(admin.getPassword())) {
            return R.create(SECURITY_ADMIN_OLD_PASSWORD_ERROR.getCode()).msg(SECURITY_ADMIN_OLD_PASSWORD_ERROR.getMsg());
        }
        newPassword = DigestUtils.md5DigestAsHex(Base64.encodeBase64((newPassword + admin.getSalt()).getBytes(StandardCharsets.UTF_8)));
        if (newPassword.equals(admin.getPassword())) {
            return R.create(SECURITY_ADMIN_NEW_PASSWORD_NOT_SAME_OLD_PASSWORD.getCode()).msg(SECURITY_ADMIN_NEW_PASSWORD_NOT_SAME_OLD_PASSWORD.getMsg());
        }
        admin.setPassword(newPassword);
        admin.setLastModifyTime(System.currentTimeMillis());
        admin.setLastModifyUser(admin.getId());
        int result = iAdminMapper.updateById(admin);
        return R.result(result);
    }
}

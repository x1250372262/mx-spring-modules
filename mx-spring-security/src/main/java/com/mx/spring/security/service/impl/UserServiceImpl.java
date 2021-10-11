package com.mx.spring.security.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.mx.spring.dev.bean.PageBean;
import com.mx.spring.dev.core.Constants;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.mybatisplus.MMP;
import com.mx.spring.dev.util.BeanUtils;
import com.mx.spring.security.SaUtils;
import com.mx.spring.security.bean.UserBean;
import com.mx.spring.security.mapper.IAdminMapper;
import com.mx.spring.security.mapper.IAdminRoleMapper;
import com.mx.spring.security.model.Admin;
import com.mx.spring.security.model.AdminRole;
import com.mx.spring.security.service.IUserService;
import com.mx.spring.security.vo.AdminRoleVO;
import com.mx.spring.security.vo.UserListVO;
import com.mx.spring.security.vo.UserVO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static com.mx.spring.security.code.SecurityCode.SECURITY_ADMIN_ROLE_EXISTS;

/**
 * @Author: 徐建鹏.
 * @create: 2021-09-24 16:08
 * @Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    private final Admin finalAdmin = Admin.init();
    @Autowired
    private IAdminMapper iAdminMapper;
    @Autowired
    private IAdminRoleMapper iAdminRoleMapper;

    @Override
    public M<Pages<UserListVO>> list(String userName, String realName, Integer disableStatus, PageBean<UserListVO> pageBean) throws MxException {
        return M.list(iAdminMapper.findAll(userName, realName, disableStatus, pageBean.toPage()));
    }

    @Override
    public R create(String password, UserBean userBean) throws MxException {
        Admin admin = iAdminMapper.selectOne(MMP.lqw(finalAdmin).eq(Admin::getUserName, userBean.getUserName()));
        if (admin != null) {
            return R.sameData("用户名");
        }
        String salt = RandomUtil.randomString(6);
        password = DigestUtils.md5DigestAsHex(Base64.encodeBase64((password + salt).getBytes(StandardCharsets.UTF_8)));
        String finalPassword = password;
        admin = BeanUtils.copy(userBean, Admin::new, (s, t) -> {
            t.bind()
                    .id(IdUtil.fastSimpleUUID())
                    .password(finalPassword)
                    .createUser(SaUtils.loginId())
                    .createTime(System.currentTimeMillis())
                    .lastModifyTime(System.currentTimeMillis())
                    .lastModifyUser(SaUtils.loginId())
                    .salt(salt)
                    .build();
        });
        return R.result(iAdminMapper.insert(admin));
    }

    @Override
    public M<UserVO> detail(String id) throws MxException {
        return M.ok(BeanUtils.copy(iAdminMapper.selectById(id), UserVO::new));
    }

    @Override
    public R status(String id, Long lastModifyTime, Integer status) throws MxException {
        Admin admin = iAdminMapper.selectById(id);
        if (admin == null) {
            return R.noData();
        }
        if (!R.checkVersion(admin.getLastModifyTime(), lastModifyTime)) {
            return R.noVersion();
        }
        admin.setLastModifyUser(SaUtils.loginId());
        admin.setLastModifyTime(System.currentTimeMillis());
        admin.setDisableStatus(status);
        return R.result(iAdminMapper.updateById(admin));
    }

    @Override
    public R unlock(String id, Long lastModifyTime) throws MxException {
        Admin admin = iAdminMapper.selectById(id);
        if (admin == null) {
            return R.noData();
        }
        if (!R.checkVersion(admin.getLastModifyTime(), lastModifyTime)) {
            return R.noVersion();
        }
        admin.setLastModifyUser(SaUtils.loginId());
        admin.setLastModifyTime(System.currentTimeMillis());
        admin.setLoginErrorCount(0);
        admin.setLoginLockStatus(Constants.BOOL_FALSE);
        admin.setLoginLockStartTime(0L);
        admin.setLoginLockEndTime(0L);
        return R.result(iAdminMapper.updateById(admin));
    }

    @Override
    public R resetPassword(String id, Long lastModifyTime) throws MxException {
        Admin admin = iAdminMapper.selectById(id);
        if (admin == null) {
            return R.noData();
        }
        if (!R.checkVersion(admin.getLastModifyTime(), lastModifyTime)) {
            return R.noVersion();
        }
        admin.setLastModifyUser(SaUtils.loginId());
        admin.setLastModifyTime(System.currentTimeMillis());
        String userName = admin.getUserName();
        userName = DigestUtils.md5DigestAsHex(Base64.encodeBase64((userName).getBytes(StandardCharsets.UTF_8)));
        String password = null;
        try {
            password = DigestUtils.md5DigestAsHex(Base64.encodeBase64((userName + admin.getSalt()).getBytes(Constants.DEFAULT_CHARTSET)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        admin.setPassword(password);
        return R.result(iAdminMapper.updateById(admin));
    }

    @Override
    public M<Pages<AdminRoleVO>> roleList(String userId, PageBean<AdminRoleVO> pageBean) throws MxException {
        return M.list(iAdminRoleMapper.roleList(userId, pageBean.toPage()));
    }

    @Override
    public R roleCreate(String userId, String roleId) throws MxException {
        AdminRole adminRole = iAdminRoleMapper.selectOne(MMP.lqw(AdminRole.init()).eq(AdminRole::getAdminId, userId)
                .eq(AdminRole::getRoleId, roleId));
        if (adminRole != null) {
            return R.create(SECURITY_ADMIN_ROLE_EXISTS.getCode())
                    .msg(SECURITY_ADMIN_ROLE_EXISTS.getMsg());
        }
        adminRole = AdminRole.builder()
                .id(IdUtil.fastSimpleUUID())
                .adminId(userId)
                .roleId(roleId)
                .createUser(SaUtils.loginId())
                .createTime(System.currentTimeMillis())
                .lastModifyUser(SaUtils.loginId())
                .lastModifyTime(System.currentTimeMillis())
                .build();
        return R.result(iAdminRoleMapper.insert(adminRole));
    }

    @Override
    public R roleDelete(String[] ids) throws MxException {
        return R.result(iAdminRoleMapper.deleteBatchIds(Arrays.asList(ids)));
    }

}

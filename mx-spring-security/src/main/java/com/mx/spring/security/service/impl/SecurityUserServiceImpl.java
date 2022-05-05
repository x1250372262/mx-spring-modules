package com.mx.spring.security.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.constants.Constants;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.M;
import com.mx.spring.dev.result.R;
import com.mx.spring.dev.support.page.PageBean;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.dev.util.BeanUtil;
import com.mx.spring.dev.util.WebUtil;
import com.mx.spring.jdbc.mybatis.plus.MP;
import com.mx.spring.jdbc.mybatis.plus.page.PageHelper;
import com.mx.spring.security.SaUtils;
import com.mx.spring.security.base.bean.SecurityUserBean;
import com.mx.spring.security.base.config.MxSecurityConfig;
import com.mx.spring.security.base.model.SecurityUser;
import com.mx.spring.security.base.model.SecurityUserRole;
import com.mx.spring.security.base.vo.SecurityUserListVO;
import com.mx.spring.security.base.vo.SecurityUserRoleVO;
import com.mx.spring.security.base.vo.SecurityUserVO;
import com.mx.spring.security.handler.Handler;
import com.mx.spring.security.handler.IUserHandler;
import com.mx.spring.security.mapper.ISecurityUserMapper;
import com.mx.spring.security.mapper.ISecurityUserRoleMapper;
import com.mx.spring.security.service.ISecurityUserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

import static com.mx.spring.security.base.code.SecurityCode.SECURITY_USER_ROLE_EXISTS;

/**
 * @Author: mengxiang.
 * @create: 2021-09-24 16:08
 * @Description:
 */
@Service
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class SecurityUserServiceImpl implements ISecurityUserService {

    private final SecurityUser bean = SecurityUser.init();
    @Autowired
    private ISecurityUserMapper iSecurityUserMapper;
    @Autowired
    private ISecurityUserRoleMapper iSecurityUserRoleMapper;
    @Autowired
    private SaUtils saUtils;
    @Autowired
    private MxSecurityConfig config;

    @Override
    public M<Pages<SecurityUserListVO>> list(String userName, String realName, Integer disableStatus, PageBean<SecurityUserListVO> pageBean) throws MxException {
        Page<SecurityUserListVO> resultData = iSecurityUserMapper.findAll(userName, realName, disableStatus, config.getClient(), PageHelper.in(pageBean));
        return M.list(PageHelper.out(resultData, SecurityUserListVO::new));
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {MxException.class, Exception.class})
    public R create(String password, SecurityUserBean userBean) throws MxException {
        Map<String, String> params = ServletUtil.getParamMap(WebUtil.request());
        IUserHandler userHandler = Handler.userHandler();
        R r = userHandler.createBefore(params);
        if (Handler.check(r)) {
            return r;
        }
        SecurityUser securityUser = iSecurityUserMapper.selectOne(MP.lqw(bean).eq(SecurityUser::getUserName, userBean.getUserName()).eq(SecurityUser::getClient, config.getClient()));
        if (securityUser != null) {
            return R.sameData("用户名");
        }
        String salt = RandomUtil.randomString(6);
        password = DigestUtils.md5DigestAsHex(Base64.encodeBase64((password + salt).getBytes(StandardCharsets.UTF_8)));
        String finalPassword = password;
        securityUser = BeanUtil.copy(userBean, SecurityUser::new, (s, t) -> {
            t.bind().id(IdUtil.fastSimpleUUID()).client(config.getClient()).password(finalPassword).createUser(saUtils.loginId()).createTime(System.currentTimeMillis()).lastModifyTime(System.currentTimeMillis()).lastModifyUser(saUtils.loginId()).salt(salt).build();
        });
        r = userHandler.createAfter(params);
        if (Handler.check(r)) {
            return r;
        }
        return R.result(iSecurityUserMapper.insert(securityUser));
    }

    @Override
    public M<SecurityUserVO> detail(String id) throws MxException {
        return M.ok(BeanUtil.copy(iSecurityUserMapper.selectById(id), SecurityUserVO::new));
    }

    @Override
    public R status(String id, Long lastModifyTime, Integer status) throws MxException {
        SecurityUser securityUser = iSecurityUserMapper.selectById(id);
        if (securityUser == null) {
            return R.noData();
        }
        if (!R.checkVersion(securityUser.getLastModifyTime(), lastModifyTime)) {
            return R.noVersion();
        }
        securityUser.setLastModifyUser(saUtils.loginId());
        securityUser.setLastModifyTime(System.currentTimeMillis());
        securityUser.setDisableStatus(status);
        return R.result(iSecurityUserMapper.updateById(securityUser));
    }

    @Override
    public R unlock(String id, Long lastModifyTime) throws MxException {
        SecurityUser securityUser = iSecurityUserMapper.selectById(id);
        if (securityUser == null) {
            return R.noData();
        }
        if (!R.checkVersion(securityUser.getLastModifyTime(), lastModifyTime)) {
            return R.noVersion();
        }
        securityUser.setLastModifyUser(saUtils.loginId());
        securityUser.setLastModifyTime(System.currentTimeMillis());
        securityUser.setLoginErrorCount(0);
        securityUser.setLoginLockStatus(Constants.BOOL_FALSE);
        securityUser.setLoginLockStartTime(0L);
        securityUser.setLoginLockEndTime(0L);
        return R.result(iSecurityUserMapper.updateById(securityUser));
    }

    @Override
    public R resetPassword(String id, Long lastModifyTime) throws MxException {
        SecurityUser securityUser = iSecurityUserMapper.selectById(id);
        if (securityUser == null) {
            return R.noData();
        }
        if (!R.checkVersion(securityUser.getLastModifyTime(), lastModifyTime)) {
            return R.noVersion();
        }
        securityUser.setLastModifyUser(saUtils.loginId());
        securityUser.setLastModifyTime(System.currentTimeMillis());
        String userName = securityUser.getUserName();
        userName = DigestUtils.md5DigestAsHex(userName.getBytes(StandardCharsets.UTF_8));
        String password = null;
        try {
            password = DigestUtils.md5DigestAsHex(Base64.encodeBase64((userName + securityUser.getSalt()).getBytes(Constants.DEFAULT_CHARTSET)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        securityUser.setPassword(password);
        return R.result(iSecurityUserMapper.updateById(securityUser));
    }

    @Override
    public M<Pages<SecurityUserRoleVO>> roleList(String userId, PageBean<SecurityUserRoleVO> pageBean) throws MxException {
        Page<SecurityUserRoleVO> resultData = iSecurityUserRoleMapper.roleList(userId, PageHelper.in(pageBean));
        return M.list(PageHelper.out(resultData, SecurityUserRoleVO::new));
    }

    @Override
    public R roleCreate(String userId, String roleId) throws MxException {
        SecurityUserRole adminRole = iSecurityUserRoleMapper.selectOne(MP.lqw(SecurityUserRole.init()).eq(SecurityUserRole::getUserId, userId).eq(SecurityUserRole::getRoleId, roleId).eq(SecurityUserRole::getClient, config.getClient()));
        if (adminRole != null) {
            return R.create(SECURITY_USER_ROLE_EXISTS.getCode()).msg(SECURITY_USER_ROLE_EXISTS.getMsg());
        }
        adminRole = SecurityUserRole.builder().id(IdUtil.fastSimpleUUID()).userId(userId).roleId(roleId).client(config.getClient()).createUser(saUtils.loginId()).createTime(System.currentTimeMillis()).lastModifyUser(saUtils.loginId()).lastModifyTime(System.currentTimeMillis()).build();
        return R.result(iSecurityUserRoleMapper.insert(adminRole));
    }

    @Override
    public R roleDelete(String[] ids) throws MxException {
        return R.result(iSecurityUserRoleMapper.deleteBatchIds(Arrays.asList(ids)));
    }

}

package com.mx.spring.security.service;


import cn.dev33.satoken.stp.SaTokenInfo;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.security.bean.SecurityLoginInfoBean;
import com.mx.spring.security.bean.SecurityUserBean;
import com.mx.spring.security.vo.SecurityLoginVO;

/**
 * @Author: mengxiang.
 * @create: 2021-09-03 15:20
 * @Description:
 */
public interface ISecurityLoginService {

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     * @throws MxException
     */
    M<SaTokenInfo> login(String userName, String password) throws MxException;


    /**
     * 获取登录人信息
     *
     * @return
     * @throws Exception
     */
    SecurityLoginVO info() throws MxException;


    /**
     * 登录人详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    SecurityLoginVO detail(String id) throws MxException;

    /**
     * 修改登录人信息
     *
     * @param securityLoginInfoBean
     * @return
     * @throws MxException
     */
    R update(SecurityLoginInfoBean securityLoginInfoBean) throws MxException;

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @param rePassword
     * @return
     * @throws MxException
     */
    R password(String oldPassword, String newPassword, String rePassword) throws MxException;
}

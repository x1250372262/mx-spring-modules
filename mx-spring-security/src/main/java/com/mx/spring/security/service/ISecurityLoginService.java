package com.mx.spring.security.service;


import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.Result;
import com.mx.spring.dev.result.View;
import com.mx.spring.security.base.bean.LoginResult;
import com.mx.spring.security.base.bean.SecurityLoginInfoBean;
import com.mx.spring.security.base.vo.SecurityLoginVO;

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
    View<LoginResult> login(String userName, String password) throws MxException;


    /**
     * 退出登录
     *
     * @return
     * @throws MxException
     */
    Result logout() throws MxException;


    /**
     * 获取登录人信息
     *
     * @return
     * @throws MxException
     */
    SecurityLoginVO info() throws MxException;

    /**
     * 修改登录人信息
     *
     * @param securityLoginInfoBean
     * @return
     * @throws MxException
     */
    Result update(SecurityLoginInfoBean securityLoginInfoBean) throws MxException;

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @param rePassword
     * @return
     * @throws MxException
     */
    Result password(String oldPassword, String newPassword, String rePassword) throws MxException;
}

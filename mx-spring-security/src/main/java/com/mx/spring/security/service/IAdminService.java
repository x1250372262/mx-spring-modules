package com.mx.spring.security.service;


import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.security.bean.AdminBean;
import com.mx.spring.security.bean.Token;
import com.mx.spring.security.vo.AdminVO;

/**
 * @Author: mengxiang.
 * @create: 2021-09-03 15:20
 * @Description:
 */
public interface IAdminService {

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     * @throws MxException
     */
    M<Token> login(String userName, String password) throws MxException;


    /**
     * 获取管理员信息
     *
     * @return
     * @throws Exception
     */
    AdminVO info() throws MxException;


    /**
     * 管理员详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    AdminVO detail(String id) throws MxException;

    /**
     * 修改管理员信息
     *
     * @param adminBean
     * @return
     * @throws MxException
     */
    R update(AdminBean adminBean) throws MxException;

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

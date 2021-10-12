package com.mx.spring.security.service;


import com.mx.spring.dev.bean.PageBean;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.security.bean.UserBean;
import com.mx.spring.security.vo.AdminRoleVO;
import com.mx.spring.security.vo.UserListVO;
import com.mx.spring.security.vo.UserVO;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 15:41
 * @Description:
 */
public interface IUserService {


    /**
     * 人员列表
     *
     * @param userName
     * @param realName
     * @param disableStatus
     * @param pageBean
     * @return
     * @throws MxException
     */
    M<Pages<UserListVO>> list(String userName, String realName, Integer disableStatus, PageBean<UserListVO> pageBean) throws MxException;

    /**
     * 添加角色
     *
     * @param password
     * @param userBean
     * @return
     * @throws MxException
     */
    R create(String password, UserBean userBean) throws MxException;

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    M<UserVO> detail(String id) throws MxException;


    /**
     * 修改状态
     *
     * @param id
     * @param lastModifyTime
     * @param status
     * @return
     * @throws MxException
     */
    R status(String id, Long lastModifyTime, Integer status) throws MxException;

    /**
     * 解锁
     *
     * @param id
     * @param lastModifyTime
     * @return
     * @throws MxException
     */
    R unlock(String id, Long lastModifyTime) throws MxException;

    /**
     * 重置密码
     *
     * @param id
     * @param lastModifyTime
     * @return
     * @throws Exception
     */
    R resetPassword(String id, Long lastModifyTime) throws MxException;

    /**
     * 人员权限列表
     *
     * @param userId
     * @param pageBean
     * @return
     * @throws MxException
     */
    M<Pages<AdminRoleVO>> roleList(String userId, PageBean<AdminRoleVO> pageBean) throws MxException;

    /**
     * 添加人员角色
     *
     * @param userId
     * @param roleId
     * @return
     * @throws MxException
     */
    R roleCreate(String userId, String roleId) throws MxException;

    /**
     * 删除人员角色
     *
     * @param ids
     * @return
     * @throws MxException
     */
    R roleDelete(String[] ids) throws MxException;
}

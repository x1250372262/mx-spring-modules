package com.mx.spring.security.service;


import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;
import com.mx.spring.dev.result.MxView;
import com.mx.spring.dev.support.page.PageBean;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.security.base.bean.SecurityUserBean;
import com.mx.spring.security.base.vo.SecurityUserListVO;
import com.mx.spring.security.base.vo.SecurityUserRoleVO;
import com.mx.spring.security.base.vo.SecurityUserVO;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 15:41
 * @Description:
 */
public interface ISecurityUserService {


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
    MxView<Pages<SecurityUserListVO>> list(String userName, String realName, Integer disableStatus, PageBean<SecurityUserListVO> pageBean) throws MxException;

    /**
     * 添加角色
     *
     * @param password
     * @param securityUserBean
     * @return
     * @throws MxException
     */
    MxResult create(String password, SecurityUserBean securityUserBean) throws MxException;

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    MxView<SecurityUserVO> detail(String id) throws MxException;


    /**
     * 修改状态
     *
     * @param id
     * @param lastModifyTime
     * @param status
     * @return
     * @throws MxException
     */
    MxResult status(String id, Long lastModifyTime, Integer status) throws MxException;

    /**
     * 解锁
     *
     * @param id
     * @param lastModifyTime
     * @return
     * @throws MxException
     */
    MxResult unlock(String id, Long lastModifyTime) throws MxException;

    /**
     * 重置密码
     *
     * @param id
     * @param lastModifyTime
     * @return
     * @throws MxException
     */
    MxResult resetPassword(String id, Long lastModifyTime) throws MxException;

    /**
     * 人员权限列表
     *
     * @param userId
     * @param pageBean
     * @return
     * @throws MxException
     */
    MxView<Pages<SecurityUserRoleVO>> roleList(String userId, PageBean<SecurityUserRoleVO> pageBean) throws MxException;

    /**
     * 添加人员角色
     *
     * @param userId
     * @param roleId
     * @return
     * @throws MxException
     */
    MxResult roleCreate(String userId, String roleId) throws MxException;

    /**
     * 删除人员角色
     *
     * @param ids
     * @return
     * @throws MxException
     */
    MxResult roleDelete(String[] ids) throws MxException;
}

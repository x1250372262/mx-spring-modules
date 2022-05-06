package com.mx.spring.security.service;


import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.Result;
import com.mx.spring.dev.result.View;
import com.mx.spring.dev.support.page.PageBean;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.security.base.model.SecurityOperationLog;
import com.mx.spring.security.base.vo.SecurityOperationLogListVO;
import com.mx.spring.security.base.vo.SecurityOperationLogVO;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 15:41
 * @Description:
 */
public interface ISecurityOperationLogService {


    /**
     * 日志列表
     *
     * @param title
     * @param startTime
     * @param endTime
     * @param pageBean
     * @return
     * @throws MxException
     */
    View<Pages<SecurityOperationLogListVO>> list(String title, Long startTime, Long endTime, PageBean<SecurityOperationLog> pageBean) throws MxException;

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws MxException
     */
    View<SecurityOperationLogVO> detail(String id) throws MxException;

    /**
     * 删除日志
     *
     * @param ids
     * @return
     * @throws MxException
     */
    Result delete(String[] ids) throws MxException;
}

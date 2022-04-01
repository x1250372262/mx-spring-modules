package com.mx.spring.log.service;


import com.mx.spring.dev.support.page.PageBean;
import com.mx.spring.dev.result.M;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.dev.result.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.log.model.SecurityLog;
import com.mx.spring.dev.support.log.vo.LogVO;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 15:41
 * @Description:
 */
public interface ILogService {


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
    M<Pages<LogVO>> list(String title, Long startTime, Long endTime, PageBean<SecurityLog> pageBean) throws MxException;


    /**
     * 删除日志
     *
     * @param ids
     * @return
     * @throws MxException
     */
    R delete(String[] ids) throws MxException;
}

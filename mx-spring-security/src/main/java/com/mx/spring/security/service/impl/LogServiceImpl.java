package com.mx.spring.security.service.impl;

import com.mx.spring.dev.bean.PageBean;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.security.mapper.IOperationLogMapper;
import com.mx.spring.security.service.ILogService;
import com.mx.spring.security.vo.LogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @Author: mengxiang.
 * @create: 2021-09-26 13:29
 * @Description:
 */
@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private IOperationLogMapper iOperationLogMapper;

    @Override
    public M<Pages<LogVO>> list(String title, Long startTime, Long endTime, PageBean<LogVO> pageBean) throws MxException {
        return M.list(iOperationLogMapper.findAll(title, startTime, endTime, pageBean.toPage()));
    }

    @Override
    public R delete(String[] ids) throws MxException {
        return R.result(iOperationLogMapper.deleteBatchIds(Arrays.asList(ids)));
    }
}

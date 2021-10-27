package com.mx.spring.dev.support.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mx.spring.dev.bean.PageBean;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.log.mapper.ISecurityLogMapper;
import com.mx.spring.dev.support.log.model.SecurityLog;
import com.mx.spring.dev.support.log.vo.LogVO;
import com.mx.spring.dev.support.mybatisplus.MMP;
import com.mx.spring.dev.util.BeanUtils;
import com.mx.spring.dev.support.log.service.ILogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Author: mengxiang.
 * @create: 2021-09-26 13:29
 * @Description:
 */
@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private ISecurityLogMapper iSecurityLogMapper;

    @Override
    public M<Pages<LogVO>> list(String title, Long startTime, Long endTime, PageBean<SecurityLog> pageBean) throws MxException {
        LambdaQueryWrapper<SecurityLog> queryWrapper = MMP.lqw(SecurityLog.init())
                .like(StringUtils.isNotBlank(title), SecurityLog::getTitle, title)
                .ge(Objects.nonNull(startTime), SecurityLog::getCreateTime, startTime)
                .le(Objects.nonNull(endTime), SecurityLog::getCreateTime, endTime)
                .orderByDesc(SecurityLog::getCreateTime);
        return M.list(BeanUtils.copyPage(iSecurityLogMapper.selectPage(pageBean.toPage(), queryWrapper), LogVO::new));
    }

    @Override
    public R delete(String[] ids) throws MxException {
        return R.result(iSecurityLogMapper.deleteBatchIds(Arrays.asList(ids)));
    }
}

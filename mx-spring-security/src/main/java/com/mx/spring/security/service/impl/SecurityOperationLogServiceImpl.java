package com.mx.spring.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;
import com.mx.spring.dev.result.MxView;
import com.mx.spring.dev.support.page.PageBean;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.dev.util.BeanUtil;
import com.mx.spring.jdbc.mybatis.plus.Mp;
import com.mx.spring.jdbc.mybatis.plus.page.PageHelper;
import com.mx.spring.jdbc.mybatis.plus.util.MpBeanUtils;
import com.mx.spring.security.base.config.MxSecurityConfig;
import com.mx.spring.security.base.model.SecurityOperationLog;
import com.mx.spring.security.base.vo.SecurityOperationLogListVO;
import com.mx.spring.security.base.vo.SecurityOperationLogVO;
import com.mx.spring.security.mapper.ISecurityOperationLogMapper;
import com.mx.spring.security.service.ISecurityOperationLogService;
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
public class SecurityOperationLogServiceImpl implements ISecurityOperationLogService {

    @Autowired
    private ISecurityOperationLogMapper iSecurityOperationLogMapper;

    @Autowired
    private MxSecurityConfig config;

    @Override
    public MxView<Pages<SecurityOperationLogListVO>> list(String title, Long startTime, Long endTime, PageBean<SecurityOperationLog> pageBean) throws MxException {
        LambdaQueryWrapper<SecurityOperationLog> queryWrapper = Mp.lqw(SecurityOperationLog.init())
                .eq(SecurityOperationLog::getClient, config.getClient())
                .like(StringUtils.isNotBlank(title), SecurityOperationLog::getTitle, title)
                .ge(Objects.nonNull(startTime), SecurityOperationLog::getCreateTime, startTime)
                .le(Objects.nonNull(endTime), SecurityOperationLog::getCreateTime, endTime)
                .orderByDesc(SecurityOperationLog::getCreateTime);
        return MxView.list(MpBeanUtils.copyPage(iSecurityOperationLogMapper.selectPage(PageHelper.in(pageBean), queryWrapper), SecurityOperationLogListVO::new));
    }

    @Override
    public MxView<SecurityOperationLogVO> detail(String id) throws MxException {
        return MxView.ok(BeanUtil.copy(iSecurityOperationLogMapper.selectById(id), SecurityOperationLogVO::new));
    }

    @Override
    public MxResult delete(String[] ids) throws MxException {
        return MxResult.result(iSecurityOperationLogMapper.deleteBatchIds(Arrays.asList(ids)));
    }
}

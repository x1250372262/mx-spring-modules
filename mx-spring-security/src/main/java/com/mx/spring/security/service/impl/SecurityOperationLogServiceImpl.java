package com.mx.spring.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.M;
import com.mx.spring.dev.result.R;
import com.mx.spring.dev.support.page.PageBean;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.dev.util.BeanUtil;
import com.mx.spring.jdbc.mybatis.plus.MP;
import com.mx.spring.jdbc.mybatis.plus.page.PageHelper;
import com.mx.spring.jdbc.mybatis.plus.util.MPBeanUtils;
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
    public M<Pages<SecurityOperationLogListVO>> list(String title, Long startTime, Long endTime, PageBean<SecurityOperationLog> pageBean) throws MxException {
        LambdaQueryWrapper<SecurityOperationLog> queryWrapper = MP.lqw(SecurityOperationLog.init())
                .eq(SecurityOperationLog::getClient,config.getClient())
                .like(StringUtils.isNotBlank(title), SecurityOperationLog::getTitle, title)
                .ge(Objects.nonNull(startTime), SecurityOperationLog::getCreateTime, startTime)
                .le(Objects.nonNull(endTime), SecurityOperationLog::getCreateTime, endTime)
                .orderByDesc(SecurityOperationLog::getCreateTime);
        return M.list(MPBeanUtils.copyPage(iSecurityOperationLogMapper.selectPage(PageHelper.in(pageBean), queryWrapper), SecurityOperationLogListVO::new));
    }

    @Override
    public M<SecurityOperationLogVO> detail(String id) throws MxException {
        return M.ok(BeanUtil.copy(iSecurityOperationLogMapper.selectById(id), SecurityOperationLogVO::new));
    }

    @Override
    public R delete(String[] ids) throws MxException {
        return R.result(iSecurityOperationLogMapper.deleteBatchIds(Arrays.asList(ids)));
    }
}

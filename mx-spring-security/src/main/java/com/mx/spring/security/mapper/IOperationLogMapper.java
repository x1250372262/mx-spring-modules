package com.mx.spring.security.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.support.mybatisplus.MxBaseMapper;
import com.mx.spring.security.model.OperationLog;
import com.mx.spring.security.vo.LogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/05.
 * @Time: 14:37:38.
 * @Description: 2021/10/05 14:37:38 生成 OperationLogMapper
 */
@Mapper
public interface IOperationLogMapper extends MxBaseMapper<OperationLog> {
    Page<LogVO> findAll(@Param("title") String title, @Param("startTime") Long startTime, @Param("endTime") Long endTime, Page<LogVO> page);
}
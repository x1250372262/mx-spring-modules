package com.mx.spring.security.mapper;

import com.mx.spring.jdbc.mybatis.plus.MxBaseMapper;
import com.mx.spring.security.base.model.SecurityOperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/22.
 * @Time: 15:14:05.
 * @Description: 2021/10/22 15:14:05 生成 OperationLogMapper
 */
@Mapper
public interface ISecurityOperationLogMapper extends MxBaseMapper<SecurityOperationLog> {

}
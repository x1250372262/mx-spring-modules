package com.mx.spring.jdbc.mybatis.plus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-09-30 08:41
 * @Description: 自定义mapper
 */
public interface MxBaseMapper<T> extends BaseMapper<T> {

    /**
     * 自定义批量插入
     *
     * @param list
     * @return
     */
    int insertBatch(@Param("list") List<T> list);

    /**
     * 自定义批量更新，条件为主键
     *
     * @param list
     * @return
     */
    int updateBatch(@Param("list") List<T> list);
}

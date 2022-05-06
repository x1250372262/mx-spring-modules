package com.mx.spring.jdbc.mybatis.plus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

/**
 * @Author: mengxiang.
 * @create: 2021-09-30 09:21
 * @Description: 便捷操作
 */
public class Mp {

    public static <T> LambdaQueryWrapper<T> lqw(T obj) {
        return new LambdaQueryWrapper<>(obj);
    }

    public static <T> QueryWrapper<T> qw(T obj) {
        return new QueryWrapper<>(obj);
    }

    public static <T> UpdateWrapper<T> uw(T obj) {
        return new UpdateWrapper<>(obj);
    }

    public static <T> LambdaUpdateWrapper<T> luw(T obj) {
        return new LambdaUpdateWrapper<>(obj);
    }
}

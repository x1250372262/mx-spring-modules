package com.mx.spring.jdbc.mybatis.plus.query;

import cn.hutool.core.util.ClassUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.jdbc.mybatis.plus.query.annotation.Cond;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2022-05-20 15:54
 * @Description:
 */
public class Warpper {

    private static Object getVal(Field field, Object entity) {
        Object val;
        try {
            val = field.get(entity);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return val;
    }

    public static <T> void create(QueryWrapper<T> queryWrapper, T entity) {
        Class<?> clazz = entity.getClass();
        Field[] fields = ClassUtil.getDeclaredFields(clazz);
        List<Query.Param> paramList = new ArrayList<>();
        for (Field field : fields) {
            Cond cond = field.getAnnotation(Cond.class);
            if (cond == null) {
                continue;
            }
            Object val = getVal(field, entity);
            Query.Param param = new Query.Param(cond.field(), val, cond.opt(), cond.notEmpty());
            paramList.add(param);
        }
        create(queryWrapper, paramList);
    }

    public static <T> void create(QueryWrapper<T> queryWrapper, List<Query.Param> paramList) {
        for (Query.Param param : paramList) {
            switch (param.getOpt()) {
                case EQ:
                    queryWrapper.eq(param.isNotEmpty(), param.getFiled(), param.getVal1());
                    break;
                case NE:
                    queryWrapper.ne(param.isNotEmpty(), param.getFiled(), param.getVal1());
                    break;
                case LT:
                    queryWrapper.lt(param.isNotEmpty(), param.getFiled(), param.getVal1());
                    break;
                case GT:
                    queryWrapper.gt(param.isNotEmpty(), param.getFiled(), param.getVal1());
                    break;
                case LE:
                    queryWrapper.le(param.isNotEmpty(), param.getFiled(), param.getVal1());
                    break;
                case GE:
                    queryWrapper.ge(param.isNotEmpty(), param.getFiled(), param.getVal1());
                    break;
                case BETWEEN:
                    queryWrapper.between(param.isNotEmpty(), param.getFiled(), param.getVal1(), param.getVal2());
                    break;
                case NOT_BETWEEN:
                    queryWrapper.notBetween(param.isNotEmpty(), param.getFiled(), param.getVal1(), param.getVal2());
                    break;
                case LIKE:
                    queryWrapper.like(param.isNotEmpty(), param.getFiled(), param.getVal1());
                    break;
                case NOT_LIKE:
                    queryWrapper.notLike(param.isNotEmpty(), param.getFiled(), param.getVal1());
                    break;
                case LIKE_LEFT:
                    queryWrapper.likeLeft(param.isNotEmpty(), param.getFiled(), param.getVal1());
                    break;
                case LIKE_RIGHT:
                    queryWrapper.likeRight(param.isNotEmpty(), param.getFiled(), param.getVal1());
                    break;
                default:
                    throw new MxException("不支持的查询方式");
            }
        }
    }

}

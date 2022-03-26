package com.mx.spring.jdbc.mybatis.plus.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.mx.spring.jdbc.mybatis.plus.method.InsertBatchMethod;
import com.mx.spring.jdbc.mybatis.plus.method.UpdateBatchMethod;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-10-11 09:33
 * @Description:
 */
public class MxSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        methodList.add(new InsertBatchMethod());
        methodList.add(new UpdateBatchMethod());
        return methodList;
    }
}

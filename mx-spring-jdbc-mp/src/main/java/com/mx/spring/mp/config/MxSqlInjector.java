package com.mx.spring.mp.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.mx.spring.mp.method.InsertBatchMethod;
import com.mx.spring.mp.method.UpdateBatchMethod;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-10-11 09:33
 * @Description:
 */
public class MxSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
//        //增加自定义方法
        methodList.add(new InsertBatchMethod());
        methodList.add(new UpdateBatchMethod());
        return methodList;
    }
}

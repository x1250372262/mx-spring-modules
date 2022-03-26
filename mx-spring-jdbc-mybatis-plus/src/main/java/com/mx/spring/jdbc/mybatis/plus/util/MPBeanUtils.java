package com.mx.spring.jdbc.mybatis.plus.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.page.Pages;
import com.mx.spring.dev.util.BeanUtils;

import java.util.List;
import java.util.function.Supplier;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
public class MPBeanUtils extends BeanUtils {

    /**
     * 复制Pages
     *
     * @param sources  要复制的
     * @param target   目标对象
     * @param callBack 处理额外数据接口
     * @return 目标对象
     */
    public static <S, T> Pages<T> copyPage(Page<S> sources, Supplier<T> target, BeanUtilCallBack<S, T> callBack, String... ignoreProperties) throws MxException {
        List<T> result = copyList(sources.getRecords(), target, callBack, ignoreProperties);
        return new Pages<>(sources.getCurrent(), sources.getSize(), sources.getPages(), sources.getTotal(), result);
    }

    /**
     * 复制Pages
     *
     * @param sources 要复制的
     * @param target  目标对象
     * @return 目标对象
     */
    public static <S, T> Pages<T> copyPage(Page<S> sources, Supplier<T> target, String... ignoreProperties) throws MxException {
        List<T> result = copyList(sources.getRecords(), target, ignoreProperties);
        return new Pages<>(sources.getCurrent(), sources.getSize(), sources.getPages(), sources.getTotal(), result);
    }


}

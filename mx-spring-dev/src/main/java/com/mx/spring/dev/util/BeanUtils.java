package com.mx.spring.dev.util;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.exception.MxException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
public class BeanUtils {

    /**
     * 复制bean
     *
     * @param source           要复制的
     * @param target           目标对象
     * @param ignoreProperties 忽略字段
     * @return 目标对象
     */
    public static <S, T> T copy(S source, Supplier<T> target, String... ignoreProperties) throws MxException {
        return copy(source, target, null, ignoreProperties);
    }

    /**
     * 复制bean
     *
     * @param source           要复制的
     * @param target           目标对象
     * @param callBack         处理额外数据接口
     * @param ignoreProperties 忽略字段
     * @return 目标对象
     */
    public static <S, T> T copy(S source, Supplier<T> target, BeanUtilCallBack<S, T> callBack, String... ignoreProperties) throws MxException {
        if (source == null || target == null || target.get() == null) {
            return null;
        }
        T t = target.get();
        BeanUtil.copyProperties(source, t, ignoreProperties);
        if (callBack != null) {
            // 回调
            callBack.callBack(source, t);
        }
        return t;
    }

    /**
     * 复制bean
     *
     * @param source           要复制的
     * @param target           目标对象
     * @param ignoreProperties 忽略字段
     * @return 目标对象
     */
    public static <S, T> T duplicate(S source, T target, String... ignoreProperties) throws MxException {
        return duplicate(source, target, null, ignoreProperties);
    }

    /**
     * 复制bean
     *
     * @param source           要复制的
     * @param target           目标对象
     * @param callBack         处理额外数据接口
     * @param ignoreProperties 忽略字段
     * @return 目标对象
     */
    public static <S, T> T duplicate(S source, T target, BeanUtilCallBack<S, T> callBack, String... ignoreProperties) throws MxException {
        if (source == null || target == null) {
            return null;
        }
        BeanUtil.copyProperties(source, target, ignoreProperties);
        if (callBack != null) {
            // 回调
            callBack.callBack(source, target);
        }
        return target;
    }

    /**
     * 复制集合
     *
     * @param source           要复制的
     * @param target           目标对象
     * @param ignoreProperties 忽略字段
     * @return 目标对象
     */
    public static <S, T> List<T> copyList(List<S> source, Supplier<T> target, String... ignoreProperties) throws MxException {
        return copyList(source, target, null, ignoreProperties);
    }

    /**
     * 复制集合
     *
     * @param source           要复制的
     * @param target           目标对象
     * @param callBack         处理额外数据接口
     * @param ignoreProperties 忽略字段
     * @return 目标对象
     */
    public static <S, T> List<T> copyList(List<S> source, Supplier<T> target, BeanUtilCallBack<S, T> callBack, String... ignoreProperties) throws MxException {
        if (source == null || target == null || target.get() == null) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>(source.size());
        for (S s : source) {
            T t = target.get();
            BeanUtil.copyProperties(s, t, ignoreProperties);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(s, t);
            }
        }
        return list;
    }

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

    @FunctionalInterface
    public interface BeanUtilCallBack<S, T> {

        /**
         * 定义默认回调方法
         *
         * @param s
         * @param t
         */
        void callBack(S s, T t) throws MxException;
    }


}

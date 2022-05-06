package com.mx.spring.dev.util;

import com.mx.spring.dev.exception.MxException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description: bean工具类
 */
public class BeanUtil {

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
        cn.hutool.core.bean.BeanUtil.copyProperties(source, t, ignoreProperties);
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
        cn.hutool.core.bean.BeanUtil.copyProperties(source, target, ignoreProperties);
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
            cn.hutool.core.bean.BeanUtil.copyProperties(s, t, ignoreProperties);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(s, t);
            }
        }
        return list;
    }

    @FunctionalInterface
    public interface BeanUtilCallBack<S, T> {

        /**
         * 回调方法
         *
         * @param s
         * @param t
         * @throws MxException
         */
        void callBack(S s, T t) throws MxException;
    }


}

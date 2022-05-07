package com.mx.spring.jdbc.mybatis.plus.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.page.PageBean;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.jdbc.mybatis.plus.util.MpBeanUtils;

import java.util.List;
import java.util.function.Supplier;

/**
 * @Author: mengxiang.
 * @create: 2022-03-26 17:02
 * @Description: 分页工具类
 */
public class PageHelper {

    public static <T> Page<T> in(PageBean<T> pageBean) throws MxException {
        return new Page<>(pageBean.getPage(), pageBean.getPageSize());
    }


    public static <S, T> Pages<T> out(Page<S> sources, Supplier<T> target) throws MxException {
        return MpBeanUtils.copyPage(sources, target);
    }

}

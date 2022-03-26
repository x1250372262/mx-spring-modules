package com.mx.spring.jdbc.mybatis.plus.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.page.PageBean;
import com.mx.spring.dev.page.Pages;
import com.mx.spring.jdbc.mybatis.plus.util.MPBeanUtils;

import java.util.function.Supplier;

/**
 * @Author: 徐建鹏.
 * @create: 2022-03-26 17:02
 * @Description:
 */
public class PageHelper<T> {

    public static <T> Page<T> in(PageBean<T> pageBean) throws MxException {
        return new Page<>(pageBean.getPage(), pageBean.getPageSize());
    }

    public static <T> Pages<T> out(Page<T> page, Supplier<T> target) throws MxException {
        return MPBeanUtils.copyPage(page, target);
    }
}

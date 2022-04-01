package com.mx.spring.dev.support.page;


/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
public class PageBean<T> {

    private Integer page;

    private Integer pageSize;

    public PageBean() {
    }

    public PageBean(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}

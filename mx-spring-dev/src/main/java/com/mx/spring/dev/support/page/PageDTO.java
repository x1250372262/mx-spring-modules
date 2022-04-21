package com.mx.spring.dev.support.page;


import cn.hutool.core.util.ObjectUtil;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.util.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
@ApiModel(value = "分页信息", description = "分页信息")
public class PageDTO<T> implements Serializable {


    @ApiModelProperty(value = "当前页")
    private Integer page;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    public PageDTO() {
    }

    public PageDTO(Integer page, Integer pageSize) {
        this.page = ObjectUtil.defaultIfNull(page, 1);
        this.pageSize = ObjectUtil.defaultIfNull(pageSize, 10);
    }

    public PageBean<T> toBean() throws MxException {
        return BeanUtil.copy(this, PageBean::new);
    }

    public Integer getPage() {
        return ObjectUtil.defaultIfNull(page, 1);
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return ObjectUtil.defaultIfNull(pageSize, 10);
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageDTO{" + "page=" + page + ", pageSize=" + pageSize + '}';
    }
}

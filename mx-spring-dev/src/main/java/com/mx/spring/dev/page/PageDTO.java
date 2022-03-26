package com.mx.spring.dev.page;


import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.util.BeanUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
@ApiModel(value = "分页信息", description = "分页信息")
public class PageDTO<T> {

    @ApiModelProperty(value = "当前页")
    private Integer page = 1;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;

    public PageDTO() {
    }

    public PageDTO(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public PageBean<T> toBean() throws MxException {
        return BeanUtils.copy(this, PageBean::new);
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

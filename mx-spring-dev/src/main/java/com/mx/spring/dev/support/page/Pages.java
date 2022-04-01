package com.mx.spring.dev.support.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
@ApiModel(value = "分页信息", description = "分页信息")
public class Pages<T> implements Serializable {

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码", required = true)
    private long pageNum;

    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量", required = true)
    private long pageSize;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数", required = true)
    private long pageCount;

    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数", required = true)
    private long recordCount;

    /**
     * 数据
     */
    @ApiModelProperty(value = "数据", required = true)
    private List<T> resultData;

    public Pages() {
    }

    public Pages(long pageNum, long pageSize, long pageCount, long recordCount, List<T> resultData) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.recordCount = recordCount;
        this.resultData = resultData;
    }

    public Pages(long pageNum, long pageSize, long pageCount, long recordCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.recordCount = recordCount;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public List<T> getResultData() {
        return resultData;
    }

    public void setResultData(List<T> resultData) {
        this.resultData = resultData;
    }
}

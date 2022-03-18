package com.mx.spring.upload.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2021-09-23 13:46
 * @Description:
 */
@ApiModel(value = "上传文件", description = "上传文件")
public class Upload implements Serializable {

    /**
     * 文件哈希值
     */
    @ApiModelProperty(value = "文件哈希值",required = true)
    private String hash;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称",required = true)
    private String name;

    /**
     * 扩展名称
     */
    @ApiModelProperty(value = "扩展名称",required = true)
    private String extension;

    /**
     * 文件静态引用URL地址
     */
    @ApiModelProperty(value = "文件静态引用URL地址",required = true)
    private String url;


    /**
     * 文件大小（字节）
     */
    @ApiModelProperty(value = "文件大小（字节）",required = true)
    private Long size;


    /**
     * 文件资源类型
     */
    @ApiModelProperty(value = "文件资源类型",required = true)
    private String type;

    /**
     * 文件创建时间
     */
    @ApiModelProperty(value = "文件创建时间",required = true)
    private Long createTime;

    public Upload(String hash, String name, String extension, String url, Long size, String type, Long createTime) {
        this.hash = hash;
        this.name = name;
        this.extension = extension;
        this.url = url;
        this.size = size;
        this.type = type;
        this.createTime = createTime;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}

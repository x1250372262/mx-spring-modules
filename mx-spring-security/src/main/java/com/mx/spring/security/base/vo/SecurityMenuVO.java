package com.mx.spring.security.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2022-04-20 00:00
 * @Description: 菜单信息
 */
@ApiModel(value = "菜单信息", description = "菜单信息")
public class SecurityMenuVO implements Serializable {

    @ApiModelProperty(value = "ID", required = true)
    private String id;

    @ApiModelProperty(value = "菜单父ID", required = true)
    private String parentId;

    @ApiModelProperty(value = "菜单名称", required = true)
    private String name;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "菜单路径")
    private String path;

    @ApiModelProperty(value = "菜单跳转路径")
    private String url;

    @ApiModelProperty(value = "菜单排序", required = true)
    private Integer sort;

    @ApiModelProperty(value = "菜单类型 0默认 1公开 2拥有者可看", required = true)
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SecurityMenuVO{" + "id='" + id + '\'' + ", parentId='" + parentId + '\'' + ", name='" + name + '\'' + ", icon='" + icon + '\'' + ", path='" + path + '\'' + ", url='" + url + '\'' + ", sort=" + sort + ", type='" + type + '\'' + '}';
    }
}

package com.mx.spring.security.base.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApiModel(value = "菜单信息", description = "菜单信息")
public class SecurityMenuListVO implements Serializable {

    @ApiModelProperty(value = "菜单名称", required = true)
    private String text;

    @ApiModelProperty(value = "菜单状态", required = true)
    private String state;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "菜单排序", required = true)
    private Integer sort;

    @ApiModelProperty(value = "菜单其他信息")
    @JsonProperty(value = "a_attr")
    private Map<Object, Object> attr;

    @ApiModelProperty(value = "子菜单数据")
    private List<SecurityMenuListVO> children = new ArrayList<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<SecurityMenuListVO> getChildren() {
        return children;
    }

    public void setChildren(List<SecurityMenuListVO> children) {
        this.children = children;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Map<Object, Object> getAttr() {
        return attr;
    }

    public void setAttr(Map<Object, Object> attr) {
        this.attr = attr;
    }
}

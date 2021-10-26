package com.mx.spring.security.dto;

import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.util.BeanUtils;
import com.mx.spring.security.bean.SecurityMenuBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value = "菜单信息", description = "菜单信息")
public class SecurityMenuDTO implements Serializable {

    @ApiModelProperty(value = "菜单父ID", required = true)
    @NotBlank(message = "不能为空")
    private String parentId;

    @ApiModelProperty(value = "菜单名称", required = true)
    @NotBlank(message = "不能为空")
    private String name;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "菜单路径")
    private String path;

    @ApiModelProperty(value = "菜单跳转路径")
    private String url;

    @ApiModelProperty(value = "菜单排序", required = true)
    @NotNull(message = "不能为空")
    private Integer sort;

    @ApiModelProperty(value = "菜单类型 0默认 1公开 2拥有者可看", required = true)
    @NotNull(message = "不能为空")
    private Integer type;

    public SecurityMenuBean toBean() throws MxException {
        return BeanUtils.copy(this, SecurityMenuBean::new);
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

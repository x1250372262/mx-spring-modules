package com.mx.spring.security.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2022-04-20 00:00
 * @Description: 菜单角色信息
 */
@ApiModel(value = "菜单角色信息", description = "菜单角色信息")
public class SecurityMenuRoleVO implements Serializable {

    @ApiModelProperty(value = "ID", required = true)
    private String id;

    @ApiModelProperty(value = "角色名称", required = true)
    private String name;

    @ApiModelProperty(value = "创建时间", required = true)
    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "SecurityMenuRoleVO{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", createTime=" + createTime + '}';
    }
}

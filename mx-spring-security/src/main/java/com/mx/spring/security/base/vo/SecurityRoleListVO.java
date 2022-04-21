package com.mx.spring.security.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2021-09-24 10:42
 * @Description:
 */
@ApiModel(value = "角色信息", description = "角色信息")
public class SecurityRoleListVO extends SecurityRoleVO implements Serializable {

    @ApiModelProperty(value = "创建时间", required = true)
    private Long createTime;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SecurityRoleListVO{" + "createTime=" + createTime + "} " + super.toString();
    }
}

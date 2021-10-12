package com.mx.spring.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2021-09-24 10:42
 * @Description:
 */
@ApiModel(value = "角色信息", description = "角色信息")
public class RoleListVO extends RoleVO implements Serializable {

    @ApiModelProperty(value = "创建时间", required = true)
    private Long createTime;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}

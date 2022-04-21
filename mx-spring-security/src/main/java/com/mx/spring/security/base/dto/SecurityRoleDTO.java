package com.mx.spring.security.base.dto;

import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.util.BeanUtil;
import com.mx.spring.security.base.bean.SecurityRoleBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2022-04-20 00:00
 * @Description: 角色信息
 */
@ApiModel(value = "角色信息", description = "角色信息")
public class SecurityRoleDTO implements Serializable {


    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank(message = "不能为空")
    private String name;

    @ApiModelProperty(value = "角色备注")
    private String remark;

    public SecurityRoleBean toBean() throws MxException {
        return BeanUtil.copy(this, SecurityRoleBean::new);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SecurityRoleDTO{" + "name='" + name + '\'' + ", remark='" + remark + '\'' + '}';
    }
}

package com.mx.spring.security.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: 徐建鹏.
 * @create: 2021-09-03 15:08
 * @Description:
 */
@ApiModel(value = "登录成功返回token信息", description = "登录成功返回token信息")
public class Token implements Serializable {


    /**
     * token名称
     */
    @ApiModelProperty(name = "token名称")
    public String tokenName;

    /**
     * token值
     */
    @ApiModelProperty(name = "token值")
    public String tokenValue;

    /**
     * 管理员ID
     */
    @ApiModelProperty(name = "管理员ID")
    public String adminId;


    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}

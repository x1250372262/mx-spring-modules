package com.mx.spring.security.base.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2021-09-24 10:42
 * @Description:
 */
@ApiModel(value = "权限信息", description = "权限信息")
public class SecurityPermissionVO implements Serializable {

    private String name;

    private String code;

    private String groupName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

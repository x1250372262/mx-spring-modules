package com.mx.spring.security.base.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-09-24 10:42
 * @Description:
 */
@ApiModel(value = "权限信息", description = "权限信息")
public class SecurityPermissionSelectVO implements Serializable {

    private String groupName;

    private List<SecurityPermissionVO> permissionVOList;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<SecurityPermissionVO> getPermissionVOList() {
        return permissionVOList;
    }

    public void setPermissionVOList(List<SecurityPermissionVO> permissionVOList) {
        this.permissionVOList = permissionVOList;
    }
}

package com.mx.spring.security.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-09-24 10:42
 * @Description:
 */
@ApiModel(value = "权限信息", description = "权限信息")
public class PermissionSelectVO implements Serializable {

    private String groupName;

    private List<PermissionVO> permissionVOList;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<PermissionVO> getPermissionVOList() {
        return permissionVOList;
    }

    public void setPermissionVOList(List<PermissionVO> permissionVOList) {
        this.permissionVOList = permissionVOList;
    }
}

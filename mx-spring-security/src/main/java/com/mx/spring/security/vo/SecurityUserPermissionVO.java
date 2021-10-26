package com.mx.spring.security.vo;

/**
 * @Author: mengxiang.
 * @create: 2021-09-04 15:45
 * @Description:
 */
public class SecurityUserPermissionVO {

    private String permissionId;

    private String userId;

    private String permissionName;

    private String permissionCode;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}

package com.mx.spring.security.vo;

/**
 * @Author: 徐建鹏.
 * @create: 2021-09-04 15:45
 * @Description:
 */
public class AdminPermissionVO {

    private String permissionId;

    private String adminId;

    private String permissionName;

    private String permissionCode;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
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

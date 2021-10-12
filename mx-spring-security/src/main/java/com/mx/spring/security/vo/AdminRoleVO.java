package com.mx.spring.security.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: mengxiang.
 * @create: 2021-09-04 15:45
 * @Description:
 */
public class AdminRoleVO {

    @ApiModelProperty(value = "ID", required = true)
    private String id;

    @ApiModelProperty(value = "管理员ID", required = true)
    private String adminId;

    @ApiModelProperty(value = "角色ID", required = true)
    private String roleId;

    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;

    @ApiModelProperty(value = "创建时间", required = true)
    private Long createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}

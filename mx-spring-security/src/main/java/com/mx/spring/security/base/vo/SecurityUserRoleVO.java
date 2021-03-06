package com.mx.spring.security.base.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2021-09-04 15:45
 * @Description:
 */
public class SecurityUserRoleVO implements Serializable {

    @ApiModelProperty(value = "ID", required = true)
    private String id;

    @ApiModelProperty(value = "管理员ID", required = true)
    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "SecurityUserRoleVO{" + "id='" + id + '\'' + ", userId='" + userId + '\'' + ", roleId='" + roleId + '\'' + ", roleName='" + roleName + '\'' + ", createTime=" + createTime + '}';
    }
}

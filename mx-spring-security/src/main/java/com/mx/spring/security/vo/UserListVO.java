package com.mx.spring.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2021-09-24 16:00
 * @Description:
 */
@ApiModel(value = "人员信息", description = "人员信息")
public class UserListVO extends UserVO implements Serializable {

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "创建时间", required = true)
    private Long createTime;

    @ApiModelProperty(value = "最后修改时间", required = true)
    private Long lastModifyTime;

    @ApiModelProperty(value = "状态", required = true)
    private Integer disableStatus;

    @ApiModelProperty(value = "帐号冻结状态", required = true)
    private Integer loginLockStatus;

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

    public Integer getDisableStatus() {
        return disableStatus;
    }

    public void setDisableStatus(Integer disableStatus) {
        this.disableStatus = disableStatus;
    }

    public Integer getLoginLockStatus() {
        return loginLockStatus;
    }

    public void setLoginLockStatus(Integer loginLockStatus) {
        this.loginLockStatus = loginLockStatus;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}

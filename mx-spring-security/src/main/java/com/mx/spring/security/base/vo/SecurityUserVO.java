package com.mx.spring.security.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2021-09-24 16:00
 * @Description:
 */
@ApiModel(value = "人员信息", description = "人员信息")
public class SecurityUserVO implements Serializable {

    @ApiModelProperty(value = "id", required = true)
    private String id;

    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    @ApiModelProperty(value = "头像")
    private String photoUri;

    @ApiModelProperty(value = "真实姓名", required = true)
    private String realName;

    @ApiModelProperty(value = "性别 1男2女")
    private Integer gender;

    @ApiModelProperty(value = "最后修改时间(乐观锁)", required = true)
    private Long lastModifyTime;


    @ApiModelProperty(value = "登录错误次数", required = true)
    private Integer loginErrorCount;

    @ApiModelProperty(value = "锁定开始时间", required = true)
    private Long loginLockStartTime;

    @ApiModelProperty(value = "锁定结束时间", required = true)
    private Long loginLockEndTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getLoginErrorCount() {
        return loginErrorCount;
    }

    public void setLoginErrorCount(Integer loginErrorCount) {
        this.loginErrorCount = loginErrorCount;
    }

    public Long getLoginLockStartTime() {
        return loginLockStartTime;
    }

    public void setLoginLockStartTime(Long loginLockStartTime) {
        this.loginLockStartTime = loginLockStartTime;
    }

    public Long getLoginLockEndTime() {
        return loginLockEndTime;
    }

    public void setLoginLockEndTime(Long loginLockEndTime) {
        this.loginLockEndTime = loginLockEndTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public String toString() {
        return "SecurityUserVO{" + "id='" + id + '\'' + ", userName='" + userName + '\'' + ", photoUri='" + photoUri + '\'' + ", realName='" + realName + '\'' + ", gender=" + gender + ", lastModifyTime=" + lastModifyTime + ", loginErrorCount=" + loginErrorCount + ", loginLockStartTime=" + loginLockStartTime + ", loginLockEndTime=" + loginLockEndTime + '}';
    }
}

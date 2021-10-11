package com.mx.spring.security.dto;

import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.util.BeanUtils;
import com.mx.spring.security.bean.AdminBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: 徐建鹏.
 * @create: 2021-09-03 15:08
 * @Description:
 */
@ApiModel(value = "管理员信息", description = "管理员信息")
public class AdminDTO implements Serializable {

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名", required = true)
    @NotBlank(message = "不能为空")
    private String realName;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String photoUri;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "不能为空")
    private String mobile;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别 1男 2女", required = true)
    @NotNull(message = "不能为空")
    private Integer gender;

    public AdminBean toBean() throws MxException {
        return BeanUtils.copy(this, AdminBean::new);
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}

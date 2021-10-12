package com.mx.spring.security.bean;

import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2021-09-03 15:08
 * @Description:
 */
public class UserBean implements Serializable {

    private String userName;

    private String realName;

    private String photoUri;

    private Integer gender;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}

package com.mx.spring.security.base.bean;

import java.io.Serializable;

public class SecurityRoleBean implements Serializable {


    private String name;

    private String remark;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

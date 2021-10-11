package com.mx.spring.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: 徐建鹏.
 * @create: 2021-09-24 16:00
 * @Description:
 */
@ApiModel(value = "日志信息", description = "日志信息")
public class LogVO implements Serializable {

    @ApiModelProperty(value = "id", required = true)
    private String id;

    @ApiModelProperty(value = "角色名称", required = true)
    private String title;

    @ApiModelProperty(value = "类型", required = true)
    private String typeName;

    @ApiModelProperty(value = "操作人", required = true)
    private String userName;

    @ApiModelProperty(value = "创建时间", required = true)
    private Long createTime;

    @ApiModelProperty(value = "请求路径", required = true)
    private String requestUrl;

    @ApiModelProperty(value = "ip地址", required = true)
    private String ip;

    @ApiModelProperty(value = "位置", required = true)
    private String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

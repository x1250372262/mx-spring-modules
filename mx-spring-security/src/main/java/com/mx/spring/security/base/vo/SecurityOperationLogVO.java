package com.mx.spring.security.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: mengxiang.
 * @create: 2021-09-24 16:00
 * @Description:
 */
@ApiModel(value = "日志信息", description = "日志信息")
public class SecurityOperationLogVO extends SecurityOperationLogListVO {

    @ApiModelProperty(value = "请求参数")
    private String requestParam;

    @ApiModelProperty(value = "返回错误码", required = true)
    private String returnCode;

    @ApiModelProperty(value = "返回错误信息", required = true)
    private String returnMessage;

    @ApiModelProperty(value = "返回结果", required = true)
    private String returnResult;

    @ApiModelProperty(value = "类名称", required = true)
    private String className;

    @ApiModelProperty(value = "方法名", required = true)
    private String methodName;

    @ApiModelProperty(value = "操作系统", required = true)
    private String os;

    @ApiModelProperty(value = "浏览器", required = true)
    private String browser;


    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    @Override
    public String toString() {
        return "SecurityOperationLogVO{" + "requestParam='" + requestParam + '\'' + ", returnCode='" + returnCode + '\'' + ", returnMessage='" + returnMessage + '\'' + ", returnResult='" + returnResult + '\'' + ", className='" + className + '\'' + ", methodName='" + methodName + '\'' + ", os='" + os + '\'' + ", browser='" + browser + '\'' + '}';
    }
}

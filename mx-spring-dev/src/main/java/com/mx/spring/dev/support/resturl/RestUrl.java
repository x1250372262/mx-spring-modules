package com.mx.spring.dev.support.resturl;

/**
 * @Author: mengxiang.
 * @create: 2022-03-25 20:54
 * @Description: restUrl对象
 */
public class RestUrl {

    /**
     * 请求url
     */
    private String requestUrl;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * java方法信息
     */
    private String javaMethodInfo;

    /**
     * 返回类型
     */
    private String returnType;

    public RestUrl() {
    }

    public RestUrl(String requestUrl, String requestMethod, String javaMethodInfo, String returnType) {
        this.requestUrl = requestUrl;
        this.requestMethod = requestMethod;
        this.javaMethodInfo = javaMethodInfo;
        this.returnType = returnType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getJavaMethodInfo() {
        return javaMethodInfo;
    }

    public void setJavaMethodInfo(String javaMethodInfo) {
        this.javaMethodInfo = javaMethodInfo;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}

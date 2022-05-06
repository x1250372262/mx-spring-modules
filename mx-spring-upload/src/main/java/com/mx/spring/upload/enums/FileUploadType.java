package com.mx.spring.upload.enums;

/**
 * @Author: mengxiang.
 * @create: 2022-04-21 13:20
 * @Description:
 */
public enum FileUploadType {

    /**
     * 文件上传类型
     */

    //默认
    DEFAULT("default"),
    //wang富文本
    WANG("wang"),
    //百度富文本
    BAIDU("baidu");

    private final String value;

    FileUploadType(String value) {
        this.value = value;
    }

    public static FileUploadType valueTo(String value) {
        switch (value) {
            case "default":
                return DEFAULT;
            case "wang":
                return WANG;
            case "baidu":
                return BAIDU;
            default:
                return DEFAULT;
        }
    }

    public String value() {
        return this.value;
    }
}

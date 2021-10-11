package com.mx.spring.dev.code;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
public enum C {

    /**
     * 通用错误码
     */
    SUCCESS(0, "操作成功"),
    ERROR(50000, "操作失败"),
    NO_DATA(50001, "数据不存在"),
    FIELDS_EXISTS(50002, "%s已存在"),
    VERSION_NOT_SAME(50003, "数据版本不一致"),
    NOT_LOGIN(50004, "用户未授权登录或会话已过期，请重新登录"),
    NOT_PERMISSION(50005, "没有访问的权限"),
    UPLOAD_CONTENT_TYPE_NOT_ALLOW(50006, "不允许的contentType"),
    UPLOAD_SIZE_ERROR(50007, "文件大小不能超过{}M"),
    UPLOAD_FILE_STORAGE_PATH_ERROR(50008, "文件保存路径未配置"),
    UPLOAD_BASE_URL_ERROR(50009, "请求域名未配置"),
    SYSTEM_ERROR(-50, "系统繁忙"),
    INVALID_PARAMETER(-1, "参数验证无效"),
    NOT_REPEAT_REQUEST(-2, "{}秒内请勿重复请求");

    private final int code;
    private final String msg;

    C(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

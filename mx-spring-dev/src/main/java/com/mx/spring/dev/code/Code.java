package com.mx.spring.dev.code;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description: 错误码枚举
 */
public enum Code {

    /**
     * 通用错误码
     */
    SUCCESS("00000", "操作成功"),
    ERROR("M0000", "操作失败"),
    NO_DATA("M0001", "数据不存在"),
    FIELDS_EXISTS("M0002", "{}已存在"),
    VERSION_NOT_SAME("M0003", "数据版本不一致"),
    NOT_LOGIN("M0004", "用户未授权登录或会话已过期，请重新登录"),
    NOT_PERMISSION("M0005", "没有访问的权限"),
    SYSTEM_ERROR("M0006", "系统繁忙"),
    INVALID_PARAMETER("M0007", "参数验证无效"),
    NOT_REPEAT_REQUEST("M0008", "{}秒内请勿重复请求"),
    NOT_SUPPORT_REQUEST("M0009", "不支持{}请求");

    private final String code;
    private final String msg;

    Code(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

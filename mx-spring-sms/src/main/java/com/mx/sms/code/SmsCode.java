package com.mx.sms.code;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
public enum SmsCode {

    /**
     * 发送短信错误码
     */
    SMS_MOBILE_LIST_NOT_EMPTY("SMS00", "发送手机号不能为空"),
    SMS_CHECK_ERROR("SMS01", "检查错误"),
    SMS_HANDLER_NOT_EXIST("SMS02", "处理类不能为空"),
    SMS_PROVIDER_NOT_EXIST("SMS03", "短信发送提供者不能为空");

    private final String code;
    private final String msg;

    SmsCode(String code, String msg) {
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

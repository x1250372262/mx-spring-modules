package com.mx.spring.security.code;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
public enum SecurityCode {

    /**
     * 安全错误码 70开头
     */
    SECURITY_LOGIN_USER_NAME_NOT_EXIST(70000, "用户名不存在"),
    SECURITY_LOGIN_USER_NAME_OR_PASSWORD_ERROR(70001, "用户名或密码错误"),
    SECURITY_LOGIN_USER_LOCKED(70002, "用户被锁住，请在{}后重试"),
    SECURITY_ADMIN_PASSWORD_NOT_SAME(70003, "两次密码输入不一致"),
    SECURITY_ADMIN_OLD_PASSWORD_ERROR(70004, "原密码错误"),
    SECURITY_ADMIN_NEW_PASSWORD_NOT_SAME_OLD_PASSWORD(70005, "新密码不能和原密码相同"),
    SECURITY_ADMIN_ROLE_EXISTS(70006, "该角色已经添加过了"),
    SECURITY_MENU_HAS_CHILD_NOT_DELETE(70007, "有子菜单不能删除!"),
    SECURITY_MENU_ROLE_EXISTS(70008, "该角色已经添加过了!");

    private final int code;
    private final String msg;

    SecurityCode(int code, String msg) {
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

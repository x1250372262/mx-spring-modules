package com.mx.spring.security.enums;

public enum MenuType
{
    /**
     * 默认
     */
    DEFAULT(0),

    /**
     * 公开
     */
    PUBLIC(1),

    /**
     * 最高权限可看
     */
    FOUNDER(2);

    private final int value;

    MenuType(int value) {
        this.value = value;
    }

    public static MenuType valueTo(int value) {
        switch (value) {
            case 0:
                return DEFAULT;
            case 1:
                return PUBLIC;
            case 2:
                return FOUNDER;
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }

}

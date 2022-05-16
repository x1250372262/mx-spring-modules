package com.mx.spring.dev.util;

import java.util.regex.Matcher;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description: 字符串创建工具
 */
public final class StrBuildUtil {


    private final static String LEFT = "\\{";
    private final static String RIGHT = "}";
    private String str;

    public static StrBuildUtil create(String str) {
        return new StrBuildUtil(str);
    }

    private StrBuildUtil(String str) {
        this.str = str;
    }

    public String get() {
        return str;
    }

    public StrBuildUtil set(String key, String value) {
        if (value != null) {
            String keyPattern = LEFT + key + RIGHT;
            this.str = this.str.replaceAll(keyPattern, Matcher.quoteReplacement(value));
        }
        return this;
    }
}

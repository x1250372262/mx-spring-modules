package com.mx.spring.dev.util;


import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
public class TimeHelper {

    public static final long SECOND = 1000L;

    public static final long MINUTE = SECOND * 60;

    public static final long HOUR = MINUTE * 60;

    public static final long DAY = HOUR * 24;

    public static final long WEEK = DAY * 7;

    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";


    private TimeHelper() {

    }

    /**
     * 本月第一天00:00:00
     *
     * @return yyyy-MM-dd
     */
    public static long monthStart() {
        return DateUtil.beginOfMonth(new Date()).getTime();
    }

    /**
     * 本月末23:59:59
     *
     * @return
     */
    public static long monthEnd() {
        return DateUtil.endOfMonth(new Date()).getTime();
    }


    /**
     * 本年第一天00:00:00
     *
     * @return yyyy-MM-dd
     */
    public static long yearStart() {
        return DateUtil.beginOfYear(new Date()).getTime();
    }

    /**
     * 本年末23:59:59
     *
     * @return
     */
    public static long yearEnd() {
        return DateUtil.endOfYear(new Date()).getTime();
    }

    /**
     * 本周第一天00:00:00
     *
     * @return yyyy-MM-dd
     */
    public static long weekStart() {
        return DateUtil.beginOfWeek(new Date()).getTime();
    }

    /**
     * 本周末23:59:59
     *
     * @return
     */
    public static long weekEnd() {
        return DateUtil.endOfWeek(new Date()).getTime();
    }

    public static String formatTime(long time, String format) {
        return DateUtil.format(new Date(time), StringUtils.defaultIfBlank(format, YYYY_MM_DD_HH_MM_SS));
    }

    public static String formatTime(long time) {
        return formatTime(time, null);
    }

}

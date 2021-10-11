package com.mx.spring.dev.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
public class WebUtils {


    /**
     * 是否ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        String requestHeader = request.getHeader("X-Requested-With");
        return StringUtils.isNotBlank(requestHeader) && "XMLHttpRequest".equalsIgnoreCase(requestHeader);
    }

    /**
     * 构建baseurl
     *
     * @return
     */
    public static String baseUrl() {
        HttpServletRequest request = request();
        //协议 :// ip地址 ：端口号 /
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest request() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Objects.requireNonNull(servletRequestAttributes).getRequest();
    }

    /**
     * 获取response
     *
     * @return
     */
    public static HttpServletResponse response() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Objects.requireNonNull(servletRequestAttributes).getResponse();
    }

    /**
     * 获取session
     *
     * @return
     */
    public static HttpSession session() {
        return request().getSession();
    }
}

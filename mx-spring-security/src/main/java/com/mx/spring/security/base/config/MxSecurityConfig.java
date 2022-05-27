package com.mx.spring.security.base.config;

import cn.hutool.core.util.ObjectUtil;
import com.mx.spring.security.handler.ILoginHandler;
import com.mx.spring.security.handler.IUserHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: mengxiang.
 * @create: 2021-09-07 16:36
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "mx.security")
public class MxSecurityConfig {

    /**
     * 客户端名称
     */
    private String client;

    /**
     * loginHandler实现类
     */
    private String loginHandlerClass;

    /**
     * userHandler实现类
     */
    private String userHandlerClass;

    /**
     * 是否单独设置跨域
     */
    private Boolean cross;

    /**
     * 验证错误N次后锁定账户  默认不锁定  -1不锁定
     */
    private Integer errorCount;

    /**
     * 是否开启日志记录 默认false
     */
    private Boolean openLog;

    /**
     * 登录拦截排除的路径用|分割
     */
    private String excludePathPatterns;


    public String getClient() {
        return StringUtils.defaultIfBlank(client, "default");
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getLoginHandlerClass() {
        return StringUtils.defaultIfBlank(loginHandlerClass, ILoginHandler.DefaultLoginHandler.class.getName());
    }

    public void setLoginHandlerClass(String loginHandlerClass) {
        this.loginHandlerClass = loginHandlerClass;
    }

    public String getUserHandlerClass() {
        return StringUtils.defaultIfBlank(userHandlerClass, IUserHandler.DefaultUserHandler.class.getName());
    }

    public void setUserHandlerClass(String userHandlerClass) {
        this.userHandlerClass = userHandlerClass;
    }

    public Boolean isCross() {
        return !Objects.isNull(cross) && cross;
    }

    public void setCross(Boolean cross) {
        this.cross = cross;
    }

    public Integer getErrorCount() {
        return ObjectUtil.defaultIfNull(this.errorCount, 0);
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public Boolean isOpenLog() {
        return !Objects.isNull(openLog) && openLog;
    }

    public void setOpenLog(Boolean openLog) {
        this.openLog = openLog;
    }

    public String getExcludePathPatterns() {
        return excludePathPatterns;
    }

    public void setExcludePathPatterns(String excludePathPatterns) {
        this.excludePathPatterns = excludePathPatterns;
    }
}

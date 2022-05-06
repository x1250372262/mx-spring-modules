package com.mx.maven.conf;

import cn.hutool.setting.dialect.Props;
import com.mx.maven.util.PropUtils;
import freemarker.template.utility.NullArgumentException;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: mengxiang.
 * @create: 2021-09-30 09:21
 * @Description: 基本配置
 */
public class MxConfig {

    private String driverClassName;

    private String url;

    private String userName;

    private String password;

    public static MxConfig me() {
        return new MxConfig();
    }

    public MxConfig init(Props props) {
        this.driverClassName = props.getStr(PropUtils.DRIVER_CLASS_NAME);
        if (StringUtils.isBlank(this.driverClassName)) {
            throw new NullArgumentException(PropUtils.DRIVER_CLASS_NAME);
        }
        this.url = props.getStr(PropUtils.URL);
        if (StringUtils.isBlank(this.url)) {
            throw new NullArgumentException(PropUtils.URL);
        }
        this.userName = props.getStr(PropUtils.USER_NAME);
        if (StringUtils.isBlank(this.userName)) {
            throw new NullArgumentException(PropUtils.USER_NAME);
        }
        this.password = props.getStr(PropUtils.PASSWORD);
        if (StringUtils.isBlank(this.password)) {
            throw new NullArgumentException(PropUtils.PASSWORD);
        }
        return this;
    }

    public MxConfig() {

    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

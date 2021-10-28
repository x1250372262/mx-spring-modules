package com.mx.spring.security.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

@ApiModel(value = "登录成功返回信息", description = "登录成功返回信息")
public class LoginResult {

	@ApiModelProperty(value = "token名称")
	public String tokenName;

	@ApiModelProperty(value = "token值")
	public String tokenValue;

	@ApiModelProperty(value = "此token是否已经登录")
	public Boolean isLogin;

	@ApiModelProperty(value = "此token对应的LoginId，未登录时为null")
	public Object loginId;

	@ApiModelProperty(value = "账号类型")
	public String loginType;

	@ApiModelProperty(value = "token剩余有效期 (单位: 秒)")
	public long tokenTimeout;

	@ApiModelProperty(value = "User-Session剩余有效时间 (单位: 秒)")
	public long sessionTimeout;

	@ApiModelProperty(value = "Token-Session剩余有效时间 (单位: 秒)")
	public long tokenSessionTimeout;

	@ApiModelProperty(value = "token剩余无操作有效时间 (单位: 秒)")
	public long tokenActivityTimeout;

	@ApiModelProperty(value = "登录设备标识")
	public String loginDevice;

	@ApiModelProperty(value = "自定义数据")
	public String tag;

	@ApiModelProperty(value = "其他数据")
	private Map<String,Object> attrs = new HashMap<>();
	
	/**
	 * @return token名称 
	 */
	public String getTokenName() {
		return tokenName;
	}

	/**
	 * @param tokenName token名称 
	 */
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	/**
	 * @return token值 
	 */
	public String getTokenValue() {
		return tokenValue;
	}

	/**
	 * @param tokenValue token值 
	 */
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	/**
	 * @return 此token是否已经登录 
	 */
	public Boolean getIsLogin() {
		return isLogin;
	}

	/**
	 * @param isLogin 此token是否已经登录 
	 */
	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}

	/**
	 * @return 此token对应的LoginId，未登录时为null 
	 */
	public Object getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId 此token对应的LoginId，未登录时为null 
	 */
	public void setLoginId(Object loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return 账号类型
	 */
	public String getLoginType() {
		return loginType;
	}

	/**
	 * @param loginType 账号类型
	 */
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	/**
	 * @return token剩余有效期 (单位: 秒) 
	 */
	public long getTokenTimeout() {
		return tokenTimeout;
	}

	/**
	 * @param tokenTimeout token剩余有效期 (单位: 秒) 
	 */
	public void setTokenTimeout(long tokenTimeout) {
		this.tokenTimeout = tokenTimeout;
	}

	/**
	 * @return User-Session剩余有效时间 (单位: 秒) 
	 */
	public long getSessionTimeout() {
		return sessionTimeout;
	}

	/**
	 * @param sessionTimeout User-Session剩余有效时间 (单位: 秒) 
	 */
	public void setSessionTimeout(long sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	/**
	 * @return Token-Session剩余有效时间 (单位: 秒) 
	 */
	public long getTokenSessionTimeout() {
		return tokenSessionTimeout;
	}

	/**
	 * @param tokenSessionTimeout Token-Session剩余有效时间 (单位: 秒) 
	 */
	public void setTokenSessionTimeout(long tokenSessionTimeout) {
		this.tokenSessionTimeout = tokenSessionTimeout;
	}

	/**
	 * @return token剩余无操作有效时间 (单位: 秒)
	 */
	public long getTokenActivityTimeout() {
		return tokenActivityTimeout;
	}

	/**
	 * @param tokenActivityTimeout token剩余无操作有效时间 (单位: 秒)
	 */
	public void setTokenActivityTimeout(long tokenActivityTimeout) {
		this.tokenActivityTimeout = tokenActivityTimeout;
	}

	/**
	 * @return 登录设备标识 
	 */
	public String getLoginDevice() {
		return loginDevice;
	}

	/**
	 * @param loginDevice 登录设备标识 
	 */
	public void setLoginDevice(String loginDevice) {
		this.loginDevice = loginDevice;
	}

	/**
	 * @return 自定义数据
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag 自定义数据
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	public Map<String, Object> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, Object> attrs) {
		this.attrs = attrs;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "SaTokenInfo [tokenName=" + tokenName + ", tokenValue=" + tokenValue + ", isLogin=" + isLogin
				+ ", loginId=" + loginId + ", loginType=" + loginType + ", tokenTimeout=" + tokenTimeout
				+ ", sessionTimeout=" + sessionTimeout + ", tokenSessionTimeout=" + tokenSessionTimeout
				+ ", tokenActivityTimeout=" + tokenActivityTimeout + ", loginDevice=" + loginDevice + ", tag=" + tag
				+ "]";
	}
	
	

}

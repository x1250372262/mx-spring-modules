package com.mx.spring.dev.support.log.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.annotation.FieldInfo;
import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/22.
 * @Time: 15:14:05.
 * @Description: 2021/10/22 15:14:05 生成 OperationLog
 */
@TableName("mx_security_log")
public class SecurityLog implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "mx_security_log";

	/**
	* id
	*/
	@FieldInfo(comment = "id", nullable = false)
	private java.lang.String id;

	/**
	* 标题
	*/
	@FieldInfo(comment = "标题", nullable = false)
	private java.lang.String title;

	/**
	* 类型具体看配置
	*/
	@FieldInfo(comment = "类型具体看配置", nullable = false)
	private java.lang.String type;

	/**
	* 类型名称
	*/
	@FieldInfo(comment = "类型名称", nullable = false)
	private java.lang.String typeName;

	/**
	* 操作人
	*/
	@FieldInfo(comment = "操作人", nullable = false)
	private java.lang.String userId;

	/**
	* 操作人名称
	*/
	@FieldInfo(comment = "操作人名称", nullable = false)
	private java.lang.String userName;

	/**
	* 创建时间
	*/
	@FieldInfo(comment = "创建时间", nullable = false)
	private java.lang.Long createTime;

	/**
	* 请求路径
	*/
	@FieldInfo(comment = "请求路径", nullable = false)
	private java.lang.String requestUrl;

	/**
	* 请求参数
	*/
	@FieldInfo(comment = "请求参数")
	private java.lang.String requestParam;

	/**
	* 返回错误码
	*/
	@FieldInfo(comment = "返回错误码", nullable = false)
	private java.lang.String returnCode;

	/**
	* 返回错误信息
	*/
	@FieldInfo(comment = "返回错误信息", nullable = false)
	private java.lang.String returnMessage;

	/**
	* 返回结果
	*/
	@FieldInfo(comment = "返回结果", nullable = false)
	private java.lang.String returnResult;

	/**
	* 类名称
	*/
	@FieldInfo(comment = "类名称", nullable = false)
	private java.lang.String className;

	/**
	* 方法名
	*/
	@FieldInfo(comment = "方法名", nullable = false)
	private java.lang.String methodName;

	/**
	* ip地址
	*/
	@FieldInfo(comment = "ip地址", nullable = false)
	private java.lang.String ip;

	/**
	* 位置
	*/
	@FieldInfo(comment = "位置", nullable = false)
	private java.lang.String location;

	/**
	* 操作系统
	*/
	@FieldInfo(comment = "操作系统", nullable = false)
	private java.lang.String os;

	/**
	* 浏览器
	*/
	@FieldInfo(comment = "浏览器", nullable = false)
	private java.lang.String browser;


	/**
	* 初始化
	*/
	public static SecurityLog init() {
		return new SecurityLog();
	}

	/**
	 * 构造器
	 */
	public SecurityLog() {
	}

	/**
	 * 构造器
	 *	@param id
	 *	@param title
	 *	@param type
	 *	@param typeName
	 *	@param userId
	 *	@param userName
	 *	@param createTime
	 *	@param requestUrl
	 *	@param requestParam
	 *	@param returnCode
	 *	@param returnMessage
	 *	@param returnResult
	 *	@param className
	 *	@param methodName
	 *	@param ip
	 *	@param location
	 *	@param os
	 *	@param browser
	 */
	public SecurityLog(java.lang.String id, java.lang.String title, java.lang.String type, java.lang.String typeName, java.lang.String userId, java.lang.String userName, java.lang.Long createTime, java.lang.String requestUrl, java.lang.String requestParam, java.lang.String returnCode, java.lang.String returnMessage, java.lang.String returnResult, java.lang.String className, java.lang.String methodName, java.lang.String ip, java.lang.String location, java.lang.String os, java.lang.String browser) {
		this.id = id;
		this.title = title;
		this.type = type;
		this.typeName = typeName;
		this.userId = userId;
		this.userName = userName;
		this.createTime = createTime;
		this.requestUrl = requestUrl;
		this.requestParam = requestParam;
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
		this.returnResult = returnResult;
		this.className = className;
		this.methodName = methodName;
		this.ip = ip;
		this.location = location;
		this.os = os;
		this.browser = browser;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}


	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getTypeName() {
		return typeName;
	}

	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(java.lang.String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public java.lang.String getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(java.lang.String requestParam) {
		this.requestParam = requestParam;
	}

	public java.lang.String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(java.lang.String returnCode) {
		this.returnCode = returnCode;
	}

	public java.lang.String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(java.lang.String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public java.lang.String getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(java.lang.String returnResult) {
		this.returnResult = returnResult;
	}

	public java.lang.String getClassName() {
		return className;
	}

	public void setClassName(java.lang.String className) {
		this.className = className;
	}

	public java.lang.String getMethodName() {
		return methodName;
	}

	public void setMethodName(java.lang.String methodName) {
		this.methodName = methodName;
	}

	public java.lang.String getIp() {
		return ip;
	}

	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}

	public java.lang.String getLocation() {
		return location;
	}

	public void setLocation(java.lang.String location) {
		this.location = location;
	}

	public java.lang.String getOs() {
		return os;
	}

	public void setOs(java.lang.String os) {
		this.os = os;
	}

	public java.lang.String getBrowser() {
		return browser;
	}

	public void setBrowser(java.lang.String browser) {
		this.browser = browser;
	}




	public static OperationLogBuilder builder() {
		return new OperationLogBuilder();
	}

	public OperationLogBuilder bind() {
    	return new OperationLogBuilder(this);
    }

	public static class OperationLogBuilder {

		private final SecurityLog modelTarget;

		public OperationLogBuilder() {
			modelTarget = new SecurityLog();
		}

		public OperationLogBuilder(SecurityLog model) {
			modelTarget = model;
		}

		public SecurityLog build() {
			return modelTarget;
		}

		public java.lang.String id() {
			return modelTarget.getId();
		}

		public OperationLogBuilder id(java.lang.String id) {
			modelTarget.setId(id);
			return this;
		}

		public java.lang.String title() {
			return modelTarget.getTitle();
		}

		public OperationLogBuilder title(java.lang.String title) {
			modelTarget.setTitle(title);
			return this;
		}

		public java.lang.String type() {
			return modelTarget.getType();
		}

		public OperationLogBuilder type(java.lang.String type) {
			modelTarget.setType(type);
			return this;
		}

		public java.lang.String typeName() {
			return modelTarget.getTypeName();
		}

		public OperationLogBuilder typeName(java.lang.String typeName) {
			modelTarget.setTypeName(typeName);
			return this;
		}

		public java.lang.String userId() {
			return modelTarget.getUserId();
		}

		public OperationLogBuilder userId(java.lang.String userId) {
			modelTarget.setUserId(userId);
			return this;
		}

		public java.lang.String userName() {
			return modelTarget.getUserName();
		}

		public OperationLogBuilder userName(java.lang.String userName) {
			modelTarget.setUserName(userName);
			return this;
		}

		public java.lang.Long createTime() {
			return modelTarget.getCreateTime();
		}

		public OperationLogBuilder createTime(java.lang.Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public java.lang.String requestUrl() {
			return modelTarget.getRequestUrl();
		}

		public OperationLogBuilder requestUrl(java.lang.String requestUrl) {
			modelTarget.setRequestUrl(requestUrl);
			return this;
		}

		public java.lang.String requestParam() {
			return modelTarget.getRequestParam();
		}

		public OperationLogBuilder requestParam(java.lang.String requestParam) {
			modelTarget.setRequestParam(requestParam);
			return this;
		}

		public java.lang.String returnCode() {
			return modelTarget.getReturnCode();
		}

		public OperationLogBuilder returnCode(java.lang.String returnCode) {
			modelTarget.setReturnCode(returnCode);
			return this;
		}

		public java.lang.String returnMessage() {
			return modelTarget.getReturnMessage();
		}

		public OperationLogBuilder returnMessage(java.lang.String returnMessage) {
			modelTarget.setReturnMessage(returnMessage);
			return this;
		}

		public java.lang.String returnResult() {
			return modelTarget.getReturnResult();
		}

		public OperationLogBuilder returnResult(java.lang.String returnResult) {
			modelTarget.setReturnResult(returnResult);
			return this;
		}

		public java.lang.String className() {
			return modelTarget.getClassName();
		}

		public OperationLogBuilder className(java.lang.String className) {
			modelTarget.setClassName(className);
			return this;
		}

		public java.lang.String methodName() {
			return modelTarget.getMethodName();
		}

		public OperationLogBuilder methodName(java.lang.String methodName) {
			modelTarget.setMethodName(methodName);
			return this;
		}

		public java.lang.String ip() {
			return modelTarget.getIp();
		}

		public OperationLogBuilder ip(java.lang.String ip) {
			modelTarget.setIp(ip);
			return this;
		}

		public java.lang.String location() {
			return modelTarget.getLocation();
		}

		public OperationLogBuilder location(java.lang.String location) {
			modelTarget.setLocation(location);
			return this;
		}

		public java.lang.String os() {
			return modelTarget.getOs();
		}

		public OperationLogBuilder os(java.lang.String os) {
			modelTarget.setOs(os);
			return this;
		}

		public java.lang.String browser() {
			return modelTarget.getBrowser();
		}

		public OperationLogBuilder browser(java.lang.String browser) {
			modelTarget.setBrowser(browser);
			return this;
		}

	}

	/**
	 * OperationLog 字段常量表
	 */
	public static class FIELDS {
		public static final String ID = "id";
		public static final String TITLE = "title";
		public static final String TYPE = "type";
		public static final String TYPE_NAME = "type_name";
		public static final String USER_ID = "user_id";
		public static final String USER_NAME = "user_name";
		public static final String CREATE_TIME = "create_time";
		public static final String REQUEST_URL = "request_url";
		public static final String REQUEST_PARAM = "request_param";
		public static final String RETURN_CODE = "return_code";
		public static final String RETURN_MESSAGE = "return_message";
		public static final String RETURN_RESULT = "return_result";
		public static final String CLASS_NAME = "class_name";
		public static final String METHOD_NAME = "method_name";
		public static final String IP = "ip";
		public static final String LOCATION = "location";
		public static final String OS = "os";
		public static final String BROWSER = "browser";
	}


}

package com.mx.spring.log.model;

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
	private String id;

	/**
	* 标题
	*/
	@FieldInfo(comment = "标题", nullable = false)
	private String title;

	/**
	* 类型具体看配置
	*/
	@FieldInfo(comment = "类型具体看配置", nullable = false)
	private String type;

	/**
	* 类型名称
	*/
	@FieldInfo(comment = "类型名称", nullable = false)
	private String typeName;

	/**
	* 操作人
	*/
	@FieldInfo(comment = "操作人", nullable = false)
	private String userId;

	/**
	* 操作人名称
	*/
	@FieldInfo(comment = "操作人名称", nullable = false)
	private String userName;

	/**
	* 创建时间
	*/
	@FieldInfo(comment = "创建时间", nullable = false)
	private Long createTime;

	/**
	* 请求路径
	*/
	@FieldInfo(comment = "请求路径", nullable = false)
	private String requestUrl;

	/**
	* 请求参数
	*/
	@FieldInfo(comment = "请求参数")
	private String requestParam;

	/**
	* 返回错误码
	*/
	@FieldInfo(comment = "返回错误码", nullable = false)
	private String returnCode;

	/**
	* 返回错误信息
	*/
	@FieldInfo(comment = "返回错误信息", nullable = false)
	private String returnMessage;

	/**
	* 返回结果
	*/
	@FieldInfo(comment = "返回结果", nullable = false)
	private String returnResult;

	/**
	* 类名称
	*/
	@FieldInfo(comment = "类名称", nullable = false)
	private String className;

	/**
	* 方法名
	*/
	@FieldInfo(comment = "方法名", nullable = false)
	private String methodName;

	/**
	* ip地址
	*/
	@FieldInfo(comment = "ip地址", nullable = false)
	private String ip;

	/**
	* 位置
	*/
	@FieldInfo(comment = "位置", nullable = false)
	private String location;

	/**
	* 操作系统
	*/
	@FieldInfo(comment = "操作系统", nullable = false)
	private String os;

	/**
	* 浏览器
	*/
	@FieldInfo(comment = "浏览器", nullable = false)
	private String browser;


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
	public SecurityLog(String id, String title, String type, String typeName, String userId, String userName, Long createTime, String requestUrl, String requestParam, String returnCode, String returnMessage, String returnResult, String className, String methodName, String ip, String location, String os, String browser) {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
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

		public String id() {
			return modelTarget.getId();
		}

		public OperationLogBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String title() {
			return modelTarget.getTitle();
		}

		public OperationLogBuilder title(String title) {
			modelTarget.setTitle(title);
			return this;
		}

		public String type() {
			return modelTarget.getType();
		}

		public OperationLogBuilder type(String type) {
			modelTarget.setType(type);
			return this;
		}

		public String typeName() {
			return modelTarget.getTypeName();
		}

		public OperationLogBuilder typeName(String typeName) {
			modelTarget.setTypeName(typeName);
			return this;
		}

		public String userId() {
			return modelTarget.getUserId();
		}

		public OperationLogBuilder userId(String userId) {
			modelTarget.setUserId(userId);
			return this;
		}

		public String userName() {
			return modelTarget.getUserName();
		}

		public OperationLogBuilder userName(String userName) {
			modelTarget.setUserName(userName);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public OperationLogBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public String requestUrl() {
			return modelTarget.getRequestUrl();
		}

		public OperationLogBuilder requestUrl(String requestUrl) {
			modelTarget.setRequestUrl(requestUrl);
			return this;
		}

		public String requestParam() {
			return modelTarget.getRequestParam();
		}

		public OperationLogBuilder requestParam(String requestParam) {
			modelTarget.setRequestParam(requestParam);
			return this;
		}

		public String returnCode() {
			return modelTarget.getReturnCode();
		}

		public OperationLogBuilder returnCode(String returnCode) {
			modelTarget.setReturnCode(returnCode);
			return this;
		}

		public String returnMessage() {
			return modelTarget.getReturnMessage();
		}

		public OperationLogBuilder returnMessage(String returnMessage) {
			modelTarget.setReturnMessage(returnMessage);
			return this;
		}

		public String returnResult() {
			return modelTarget.getReturnResult();
		}

		public OperationLogBuilder returnResult(String returnResult) {
			modelTarget.setReturnResult(returnResult);
			return this;
		}

		public String className() {
			return modelTarget.getClassName();
		}

		public OperationLogBuilder className(String className) {
			modelTarget.setClassName(className);
			return this;
		}

		public String methodName() {
			return modelTarget.getMethodName();
		}

		public OperationLogBuilder methodName(String methodName) {
			modelTarget.setMethodName(methodName);
			return this;
		}

		public String ip() {
			return modelTarget.getIp();
		}

		public OperationLogBuilder ip(String ip) {
			modelTarget.setIp(ip);
			return this;
		}

		public String location() {
			return modelTarget.getLocation();
		}

		public OperationLogBuilder location(String location) {
			modelTarget.setLocation(location);
			return this;
		}

		public String os() {
			return modelTarget.getOs();
		}

		public OperationLogBuilder os(String os) {
			modelTarget.setOs(os);
			return this;
		}

		public String browser() {
			return modelTarget.getBrowser();
		}

		public OperationLogBuilder browser(String browser) {
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

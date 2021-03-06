package com.mx.spring.security.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.support.generator.annotation.FieldInfo;

import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2022/05/05.
 * @Time: 14:45:23.
 * @Description: 2022/05/05 14:45:23 生成 SecurityOperationLog
 */
@TableName(SecurityOperationLog.TABLE_NAME)
public class SecurityOperationLog implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "mx_security_operation_log";

	/**
	* id
	*/
	@TableId(value = Fields.ID, type = IdType.INPUT)
	@FieldInfo(comment = "id", nullable = false)
	private String id;

	/**
	* 客户端
	*/
	@TableField(value = Fields.CLIENT)
	@FieldInfo(comment = "客户端", nullable = false)
	private String client;

	/**
	* 标题
	*/
	@TableField(value = Fields.TITLE)
	@FieldInfo(comment = "标题", nullable = false)
	private String title;

	/**
	* 类型具体看配置
	*/
	@TableField(value = Fields.TYPE)
	@FieldInfo(comment = "类型具体看配置", nullable = false)
	private String type;

	/**
	* 类型名称
	*/
	@TableField(value = Fields.TYPE_NAME)
	@FieldInfo(comment = "类型名称", nullable = false)
	private String typeName;

	/**
	* 操作人
	*/
	@TableField(value = Fields.USER_ID)
	@FieldInfo(comment = "操作人", nullable = false)
	private String userId;

	/**
	* 操作人名称
	*/
	@TableField(value = Fields.USER_NAME)
	@FieldInfo(comment = "操作人名称", nullable = false)
	private String userName;

	/**
	* 创建时间
	*/
	@TableField(value = Fields.CREATE_TIME)
	@FieldInfo(comment = "创建时间", nullable = false)
	private Long createTime;

	/**
	* 请求路径
	*/
	@TableField(value = Fields.REQUEST_URL)
	@FieldInfo(comment = "请求路径", nullable = false)
	private String requestUrl;

	/**
	* 请求参数
	*/
	@TableField(value = Fields.REQUEST_PARAM)
	@FieldInfo(comment = "请求参数")
	private String requestParam;

	/**
	* 返回错误码
	*/
	@TableField(value = Fields.RETURN_CODE)
	@FieldInfo(comment = "返回错误码", nullable = false)
	private String returnCode;

	/**
	* 返回错误信息
	*/
	@TableField(value = Fields.RETURN_MESSAGE)
	@FieldInfo(comment = "返回错误信息", nullable = false)
	private String returnMessage;

	/**
	* 返回结果
	*/
	@TableField(value = Fields.RETURN_RESULT)
	@FieldInfo(comment = "返回结果", nullable = false)
	private String returnResult;

	/**
	* 类名称
	*/
	@TableField(value = Fields.CLASS_NAME)
	@FieldInfo(comment = "类名称", nullable = false)
	private String className;

	/**
	* 方法名
	*/
	@TableField(value = Fields.METHOD_NAME)
	@FieldInfo(comment = "方法名", nullable = false)
	private String methodName;

	/**
	* ip地址
	*/
	@TableField(value = Fields.IP)
	@FieldInfo(comment = "ip地址", nullable = false)
	private String ip;

	/**
	* 位置
	*/
	@TableField(value = Fields.LOCATION)
	@FieldInfo(comment = "位置", nullable = false)
	private String location;

	/**
	* 操作系统
	*/
	@TableField(value = Fields.OS)
	@FieldInfo(comment = "操作系统", nullable = false)
	private String os;

	/**
	* 浏览器
	*/
	@TableField(value = Fields.BROWSER)
	@FieldInfo(comment = "浏览器", nullable = false)
	private String browser;


	/**
	* 初始化
	*/
	public static SecurityOperationLog init() {
		return new SecurityOperationLog();
	}

	/**
	 * 构造器
	 */
	public SecurityOperationLog() {
	}

	/**
	 * 构造器
	 *	@param id
	 *	@param client
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
	public SecurityOperationLog(String id, String client, String title, String type, String typeName, String userId, String userName, Long createTime, String requestUrl, String requestParam, String returnCode, String returnMessage, String returnResult, String className, String methodName, String ip, String location, String os, String browser) {
		this.id = id;
		this.client = client;
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


	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
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




	public static SecurityOperationLogBuilder builder() {
		return new SecurityOperationLogBuilder();
	}

	public SecurityOperationLogBuilder bind() {
    	return new SecurityOperationLogBuilder(this);
    }

	public static class SecurityOperationLogBuilder {

		private final SecurityOperationLog modelTarget;

		public SecurityOperationLogBuilder() {
			modelTarget = new SecurityOperationLog();
		}

		public SecurityOperationLogBuilder(SecurityOperationLog model) {
			modelTarget = model;
		}

		public SecurityOperationLog build() {
			return modelTarget;
		}

		public String id() {
			return modelTarget.getId();
		}

		public SecurityOperationLogBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String client() {
			return modelTarget.getClient();
		}

		public SecurityOperationLogBuilder client(String client) {
			modelTarget.setClient(client);
			return this;
		}

		public String title() {
			return modelTarget.getTitle();
		}

		public SecurityOperationLogBuilder title(String title) {
			modelTarget.setTitle(title);
			return this;
		}

		public String type() {
			return modelTarget.getType();
		}

		public SecurityOperationLogBuilder type(String type) {
			modelTarget.setType(type);
			return this;
		}

		public String typeName() {
			return modelTarget.getTypeName();
		}

		public SecurityOperationLogBuilder typeName(String typeName) {
			modelTarget.setTypeName(typeName);
			return this;
		}

		public String userId() {
			return modelTarget.getUserId();
		}

		public SecurityOperationLogBuilder userId(String userId) {
			modelTarget.setUserId(userId);
			return this;
		}

		public String userName() {
			return modelTarget.getUserName();
		}

		public SecurityOperationLogBuilder userName(String userName) {
			modelTarget.setUserName(userName);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public SecurityOperationLogBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public String requestUrl() {
			return modelTarget.getRequestUrl();
		}

		public SecurityOperationLogBuilder requestUrl(String requestUrl) {
			modelTarget.setRequestUrl(requestUrl);
			return this;
		}

		public String requestParam() {
			return modelTarget.getRequestParam();
		}

		public SecurityOperationLogBuilder requestParam(String requestParam) {
			modelTarget.setRequestParam(requestParam);
			return this;
		}

		public String returnCode() {
			return modelTarget.getReturnCode();
		}

		public SecurityOperationLogBuilder returnCode(String returnCode) {
			modelTarget.setReturnCode(returnCode);
			return this;
		}

		public String returnMessage() {
			return modelTarget.getReturnMessage();
		}

		public SecurityOperationLogBuilder returnMessage(String returnMessage) {
			modelTarget.setReturnMessage(returnMessage);
			return this;
		}

		public String returnResult() {
			return modelTarget.getReturnResult();
		}

		public SecurityOperationLogBuilder returnResult(String returnResult) {
			modelTarget.setReturnResult(returnResult);
			return this;
		}

		public String className() {
			return modelTarget.getClassName();
		}

		public SecurityOperationLogBuilder className(String className) {
			modelTarget.setClassName(className);
			return this;
		}

		public String methodName() {
			return modelTarget.getMethodName();
		}

		public SecurityOperationLogBuilder methodName(String methodName) {
			modelTarget.setMethodName(methodName);
			return this;
		}

		public String ip() {
			return modelTarget.getIp();
		}

		public SecurityOperationLogBuilder ip(String ip) {
			modelTarget.setIp(ip);
			return this;
		}

		public String location() {
			return modelTarget.getLocation();
		}

		public SecurityOperationLogBuilder location(String location) {
			modelTarget.setLocation(location);
			return this;
		}

		public String os() {
			return modelTarget.getOs();
		}

		public SecurityOperationLogBuilder os(String os) {
			modelTarget.setOs(os);
			return this;
		}

		public String browser() {
			return modelTarget.getBrowser();
		}

		public SecurityOperationLogBuilder browser(String browser) {
			modelTarget.setBrowser(browser);
			return this;
		}

	}

	/**
	 * SecurityOperationLog 字段常量表
	 */
	public static class Fields {
		public static final String ID = "id";
		public static final String CLIENT = "client";
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

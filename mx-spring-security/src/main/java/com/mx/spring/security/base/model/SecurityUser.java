package com.mx.spring.security.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.support.generator.annotation.FieldInfo;

import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/22.
 * @Time: 15:14:05.
 * @Description: 2021/10/22 15:14:05 生成 SecurityUser
 */
@TableName("mx_security_user")
public class SecurityUser implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "mx_security_user";

	/**
	* id
	*/
	@FieldInfo(comment = "id", nullable = false)
	private String id;

	/**
	 * client
	 */
	@FieldInfo(comment = "client", nullable = false)
	private String client;

	/**
	* 用户名
	*/
	@FieldInfo(comment = "用户名", nullable = false)
	private String userName;

	/**
	* 真实姓名
	*/
	@FieldInfo(comment = "真实姓名")
	private String realName;

	/**
	* 头像
	*/
	@FieldInfo(comment = "头像")
	private String photoUri;

	/**
	* 密码
	*/
	@FieldInfo(comment = "密码", nullable = false)
	private String password;

	/**
	* 手机号
	*/
	@FieldInfo(comment = "手机号")
	private String mobile;

	/**
	* 性别
	*/
	@FieldInfo(comment = "性别")
	private Integer gender;

	/**
	* 创建人
	*/
	@FieldInfo(comment = "创建人", nullable = false)
	private String createUser;

	/**
	* 创建时间
	*/
	@FieldInfo(comment = "创建时间", nullable = false)
	private Long createTime;

	/**
	* 最后修改时间
	*/
	@FieldInfo(comment = "最后修改时间", nullable = false)
	private Long lastModifyTime;

	/**
	* 最后修改人
	*/
	@FieldInfo(comment = "最后修改人", nullable = false)
	private String lastModifyUser;

	/**
	* 密码加密字符串
	*/
	@FieldInfo(comment = "密码加密字符串", nullable = false)
	private String salt;

	/**
	* 禁用状态
	*/
	@FieldInfo(comment = "禁用状态", nullable = false)
	private Integer disableStatus;

	/**
	* 是否是总管理员
	*/
	@FieldInfo(comment = "是否是总管理员", nullable = false)
	private Integer founder;

	/**
	* 登录错误次数
	*/
	@FieldInfo(comment = "登录错误次数")
	private Integer loginErrorCount;

	/**
	* 锁定状态
	*/
	@FieldInfo(comment = "锁定状态")
	private Integer loginLockStatus;

	/**
	* 锁定开始时间
	*/
	@FieldInfo(comment = "锁定开始时间")
	private Long loginLockStartTime;

	/**
	* 锁定结束时间
	*/
	@FieldInfo(comment = "锁定结束时间")
	private Long loginLockEndTime;


	/**
	* 初始化
	*/
	public static SecurityUser init() {
		return new SecurityUser();
	}

	/**
	 * 构造器
	 */
	public SecurityUser() {
	}

	/**
	 * 构造器
	 *	@param id
	 *	@param client
	 *	@param userName
	 *	@param realName
	 *	@param photoUri
	 *	@param password
	 *	@param mobile
	 *	@param gender
	 *	@param createUser
	 *	@param createTime
	 *	@param lastModifyTime
	 *	@param lastModifyUser
	 *	@param salt
	 *	@param disableStatus
	 *	@param founder
	 *	@param loginErrorCount
	 *	@param loginLockStatus
	 *	@param loginLockStartTime
	 *	@param loginLockEndTime
	 */
	public SecurityUser(String id, String client, String userName, String realName, String photoUri, String password, String mobile, Integer gender, String createUser, Long createTime, Long lastModifyTime, String lastModifyUser, String salt, Integer disableStatus, Integer founder, Integer loginErrorCount, Integer loginLockStatus, Long loginLockStartTime, Long loginLockEndTime) {
		this.id = id;
		this.client = client;
		this.userName = userName;
		this.realName = realName;
		this.photoUri = photoUri;
		this.password = password;
		this.mobile = mobile;
		this.gender = gender;
		this.createUser = createUser;
		this.createTime = createTime;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyUser = lastModifyUser;
		this.salt = salt;
		this.disableStatus = disableStatus;
		this.founder = founder;
		this.loginErrorCount = loginErrorCount;
		this.loginLockStatus = loginLockStatus;
		this.loginLockStartTime = loginLockStartTime;
		this.loginLockEndTime = loginLockEndTime;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhotoUri() {
		return photoUri;
	}

	public void setPhotoUri(String photoUri) {
		this.photoUri = photoUri;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getDisableStatus() {
		return disableStatus;
	}

	public void setDisableStatus(Integer disableStatus) {
		this.disableStatus = disableStatus;
	}

	public Integer getFounder() {
		return founder;
	}

	public void setFounder(Integer founder) {
		this.founder = founder;
	}

	public Integer getLoginErrorCount() {
		return loginErrorCount;
	}

	public void setLoginErrorCount(Integer loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}

	public Integer getLoginLockStatus() {
		return loginLockStatus;
	}

	public void setLoginLockStatus(Integer loginLockStatus) {
		this.loginLockStatus = loginLockStatus;
	}

	public Long getLoginLockStartTime() {
		return loginLockStartTime;
	}

	public void setLoginLockStartTime(Long loginLockStartTime) {
		this.loginLockStartTime = loginLockStartTime;
	}

	public Long getLoginLockEndTime() {
		return loginLockEndTime;
	}

	public void setLoginLockEndTime(Long loginLockEndTime) {
		this.loginLockEndTime = loginLockEndTime;
	}




	public static SecurityUserBuilder builder() {
		return new SecurityUserBuilder();
	}

	public SecurityUserBuilder bind() {
    	return new SecurityUserBuilder(this);
    }

	public static class SecurityUserBuilder {

		private final SecurityUser modelTarget;

		public SecurityUserBuilder() {
			modelTarget = new SecurityUser();
		}

		public SecurityUserBuilder(SecurityUser model) {
			modelTarget = model;
		}

		public SecurityUser build() {
			return modelTarget;
		}

		public String id() {
			return modelTarget.getId();
		}

		public SecurityUserBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String client() {
			return modelTarget.getClient();
		}

		public SecurityUserBuilder client(String client) {
			modelTarget.setClient(client);
			return this;
		}

		public String userName() {
			return modelTarget.getUserName();
		}

		public SecurityUserBuilder userName(String userName) {
			modelTarget.setUserName(userName);
			return this;
		}

		public String realName() {
			return modelTarget.getRealName();
		}

		public SecurityUserBuilder realName(String realName) {
			modelTarget.setRealName(realName);
			return this;
		}

		public String photoUri() {
			return modelTarget.getPhotoUri();
		}

		public SecurityUserBuilder photoUri(String photoUri) {
			modelTarget.setPhotoUri(photoUri);
			return this;
		}

		public String password() {
			return modelTarget.getPassword();
		}

		public SecurityUserBuilder password(String password) {
			modelTarget.setPassword(password);
			return this;
		}

		public String mobile() {
			return modelTarget.getMobile();
		}

		public SecurityUserBuilder mobile(String mobile) {
			modelTarget.setMobile(mobile);
			return this;
		}

		public Integer gender() {
			return modelTarget.getGender();
		}

		public SecurityUserBuilder gender(Integer gender) {
			modelTarget.setGender(gender);
			return this;
		}

		public String createUser() {
			return modelTarget.getCreateUser();
		}

		public SecurityUserBuilder createUser(String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public SecurityUserBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public Long lastModifyTime() {
			return modelTarget.getLastModifyTime();
		}

		public SecurityUserBuilder lastModifyTime(Long lastModifyTime) {
			modelTarget.setLastModifyTime(lastModifyTime);
			return this;
		}

		public String lastModifyUser() {
			return modelTarget.getLastModifyUser();
		}

		public SecurityUserBuilder lastModifyUser(String lastModifyUser) {
			modelTarget.setLastModifyUser(lastModifyUser);
			return this;
		}

		public String salt() {
			return modelTarget.getSalt();
		}

		public SecurityUserBuilder salt(String salt) {
			modelTarget.setSalt(salt);
			return this;
		}

		public Integer disableStatus() {
			return modelTarget.getDisableStatus();
		}

		public SecurityUserBuilder disableStatus(Integer disableStatus) {
			modelTarget.setDisableStatus(disableStatus);
			return this;
		}

		public Integer founder() {
			return modelTarget.getFounder();
		}

		public SecurityUserBuilder founder(Integer founder) {
			modelTarget.setFounder(founder);
			return this;
		}

		public Integer loginErrorCount() {
			return modelTarget.getLoginErrorCount();
		}

		public SecurityUserBuilder loginErrorCount(Integer loginErrorCount) {
			modelTarget.setLoginErrorCount(loginErrorCount);
			return this;
		}

		public Integer loginLockStatus() {
			return modelTarget.getLoginLockStatus();
		}

		public SecurityUserBuilder loginLockStatus(Integer loginLockStatus) {
			modelTarget.setLoginLockStatus(loginLockStatus);
			return this;
		}

		public Long loginLockStartTime() {
			return modelTarget.getLoginLockStartTime();
		}

		public SecurityUserBuilder loginLockStartTime(Long loginLockStartTime) {
			modelTarget.setLoginLockStartTime(loginLockStartTime);
			return this;
		}

		public Long loginLockEndTime() {
			return modelTarget.getLoginLockEndTime();
		}

		public SecurityUserBuilder loginLockEndTime(Long loginLockEndTime) {
			modelTarget.setLoginLockEndTime(loginLockEndTime);
			return this;
		}

	}

	/**
	 * SecurityUser 字段常量表
	 */
	public static class FIELDS {
		public static final String ID = "id";
		public static final String CLIENT = "client";
		public static final String USER_NAME = "user_name";
		public static final String REAL_NAME = "real_name";
		public static final String PHOTO_URI = "photo_uri";
		public static final String PASSWORD = "password";
		public static final String MOBILE = "mobile";
		public static final String GENDER = "gender";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
		public static final String LAST_MODIFY_TIME = "last_modify_time";
		public static final String LAST_MODIFY_USER = "last_modify_user";
		public static final String SALT = "salt";
		public static final String DISABLE_STATUS = "disable_status";
		public static final String FOUNDER = "founder";
		public static final String LOGIN_ERROR_COUNT = "login_error_count";
		public static final String LOGIN_LOCK_STATUS = "login_lock_status";
		public static final String LOGIN_LOCK_START_TIME = "login_lock_start_time";
		public static final String LOGIN_LOCK_END_TIME = "login_lock_end_time";
	}


}

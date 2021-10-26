package com.mx.spring.dev.support.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.annotation.FieldInfo;
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
	private java.lang.String id;

	/**
	* 用户名
	*/
	@FieldInfo(comment = "用户名", nullable = false)
	private java.lang.String userName;

	/**
	* 真实姓名
	*/
	@FieldInfo(comment = "真实姓名")
	private java.lang.String realName;

	/**
	* 头像
	*/
	@FieldInfo(comment = "头像")
	private java.lang.String photoUri;

	/**
	* 密码
	*/
	@FieldInfo(comment = "密码", nullable = false)
	private java.lang.String password;

	/**
	* 手机号
	*/
	@FieldInfo(comment = "手机号")
	private java.lang.String mobile;

	/**
	* 性别
	*/
	@FieldInfo(comment = "性别")
	private java.lang.Integer gender;

	/**
	* 创建人
	*/
	@FieldInfo(comment = "创建人", nullable = false)
	private java.lang.String createUser;

	/**
	* 创建时间
	*/
	@FieldInfo(comment = "创建时间", nullable = false)
	private java.lang.Long createTime;

	/**
	* 最后修改时间
	*/
	@FieldInfo(comment = "最后修改时间", nullable = false)
	private java.lang.Long lastModifyTime;

	/**
	* 最后修改人
	*/
	@FieldInfo(comment = "最后修改人", nullable = false)
	private java.lang.String lastModifyUser;

	/**
	* 密码加密字符串
	*/
	@FieldInfo(comment = "密码加密字符串", nullable = false)
	private java.lang.String salt;

	/**
	* 禁用状态
	*/
	@FieldInfo(comment = "禁用状态", nullable = false)
	private java.lang.Integer disableStatus;

	/**
	* 是否是总管理员
	*/
	@FieldInfo(comment = "是否是总管理员", nullable = false)
	private java.lang.Integer founder;

	/**
	* 登录错误次数
	*/
	@FieldInfo(comment = "登录错误次数")
	private java.lang.Integer loginErrorCount;

	/**
	* 锁定状态
	*/
	@FieldInfo(comment = "锁定状态")
	private java.lang.Integer loginLockStatus;

	/**
	* 锁定开始时间
	*/
	@FieldInfo(comment = "锁定开始时间")
	private java.lang.Long loginLockStartTime;

	/**
	* 锁定结束时间
	*/
	@FieldInfo(comment = "锁定结束时间")
	private java.lang.Long loginLockEndTime;


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
	public SecurityUser(java.lang.String id, java.lang.String userName, java.lang.String realName, java.lang.String photoUri, java.lang.String password, java.lang.String mobile, java.lang.Integer gender, java.lang.String createUser, java.lang.Long createTime, java.lang.Long lastModifyTime, java.lang.String lastModifyUser, java.lang.String salt, java.lang.Integer disableStatus, java.lang.Integer founder, java.lang.Integer loginErrorCount, java.lang.Integer loginLockStatus, java.lang.Long loginLockStartTime, java.lang.Long loginLockEndTime) {
		this.id = id;
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

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}


	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getRealName() {
		return realName;
	}

	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}

	public java.lang.String getPhotoUri() {
		return photoUri;
	}

	public void setPhotoUri(java.lang.String photoUri) {
		this.photoUri = photoUri;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public java.lang.Integer getGender() {
		return gender;
	}

	public void setGender(java.lang.Integer gender) {
		this.gender = gender;
	}

	public java.lang.String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(java.lang.String createUser) {
		this.createUser = createUser;
	}

	public java.lang.Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}

	public java.lang.Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(java.lang.Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public java.lang.String getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(java.lang.String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public java.lang.String getSalt() {
		return salt;
	}

	public void setSalt(java.lang.String salt) {
		this.salt = salt;
	}

	public java.lang.Integer getDisableStatus() {
		return disableStatus;
	}

	public void setDisableStatus(java.lang.Integer disableStatus) {
		this.disableStatus = disableStatus;
	}

	public java.lang.Integer getFounder() {
		return founder;
	}

	public void setFounder(java.lang.Integer founder) {
		this.founder = founder;
	}

	public java.lang.Integer getLoginErrorCount() {
		return loginErrorCount;
	}

	public void setLoginErrorCount(java.lang.Integer loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}

	public java.lang.Integer getLoginLockStatus() {
		return loginLockStatus;
	}

	public void setLoginLockStatus(java.lang.Integer loginLockStatus) {
		this.loginLockStatus = loginLockStatus;
	}

	public java.lang.Long getLoginLockStartTime() {
		return loginLockStartTime;
	}

	public void setLoginLockStartTime(java.lang.Long loginLockStartTime) {
		this.loginLockStartTime = loginLockStartTime;
	}

	public java.lang.Long getLoginLockEndTime() {
		return loginLockEndTime;
	}

	public void setLoginLockEndTime(java.lang.Long loginLockEndTime) {
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

		public java.lang.String id() {
			return modelTarget.getId();
		}

		public SecurityUserBuilder id(java.lang.String id) {
			modelTarget.setId(id);
			return this;
		}

		public java.lang.String userName() {
			return modelTarget.getUserName();
		}

		public SecurityUserBuilder userName(java.lang.String userName) {
			modelTarget.setUserName(userName);
			return this;
		}

		public java.lang.String realName() {
			return modelTarget.getRealName();
		}

		public SecurityUserBuilder realName(java.lang.String realName) {
			modelTarget.setRealName(realName);
			return this;
		}

		public java.lang.String photoUri() {
			return modelTarget.getPhotoUri();
		}

		public SecurityUserBuilder photoUri(java.lang.String photoUri) {
			modelTarget.setPhotoUri(photoUri);
			return this;
		}

		public java.lang.String password() {
			return modelTarget.getPassword();
		}

		public SecurityUserBuilder password(java.lang.String password) {
			modelTarget.setPassword(password);
			return this;
		}

		public java.lang.String mobile() {
			return modelTarget.getMobile();
		}

		public SecurityUserBuilder mobile(java.lang.String mobile) {
			modelTarget.setMobile(mobile);
			return this;
		}

		public java.lang.Integer gender() {
			return modelTarget.getGender();
		}

		public SecurityUserBuilder gender(java.lang.Integer gender) {
			modelTarget.setGender(gender);
			return this;
		}

		public java.lang.String createUser() {
			return modelTarget.getCreateUser();
		}

		public SecurityUserBuilder createUser(java.lang.String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public java.lang.Long createTime() {
			return modelTarget.getCreateTime();
		}

		public SecurityUserBuilder createTime(java.lang.Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public java.lang.Long lastModifyTime() {
			return modelTarget.getLastModifyTime();
		}

		public SecurityUserBuilder lastModifyTime(java.lang.Long lastModifyTime) {
			modelTarget.setLastModifyTime(lastModifyTime);
			return this;
		}

		public java.lang.String lastModifyUser() {
			return modelTarget.getLastModifyUser();
		}

		public SecurityUserBuilder lastModifyUser(java.lang.String lastModifyUser) {
			modelTarget.setLastModifyUser(lastModifyUser);
			return this;
		}

		public java.lang.String salt() {
			return modelTarget.getSalt();
		}

		public SecurityUserBuilder salt(java.lang.String salt) {
			modelTarget.setSalt(salt);
			return this;
		}

		public java.lang.Integer disableStatus() {
			return modelTarget.getDisableStatus();
		}

		public SecurityUserBuilder disableStatus(java.lang.Integer disableStatus) {
			modelTarget.setDisableStatus(disableStatus);
			return this;
		}

		public java.lang.Integer founder() {
			return modelTarget.getFounder();
		}

		public SecurityUserBuilder founder(java.lang.Integer founder) {
			modelTarget.setFounder(founder);
			return this;
		}

		public java.lang.Integer loginErrorCount() {
			return modelTarget.getLoginErrorCount();
		}

		public SecurityUserBuilder loginErrorCount(java.lang.Integer loginErrorCount) {
			modelTarget.setLoginErrorCount(loginErrorCount);
			return this;
		}

		public java.lang.Integer loginLockStatus() {
			return modelTarget.getLoginLockStatus();
		}

		public SecurityUserBuilder loginLockStatus(java.lang.Integer loginLockStatus) {
			modelTarget.setLoginLockStatus(loginLockStatus);
			return this;
		}

		public java.lang.Long loginLockStartTime() {
			return modelTarget.getLoginLockStartTime();
		}

		public SecurityUserBuilder loginLockStartTime(java.lang.Long loginLockStartTime) {
			modelTarget.setLoginLockStartTime(loginLockStartTime);
			return this;
		}

		public java.lang.Long loginLockEndTime() {
			return modelTarget.getLoginLockEndTime();
		}

		public SecurityUserBuilder loginLockEndTime(java.lang.Long loginLockEndTime) {
			modelTarget.setLoginLockEndTime(loginLockEndTime);
			return this;
		}

	}

	/**
	 * SecurityUser 字段常量表
	 */
	public static class FIELDS {
		public static final String ID = "id";
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

package com.mx.spring.security.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/08.
 * @Time: 09:22:59.
 * @Description: 2021/10/08 09:22:59 生成 Admin
 */
@TableName("mx_admin")
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* id
	*/
	private String id;

	/**
	* 用户名
	*/
	private String userName;

	/**
	* 真实姓名
	*/
	private String realName;

	/**
	* 头像
	*/
	private String photoUri;

	/**
	* 密码
	*/
	private String password;

	/**
	* 手机号
	*/
	private String mobile;

	/**
	* 性别
	*/
	private Integer gender;

	/**
	* 创建人
	*/
	private String createUser;

	/**
	* 创建时间
	*/
	private Long createTime;

	/**
	* 最后修改时间
	*/
	private Long lastModifyTime;

	/**
	* 最后修改人
	*/
	private String lastModifyUser;

	/**
	* 密码加密字符串
	*/
	private String salt;

	/**
	* 禁用状态
	*/
	private Integer disableStatus;

	/**
	* 是否是总管理员
	*/
	private Integer founder;

	/**
	* 登录错误次数
	*/
	private Integer loginErrorCount;

	/**
	* 锁定状态
	*/
	private Integer loginLockStatus;

	/**
	* 锁定开始时间
	*/
	private Long loginLockStartTime;

	/**
	* 锁定结束时间
	*/
	private Long loginLockEndTime;


	/**
	* 初始化
	*/
	public static Admin init() {
		return new Admin();
	}

	/**
	 * 构造器
	 */
	public Admin() {
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
	public Admin(String id, String userName, String realName, String photoUri, String password, String mobile, Integer gender, String createUser, Long createTime, Long lastModifyTime, String lastModifyUser, String salt, Integer disableStatus, Integer founder, Integer loginErrorCount, Integer loginLockStatus, Long loginLockStartTime, Long loginLockEndTime) {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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




	public static AdminBuilder builder() {
		return new AdminBuilder();
	}

	public AdminBuilder bind() {
    	return new AdminBuilder(this);
    }

	public static class AdminBuilder {

		private final Admin modelTarget;

		public AdminBuilder() {
			modelTarget = new Admin();
		}

		public AdminBuilder(Admin model) {
			modelTarget = model;
		}

		public Admin build() {
			return modelTarget;
		}

		public String id() {
			return modelTarget.getId();
		}

		public AdminBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String userName() {
			return modelTarget.getUserName();
		}

		public AdminBuilder userName(String userName) {
			modelTarget.setUserName(userName);
			return this;
		}

		public String realName() {
			return modelTarget.getRealName();
		}

		public AdminBuilder realName(String realName) {
			modelTarget.setRealName(realName);
			return this;
		}

		public String photoUri() {
			return modelTarget.getPhotoUri();
		}

		public AdminBuilder photoUri(String photoUri) {
			modelTarget.setPhotoUri(photoUri);
			return this;
		}

		public String password() {
			return modelTarget.getPassword();
		}

		public AdminBuilder password(String password) {
			modelTarget.setPassword(password);
			return this;
		}

		public String mobile() {
			return modelTarget.getMobile();
		}

		public AdminBuilder mobile(String mobile) {
			modelTarget.setMobile(mobile);
			return this;
		}

		public Integer gender() {
			return modelTarget.getGender();
		}

		public AdminBuilder gender(Integer gender) {
			modelTarget.setGender(gender);
			return this;
		}

		public String createUser() {
			return modelTarget.getCreateUser();
		}

		public AdminBuilder createUser(String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public AdminBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public Long lastModifyTime() {
			return modelTarget.getLastModifyTime();
		}

		public AdminBuilder lastModifyTime(Long lastModifyTime) {
			modelTarget.setLastModifyTime(lastModifyTime);
			return this;
		}

		public String lastModifyUser() {
			return modelTarget.getLastModifyUser();
		}

		public AdminBuilder lastModifyUser(String lastModifyUser) {
			modelTarget.setLastModifyUser(lastModifyUser);
			return this;
		}

		public String salt() {
			return modelTarget.getSalt();
		}

		public AdminBuilder salt(String salt) {
			modelTarget.setSalt(salt);
			return this;
		}

		public Integer disableStatus() {
			return modelTarget.getDisableStatus();
		}

		public AdminBuilder disableStatus(Integer disableStatus) {
			modelTarget.setDisableStatus(disableStatus);
			return this;
		}

		public Integer founder() {
			return modelTarget.getFounder();
		}

		public AdminBuilder founder(Integer founder) {
			modelTarget.setFounder(founder);
			return this;
		}

		public Integer loginErrorCount() {
			return modelTarget.getLoginErrorCount();
		}

		public AdminBuilder loginErrorCount(Integer loginErrorCount) {
			modelTarget.setLoginErrorCount(loginErrorCount);
			return this;
		}

		public Integer loginLockStatus() {
			return modelTarget.getLoginLockStatus();
		}

		public AdminBuilder loginLockStatus(Integer loginLockStatus) {
			modelTarget.setLoginLockStatus(loginLockStatus);
			return this;
		}

		public Long loginLockStartTime() {
			return modelTarget.getLoginLockStartTime();
		}

		public AdminBuilder loginLockStartTime(Long loginLockStartTime) {
			modelTarget.setLoginLockStartTime(loginLockStartTime);
			return this;
		}

		public Long loginLockEndTime() {
			return modelTarget.getLoginLockEndTime();
		}

		public AdminBuilder loginLockEndTime(Long loginLockEndTime) {
			modelTarget.setLoginLockEndTime(loginLockEndTime);
			return this;
		}

	}

	/**
	 * Admin 字段常量表
	 */
	public class FIELDS {
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

	public static final String TABLE_NAME = "mx_admin";

}

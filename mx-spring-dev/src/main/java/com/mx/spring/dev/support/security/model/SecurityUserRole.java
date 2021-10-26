package com.mx.spring.dev.support.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.annotation.FieldInfo;
import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/22.
 * @Time: 15:14:05.
 * @Description: 2021/10/22 15:14:05 生成 SecurityUserRole
 */
@TableName("mx_security_user_role")
public class SecurityUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "mx_security_user_role";

	/**
	* id
	*/
	@FieldInfo(comment = "id", nullable = false)
	private java.lang.String id;

	/**
	* 管理员id
	*/
	@FieldInfo(comment = "管理员id", nullable = false)
	private java.lang.String userId;

	/**
	* 角色id
	*/
	@FieldInfo(comment = "角色id", nullable = false)
	private java.lang.String roleId;

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
	* 最后更新人
	*/
	@FieldInfo(comment = "最后更新人", nullable = false)
	private java.lang.String lastModifyUser;

	/**
	* 最后更新时间
	*/
	@FieldInfo(comment = "最后更新时间", nullable = false)
	private java.lang.Long lastModifyTime;


	/**
	* 初始化
	*/
	public static SecurityUserRole init() {
		return new SecurityUserRole();
	}

	/**
	 * 构造器
	 */
	public SecurityUserRole() {
	}

	/**
	 * 构造器
	 *	@param id
	 *	@param userId
	 *	@param roleId
	 *	@param createUser
	 *	@param createTime
	 *	@param lastModifyUser
	 *	@param lastModifyTime
	 */
	public SecurityUserRole(java.lang.String id, java.lang.String userId, java.lang.String roleId, java.lang.String createUser, java.lang.Long createTime, java.lang.String lastModifyUser, java.lang.Long lastModifyTime) {
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
		this.createUser = createUser;
		this.createTime = createTime;
		this.lastModifyUser = lastModifyUser;
		this.lastModifyTime = lastModifyTime;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public java.lang.String getRoleId() {
		return roleId;
	}

	public void setRoleId(java.lang.String roleId) {
		this.roleId = roleId;
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

	public java.lang.String getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(java.lang.String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public java.lang.Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(java.lang.Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}




	public static SecurityUserRoleBuilder builder() {
		return new SecurityUserRoleBuilder();
	}

	public SecurityUserRoleBuilder bind() {
    	return new SecurityUserRoleBuilder(this);
    }

	public static class SecurityUserRoleBuilder {

		private final SecurityUserRole modelTarget;

		public SecurityUserRoleBuilder() {
			modelTarget = new SecurityUserRole();
		}

		public SecurityUserRoleBuilder(SecurityUserRole model) {
			modelTarget = model;
		}

		public SecurityUserRole build() {
			return modelTarget;
		}

		public java.lang.String id() {
			return modelTarget.getId();
		}

		public SecurityUserRoleBuilder id(java.lang.String id) {
			modelTarget.setId(id);
			return this;
		}

		public java.lang.String userId() {
			return modelTarget.getUserId();
		}

		public SecurityUserRoleBuilder userId(java.lang.String userId) {
			modelTarget.setUserId(userId);
			return this;
		}

		public java.lang.String roleId() {
			return modelTarget.getRoleId();
		}

		public SecurityUserRoleBuilder roleId(java.lang.String roleId) {
			modelTarget.setRoleId(roleId);
			return this;
		}

		public java.lang.String createUser() {
			return modelTarget.getCreateUser();
		}

		public SecurityUserRoleBuilder createUser(java.lang.String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public java.lang.Long createTime() {
			return modelTarget.getCreateTime();
		}

		public SecurityUserRoleBuilder createTime(java.lang.Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public java.lang.String lastModifyUser() {
			return modelTarget.getLastModifyUser();
		}

		public SecurityUserRoleBuilder lastModifyUser(java.lang.String lastModifyUser) {
			modelTarget.setLastModifyUser(lastModifyUser);
			return this;
		}

		public java.lang.Long lastModifyTime() {
			return modelTarget.getLastModifyTime();
		}

		public SecurityUserRoleBuilder lastModifyTime(java.lang.Long lastModifyTime) {
			modelTarget.setLastModifyTime(lastModifyTime);
			return this;
		}

	}

	/**
	 * SecurityUserRole 字段常量表
	 */
	public static class FIELDS {
		public static final String ID = "id";
		public static final String USER_ID = "user_id";
		public static final String ROLE_ID = "role_id";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
		public static final String LAST_MODIFY_USER = "last_modify_user";
		public static final String LAST_MODIFY_TIME = "last_modify_time";
	}


}

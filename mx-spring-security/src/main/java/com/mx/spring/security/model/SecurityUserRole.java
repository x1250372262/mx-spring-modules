package com.mx.spring.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.support.generator.annotation.FieldInfo;

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
	private String id;

	/**
	* 管理员id
	*/
	@FieldInfo(comment = "管理员id", nullable = false)
	private String userId;

	/**
	* 角色id
	*/
	@FieldInfo(comment = "角色id", nullable = false)
	private String roleId;

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
	* 最后更新人
	*/
	@FieldInfo(comment = "最后更新人", nullable = false)
	private String lastModifyUser;

	/**
	* 最后更新时间
	*/
	@FieldInfo(comment = "最后更新时间", nullable = false)
	private Long lastModifyTime;


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
	public SecurityUserRole(String id, String userId, String roleId, String createUser, Long createTime, String lastModifyUser, Long lastModifyTime) {
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
		this.createUser = createUser;
		this.createTime = createTime;
		this.lastModifyUser = lastModifyUser;
		this.lastModifyTime = lastModifyTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

	public String getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
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

		public String id() {
			return modelTarget.getId();
		}

		public SecurityUserRoleBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String userId() {
			return modelTarget.getUserId();
		}

		public SecurityUserRoleBuilder userId(String userId) {
			modelTarget.setUserId(userId);
			return this;
		}

		public String roleId() {
			return modelTarget.getRoleId();
		}

		public SecurityUserRoleBuilder roleId(String roleId) {
			modelTarget.setRoleId(roleId);
			return this;
		}

		public String createUser() {
			return modelTarget.getCreateUser();
		}

		public SecurityUserRoleBuilder createUser(String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public SecurityUserRoleBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public String lastModifyUser() {
			return modelTarget.getLastModifyUser();
		}

		public SecurityUserRoleBuilder lastModifyUser(String lastModifyUser) {
			modelTarget.setLastModifyUser(lastModifyUser);
			return this;
		}

		public Long lastModifyTime() {
			return modelTarget.getLastModifyTime();
		}

		public SecurityUserRoleBuilder lastModifyTime(Long lastModifyTime) {
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

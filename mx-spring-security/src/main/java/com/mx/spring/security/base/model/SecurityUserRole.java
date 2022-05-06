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
 * @Description: 2022/05/05 14:45:23 生成 SecurityUserRole
 */
@TableName(SecurityUserRole.TABLE_NAME)
public class SecurityUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "mx_security_user_role";

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
	* 管理员id
	*/
	@TableField(value = Fields.USER_ID)
	@FieldInfo(comment = "管理员id", nullable = false)
	private String userId;

	/**
	* 角色id
	*/
	@TableField(value = Fields.ROLE_ID)
	@FieldInfo(comment = "角色id", nullable = false)
	private String roleId;

	/**
	* 创建人
	*/
	@TableField(value = Fields.CREATE_USER)
	@FieldInfo(comment = "创建人", nullable = false)
	private String createUser;

	/**
	* 创建时间
	*/
	@TableField(value = Fields.CREATE_TIME)
	@FieldInfo(comment = "创建时间", nullable = false)
	private Long createTime;

	/**
	* 最后更新人
	*/
	@TableField(value = Fields.LAST_MODIFY_USER)
	@FieldInfo(comment = "最后更新人", nullable = false)
	private String lastModifyUser;

	/**
	* 最后更新时间
	*/
	@TableField(value = Fields.LAST_MODIFY_TIME)
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
	 *	@param client
	 *	@param userId
	 *	@param roleId
	 *	@param createUser
	 *	@param createTime
	 *	@param lastModifyUser
	 *	@param lastModifyTime
	 */
	public SecurityUserRole(String id, String client, String userId, String roleId, String createUser, Long createTime, String lastModifyUser, Long lastModifyTime) {
		this.id = id;
		this.client = client;
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


	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
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

		public String client() {
			return modelTarget.getClient();
		}

		public SecurityUserRoleBuilder client(String client) {
			modelTarget.setClient(client);
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
	public static class Fields {
		public static final String ID = "id";
		public static final String CLIENT = "client";
		public static final String USER_ID = "user_id";
		public static final String ROLE_ID = "role_id";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
		public static final String LAST_MODIFY_USER = "last_modify_user";
		public static final String LAST_MODIFY_TIME = "last_modify_time";
	}


}

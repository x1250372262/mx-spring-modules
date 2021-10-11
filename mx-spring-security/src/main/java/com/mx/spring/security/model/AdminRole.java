package com.mx.spring.security.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/08.
 * @Time: 09:22:59.
 * @Description: 2021/10/08 09:22:59 生成 AdminRole
 */
@TableName("mx_admin_role")
public class AdminRole implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* id
	*/
	private String id;

	/**
	* 管理员id
	*/
	private String adminId;

	/**
	* 角色id
	*/
	private String roleId;

	/**
	* 创建人
	*/
	private String createUser;

	/**
	* 创建时间
	*/
	private Long createTime;

	/**
	* 最后更新人
	*/
	private String lastModifyUser;

	/**
	* 最后更新时间
	*/
	private Long lastModifyTime;


	/**
	* 初始化
	*/
	public static AdminRole init() {
		return new AdminRole();
	}

	/**
	 * 构造器
	 */
	public AdminRole() {
	}

	/**
	 * 构造器
	 *	@param id
	 *	@param adminId
	 *	@param roleId
	 *	@param createUser
	 *	@param createTime
	 *	@param lastModifyUser
	 *	@param lastModifyTime
	 */
	public AdminRole(String id, String adminId, String roleId, String createUser, Long createTime, String lastModifyUser, Long lastModifyTime) {
		this.id = id;
		this.adminId = adminId;
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


	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
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




	public static AdminRoleBuilder builder() {
		return new AdminRoleBuilder();
	}

	public AdminRoleBuilder bind() {
    	return new AdminRoleBuilder(this);
    }

	public static class AdminRoleBuilder {

		private final AdminRole modelTarget;

		public AdminRoleBuilder() {
			modelTarget = new AdminRole();
		}

		public AdminRoleBuilder(AdminRole model) {
			modelTarget = model;
		}

		public AdminRole build() {
			return modelTarget;
		}

		public String id() {
			return modelTarget.getId();
		}

		public AdminRoleBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String adminId() {
			return modelTarget.getAdminId();
		}

		public AdminRoleBuilder adminId(String adminId) {
			modelTarget.setAdminId(adminId);
			return this;
		}

		public String roleId() {
			return modelTarget.getRoleId();
		}

		public AdminRoleBuilder roleId(String roleId) {
			modelTarget.setRoleId(roleId);
			return this;
		}

		public String createUser() {
			return modelTarget.getCreateUser();
		}

		public AdminRoleBuilder createUser(String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public AdminRoleBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public String lastModifyUser() {
			return modelTarget.getLastModifyUser();
		}

		public AdminRoleBuilder lastModifyUser(String lastModifyUser) {
			modelTarget.setLastModifyUser(lastModifyUser);
			return this;
		}

		public Long lastModifyTime() {
			return modelTarget.getLastModifyTime();
		}

		public AdminRoleBuilder lastModifyTime(Long lastModifyTime) {
			modelTarget.setLastModifyTime(lastModifyTime);
			return this;
		}

	}

	/**
	 * AdminRole 字段常量表
	 */
	public class FIELDS {
		public static final String ID = "id";
		public static final String ADMIN_ID = "admin_id";
		public static final String ROLE_ID = "role_id";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
		public static final String LAST_MODIFY_USER = "last_modify_user";
		public static final String LAST_MODIFY_TIME = "last_modify_time";
	}

	public static final String TABLE_NAME = "mx_admin_role";

}

package com.mx.spring.security.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/08.
 * @Time: 09:22:59.
 * @Description: 2021/10/08 09:22:59 生成 RolePermission
 */
@TableName("mx_role_permission")
public class RolePermission implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* id
	*/
	private String id;

	/**
	* 角色id
	*/
	private String roleId;

	/**
	* 权限id
	*/
	private String permissonId;

	/**
	* 创建时间
	*/
	private Long createTime;

	/**
	* 权限组名称
	*/
	private String groupName;

	/**
	* 权限名称
	*/
	private String permissionName;

	/**
	* 权限码
	*/
	private String permissionCode;


	/**
	* 初始化
	*/
	public static RolePermission init() {
		return new RolePermission();
	}

	/**
	 * 构造器
	 */
	public RolePermission() {
	}

	/**
	 * 构造器
	 *	@param id
	 *	@param roleId
	 *	@param permissonId
	 *	@param createTime
	 *	@param groupName
	 *	@param permissionName
	 *	@param permissionCode
	 */
	public RolePermission(String id, String roleId, String permissonId, Long createTime, String groupName, String permissionName, String permissionCode) {
		this.id = id;
		this.roleId = roleId;
		this.permissonId = permissonId;
		this.createTime = createTime;
		this.groupName = groupName;
		this.permissionName = permissionName;
		this.permissionCode = permissionCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPermissonId() {
		return permissonId;
	}

	public void setPermissonId(String permissonId) {
		this.permissonId = permissonId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}




	public static RolePermissionBuilder builder() {
		return new RolePermissionBuilder();
	}

	public RolePermissionBuilder bind() {
    	return new RolePermissionBuilder(this);
    }

	public static class RolePermissionBuilder {

		private final RolePermission modelTarget;

		public RolePermissionBuilder() {
			modelTarget = new RolePermission();
		}

		public RolePermissionBuilder(RolePermission model) {
			modelTarget = model;
		}

		public RolePermission build() {
			return modelTarget;
		}

		public String id() {
			return modelTarget.getId();
		}

		public RolePermissionBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String roleId() {
			return modelTarget.getRoleId();
		}

		public RolePermissionBuilder roleId(String roleId) {
			modelTarget.setRoleId(roleId);
			return this;
		}

		public String permissonId() {
			return modelTarget.getPermissonId();
		}

		public RolePermissionBuilder permissonId(String permissonId) {
			modelTarget.setPermissonId(permissonId);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public RolePermissionBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public String groupName() {
			return modelTarget.getGroupName();
		}

		public RolePermissionBuilder groupName(String groupName) {
			modelTarget.setGroupName(groupName);
			return this;
		}

		public String permissionName() {
			return modelTarget.getPermissionName();
		}

		public RolePermissionBuilder permissionName(String permissionName) {
			modelTarget.setPermissionName(permissionName);
			return this;
		}

		public String permissionCode() {
			return modelTarget.getPermissionCode();
		}

		public RolePermissionBuilder permissionCode(String permissionCode) {
			modelTarget.setPermissionCode(permissionCode);
			return this;
		}

	}

	/**
	 * RolePermission 字段常量表
	 */
	public class FIELDS {
		public static final String ID = "id";
		public static final String ROLE_ID = "role_id";
		public static final String PERMISSON_ID = "permisson_id";
		public static final String CREATE_TIME = "create_time";
		public static final String GROUP_NAME = "group_name";
		public static final String PERMISSION_NAME = "permission_name";
		public static final String PERMISSION_CODE = "permission_code";
	}

	public static final String TABLE_NAME = "mx_role_permission";

}

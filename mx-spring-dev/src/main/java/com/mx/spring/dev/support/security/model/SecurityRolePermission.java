package com.mx.spring.dev.support.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.annotation.FieldInfo;
import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/22.
 * @Time: 15:14:06.
 * @Description: 2021/10/22 15:14:06 生成 SecurityRolePermission
 */
@TableName("mx_security_role_permission")
public class SecurityRolePermission implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "mx_security_role_permission";

	/**
	* id
	*/
	@FieldInfo(comment = "id", nullable = false)
	private java.lang.String id;

	/**
	* 角色id
	*/
	@FieldInfo(comment = "角色id", nullable = false)
	private java.lang.String roleId;

	/**
	* 权限id
	*/
	@FieldInfo(comment = "权限id", nullable = false)
	private java.lang.String permissonId;

	/**
	* 创建时间
	*/
	@FieldInfo(comment = "创建时间", nullable = false)
	private java.lang.Long createTime;

	/**
	* 权限组名称
	*/
	@FieldInfo(comment = "权限组名称", nullable = false)
	private java.lang.String groupName;

	/**
	* 权限名称
	*/
	@FieldInfo(comment = "权限名称", nullable = false)
	private java.lang.String permissionName;

	/**
	* 权限码
	*/
	@FieldInfo(comment = "权限码", nullable = false)
	private java.lang.String permissionCode;


	/**
	* 初始化
	*/
	public static SecurityRolePermission init() {
		return new SecurityRolePermission();
	}

	/**
	 * 构造器
	 */
	public SecurityRolePermission() {
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
	public SecurityRolePermission(java.lang.String id, java.lang.String roleId, java.lang.String permissonId, java.lang.Long createTime, java.lang.String groupName, java.lang.String permissionName, java.lang.String permissionCode) {
		this.id = id;
		this.roleId = roleId;
		this.permissonId = permissonId;
		this.createTime = createTime;
		this.groupName = groupName;
		this.permissionName = permissionName;
		this.permissionCode = permissionCode;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}


	public java.lang.String getRoleId() {
		return roleId;
	}

	public void setRoleId(java.lang.String roleId) {
		this.roleId = roleId;
	}

	public java.lang.String getPermissonId() {
		return permissonId;
	}

	public void setPermissonId(java.lang.String permissonId) {
		this.permissonId = permissonId;
	}

	public java.lang.Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getGroupName() {
		return groupName;
	}

	public void setGroupName(java.lang.String groupName) {
		this.groupName = groupName;
	}

	public java.lang.String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(java.lang.String permissionName) {
		this.permissionName = permissionName;
	}

	public java.lang.String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(java.lang.String permissionCode) {
		this.permissionCode = permissionCode;
	}




	public static SecurityRolePermissionBuilder builder() {
		return new SecurityRolePermissionBuilder();
	}

	public SecurityRolePermissionBuilder bind() {
    	return new SecurityRolePermissionBuilder(this);
    }

	public static class SecurityRolePermissionBuilder {

		private final SecurityRolePermission modelTarget;

		public SecurityRolePermissionBuilder() {
			modelTarget = new SecurityRolePermission();
		}

		public SecurityRolePermissionBuilder(SecurityRolePermission model) {
			modelTarget = model;
		}

		public SecurityRolePermission build() {
			return modelTarget;
		}

		public java.lang.String id() {
			return modelTarget.getId();
		}

		public SecurityRolePermissionBuilder id(java.lang.String id) {
			modelTarget.setId(id);
			return this;
		}

		public java.lang.String roleId() {
			return modelTarget.getRoleId();
		}

		public SecurityRolePermissionBuilder roleId(java.lang.String roleId) {
			modelTarget.setRoleId(roleId);
			return this;
		}

		public java.lang.String permissonId() {
			return modelTarget.getPermissonId();
		}

		public SecurityRolePermissionBuilder permissonId(java.lang.String permissonId) {
			modelTarget.setPermissonId(permissonId);
			return this;
		}

		public java.lang.Long createTime() {
			return modelTarget.getCreateTime();
		}

		public SecurityRolePermissionBuilder createTime(java.lang.Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public java.lang.String groupName() {
			return modelTarget.getGroupName();
		}

		public SecurityRolePermissionBuilder groupName(java.lang.String groupName) {
			modelTarget.setGroupName(groupName);
			return this;
		}

		public java.lang.String permissionName() {
			return modelTarget.getPermissionName();
		}

		public SecurityRolePermissionBuilder permissionName(java.lang.String permissionName) {
			modelTarget.setPermissionName(permissionName);
			return this;
		}

		public java.lang.String permissionCode() {
			return modelTarget.getPermissionCode();
		}

		public SecurityRolePermissionBuilder permissionCode(java.lang.String permissionCode) {
			modelTarget.setPermissionCode(permissionCode);
			return this;
		}

	}

	/**
	 * SecurityRolePermission 字段常量表
	 */
	public static class FIELDS {
		public static final String ID = "id";
		public static final String ROLE_ID = "role_id";
		public static final String PERMISSON_ID = "permisson_id";
		public static final String CREATE_TIME = "create_time";
		public static final String GROUP_NAME = "group_name";
		public static final String PERMISSION_NAME = "permission_name";
		public static final String PERMISSION_CODE = "permission_code";
	}


}

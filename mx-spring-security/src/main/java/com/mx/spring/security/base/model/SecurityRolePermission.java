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
 * @Description: 2022/05/05 14:45:23 生成 SecurityRolePermission
 */
@TableName(SecurityRolePermission.TABLE_NAME)
public class SecurityRolePermission implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "mx_security_role_permission";

	/**
	* id
	*/
	@TableId(value = FIELDS.ID, type = IdType.INPUT)
	@FieldInfo(comment = "id", nullable = false)
	private String id;

	/**
	* 客户端
	*/
	@TableField(value = FIELDS.CLIENT)
	@FieldInfo(comment = "客户端", nullable = false)
	private String client;

	/**
	* 角色id
	*/
	@TableField(value = FIELDS.ROLE_ID)
	@FieldInfo(comment = "角色id", nullable = false)
	private String roleId;

	/**
	* 权限id
	*/
	@TableField(value = FIELDS.PERMISSON_ID)
	@FieldInfo(comment = "权限id", nullable = false)
	private String permissonId;

	/**
	* 创建时间
	*/
	@TableField(value = FIELDS.CREATE_TIME)
	@FieldInfo(comment = "创建时间", nullable = false)
	private Long createTime;

	/**
	* 权限组名称
	*/
	@TableField(value = FIELDS.GROUP_NAME)
	@FieldInfo(comment = "权限组名称", nullable = false)
	private String groupName;

	/**
	* 权限名称
	*/
	@TableField(value = FIELDS.PERMISSION_NAME)
	@FieldInfo(comment = "权限名称", nullable = false)
	private String permissionName;

	/**
	* 权限码
	*/
	@TableField(value = FIELDS.PERMISSION_CODE)
	@FieldInfo(comment = "权限码", nullable = false)
	private String permissionCode;


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
	 *	@param client
	 *	@param roleId
	 *	@param permissonId
	 *	@param createTime
	 *	@param groupName
	 *	@param permissionName
	 *	@param permissionCode
	 */
	public SecurityRolePermission(String id, String client, String roleId, String permissonId, Long createTime, String groupName, String permissionName, String permissionCode) {
		this.id = id;
		this.client = client;
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


	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
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

		public String id() {
			return modelTarget.getId();
		}

		public SecurityRolePermissionBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String client() {
			return modelTarget.getClient();
		}

		public SecurityRolePermissionBuilder client(String client) {
			modelTarget.setClient(client);
			return this;
		}

		public String roleId() {
			return modelTarget.getRoleId();
		}

		public SecurityRolePermissionBuilder roleId(String roleId) {
			modelTarget.setRoleId(roleId);
			return this;
		}

		public String permissonId() {
			return modelTarget.getPermissonId();
		}

		public SecurityRolePermissionBuilder permissonId(String permissonId) {
			modelTarget.setPermissonId(permissonId);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public SecurityRolePermissionBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public String groupName() {
			return modelTarget.getGroupName();
		}

		public SecurityRolePermissionBuilder groupName(String groupName) {
			modelTarget.setGroupName(groupName);
			return this;
		}

		public String permissionName() {
			return modelTarget.getPermissionName();
		}

		public SecurityRolePermissionBuilder permissionName(String permissionName) {
			modelTarget.setPermissionName(permissionName);
			return this;
		}

		public String permissionCode() {
			return modelTarget.getPermissionCode();
		}

		public SecurityRolePermissionBuilder permissionCode(String permissionCode) {
			modelTarget.setPermissionCode(permissionCode);
			return this;
		}

	}

	/**
	 * SecurityRolePermission 字段常量表
	 */
	public static class FIELDS {
		public static final String ID = "id";
		public static final String CLIENT = "client";
		public static final String ROLE_ID = "role_id";
		public static final String PERMISSON_ID = "permisson_id";
		public static final String CREATE_TIME = "create_time";
		public static final String GROUP_NAME = "group_name";
		public static final String PERMISSION_NAME = "permission_name";
		public static final String PERMISSION_CODE = "permission_code";
	}


}

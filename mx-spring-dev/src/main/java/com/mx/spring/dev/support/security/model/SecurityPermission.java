package com.mx.spring.dev.support.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.annotation.FieldInfo;
import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/22.
 * @Time: 15:15:28.
 * @Description: 2021/10/22 15:15:28 生成 SecurityPermission
 */
@TableName("mx_security_permission")
public class SecurityPermission implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "mx_security_permission";

	/**
	* id
	*/
	@FieldInfo(comment = "id", nullable = false)
	private java.lang.String id;

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
	public static SecurityPermission init() {
		return new SecurityPermission();
	}

	/**
	 * 构造器
	 */
	public SecurityPermission() {
	}

	/**
	 * 构造器
	 *	@param id
	 *	@param groupName
	 *	@param permissionName
	 *	@param permissionCode
	 *	@param createUser
	 *	@param createTime
	 *	@param lastModifyUser
	 *	@param lastModifyTime
	 */
	public SecurityPermission(java.lang.String id, java.lang.String groupName, java.lang.String permissionName, java.lang.String permissionCode, java.lang.String createUser, java.lang.Long createTime, java.lang.String lastModifyUser, java.lang.Long lastModifyTime) {
		this.id = id;
		this.groupName = groupName;
		this.permissionName = permissionName;
		this.permissionCode = permissionCode;
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




	public static SecurityPermissionBuilder builder() {
		return new SecurityPermissionBuilder();
	}

	public SecurityPermissionBuilder bind() {
    	return new SecurityPermissionBuilder(this);
    }

	public static class SecurityPermissionBuilder {

		private final SecurityPermission modelTarget;

		public SecurityPermissionBuilder() {
			modelTarget = new SecurityPermission();
		}

		public SecurityPermissionBuilder(SecurityPermission model) {
			modelTarget = model;
		}

		public SecurityPermission build() {
			return modelTarget;
		}

		public java.lang.String id() {
			return modelTarget.getId();
		}

		public SecurityPermissionBuilder id(java.lang.String id) {
			modelTarget.setId(id);
			return this;
		}

		public java.lang.String groupName() {
			return modelTarget.getGroupName();
		}

		public SecurityPermissionBuilder groupName(java.lang.String groupName) {
			modelTarget.setGroupName(groupName);
			return this;
		}

		public java.lang.String permissionName() {
			return modelTarget.getPermissionName();
		}

		public SecurityPermissionBuilder permissionName(java.lang.String permissionName) {
			modelTarget.setPermissionName(permissionName);
			return this;
		}

		public java.lang.String permissionCode() {
			return modelTarget.getPermissionCode();
		}

		public SecurityPermissionBuilder permissionCode(java.lang.String permissionCode) {
			modelTarget.setPermissionCode(permissionCode);
			return this;
		}

		public java.lang.String createUser() {
			return modelTarget.getCreateUser();
		}

		public SecurityPermissionBuilder createUser(java.lang.String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public java.lang.Long createTime() {
			return modelTarget.getCreateTime();
		}

		public SecurityPermissionBuilder createTime(java.lang.Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public java.lang.String lastModifyUser() {
			return modelTarget.getLastModifyUser();
		}

		public SecurityPermissionBuilder lastModifyUser(java.lang.String lastModifyUser) {
			modelTarget.setLastModifyUser(lastModifyUser);
			return this;
		}

		public java.lang.Long lastModifyTime() {
			return modelTarget.getLastModifyTime();
		}

		public SecurityPermissionBuilder lastModifyTime(java.lang.Long lastModifyTime) {
			modelTarget.setLastModifyTime(lastModifyTime);
			return this;
		}

	}

	/**
	 * SecurityPermission 字段常量表
	 */
	public static class FIELDS {
		public static final String ID = "id";
		public static final String GROUP_NAME = "group_name";
		public static final String PERMISSION_NAME = "permission_name";
		public static final String PERMISSION_CODE = "permission_code";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
		public static final String LAST_MODIFY_USER = "last_modify_user";
		public static final String LAST_MODIFY_TIME = "last_modify_time";
	}


}

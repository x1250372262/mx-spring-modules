package com.mx.spring.security.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/08.
 * @Time: 09:22:59.
 * @Description: 2021/10/08 09:22:59 生成 Permission
 */
@TableName("mx_permission")
public class Permission implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* id
	*/
	private String id;

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
	public static Permission init() {
		return new Permission();
	}

	/**
	 * 构造器
	 */
	public Permission() {
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
	public Permission(String id, String groupName, String permissionName, String permissionCode, String createUser, Long createTime, String lastModifyUser, Long lastModifyTime) {
		this.id = id;
		this.groupName = groupName;
		this.permissionName = permissionName;
		this.permissionCode = permissionCode;
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




	public static PermissionBuilder builder() {
		return new PermissionBuilder();
	}

	public PermissionBuilder bind() {
    	return new PermissionBuilder(this);
    }

	public static class PermissionBuilder {

		private final Permission modelTarget;

		public PermissionBuilder() {
			modelTarget = new Permission();
		}

		public PermissionBuilder(Permission model) {
			modelTarget = model;
		}

		public Permission build() {
			return modelTarget;
		}

		public String id() {
			return modelTarget.getId();
		}

		public PermissionBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String groupName() {
			return modelTarget.getGroupName();
		}

		public PermissionBuilder groupName(String groupName) {
			modelTarget.setGroupName(groupName);
			return this;
		}

		public String permissionName() {
			return modelTarget.getPermissionName();
		}

		public PermissionBuilder permissionName(String permissionName) {
			modelTarget.setPermissionName(permissionName);
			return this;
		}

		public String permissionCode() {
			return modelTarget.getPermissionCode();
		}

		public PermissionBuilder permissionCode(String permissionCode) {
			modelTarget.setPermissionCode(permissionCode);
			return this;
		}

		public String createUser() {
			return modelTarget.getCreateUser();
		}

		public PermissionBuilder createUser(String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public PermissionBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public String lastModifyUser() {
			return modelTarget.getLastModifyUser();
		}

		public PermissionBuilder lastModifyUser(String lastModifyUser) {
			modelTarget.setLastModifyUser(lastModifyUser);
			return this;
		}

		public Long lastModifyTime() {
			return modelTarget.getLastModifyTime();
		}

		public PermissionBuilder lastModifyTime(Long lastModifyTime) {
			modelTarget.setLastModifyTime(lastModifyTime);
			return this;
		}

	}

	/**
	 * Permission 字段常量表
	 */
	public class FIELDS {
		public static final String ID = "id";
		public static final String GROUP_NAME = "group_name";
		public static final String PERMISSION_NAME = "permission_name";
		public static final String PERMISSION_CODE = "permission_code";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
		public static final String LAST_MODIFY_USER = "last_modify_user";
		public static final String LAST_MODIFY_TIME = "last_modify_time";
	}

	public static final String TABLE_NAME = "mx_permission";

}

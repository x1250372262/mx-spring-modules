package com.mx.spring.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.support.generator.annotation.FieldInfo;

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
	private String id;

	/**
	* client
	*/
	@FieldInfo(comment = "client", nullable = false)
	private String client;

	/**
	* 权限组名称
	*/
	@FieldInfo(comment = "权限组名称", nullable = false)
	private String groupName;

	/**
	* 权限名称
	*/
	@FieldInfo(comment = "权限名称", nullable = false)
	private String permissionName;

	/**
	* 权限码
	*/
	@FieldInfo(comment = "权限码", nullable = false)
	private String permissionCode;

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
	 *	@param client
	 *	@param groupName
	 *	@param permissionName
	 *	@param permissionCode
	 *	@param createUser
	 *	@param createTime
	 *	@param lastModifyUser
	 *	@param lastModifyTime
	 */
	public SecurityPermission(String id, String client, String groupName, String permissionName, String permissionCode, String createUser, Long createTime, String lastModifyUser, Long lastModifyTime) {
		this.id = id;
		this.client = client;
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

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
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

		public String id() {
			return modelTarget.getId();
		}

		public SecurityPermissionBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String client() {
			return modelTarget.getClient();
		}

		public SecurityPermissionBuilder client(String client) {
			modelTarget.setClient(client);
			return this;
		}

		public String groupName() {
			return modelTarget.getGroupName();
		}

		public SecurityPermissionBuilder groupName(String groupName) {
			modelTarget.setGroupName(groupName);
			return this;
		}

		public String permissionName() {
			return modelTarget.getPermissionName();
		}

		public SecurityPermissionBuilder permissionName(String permissionName) {
			modelTarget.setPermissionName(permissionName);
			return this;
		}

		public String permissionCode() {
			return modelTarget.getPermissionCode();
		}

		public SecurityPermissionBuilder permissionCode(String permissionCode) {
			modelTarget.setPermissionCode(permissionCode);
			return this;
		}

		public String createUser() {
			return modelTarget.getCreateUser();
		}

		public SecurityPermissionBuilder createUser(String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public SecurityPermissionBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public String lastModifyUser() {
			return modelTarget.getLastModifyUser();
		}

		public SecurityPermissionBuilder lastModifyUser(String lastModifyUser) {
			modelTarget.setLastModifyUser(lastModifyUser);
			return this;
		}

		public Long lastModifyTime() {
			return modelTarget.getLastModifyTime();
		}

		public SecurityPermissionBuilder lastModifyTime(Long lastModifyTime) {
			modelTarget.setLastModifyTime(lastModifyTime);
			return this;
		}

	}

	/**
	 * SecurityPermission 字段常量表
	 */
	public static class FIELDS {
		public static final String ID = "id";
		public static final String CLIENT = "client";
		public static final String GROUP_NAME = "group_name";
		public static final String PERMISSION_NAME = "permission_name";
		public static final String PERMISSION_CODE = "permission_code";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
		public static final String LAST_MODIFY_USER = "last_modify_user";
		public static final String LAST_MODIFY_TIME = "last_modify_time";
	}


}

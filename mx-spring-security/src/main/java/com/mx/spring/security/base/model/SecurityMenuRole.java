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
 * @Description: 2022/05/05 14:45:23 生成 SecurityMenuRole
 */
@TableName(SecurityMenuRole.TABLE_NAME)
public class SecurityMenuRole implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "mx_security_menu_role";

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
	* 菜单id
	*/
	@TableField(value = Fields.MENU_ID)
	@FieldInfo(comment = "菜单id", nullable = false)
	private String menuId;

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
	* 初始化
	*/
	public static SecurityMenuRole init() {
		return new SecurityMenuRole();
	}

	/**
	 * 构造器
	 */
	public SecurityMenuRole() {
	}

	/**
	 * 构造器
	 *	@param id
	 *	@param client
	 *	@param menuId
	 *	@param roleId
	 *	@param createUser
	 *	@param createTime
	 */
	public SecurityMenuRole(String id, String client, String menuId, String roleId, String createUser, Long createTime) {
		this.id = id;
		this.client = client;
		this.menuId = menuId;
		this.roleId = roleId;
		this.createUser = createUser;
		this.createTime = createTime;
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

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
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




	public static SecurityMenuRoleBuilder builder() {
		return new SecurityMenuRoleBuilder();
	}

	public SecurityMenuRoleBuilder bind() {
    	return new SecurityMenuRoleBuilder(this);
    }

	public static class SecurityMenuRoleBuilder {

		private final SecurityMenuRole modelTarget;

		public SecurityMenuRoleBuilder() {
			modelTarget = new SecurityMenuRole();
		}

		public SecurityMenuRoleBuilder(SecurityMenuRole model) {
			modelTarget = model;
		}

		public SecurityMenuRole build() {
			return modelTarget;
		}

		public String id() {
			return modelTarget.getId();
		}

		public SecurityMenuRoleBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String client() {
			return modelTarget.getClient();
		}

		public SecurityMenuRoleBuilder client(String client) {
			modelTarget.setClient(client);
			return this;
		}

		public String menuId() {
			return modelTarget.getMenuId();
		}

		public SecurityMenuRoleBuilder menuId(String menuId) {
			modelTarget.setMenuId(menuId);
			return this;
		}

		public String roleId() {
			return modelTarget.getRoleId();
		}

		public SecurityMenuRoleBuilder roleId(String roleId) {
			modelTarget.setRoleId(roleId);
			return this;
		}

		public String createUser() {
			return modelTarget.getCreateUser();
		}

		public SecurityMenuRoleBuilder createUser(String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public SecurityMenuRoleBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

	}

	/**
	 * SecurityMenuRole 字段常量表
	 */
	public static class Fields {
		public static final String ID = "id";
		public static final String CLIENT = "client";
		public static final String MENU_ID = "menu_id";
		public static final String ROLE_ID = "role_id";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
	}


}

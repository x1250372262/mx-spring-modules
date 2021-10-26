package com.mx.spring.dev.support.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.annotation.FieldInfo;
import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/22.
 * @Time: 15:14:05.
 * @Description: 2021/10/22 15:14:05 生成 SecurityMenuRole
 */
@TableName("mx_security_menu_role")
public class SecurityMenuRole implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "mx_security_menu_role";

	/**
	* id
	*/
	@FieldInfo(comment = "id", nullable = false)
	private java.lang.String id;

	/**
	* 菜单id
	*/
	@FieldInfo(comment = "菜单id", nullable = false)
	private java.lang.String menuId;

	/**
	* 角色id
	*/
	@FieldInfo(comment = "角色id", nullable = false)
	private java.lang.String roleId;

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
	 *	@param menuId
	 *	@param roleId
	 *	@param createUser
	 *	@param createTime
	 */
	public SecurityMenuRole(java.lang.String id, java.lang.String menuId, java.lang.String roleId, java.lang.String createUser, java.lang.Long createTime) {
		this.id = id;
		this.menuId = menuId;
		this.roleId = roleId;
		this.createUser = createUser;
		this.createTime = createTime;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}


	public java.lang.String getMenuId() {
		return menuId;
	}

	public void setMenuId(java.lang.String menuId) {
		this.menuId = menuId;
	}

	public java.lang.String getRoleId() {
		return roleId;
	}

	public void setRoleId(java.lang.String roleId) {
		this.roleId = roleId;
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

		public java.lang.String id() {
			return modelTarget.getId();
		}

		public SecurityMenuRoleBuilder id(java.lang.String id) {
			modelTarget.setId(id);
			return this;
		}

		public java.lang.String menuId() {
			return modelTarget.getMenuId();
		}

		public SecurityMenuRoleBuilder menuId(java.lang.String menuId) {
			modelTarget.setMenuId(menuId);
			return this;
		}

		public java.lang.String roleId() {
			return modelTarget.getRoleId();
		}

		public SecurityMenuRoleBuilder roleId(java.lang.String roleId) {
			modelTarget.setRoleId(roleId);
			return this;
		}

		public java.lang.String createUser() {
			return modelTarget.getCreateUser();
		}

		public SecurityMenuRoleBuilder createUser(java.lang.String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public java.lang.Long createTime() {
			return modelTarget.getCreateTime();
		}

		public SecurityMenuRoleBuilder createTime(java.lang.Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

	}

	/**
	 * SecurityMenuRole 字段常量表
	 */
	public static class FIELDS {
		public static final String ID = "id";
		public static final String MENU_ID = "menu_id";
		public static final String ROLE_ID = "role_id";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
	}


}

package com.mx.spring.security.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/08.
 * @Time: 09:22:59.
 * @Description: 2021/10/08 09:22:59 生成 MenuRole
 */
@TableName("mx_menu_role")
public class MenuRole implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* id
	*/
	private String id;

	/**
	* 菜单id
	*/
	private String menuId;

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
	* 初始化
	*/
	public static MenuRole init() {
		return new MenuRole();
	}

	/**
	 * 构造器
	 */
	public MenuRole() {
	}

	/**
	 * 构造器
	 *	@param id
	 *	@param menuId
	 *	@param roleId
	 *	@param createUser
	 *	@param createTime
	 */
	public MenuRole(String id, String menuId, String roleId, String createUser, Long createTime) {
		this.id = id;
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




	public static MenuRoleBuilder builder() {
		return new MenuRoleBuilder();
	}

	public MenuRoleBuilder bind() {
    	return new MenuRoleBuilder(this);
    }

	public static class MenuRoleBuilder {

		private final MenuRole modelTarget;

		public MenuRoleBuilder() {
			modelTarget = new MenuRole();
		}

		public MenuRoleBuilder(MenuRole model) {
			modelTarget = model;
		}

		public MenuRole build() {
			return modelTarget;
		}

		public String id() {
			return modelTarget.getId();
		}

		public MenuRoleBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String menuId() {
			return modelTarget.getMenuId();
		}

		public MenuRoleBuilder menuId(String menuId) {
			modelTarget.setMenuId(menuId);
			return this;
		}

		public String roleId() {
			return modelTarget.getRoleId();
		}

		public MenuRoleBuilder roleId(String roleId) {
			modelTarget.setRoleId(roleId);
			return this;
		}

		public String createUser() {
			return modelTarget.getCreateUser();
		}

		public MenuRoleBuilder createUser(String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public MenuRoleBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

	}

	/**
	 * MenuRole 字段常量表
	 */
	public class FIELDS {
		public static final String ID = "id";
		public static final String MENU_ID = "menu_id";
		public static final String ROLE_ID = "role_id";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
	}

	public static final String TABLE_NAME = "mx_menu_role";

}

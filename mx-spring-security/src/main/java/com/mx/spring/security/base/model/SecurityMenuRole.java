package com.mx.spring.security.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.support.generator.annotation.FieldInfo;

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
	private String id;

	/**
	* 菜单id
	*/
	@FieldInfo(comment = "菜单id", nullable = false)
	private String menuId;

	/**
	* 角色id
	*/
	@FieldInfo(comment = "角色id", nullable = false)
	private String roleId;

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
	public SecurityMenuRole(String id, String menuId, String roleId, String createUser, Long createTime) {
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
	public static class FIELDS {
		public static final String ID = "id";
		public static final String MENU_ID = "menu_id";
		public static final String ROLE_ID = "role_id";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
	}


}

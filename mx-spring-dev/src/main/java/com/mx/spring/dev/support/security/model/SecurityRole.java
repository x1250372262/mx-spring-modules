package com.mx.spring.dev.support.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.annotation.FieldInfo;
import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/22.
 * @Time: 15:14:06.
 * @Description: 2021/10/22 15:14:06 生成 SecurityRole
 */
@TableName("mx_security_role")
public class SecurityRole implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "mx_security_role";

	/**
	* id
	*/
	@FieldInfo(comment = "id", nullable = false)
	private java.lang.String id;

	/**
	* 名称
	*/
	@FieldInfo(comment = "名称", nullable = false)
	private java.lang.String name;

	/**
	* 备注
	*/
	@FieldInfo(comment = "备注")
	private java.lang.String remark;

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
	public static SecurityRole init() {
		return new SecurityRole();
	}

	/**
	 * 构造器
	 */
	public SecurityRole() {
	}

	/**
	 * 构造器
	 *	@param id
	 *	@param name
	 *	@param remark
	 *	@param createUser
	 *	@param createTime
	 *	@param lastModifyUser
	 *	@param lastModifyTime
	 */
	public SecurityRole(java.lang.String id, java.lang.String name, java.lang.String remark, java.lang.String createUser, java.lang.Long createTime, java.lang.String lastModifyUser, java.lang.Long lastModifyTime) {
		this.id = id;
		this.name = name;
		this.remark = remark;
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


	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
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




	public static SecurityRoleBuilder builder() {
		return new SecurityRoleBuilder();
	}

	public SecurityRoleBuilder bind() {
    	return new SecurityRoleBuilder(this);
    }

	public static class SecurityRoleBuilder {

		private final SecurityRole modelTarget;

		public SecurityRoleBuilder() {
			modelTarget = new SecurityRole();
		}

		public SecurityRoleBuilder(SecurityRole model) {
			modelTarget = model;
		}

		public SecurityRole build() {
			return modelTarget;
		}

		public java.lang.String id() {
			return modelTarget.getId();
		}

		public SecurityRoleBuilder id(java.lang.String id) {
			modelTarget.setId(id);
			return this;
		}

		public java.lang.String name() {
			return modelTarget.getName();
		}

		public SecurityRoleBuilder name(java.lang.String name) {
			modelTarget.setName(name);
			return this;
		}

		public java.lang.String remark() {
			return modelTarget.getRemark();
		}

		public SecurityRoleBuilder remark(java.lang.String remark) {
			modelTarget.setRemark(remark);
			return this;
		}

		public java.lang.String createUser() {
			return modelTarget.getCreateUser();
		}

		public SecurityRoleBuilder createUser(java.lang.String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public java.lang.Long createTime() {
			return modelTarget.getCreateTime();
		}

		public SecurityRoleBuilder createTime(java.lang.Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public java.lang.String lastModifyUser() {
			return modelTarget.getLastModifyUser();
		}

		public SecurityRoleBuilder lastModifyUser(java.lang.String lastModifyUser) {
			modelTarget.setLastModifyUser(lastModifyUser);
			return this;
		}

		public java.lang.Long lastModifyTime() {
			return modelTarget.getLastModifyTime();
		}

		public SecurityRoleBuilder lastModifyTime(java.lang.Long lastModifyTime) {
			modelTarget.setLastModifyTime(lastModifyTime);
			return this;
		}

	}

	/**
	 * SecurityRole 字段常量表
	 */
	public static class FIELDS {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String REMARK = "remark";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
		public static final String LAST_MODIFY_USER = "last_modify_user";
		public static final String LAST_MODIFY_TIME = "last_modify_time";
	}


}

package com.mx.spring.security.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/08.
 * @Time: 09:22:59.
 * @Description: 2021/10/08 09:22:59 生成 Role
 */
@TableName("mx_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* id
	*/
	private String id;

	/**
	* 名称
	*/
	private String name;

	/**
	* 备注
	*/
	private String remark;

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
	public static Role init() {
		return new Role();
	}

	/**
	 * 构造器
	 */
	public Role() {
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
	public Role(String id, String name, String remark, String createUser, Long createTime, String lastModifyUser, Long lastModifyTime) {
		this.id = id;
		this.name = name;
		this.remark = remark;
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


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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




	public static RoleBuilder builder() {
		return new RoleBuilder();
	}

	public RoleBuilder bind() {
    	return new RoleBuilder(this);
    }

	public static class RoleBuilder {

		private final Role modelTarget;

		public RoleBuilder() {
			modelTarget = new Role();
		}

		public RoleBuilder(Role model) {
			modelTarget = model;
		}

		public Role build() {
			return modelTarget;
		}

		public String id() {
			return modelTarget.getId();
		}

		public RoleBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String name() {
			return modelTarget.getName();
		}

		public RoleBuilder name(String name) {
			modelTarget.setName(name);
			return this;
		}

		public String remark() {
			return modelTarget.getRemark();
		}

		public RoleBuilder remark(String remark) {
			modelTarget.setRemark(remark);
			return this;
		}

		public String createUser() {
			return modelTarget.getCreateUser();
		}

		public RoleBuilder createUser(String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public RoleBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public String lastModifyUser() {
			return modelTarget.getLastModifyUser();
		}

		public RoleBuilder lastModifyUser(String lastModifyUser) {
			modelTarget.setLastModifyUser(lastModifyUser);
			return this;
		}

		public Long lastModifyTime() {
			return modelTarget.getLastModifyTime();
		}

		public RoleBuilder lastModifyTime(Long lastModifyTime) {
			modelTarget.setLastModifyTime(lastModifyTime);
			return this;
		}

	}

	/**
	 * Role 字段常量表
	 */
	public class FIELDS {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String REMARK = "remark";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
		public static final String LAST_MODIFY_USER = "last_modify_user";
		public static final String LAST_MODIFY_TIME = "last_modify_time";
	}

	public static final String TABLE_NAME = "mx_role";

}

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
 * @Description: 2022/05/05 14:45:23 生成 SecurityRole
 */
@TableName(SecurityRole.TABLE_NAME)
public class SecurityRole implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "mx_security_role";

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
	* 名称
	*/
	@TableField(value = Fields.NAME)
	@FieldInfo(comment = "名称", nullable = false)
	private String name;

	/**
	* 备注
	*/
	@TableField(value = Fields.REMARK)
	@FieldInfo(comment = "备注")
	private String remark;

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
	* 最后更新人
	*/
	@TableField(value = Fields.LAST_MODIFY_USER)
	@FieldInfo(comment = "最后更新人", nullable = false)
	private String lastModifyUser;

	/**
	* 最后更新时间
	*/
	@TableField(value = Fields.LAST_MODIFY_TIME)
	@FieldInfo(comment = "最后更新时间", nullable = false)
	private Long lastModifyTime;


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
	 *	@param client
	 *	@param name
	 *	@param remark
	 *	@param createUser
	 *	@param createTime
	 *	@param lastModifyUser
	 *	@param lastModifyTime
	 */
	public SecurityRole(String id, String client, String name, String remark, String createUser, Long createTime, String lastModifyUser, Long lastModifyTime) {
		this.id = id;
		this.client = client;
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


	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
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

		public String id() {
			return modelTarget.getId();
		}

		public SecurityRoleBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String client() {
			return modelTarget.getClient();
		}

		public SecurityRoleBuilder client(String client) {
			modelTarget.setClient(client);
			return this;
		}

		public String name() {
			return modelTarget.getName();
		}

		public SecurityRoleBuilder name(String name) {
			modelTarget.setName(name);
			return this;
		}

		public String remark() {
			return modelTarget.getRemark();
		}

		public SecurityRoleBuilder remark(String remark) {
			modelTarget.setRemark(remark);
			return this;
		}

		public String createUser() {
			return modelTarget.getCreateUser();
		}

		public SecurityRoleBuilder createUser(String createUser) {
			modelTarget.setCreateUser(createUser);
			return this;
		}

		public Long createTime() {
			return modelTarget.getCreateTime();
		}

		public SecurityRoleBuilder createTime(Long createTime) {
			modelTarget.setCreateTime(createTime);
			return this;
		}

		public String lastModifyUser() {
			return modelTarget.getLastModifyUser();
		}

		public SecurityRoleBuilder lastModifyUser(String lastModifyUser) {
			modelTarget.setLastModifyUser(lastModifyUser);
			return this;
		}

		public Long lastModifyTime() {
			return modelTarget.getLastModifyTime();
		}

		public SecurityRoleBuilder lastModifyTime(Long lastModifyTime) {
			modelTarget.setLastModifyTime(lastModifyTime);
			return this;
		}

	}

	/**
	 * SecurityRole 字段常量表
	 */
	public static class Fields {
		public static final String ID = "id";
		public static final String CLIENT = "client";
		public static final String NAME = "name";
		public static final String REMARK = "remark";
		public static final String CREATE_USER = "create_user";
		public static final String CREATE_TIME = "create_time";
		public static final String LAST_MODIFY_USER = "last_modify_user";
		public static final String LAST_MODIFY_TIME = "last_modify_time";
	}


}

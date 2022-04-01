package com.mx.spring.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.support.generator.annotation.FieldInfo;

import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/22.
 * @Time: 15:14:05.
 * @Description: 2021/10/22 15:14:05 生成 SecurityMenu
 */
@TableName("mx_security_menu")
public class SecurityMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "mx_security_menu";

	/**
	* id
	*/
	@FieldInfo(comment = "id", nullable = false)
	private String id;

	/**
	* 客户端
	*/
	@FieldInfo(comment = "client", nullable = false)
	private String client;

	/**
	* 菜单类型 0默认 1公开 2拥有者可看
	*/
	@FieldInfo(comment = "菜单类型 0默认 1公开 2拥有者可看", nullable = false)
	private Integer type;

	/**
	* 父id
	*/
	@FieldInfo(comment = "父id", nullable = false)
	private String parentId;

	/**
	* 菜单名称
	*/
	@FieldInfo(comment = "菜单名称", nullable = false)
	private String name;

	/**
	* 图标
	*/
	@FieldInfo(comment = "图标")
	private String icon;

	/**
	* 路径
	*/
	@FieldInfo(comment = "路径")
	private String path;

	/**
	* 地址
	*/
	@FieldInfo(comment = "地址")
	private String url;

	/**
	* 排序
	*/
	@FieldInfo(comment = "排序", nullable = false)
	private Integer sort;


	/**
	* 初始化
	*/
	public static SecurityMenu init() {
		return new SecurityMenu();
	}

	/**
	 * 构造器
	 */
	public SecurityMenu() {
	}

	/**
	 * 构造器
	 *	@param id
	 *	@param client
	 *	@param type
	 *	@param parentId
	 *	@param name
	 *	@param icon
	 *	@param path
	 *	@param url
	 *	@param sort
	 */
	public SecurityMenu(String id, String client, Integer type, String parentId, String name, String icon, String path, String url, Integer sort) {
		this.id = id;
		this.client = client;
		this.type = type;
		this.parentId = parentId;
		this.name = name;
		this.icon = icon;
		this.path = path;
		this.url = url;
		this.sort = sort;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}




	public static SecurityMenuBuilder builder() {
		return new SecurityMenuBuilder();
	}

	public SecurityMenuBuilder bind() {
    	return new SecurityMenuBuilder(this);
    }

	public static class SecurityMenuBuilder {

		private final SecurityMenu modelTarget;

		public SecurityMenuBuilder() {
			modelTarget = new SecurityMenu();
		}

		public SecurityMenuBuilder(SecurityMenu model) {
			modelTarget = model;
		}

		public SecurityMenu build() {
			return modelTarget;
		}

		public String id() {
			return modelTarget.getId();
		}

		public SecurityMenuBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public String client() {
			return modelTarget.getClient();
		}

		public SecurityMenuBuilder client(String client) {
			modelTarget.setClient(client);
			return this;
		}

		public Integer type() {
			return modelTarget.getType();
		}

		public SecurityMenuBuilder type(Integer type) {
			modelTarget.setType(type);
			return this;
		}

		public String parentId() {
			return modelTarget.getParentId();
		}

		public SecurityMenuBuilder parentId(String parentId) {
			modelTarget.setParentId(parentId);
			return this;
		}

		public String name() {
			return modelTarget.getName();
		}

		public SecurityMenuBuilder name(String name) {
			modelTarget.setName(name);
			return this;
		}

		public String icon() {
			return modelTarget.getIcon();
		}

		public SecurityMenuBuilder icon(String icon) {
			modelTarget.setIcon(icon);
			return this;
		}

		public String path() {
			return modelTarget.getPath();
		}

		public SecurityMenuBuilder path(String path) {
			modelTarget.setPath(path);
			return this;
		}

		public String url() {
			return modelTarget.getUrl();
		}

		public SecurityMenuBuilder url(String url) {
			modelTarget.setUrl(url);
			return this;
		}

		public Integer sort() {
			return modelTarget.getSort();
		}

		public SecurityMenuBuilder sort(Integer sort) {
			modelTarget.setSort(sort);
			return this;
		}

	}

	/**
	 * SecurityMenu 字段常量表
	 */
	public static class FIELDS {
		public static final String ID = "id";
		public static final String CLIENT = "client";
		public static final String TYPE = "type";
		public static final String PARENT_ID = "parent_id";
		public static final String NAME = "name";
		public static final String ICON = "icon";
		public static final String PATH = "path";
		public static final String URL = "url";
		public static final String SORT = "sort";
	}


}

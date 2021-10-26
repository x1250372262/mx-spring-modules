package com.mx.spring.dev.support.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mx.spring.dev.annotation.FieldInfo;
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
	private java.lang.String id;

	/**
	* 菜单类型 0默认 1公开 2拥有者可看
	*/
	@FieldInfo(comment = "菜单类型 0默认 1公开 2拥有者可看", nullable = false)
	private java.lang.Integer type;

	/**
	* 父id
	*/
	@FieldInfo(comment = "父id", nullable = false)
	private java.lang.String parentId;

	/**
	* 菜单名称
	*/
	@FieldInfo(comment = "菜单名称", nullable = false)
	private java.lang.String name;

	/**
	* 图标
	*/
	@FieldInfo(comment = "图标")
	private java.lang.String icon;

	/**
	* 路径
	*/
	@FieldInfo(comment = "路径")
	private java.lang.String path;

	/**
	* 地址
	*/
	@FieldInfo(comment = "地址")
	private java.lang.String url;

	/**
	* 排序
	*/
	@FieldInfo(comment = "排序", nullable = false)
	private java.lang.Integer sort;


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
	 *	@param type
	 *	@param parentId
	 *	@param name
	 *	@param icon
	 *	@param path
	 *	@param url
	 *	@param sort
	 */
	public SecurityMenu(java.lang.String id, java.lang.Integer type, java.lang.String parentId, java.lang.String name, java.lang.String icon, java.lang.String path, java.lang.String url, java.lang.Integer sort) {
		this.id = id;
		this.type = type;
		this.parentId = parentId;
		this.name = name;
		this.icon = icon;
		this.path = path;
		this.url = url;
		this.sort = sort;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}


	public java.lang.Integer getType() {
		return type;
	}

	public void setType(java.lang.Integer type) {
		this.type = type;
	}

	public java.lang.String getParentId() {
		return parentId;
	}

	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getIcon() {
		return icon;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	public java.lang.String getPath() {
		return path;
	}

	public void setPath(java.lang.String path) {
		this.path = path;
	}

	public java.lang.String getUrl() {
		return url;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public java.lang.Integer getSort() {
		return sort;
	}

	public void setSort(java.lang.Integer sort) {
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

		public java.lang.String id() {
			return modelTarget.getId();
		}

		public SecurityMenuBuilder id(java.lang.String id) {
			modelTarget.setId(id);
			return this;
		}

		public java.lang.Integer type() {
			return modelTarget.getType();
		}

		public SecurityMenuBuilder type(java.lang.Integer type) {
			modelTarget.setType(type);
			return this;
		}

		public java.lang.String parentId() {
			return modelTarget.getParentId();
		}

		public SecurityMenuBuilder parentId(java.lang.String parentId) {
			modelTarget.setParentId(parentId);
			return this;
		}

		public java.lang.String name() {
			return modelTarget.getName();
		}

		public SecurityMenuBuilder name(java.lang.String name) {
			modelTarget.setName(name);
			return this;
		}

		public java.lang.String icon() {
			return modelTarget.getIcon();
		}

		public SecurityMenuBuilder icon(java.lang.String icon) {
			modelTarget.setIcon(icon);
			return this;
		}

		public java.lang.String path() {
			return modelTarget.getPath();
		}

		public SecurityMenuBuilder path(java.lang.String path) {
			modelTarget.setPath(path);
			return this;
		}

		public java.lang.String url() {
			return modelTarget.getUrl();
		}

		public SecurityMenuBuilder url(java.lang.String url) {
			modelTarget.setUrl(url);
			return this;
		}

		public java.lang.Integer sort() {
			return modelTarget.getSort();
		}

		public SecurityMenuBuilder sort(java.lang.Integer sort) {
			modelTarget.setSort(sort);
			return this;
		}

	}

	/**
	 * SecurityMenu 字段常量表
	 */
	public static class FIELDS {
		public static final String ID = "id";
		public static final String TYPE = "type";
		public static final String PARENT_ID = "parent_id";
		public static final String NAME = "name";
		public static final String ICON = "icon";
		public static final String PATH = "path";
		public static final String URL = "url";
		public static final String SORT = "sort";
	}


}

package com.mx.spring.security.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @Author: mx-maven-plugin.
 * @Date: 2021/10/08.
 * @Time: 09:22:59.
 * @Description: 2021/10/08 09:22:59 生成 Menu
 */
@TableName("mx_menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* id
	*/
	private String id;

	/**
	* 菜单类型 0默认 1公开 2拥有者可看
	*/
	private Integer type;

	/**
	* 父id
	*/
	private String parentId;

	/**
	* 菜单名称
	*/
	private String name;

	/**
	* 图标
	*/
	private String icon;

	/**
	* 路径
	*/
	private String path;

	/**
	* 地址
	*/
	private String url;

	/**
	* 排序
	*/
	private Integer sort;


	/**
	* 初始化
	*/
	public static Menu init() {
		return new Menu();
	}

	/**
	 * 构造器
	 */
	public Menu() {
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
	public Menu(String id, Integer type, String parentId, String name, String icon, String path, String url, Integer sort) {
		this.id = id;
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




	public static MenuBuilder builder() {
		return new MenuBuilder();
	}

	public MenuBuilder bind() {
    	return new MenuBuilder(this);
    }

	public static class MenuBuilder {

		private final Menu modelTarget;

		public MenuBuilder() {
			modelTarget = new Menu();
		}

		public MenuBuilder(Menu model) {
			modelTarget = model;
		}

		public Menu build() {
			return modelTarget;
		}

		public String id() {
			return modelTarget.getId();
		}

		public MenuBuilder id(String id) {
			modelTarget.setId(id);
			return this;
		}

		public Integer type() {
			return modelTarget.getType();
		}

		public MenuBuilder type(Integer type) {
			modelTarget.setType(type);
			return this;
		}

		public String parentId() {
			return modelTarget.getParentId();
		}

		public MenuBuilder parentId(String parentId) {
			modelTarget.setParentId(parentId);
			return this;
		}

		public String name() {
			return modelTarget.getName();
		}

		public MenuBuilder name(String name) {
			modelTarget.setName(name);
			return this;
		}

		public String icon() {
			return modelTarget.getIcon();
		}

		public MenuBuilder icon(String icon) {
			modelTarget.setIcon(icon);
			return this;
		}

		public String path() {
			return modelTarget.getPath();
		}

		public MenuBuilder path(String path) {
			modelTarget.setPath(path);
			return this;
		}

		public String url() {
			return modelTarget.getUrl();
		}

		public MenuBuilder url(String url) {
			modelTarget.setUrl(url);
			return this;
		}

		public Integer sort() {
			return modelTarget.getSort();
		}

		public MenuBuilder sort(Integer sort) {
			modelTarget.setSort(sort);
			return this;
		}

	}

	/**
	 * Menu 字段常量表
	 */
	public class FIELDS {
		public static final String ID = "id";
		public static final String TYPE = "type";
		public static final String PARENT_ID = "parent_id";
		public static final String NAME = "name";
		public static final String ICON = "icon";
		public static final String PATH = "path";
		public static final String URL = "url";
		public static final String SORT = "sort";
	}

	public static final String TABLE_NAME = "mx_menu";

}

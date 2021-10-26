package com.mx.spring.security.config;

import com.mx.spring.dev.bean.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mx.spring.security.config.SPConfig.GroupEnum.*;

/**
 * @Author: mengxiang.
 * @create: 2021-09-07 15:16
 * @Description: 权限配置 常量 方法
 */
public class SPConfig {

    //菜单管理
    public static final String SECURITY_MENU_LIST = "SECURITY_MENU_LIST";
    public static final String SECURITY_MENU_CREATE = "SECURITY_MENU_CREATE";
    public static final String SECURITY_MENU_UPDATE = "SECURITY_MENU_UPDATE";
    public static final String SECURITY_MENU_DETAIL = "SECURITY_MENU_DETAIL";
    public static final String SECURITY_MENU_DELETE = "SECURITY_MENU_DELETE";
    public static final String SECURITY_MENU_ROLE_LIST = "SECURITY_MENU_ROLE_LIST";
    public static final String SECURITY_MENU_ROLE_CREATE = "SECURITY_MENU_ROLE_CREATE";
    public static final String SECURITY_MENU_ROLE_DELETE = "SECURITY_MENU_ROLE_DELETE";
    //角色管理
    public static final String SECURITY_ROLE_CREATE = "SECURITY_ROLE_CREATE";
    public static final String SECURITY_ROLE_UPDATE = "SECURITY_ROLE_UPDATE";
    public static final String SECURITY_ROLE_LIST = "SECURITY_ROLE_LIST";
    public static final String SECURITY_ROLE_DETAIL = "SECURITY_ROLE_DETAIL";
    public static final String SECURITY_ROLE_DELETE = "SECURITY_ROLE_DELETE";
    public static final String SECURITY_ROLE_PERMISSION_LIST = "SECURITY_ROLE_PERMISSION_LIST";
    public static final String SECURITY_ROLE_PERMISSION_BIND = "SECURITY_ROLE_PERMISSION_BIND";
    //人员管理
    public static final String SECURITY_USER_LIST = "SECURITY_USER_LIST";
    public static final String SECURITY_USER_CREATE = "SECURITY_USER_CREATE";
    public static final String SECURITY_USER_UPDATE_STATUS = "SECURITY_USER_UPDATE_STATUS";
    public static final String SECURITY_USER_UNLOCK = "SECURITY_USER_UNLOCK";
    public static final String SECURITY_USER_RESET_PASSWORD = "SECURITY_USER_RESET_PASSWORD";
    public static final String SECURITY_USER_DETAIL = "SECURITY_USER_DETAIL";
    public static final String SECURITY_USER_ROLE_LIST = "SECURITY_USER_ROLE_LIST";
    public static final String SECURITY_USER_ROLE_CREATE = "SECURITY_USER_ROLE_CREATE";
    public static final String SECURITY_USER_ROLE_DELETE = "SECURITY_USER_ROLE_DELETE";
    //日志管理
    public static final String SECURITY_LOG_LIST = "SECURITY_LOG_LIST";
    public static final String SECURITY_LOG_DELETE = "SECURITY_LOG_DELETE";
    private static final List<Permission> permissionList = new ArrayList<>();

    static {
        //菜单管理
        permissionList.add(new Permission(SECURITY_MENU.value(), PermissionEnum.SECURITY_MENU_LIST.value(), PermissionEnum.SECURITY_MENU_LIST.name()));
        permissionList.add(new Permission(SECURITY_MENU.value(), PermissionEnum.SECURITY_MENU_CREATE.value(), PermissionEnum.SECURITY_MENU_CREATE.name()));
        permissionList.add(new Permission(SECURITY_MENU.value(), PermissionEnum.SECURITY_MENU_UPDATE.value(), PermissionEnum.SECURITY_MENU_UPDATE.name()));
        permissionList.add(new Permission(SECURITY_MENU.value(), PermissionEnum.SECURITY_MENU_DETAIL.value(), PermissionEnum.SECURITY_MENU_DETAIL.name()));
        permissionList.add(new Permission(SECURITY_MENU.value(), PermissionEnum.SECURITY_MENU_DELETE.value(), PermissionEnum.SECURITY_MENU_DELETE.name()));
        permissionList.add(new Permission(SECURITY_MENU.value(), PermissionEnum.SECURITY_MENU_ROLE_LIST.value(), PermissionEnum.SECURITY_MENU_ROLE_LIST.name()));
        permissionList.add(new Permission(SECURITY_MENU.value(), PermissionEnum.SECURITY_MENU_ROLE_CREATE.value(), PermissionEnum.SECURITY_MENU_ROLE_CREATE.name()));
        permissionList.add(new Permission(SECURITY_MENU.value(), PermissionEnum.SECURITY_MENU_ROLE_DELETE.value(), PermissionEnum.SECURITY_MENU_ROLE_DELETE.name()));
        //角色管理
        permissionList.add(new Permission(SECURITY_ROLE.value(), PermissionEnum.SECURITY_ROLE_CREATE.value(), PermissionEnum.SECURITY_ROLE_CREATE.name()));
        permissionList.add(new Permission(SECURITY_ROLE.value(), PermissionEnum.SECURITY_ROLE_UPDATE.value(), PermissionEnum.SECURITY_ROLE_UPDATE.name()));
        permissionList.add(new Permission(SECURITY_ROLE.value(), PermissionEnum.SECURITY_ROLE_LIST.value(), PermissionEnum.SECURITY_ROLE_LIST.name()));
        permissionList.add(new Permission(SECURITY_ROLE.value(), PermissionEnum.SECURITY_ROLE_DETAIL.value(), PermissionEnum.SECURITY_ROLE_DETAIL.name()));
        permissionList.add(new Permission(SECURITY_ROLE.value(), PermissionEnum.SECURITY_ROLE_DELETE.value(), PermissionEnum.SECURITY_ROLE_DELETE.name()));
        permissionList.add(new Permission(SECURITY_ROLE.value(), PermissionEnum.SECURITY_ROLE_PERMISSION_LIST.value(), PermissionEnum.SECURITY_ROLE_PERMISSION_LIST.name()));
        permissionList.add(new Permission(SECURITY_ROLE.value(), PermissionEnum.SECURITY_ROLE_PERMISSION_BIND.value(), PermissionEnum.SECURITY_ROLE_PERMISSION_BIND.name()));
        //人员管理
        permissionList.add(new Permission(SECURITY_USER.value(), PermissionEnum.SECURITY_USER_LIST.value(), PermissionEnum.SECURITY_USER_LIST.name()));
        permissionList.add(new Permission(SECURITY_USER.value(), PermissionEnum.SECURITY_USER_CREATE.value(), PermissionEnum.SECURITY_USER_CREATE.name()));
        permissionList.add(new Permission(SECURITY_USER.value(), PermissionEnum.SECURITY_USER_UPDATE_STATUS.value(), PermissionEnum.SECURITY_USER_UPDATE_STATUS.name()));
        permissionList.add(new Permission(SECURITY_USER.value(), PermissionEnum.SECURITY_USER_UNLOCK.value(), PermissionEnum.SECURITY_USER_UNLOCK.name()));
        permissionList.add(new Permission(SECURITY_USER.value(), PermissionEnum.SECURITY_USER_RESET_PASSWORD.value(), PermissionEnum.SECURITY_USER_RESET_PASSWORD.name()));
        permissionList.add(new Permission(SECURITY_USER.value(), PermissionEnum.SECURITY_USER_DETAIL.value(), PermissionEnum.SECURITY_USER_DETAIL.name()));
        permissionList.add(new Permission(SECURITY_USER.value(), PermissionEnum.SECURITY_USER_ROLE_LIST.value(), PermissionEnum.SECURITY_USER_ROLE_LIST.name()));
        permissionList.add(new Permission(SECURITY_USER.value(), PermissionEnum.SECURITY_USER_ROLE_CREATE.value(), PermissionEnum.SECURITY_USER_ROLE_CREATE.name()));
        permissionList.add(new Permission(SECURITY_USER.value(), PermissionEnum.SECURITY_USER_ROLE_DELETE.value(), PermissionEnum.SECURITY_USER_ROLE_DELETE.name()));
        //日志管理
        permissionList.add(new Permission(SECURITY_LOG.value(), PermissionEnum.SECURITY_LOG_LIST.value(), PermissionEnum.SECURITY_LOG_LIST.name()));
        permissionList.add(new Permission(SECURITY_LOG.value(), PermissionEnum.SECURITY_LOG_DELETE.value(), PermissionEnum.SECURITY_LOG_DELETE.name()));
    }

    public static List<Permission> permissionList() {
        return permissionList;
    }

    public static List<Permission> permissionList(String groupName) {
        return permissionList.stream().filter(permission -> permission.getGroupName().equals(groupName)).collect(Collectors.toList());
    }

    public enum GroupEnum {
        SECURITY_MENU("菜单管理"), SECURITY_ROLE("角色管理"), SECURITY_USER("人员管理"), SECURITY_LOG("日志管理");

        private final String value;

        GroupEnum(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    public enum PermissionEnum {

        //菜单管理
        SECURITY_MENU_LIST("菜单列表"), SECURITY_MENU_CREATE("添加菜单"), SECURITY_MENU_UPDATE("修改菜单"), SECURITY_MENU_DETAIL("菜单详情"), SECURITY_MENU_DELETE("删除菜单"), SECURITY_MENU_ROLE_LIST("菜单角色列表"), SECURITY_MENU_ROLE_CREATE("添加菜单角色"), SECURITY_MENU_ROLE_DELETE("删除菜单角色"),
        //角色管理
        SECURITY_ROLE_CREATE("添加角色"), SECURITY_ROLE_UPDATE("修改角色"), SECURITY_ROLE_LIST("角色列表"), SECURITY_ROLE_DETAIL("角色详情"), SECURITY_ROLE_DELETE("删除角色"), SECURITY_ROLE_PERMISSION_LIST("角色权限列表"), SECURITY_ROLE_PERMISSION_BIND("角色授权"),
        //人员管理
        SECURITY_USER_LIST("人员列表"), SECURITY_USER_CREATE("添加人员"), SECURITY_USER_UPDATE_STATUS("修改人员状态"), SECURITY_USER_UNLOCK("解除人员冻结"), SECURITY_USER_RESET_PASSWORD("重置人员密码"), SECURITY_USER_DETAIL("人员详情"), SECURITY_USER_ROLE_LIST("人员角色列表"), SECURITY_USER_ROLE_CREATE("添加人员角色"), SECURITY_USER_ROLE_DELETE("删除人员角色"),
        //日志管理
        SECURITY_LOG_LIST("日志列表"), SECURITY_LOG_DELETE("删除日志");

        private final String value;

        PermissionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }


}

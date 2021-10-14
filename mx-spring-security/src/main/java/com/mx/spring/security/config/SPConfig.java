package com.mx.spring.security.config;

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

    public static final String SECURITY_ROLE_NAME = "安全管理";

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


    public static List<Permission> permissionList = new ArrayList<>();

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
        SECURITY_MENU_LIST("SECURITY_MENU_LIST"), SECURITY_MENU_CREATE("SECURITY_MENU_CREATE"), SECURITY_MENU_UPDATE("SECURITY_MENU_UPDATE"), SECURITY_MENU_DETAIL("SECURITY_MENU_DETAIL"), SECURITY_MENU_DELETE("SECURITY_MENU_DELETE"), SECURITY_MENU_ROLE_LIST("SECURITY_MENU_ROLE_LIST"), SECURITY_MENU_ROLE_CREATE("SECURITY_MENU_ROLE_CREATE"), SECURITY_MENU_ROLE_DELETE("SECURITY_MENU_ROLE_DELETE"),
        //角色管理
        SECURITY_ROLE_CREATE("SECURITY_ROLE_CREATE"), SECURITY_ROLE_UPDATE("SECURITY_ROLE_UPDATE"), SECURITY_ROLE_LIST("SECURITY_ROLE_LIST"), SECURITY_ROLE_DETAIL("SECURITY_ROLE_DETAIL"), SECURITY_ROLE_DELETE("SECURITY_ROLE_DELETE"), SECURITY_ROLE_PERMISSION_LIST("SECURITY_ROLE_PERMISSION_LIST"), SECURITY_ROLE_PERMISSION_BIND("SECURITY_ROLE_PERMISSION_BIND"),
        //人员管理
        SECURITY_USER_LIST("SECURITY_USER_LIST"), SECURITY_USER_CREATE("SECURITY_USER_CREATE"), SECURITY_USER_UPDATE_STATUS("SECURITY_USER_UPDATE_STATUS"), SECURITY_USER_UNLOCK("SECURITY_USER_UNLOCK"), SECURITY_USER_RESET_PASSWORD("SECURITY_USER_RESET_PASSWORD"), SECURITY_USER_DETAIL("SECURITY_USER_DETAIL"), SECURITY_USER_ROLE_LIST("SECURITY_USER_ROLE_LIST"), SECURITY_USER_ROLE_CREATE("SECURITY_USER_ROLE_CREATE"), SECURITY_USER_ROLE_DELETE("SECURITY_USER_ROLE_DELETE"),
        //日志管理
        SECURITY_LOG_LIST("SECURITY_LOG_LIST"), SECURITY_LOG_DELETE("SECURITY_LOG_DELETE");

        private final String value;

        PermissionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    public static class Permission {

        private String groupName;

        private String name;

        private String code;

        public Permission(String groupName, String name, String code) {
            this.groupName = groupName;
            this.name = name;
            this.code = code;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }


}

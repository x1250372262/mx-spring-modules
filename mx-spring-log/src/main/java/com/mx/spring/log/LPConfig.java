package com.mx.spring.log;

import com.mx.spring.dev.bean.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: mengxiang.
 * @create: 2021-09-07 15:16
 * @Description: 权限配置 常量 方法
 */
public class LPConfig {

    //日志管理
    public static final String LOG_LIST = "LOG_LIST";
    public static final String LOG_DELETE = "LOG_DELETE";
    private static final List<Permission> permissionList = new ArrayList<>();

    static {
        //日志管理
        permissionList.add(new Permission(GroupEnum.LOG.value(), PermissionEnum.LOG_LIST.value(), PermissionEnum.LOG_LIST.name()));
        permissionList.add(new Permission(GroupEnum.LOG.value(), PermissionEnum.LOG_DELETE.value(), PermissionEnum.LOG_DELETE.name()));
    }

    public static List<Permission> permissionList() {
        return permissionList;
    }

    public static List<Permission> permissionList(String groupName) {
        return permissionList.stream().filter(permission -> permission.getGroupName().equals(groupName)).collect(Collectors.toList());
    }

    public enum GroupEnum {
        LOG("日志管理");

        private final String value;

        GroupEnum(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    public enum PermissionEnum {
        //日志管理
        LOG_LIST("日志列表"), LOG_DELETE("删除日志");

        private final String value;

        PermissionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

}

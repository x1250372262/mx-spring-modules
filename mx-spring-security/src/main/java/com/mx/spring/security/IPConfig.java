package com.mx.spring.security;

import com.mx.spring.security.bean.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 徐建鹏.
 * @create: 2021-10-18 10:08
 * @Description:
 */
public class IPConfig {

    protected static List<Permission> permissionList = new ArrayList<>();

    public static void add(Permission permission){
        permissionList.add(permission);
    }

    public static List<Permission> permissionList() {
        return permissionList;
    }

    public static List<Permission> permissionList(String groupName) {
        return permissionList.stream().filter(permission -> permission.getGroupName().equals(groupName)).collect(Collectors.toList());
    }

}

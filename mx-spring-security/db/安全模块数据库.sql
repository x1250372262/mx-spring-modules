/*
 Navicat Premium Data Transfer

 Source Server         : localhost_自己
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : mx-bdm

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 26/10/2021 11:13:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mx_security_menu
-- ----------------------------
DROP TABLE IF EXISTS `mx_security_menu`;
CREATE TABLE `mx_security_menu` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `type` smallint(1) NOT NULL DEFAULT '0' COMMENT '菜单类型 0默认 1公开 2拥有者可看',
  `parent_id` varchar(32) NOT NULL COMMENT '父id',
  `name` varchar(100) NOT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `url` text COMMENT '地址',
  `sort` int(4) NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `parent_id_idx` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Table structure for mx_security_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `mx_security_menu_role`;
CREATE TABLE `mx_security_menu_role` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `menu_id_idx` (`menu_id`),
  KEY `role_id_idx` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单角色表 ';

-- ----------------------------
-- Table structure for mx_security_permission
-- ----------------------------
DROP TABLE IF EXISTS `mx_security_permission`;
CREATE TABLE `mx_security_permission` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `group_name` varchar(32) NOT NULL COMMENT '权限组名称',
  `permission_name` varchar(32) NOT NULL COMMENT '权限名称',
  `permission_code` varchar(100) NOT NULL COMMENT '权限码',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `last_modify_user` varchar(32) NOT NULL COMMENT '最后更新人',
  `last_modify_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表 ';

-- ----------------------------
-- Table structure for mx_security_role
-- ----------------------------
DROP TABLE IF EXISTS `mx_security_role`;
CREATE TABLE `mx_security_role` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `last_modify_user` varchar(32) NOT NULL COMMENT '最后更新人',
  `last_modify_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表 ';

-- ----------------------------
-- Table structure for mx_security_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `mx_security_role_permission`;
CREATE TABLE `mx_security_role_permission` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `permisson_id` varchar(32) NOT NULL COMMENT '权限id',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `group_name` varchar(32) NOT NULL COMMENT '权限组名称',
  `permission_name` varchar(32) NOT NULL COMMENT '权限名称',
  `permission_code` varchar(100) NOT NULL COMMENT '权限码',
  PRIMARY KEY (`id`),
  KEY `role_id_idx` (`role_id`),
  KEY `permission_id_idx` (`permisson_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- Table structure for mx_security_user
-- ----------------------------
DROP TABLE IF EXISTS `mx_security_user`;
CREATE TABLE `mx_security_user` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `photo_uri` text COMMENT '头像',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `gender` smallint(1) DEFAULT '0' COMMENT '性别',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `last_modify_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  `last_modify_user` varchar(32) NOT NULL COMMENT '最后修改人',
  `salt` varchar(11) NOT NULL COMMENT '密码加密字符串',
  `disable_status` smallint(1) NOT NULL DEFAULT '0' COMMENT '禁用状态',
  `founder` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否是总管理员',
  `login_error_count` int(4) DEFAULT '0' COMMENT '登录错误次数',
  `login_lock_status` smallint(1) DEFAULT '0' COMMENT '锁定状态',
  `login_lock_start_time` bigint(13) DEFAULT '0' COMMENT '锁定开始时间',
  `login_lock_end_time` bigint(13) DEFAULT '0' COMMENT '锁定结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
-- Table structure for mx_security_user_role
-- ----------------------------
DROP TABLE IF EXISTS `mx_security_user_role`;
CREATE TABLE `mx_security_user_role` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `user_id` varchar(32) NOT NULL COMMENT '管理员id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `last_modify_user` varchar(32) NOT NULL COMMENT '最后更新人',
  `last_modify_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `admin_id_idx` (`user_id`),
  KEY `role_id_idx` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员角色表 ';

SET FOREIGN_KEY_CHECKS = 1;

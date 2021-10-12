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

 Date: 12/10/2021 13:50:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mx_admin
-- ----------------------------
DROP TABLE IF EXISTS `mx_admin`;
CREATE TABLE `mx_admin` (
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
-- Records of mx_admin
-- ----------------------------
BEGIN;
INSERT INTO `mx_admin` VALUES ('1', 'admin', '管理员', 'http://192.168.1.121:9997/IMAGE/2021/10/12/27b3226150f44ef546434c250edfb221.pic.jpg', '85f332c2017c8203adee76f7aed9fa69', '13942046422', 1, '8039b00968a14115a8133412d8d65a20', 1615858053057, 1632374314086, '1', 'rZI7BG', 0, 1, 0, 0, 0, 0);
INSERT INTO `mx_admin` VALUES ('16f2d04fc0a64e85a59b600506aabf02', 'ceshi', '测试用户', '', '45e6d9f66a373ea46f337c2528af89c3', NULL, 1, '45be91ca447b4e4eb4641c8036157321', 1634017041575, 1634017041575, '45be91ca447b4e4eb4641c8036157321', 'vh14s0', 0, 0, 0, 0, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for mx_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `mx_admin_role`;
CREATE TABLE `mx_admin_role` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `admin_id` varchar(32) NOT NULL COMMENT '管理员id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `last_modify_user` varchar(32) NOT NULL COMMENT '最后更新人',
  `last_modify_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `admin_id_idx` (`admin_id`),
  KEY `role_id_idx` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员角色表 ';

-- ----------------------------
-- Records of mx_admin_role
-- ----------------------------
BEGIN;
INSERT INTO `mx_admin_role` VALUES ('011673c41dbd461896095de82ec876fd', '16f2d04fc0a64e85a59b600506aabf02', '458006c3769141489e28b0b0fafd9c60', '45be91ca447b4e4eb4641c8036157321', 1634017090778, '45be91ca447b4e4eb4641c8036157321', 1634017090779);
COMMIT;

-- ----------------------------
-- Table structure for mx_menu
-- ----------------------------
DROP TABLE IF EXISTS `mx_menu`;
CREATE TABLE `mx_menu` (
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
-- Records of mx_menu
-- ----------------------------
BEGIN;
INSERT INTO `mx_menu` VALUES ('29193a04ef4e46bfa5ec77ae73843fb3', 0, '0', '安全管理', 'mdi mdi-alarm-light', '/security', '', 100);
INSERT INTO `mx_menu` VALUES ('29193a04ef4e46bfa5ec77ae73843fb4', 0, '29193a04ef4e46bfa5ec77ae73843fb3', '菜单管理', '', '', '/security/menu/list.html', 1);
INSERT INTO `mx_menu` VALUES ('726463c9af3c4865a7912a92175735b0', 0, '29193a04ef4e46bfa5ec77ae73843fb3', '人员管理', '', '/security/user', '/security/user/list.html', 3);
INSERT INTO `mx_menu` VALUES ('824dfa1d590e41aa9d88321b75302ddd', 0, '29193a04ef4e46bfa5ec77ae73843fb3', '角色管理', '', '', '/security/role/list.html', 2);
INSERT INTO `mx_menu` VALUES ('a4c9494d690b419595a9f823bae2cf01', 0, '29193a04ef4e46bfa5ec77ae73843fb3', '日志管理', '', '', '/security/log/list.html', 4);
COMMIT;

-- ----------------------------
-- Table structure for mx_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `mx_menu_role`;
CREATE TABLE `mx_menu_role` (
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
-- Records of mx_menu_role
-- ----------------------------
BEGIN;
INSERT INTO `mx_menu_role` VALUES ('431088c70e5941f3bdcb97d795dec27d', '726463c9af3c4865a7912a92175735b0', '458006c3769141489e28b0b0fafd9c60', '1', 1634017755751);
INSERT INTO `mx_menu_role` VALUES ('69caf543e90b457b8775625c717f5046', '29193a04ef4e46bfa5ec77ae73843fb4', '458006c3769141489e28b0b0fafd9c60', '1', 1634017741771);
INSERT INTO `mx_menu_role` VALUES ('9100598ab9b94439909dbec619d6f8ef', '824dfa1d590e41aa9d88321b75302ddd', '458006c3769141489e28b0b0fafd9c60', '1', 1634017750253);
INSERT INTO `mx_menu_role` VALUES ('cc5ebba3fb5046a0a45b3123319551ba', '29193a04ef4e46bfa5ec77ae73843fb3', '458006c3769141489e28b0b0fafd9c60', '1', 1634017618862);
COMMIT;

-- ----------------------------
-- Table structure for mx_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `mx_operation_log`;
CREATE TABLE `mx_operation_log` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `type` varchar(32) NOT NULL COMMENT '类型具体看配置',
  `type_name` varchar(32) NOT NULL COMMENT '类型名称',
  `user_id` varchar(32) NOT NULL COMMENT '操作人',
  `user_name` varchar(100) NOT NULL COMMENT '操作人名称',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `request_url` varchar(255) NOT NULL COMMENT '请求路径',
  `request_param` text COMMENT '请求参数',
  `return_code` varchar(50) NOT NULL COMMENT '返回错误码',
  `return_message` text NOT NULL COMMENT '返回错误信息',
  `return_result` text NOT NULL COMMENT '返回结果',
  `class_name` varchar(255) NOT NULL COMMENT '类名称',
  `method_name` varchar(255) NOT NULL COMMENT '方法名',
  `ip` varchar(50) NOT NULL COMMENT 'ip地址',
  `location` varchar(255) NOT NULL COMMENT '位置',
  `os` varchar(255) NOT NULL COMMENT '操作系统',
  `browser` varchar(255) NOT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';

-- ----------------------------
-- Records of mx_operation_log
-- ----------------------------
BEGIN;
INSERT INTO `mx_operation_log` VALUES ('093d10cbb22c427b92f36b479fc9c6d4', '管理员退出', 'LOGIN', '登录', '16f2d04fc0a64e85a59b600506aabf02', 'ceshi', 1634017623913, '/mx/security/admin/logout', '{\"date\":[\"1634017623899\"],\"format\":[\"json\"]}', '0', '操作成功', '{\"attrs\":{\"adminId\":\"16f2d04fc0a64e85a59b600506aabf02\"},\"code\":0,\"msg\":\"操作成功\"}', 'com.mx.spring.security.controller.AdminController', 'logout', '0:0:0:0:0:0:0:1', '', 'OSX', 'Chrome');
INSERT INTO `mx_operation_log` VALUES ('4a12bf8ab2ac475eb06dc92a7231e182', '管理员登录', 'LOGIN', '登录', '16f2d04fc0a64e85a59b600506aabf02', 'ceshi', 1634017630448, '/mx/security/admin/login', '{\"userName\":[\"ceshi\"],\"password\":[\"96e79218965eb72c92a549dd5a330112\"]}', '0', '操作成功', '{\"code\":0,\"data\":{\"adminId\":\"16f2d04fc0a64e85a59b600506aabf02\",\"tokenName\":\"mxToken\",\"tokenValue\":\"24ed255b-b060-428a-8486-4da17f329163\"},\"msg\":\"操作成功\"}', 'com.mx.spring.security.controller.AdminController', 'login', '0:0:0:0:0:0:0:1', '', 'OSX', 'Chrome');
INSERT INTO `mx_operation_log` VALUES ('613bac4c048a4b4985242ca3c0e98b13', '管理员退出', 'LOGIN', '登录', '16f2d04fc0a64e85a59b600506aabf02', 'ceshi', 1634017569327, '/mx/security/admin/logout', '{\"date\":[\"1634017569309\"],\"format\":[\"json\"]}', '0', '操作成功', '{\"attrs\":{\"adminId\":\"16f2d04fc0a64e85a59b600506aabf02\"},\"code\":0,\"msg\":\"操作成功\"}', 'com.mx.spring.security.controller.AdminController', 'logout', '0:0:0:0:0:0:0:1', '', 'OSX', 'Chrome');
INSERT INTO `mx_operation_log` VALUES ('74a4dc10f8a94b859e0c2de848936239', '管理员登录', 'LOGIN', '登录', '1', 'admin', 1634017276010, '/mx/security/admin/login', '{\"userName\":[\"admin\"],\"password\":[\"21232f297a57a5a743894a0e4a801fc3\"]}', '0', '操作成功', '{\"code\":0,\"data\":{\"adminId\":\"1\",\"tokenName\":\"mxToken\",\"tokenValue\":\"6dd94b03-eeeb-41f3-9e3a-e136d150efa1\"},\"msg\":\"操作成功\"}', 'com.mx.spring.security.controller.AdminController', 'login', '0:0:0:0:0:0:0:1', '', 'OSX', 'Chrome');
INSERT INTO `mx_operation_log` VALUES ('95abaadafef240d0b1a8fa34a260beec', '管理员登录', 'LOGIN', '登录', '16f2d04fc0a64e85a59b600506aabf02', 'ceshi', 1634017573478, '/mx/security/admin/login', '{\"userName\":[\"ceshi\"],\"password\":[\"96e79218965eb72c92a549dd5a330112\"]}', '0', '操作成功', '{\"code\":0,\"data\":{\"adminId\":\"16f2d04fc0a64e85a59b600506aabf02\",\"tokenName\":\"mxToken\",\"tokenValue\":\"150418c6-f2b4-495f-a274-941a3d73319b\"},\"msg\":\"操作成功\"}', 'com.mx.spring.security.controller.AdminController', 'login', '0:0:0:0:0:0:0:1', '', 'OSX', 'Chrome');
INSERT INTO `mx_operation_log` VALUES ('dc8a81ae72a440fa8a8c0dadcbb1024c', '管理员登录', 'LOGIN', '登录', '16f2d04fc0a64e85a59b600506aabf02', 'ceshi', 1634017058646, '/mx/security/admin/login', '{\"userName\":[\"ceshi\"],\"password\":[\"96e79218965eb72c92a549dd5a330112\"]}', '0', '操作成功', '{\"code\":0,\"data\":{\"adminId\":\"16f2d04fc0a64e85a59b600506aabf02\",\"tokenName\":\"mxToken\",\"tokenValue\":\"98cb3760-ba3d-4459-89c4-2df82e4759ba\"},\"msg\":\"操作成功\"}', 'com.mx.spring.security.controller.AdminController', 'login', '0:0:0:0:0:0:0:1', '', 'OSX', 'Chrome');
COMMIT;

-- ----------------------------
-- Table structure for mx_permission
-- ----------------------------
DROP TABLE IF EXISTS `mx_permission`;
CREATE TABLE `mx_permission` (
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
-- Records of mx_permission
-- ----------------------------
BEGIN;
INSERT INTO `mx_permission` VALUES ('0b27dbc879064aec8bd09f0ba5c2b6f3', '菜单管理', 'SECURITY_MENU_ROLE_LIST', 'SECURITY_MENU_ROLE_LIST', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('0e6650c31e174fe1a5f17c5957ab94e1', '角色管理', 'SECURITY_ROLE_CREATE', 'SECURITY_ROLE_CREATE', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('10d858bb0ec94a7a85f48880f982d015', '菜单管理', 'SECURITY_MENU_UPDATE', 'SECURITY_MENU_UPDATE', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('27d048d0e37b4e14a3b30516f952e054', '日志管理', 'SECURITY_LOG_DELETE', 'SECURITY_LOG_DELETE', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('340d548b52264de2870412d1a4e647f3', '人员管理', 'SECURITY_USER_DETAIL', 'SECURITY_USER_DETAIL', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('3756c6bc6aac478c8c3bb498ad16a181', '角色管理', 'SECURITY_ROLE_DETAIL', 'SECURITY_ROLE_DETAIL', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('379c23b3df984af9ae4d60d231620694', '菜单管理', 'SECURITY_MENU_DELETE', 'SECURITY_MENU_DELETE', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('5c00473158524741be64fb0ebad61d3d', '角色管理', 'SECURITY_ROLE_LIST', 'SECURITY_ROLE_LIST', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('787a1b6e4fd44618b0ef944e3c87b3eb', '角色管理', 'SECURITY_ROLE_PERMISSION_BIND', 'SECURITY_ROLE_PERMISSION_BIND', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('82dd237be24348d6bac1479d7d954c81', '人员管理', 'SECURITY_USER_ROLE_CREATE', 'SECURITY_USER_ROLE_CREATE', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('85020cac4e7d409580e0d5c8df0bd27e', '人员管理', 'SECURITY_USER_UNLOCK', 'SECURITY_USER_UNLOCK', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('878210bf7e734c20a68d953430e0810f', '菜单管理', 'SECURITY_MENU_LIST', 'SECURITY_MENU_LIST', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('929889405d4e417ba80c3dac6daf4629', '人员管理', 'SECURITY_USER_ROLE_DELETE', 'SECURITY_USER_ROLE_DELETE', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('92a585526fd34864a7950f70714bbd22', '菜单管理', 'SECURITY_MENU_CREATE', 'SECURITY_MENU_CREATE', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('a024a359f06e4d318b21cd2377c1c45b', '日志管理', 'SECURITY_LOG_LIST', 'SECURITY_LOG_LIST', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('ab0e9da2b27548ebbcb59499696f87a4', '角色管理', 'SECURITY_ROLE_PERMISSION_LIST', 'SECURITY_ROLE_PERMISSION_LIST', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('be717304823941a7936bcfefc79e08b1', '人员管理', 'SECURITY_USER_LIST', 'SECURITY_USER_LIST', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('bf77f222ce104d25a6ecc77eb857f91d', '人员管理', 'SECURITY_USER_ROLE_LIST', 'SECURITY_USER_ROLE_LIST', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('c29326ab745e4f1ea35d6931fd3c6ad5', '角色管理', 'SECURITY_ROLE_DELETE', 'SECURITY_ROLE_DELETE', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('c7a68b189d974a0fb955c8cab34f4030', '人员管理', 'SECURITY_USER_UPDATE_STATUS', 'SECURITY_USER_UPDATE_STATUS', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('cc6502c74473448891b8b5d45b2d7a00', '菜单管理', 'SECURITY_MENU_ROLE_CREATE', 'SECURITY_MENU_ROLE_CREATE', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('cd23eb56c4a043afab0060dbe79542ab', '菜单管理', 'SECURITY_MENU_DETAIL', 'SECURITY_MENU_DETAIL', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('eeb25803507d4773b92d64b80efeb841', '人员管理', 'SECURITY_USER_RESET_PASSWORD', 'SECURITY_USER_RESET_PASSWORD', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('ef9dbadfe51f4a1898c94be47a12607c', '角色管理', 'SECURITY_ROLE_UPDATE', 'SECURITY_ROLE_UPDATE', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('fb03c90aee61432abd878fe0d1bbdf4d', '人员管理', 'SECURITY_USER_CREATE', 'SECURITY_USER_CREATE', '1', 1634016987076, '1', 1634016987076);
INSERT INTO `mx_permission` VALUES ('ffc82af3f667474c83af0af73514d518', '菜单管理', 'SECURITY_MENU_ROLE_DELETE', 'SECURITY_MENU_ROLE_DELETE', '1', 1634016987076, '1', 1634016987076);
COMMIT;

-- ----------------------------
-- Table structure for mx_role
-- ----------------------------
DROP TABLE IF EXISTS `mx_role`;
CREATE TABLE `mx_role` (
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
-- Records of mx_role
-- ----------------------------
BEGIN;
INSERT INTO `mx_role` VALUES ('458006c3769141489e28b0b0fafd9c60', '安全管理', '安全管理', '1', 1634016987077, '1', 1634016987077);
COMMIT;

-- ----------------------------
-- Table structure for mx_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `mx_role_permission`;
CREATE TABLE `mx_role_permission` (
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
-- Records of mx_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `mx_role_permission` VALUES ('06c81b2563da43e2ba94dac13f1c713c', '458006c3769141489e28b0b0fafd9c60', '787a1b6e4fd44618b0ef944e3c87b3eb', 1634017084586, '角色管理', 'SECURITY_ROLE_PERMISSION_BIND', 'SECURITY_ROLE_PERMISSION_BIND');
INSERT INTO `mx_role_permission` VALUES ('154d1fc7af514c38892d72304f8c9aa9', '458006c3769141489e28b0b0fafd9c60', 'bf77f222ce104d25a6ecc77eb857f91d', 1634017084586, '人员管理', 'SECURITY_USER_ROLE_LIST', 'SECURITY_USER_ROLE_LIST');
INSERT INTO `mx_role_permission` VALUES ('18327be2b77e48d89f6f85b3cb8cd5bc', '458006c3769141489e28b0b0fafd9c60', '3756c6bc6aac478c8c3bb498ad16a181', 1634017084586, '角色管理', 'SECURITY_ROLE_DETAIL', 'SECURITY_ROLE_DETAIL');
INSERT INTO `mx_role_permission` VALUES ('1a08dabd3576465784e76c796395c838', '458006c3769141489e28b0b0fafd9c60', 'fb03c90aee61432abd878fe0d1bbdf4d', 1634017084586, '人员管理', 'SECURITY_USER_CREATE', 'SECURITY_USER_CREATE');
INSERT INTO `mx_role_permission` VALUES ('1c36ef117f8444a0973ef6151ee11419', '458006c3769141489e28b0b0fafd9c60', '27d048d0e37b4e14a3b30516f952e054', 1634017084586, '日志管理', 'SECURITY_LOG_DELETE', 'SECURITY_LOG_DELETE');
INSERT INTO `mx_role_permission` VALUES ('2300ca5db89f4392a2d9c3b9abc707b5', '458006c3769141489e28b0b0fafd9c60', 'ffc82af3f667474c83af0af73514d518', 1634017084586, '菜单管理', 'SECURITY_MENU_ROLE_DELETE', 'SECURITY_MENU_ROLE_DELETE');
INSERT INTO `mx_role_permission` VALUES ('28cf41fc32fe41dc8889e30fdb798544', '458006c3769141489e28b0b0fafd9c60', '10d858bb0ec94a7a85f48880f982d015', 1634017084586, '菜单管理', 'SECURITY_MENU_UPDATE', 'SECURITY_MENU_UPDATE');
INSERT INTO `mx_role_permission` VALUES ('44a53445b3f44a4c92e2a918d56392d4', '458006c3769141489e28b0b0fafd9c60', '878210bf7e734c20a68d953430e0810f', 1634017084586, '菜单管理', 'SECURITY_MENU_LIST', 'SECURITY_MENU_LIST');
INSERT INTO `mx_role_permission` VALUES ('4afd8bf87feb48e78c3a679ec58ac54d', '458006c3769141489e28b0b0fafd9c60', '0b27dbc879064aec8bd09f0ba5c2b6f3', 1634017084586, '菜单管理', 'SECURITY_MENU_ROLE_LIST', 'SECURITY_MENU_ROLE_LIST');
INSERT INTO `mx_role_permission` VALUES ('54945cb272164e66a1966c6448614de1', '458006c3769141489e28b0b0fafd9c60', '340d548b52264de2870412d1a4e647f3', 1634017084586, '人员管理', 'SECURITY_USER_DETAIL', 'SECURITY_USER_DETAIL');
INSERT INTO `mx_role_permission` VALUES ('56b975c9d44742e2aa1a0a55592935fd', '458006c3769141489e28b0b0fafd9c60', '0e6650c31e174fe1a5f17c5957ab94e1', 1634017084586, '角色管理', 'SECURITY_ROLE_CREATE', 'SECURITY_ROLE_CREATE');
INSERT INTO `mx_role_permission` VALUES ('5b6524cc9d3f463e934c22d7acddf44c', '458006c3769141489e28b0b0fafd9c60', 'cd23eb56c4a043afab0060dbe79542ab', 1634017084586, '菜单管理', 'SECURITY_MENU_DETAIL', 'SECURITY_MENU_DETAIL');
INSERT INTO `mx_role_permission` VALUES ('63aff1e3194e4ede8b582b8c292c55f5', '458006c3769141489e28b0b0fafd9c60', 'ab0e9da2b27548ebbcb59499696f87a4', 1634017084586, '角色管理', 'SECURITY_ROLE_PERMISSION_LIST', 'SECURITY_ROLE_PERMISSION_LIST');
INSERT INTO `mx_role_permission` VALUES ('71782080ce61402fb7e78cd1d5214cdf', '458006c3769141489e28b0b0fafd9c60', 'cc6502c74473448891b8b5d45b2d7a00', 1634017084586, '菜单管理', 'SECURITY_MENU_ROLE_CREATE', 'SECURITY_MENU_ROLE_CREATE');
INSERT INTO `mx_role_permission` VALUES ('738ac30a888c41d593bb7aa17c01ecbf', '458006c3769141489e28b0b0fafd9c60', 'eeb25803507d4773b92d64b80efeb841', 1634017084586, '人员管理', 'SECURITY_USER_RESET_PASSWORD', 'SECURITY_USER_RESET_PASSWORD');
INSERT INTO `mx_role_permission` VALUES ('7cbf24eea84b494fa35385866434eecc', '458006c3769141489e28b0b0fafd9c60', 'be717304823941a7936bcfefc79e08b1', 1634017084586, '人员管理', 'SECURITY_USER_LIST', 'SECURITY_USER_LIST');
INSERT INTO `mx_role_permission` VALUES ('816bc917b38240deb3087a633cd17b96', '458006c3769141489e28b0b0fafd9c60', '929889405d4e417ba80c3dac6daf4629', 1634017084586, '人员管理', 'SECURITY_USER_ROLE_DELETE', 'SECURITY_USER_ROLE_DELETE');
INSERT INTO `mx_role_permission` VALUES ('8b13079ae9ae49c4aedc43826d6693f9', '458006c3769141489e28b0b0fafd9c60', '379c23b3df984af9ae4d60d231620694', 1634017084586, '菜单管理', 'SECURITY_MENU_DELETE', 'SECURITY_MENU_DELETE');
INSERT INTO `mx_role_permission` VALUES ('8d4ede779957451db94e8d7564df61e8', '458006c3769141489e28b0b0fafd9c60', '5c00473158524741be64fb0ebad61d3d', 1634017084586, '角色管理', 'SECURITY_ROLE_LIST', 'SECURITY_ROLE_LIST');
INSERT INTO `mx_role_permission` VALUES ('931d75d5f01f41349bdb3c58759015f4', '458006c3769141489e28b0b0fafd9c60', 'c29326ab745e4f1ea35d6931fd3c6ad5', 1634017084586, '角色管理', 'SECURITY_ROLE_DELETE', 'SECURITY_ROLE_DELETE');
INSERT INTO `mx_role_permission` VALUES ('9355da2c6b87409e93bc01aed8a837d6', '458006c3769141489e28b0b0fafd9c60', '82dd237be24348d6bac1479d7d954c81', 1634017084586, '人员管理', 'SECURITY_USER_ROLE_CREATE', 'SECURITY_USER_ROLE_CREATE');
INSERT INTO `mx_role_permission` VALUES ('a857a243f09247699a88fc5a3ac09f1e', '458006c3769141489e28b0b0fafd9c60', '92a585526fd34864a7950f70714bbd22', 1634017084586, '菜单管理', 'SECURITY_MENU_CREATE', 'SECURITY_MENU_CREATE');
INSERT INTO `mx_role_permission` VALUES ('bdf10a43fdc4479db88ce4030da4cb8d', '458006c3769141489e28b0b0fafd9c60', 'a024a359f06e4d318b21cd2377c1c45b', 1634017084586, '日志管理', 'SECURITY_LOG_LIST', 'SECURITY_LOG_LIST');
INSERT INTO `mx_role_permission` VALUES ('d8ad9310a5844359a77960c1a27c5aea', '458006c3769141489e28b0b0fafd9c60', 'ef9dbadfe51f4a1898c94be47a12607c', 1634017084586, '角色管理', 'SECURITY_ROLE_UPDATE', 'SECURITY_ROLE_UPDATE');
INSERT INTO `mx_role_permission` VALUES ('edbefb7004814d8593552d896566848f', '458006c3769141489e28b0b0fafd9c60', '85020cac4e7d409580e0d5c8df0bd27e', 1634017084586, '人员管理', 'SECURITY_USER_UNLOCK', 'SECURITY_USER_UNLOCK');
INSERT INTO `mx_role_permission` VALUES ('fc54d8f18fd44abca87da0f60699a62c', '458006c3769141489e28b0b0fafd9c60', 'c7a68b189d974a0fb955c8cab34f4030', 1634017084586, '人员管理', 'SECURITY_USER_UPDATE_STATUS', 'SECURITY_USER_UPDATE_STATUS');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

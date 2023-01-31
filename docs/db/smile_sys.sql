/*
 Navicat Premium Data Transfer

 Source Server         : centos7vm
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 192.168.56.10:3306
 Source Schema         : smile_sys

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 31/01/2023 16:42:46
*/

CREATE DATABASE IF NOT EXISTS smile_sys default charset utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE smile_sys;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_demo
-- ----------------------------
DROP TABLE IF EXISTS `sys_demo`;
CREATE TABLE `sys_demo`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `money` decimal(16, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '金额',
  `money1` decimal(16, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '金额',
  `sn` bigint(0) NULL DEFAULT NULL COMMENT '流水号',
  `active` tinyint(1) NULL DEFAULT NULL COMMENT '是否激活',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_demo
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `enable_status` tinyint(1) NULL DEFAULT NULL COMMENT '启动状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'zhangsan', '123456', 1);

SET FOREIGN_KEY_CHECKS = 1;

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

 Date: 05/12/2022 18:05:58
*/

CREATE DATABASE IF NOT EXISTS smile_sys default charset utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE smile_sys;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `money` decimal(16, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '金额',
  `sn` bigint(0) NULL DEFAULT NULL COMMENT '流水号',
  `active` tinyint(1) NULL DEFAULT NULL COMMENT '是否激活',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

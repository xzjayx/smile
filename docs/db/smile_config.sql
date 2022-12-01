/*
 Navicat Premium Data Transfer

 Source Server         : centos7vm
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 192.168.56.10:3306
 Source Schema         : smile-config

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 01/12/2022 09:14:32
*/
CREATE DATABASE IF NOT EXISTS smile_config default charset utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE smile_config;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (20, 'smile-openfeign-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 8090\n  servlet:\n    context-path: /openfeign\nlogging:\n  level:\n    com.smile.ability.openfeign.customer: debug\n', '1cf4ead8644898511fbdf876424d3ca0', '2022-11-30 16:02:06', '2022-11-30 16:02:06', NULL, '192.168.56.1', '', '764f9c7a-1874-4c91-985b-0678754a8208', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (21, 'application-dev.yml', 'DEFAULT_GROUP', 'management:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\nfeign:\n  logger:\n    enable: true\n    level: FULL\n  client:\n    config:\n      ## default 设置的全局超时时间，指定服务名称可以设置单个服务的超时时间\n      default:\n        connectTimeout: 5000\n        readTimeout: 5000\nspring:\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n#这段url ignore配置必须要配置到云配置共享里面去，因为别的服务要用到        \nsmile:\n  gateway:\n    ignore:\n      urls: #配置白名单路径\n        - \"/actuator/**\"\n        - \"/auth/oauth/token\"', '8e623b705e9d8e0eba5ede437acf5218', '2022-11-30 16:02:06', '2022-11-30 16:02:06', NULL, '192.168.56.1', '', '764f9c7a-1874-4c91-985b-0678754a8208', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (22, 'smile-auth-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 8100\nspring:\n  redis:\n    database: 0\n    port: 6379\n    host: localhost\nlogging:\n  level:\n    com.smile.api.admin.feign.UserFeignClient: debug\n', '22ce004c31b7c3752908f00b2eb03a77', '2022-11-30 16:02:06', '2022-11-30 16:02:06', NULL, '192.168.56.1', '', '764f9c7a-1874-4c91-985b-0678754a8208', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (23, 'smile-sys-dev.yml', 'DEFAULT_GROUP', 'spring:\r\n  datasource:\r\n    type: com.zaxxer.hikari.HikariDataSource #springboot2.x之后默认\r\n    # serverTimezone=GMT%2B8 默认北京时区 useSSL=false 测试不用服务器的验证证书生产需要打开配置\r\n    url: jdbc:mysql://127.0.0.1:3306/smile?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: root\r\n    # 8.0之后用cj\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      #必须设置属性  maximum-pool-size  max-lifetime https://blog.csdn.net/michael_728/article/details/106914946\r\n      #连接池名字\r\n      pool-name: adminHikerPool\r\n      #最小空闲连接 此属性控制 HikariCP 尝试在池中维护的最小空闲连接数。 如果空闲连接低于此值并且池中的总连接数小于 maximumPoolSize，\r\n      #则 HikariCP 将尽最大努力快速有效地添加其他连接。 但是，为了获得最高性能和对峰值需求的响应，\r\n      #我们建议不要设置此值，而是允许 HikariCP 充当固定大小的连接池。 默认值：与 maximumPoolSize 相同 （一般配置和maximumPoolSize相同）\r\n      minimum-idle: 10\r\n      #此属性控制允许池到达的最大大小，包括空闲和正在使用的连接。 基本上，此值将确定数据库后端的最大实际连接数。\r\n      #对此的合理值最好由您的执行环境决定。 当池达到此大小，并且没有可用的空闲连接时，对 getConnection() 的调用将在超时前阻塞最多\r\n      #connectionTimeout 毫秒。 请阅读有关游泳池尺寸的信息。 默认值：10\r\n      maximum-pool-size: 10\r\n      #空闲连接存活最大时间，此属性控制允许连接在池中空闲的最长时间。 此设置仅在 minimumIdle 定义为小于maximumPoolSize 时适用。 一旦池达到 minimumIdle 连接，\r\n      #空闲连接将不会退出。 连接是否空闲退出的最大变化为 +30 秒，平均变化为 +15 秒。 在此超时之前，连接永远不会被空闲。\r\n      #值为 0 表示永远不会从池中删除空闲连接。 允许的最小值为 10000 毫秒（10秒）。 默认值：600000（10分钟） 一般不适用\r\n      idle-timeout: 600000\r\n      #此属性控制从池返回的连接的默认自动提交行为,默认值：true\r\n      auto-commit: true\r\n      #此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟 此属性控制池中连接的最长生命周期。 使用中的连接永远不会退役，\r\n      #只有当它关闭时才会被删除。 在逐个连接的基础上，应用轻微的负衰减以避免池中的大量灭绝。 我们强烈建议设置此值，它应比任何数据库\r\n      #或基础结构强加的连接时间限制短几秒。 值 0 表示没有最大生命周期（无限生命周期），当然主题是 idleTimeout 设置。 默认值：1800000（30分钟）\r\n      max-lifetime: 1800000\r\n      #数据库连接超时时间  此属性控制客户端（即您）等待池中连接的最大毫秒数。 如果在没有连接可用的情况下超过此时间，则将抛出 SQLException。\r\n      #最低可接受的连接超时为 250 毫秒。 默认值：30000（30秒）\r\n      connection-timeout: 30000\r\n  jackson:\r\n    default-property-inclusion: ALWAYS\r\n    \r\nsmile:\r\n  sys:\r\n    #配置白名单路径,专供admin模块服务\r\n    ignore:\r\n      check: \"1\"\r\n      urls:\r\n        - \"/actuator/**\"\r\n        - \"/doc.html\"\r\n        - \"/webjars/**\"\r\n        - \"/swagger-resources/**\"\r\n        - \"/v2/api-docs\"\r\n        - \"/favicon.ico\"\r\n        - \"/user/**\"\r\nserver:\r\n  port: 8110  \r\n#启用knife4j增强模式\r\nknife4j:\r\n  enable: true\r\n  #生产模式屏蔽, true将禁止访问API在线文档, false可以看到文档, 密码失效\r\n  production: false\r\n  basic:\r\n    enable: true\r\n    username: root\r\n    password: root\r\nmybatis-plus:\r\n  configuration:\r\n    # 这个配置会将执行的sql打印出来，在开发或测试的时候可以用，不能输出到文件,假如需要输出到日志文件中那么 https://www.jb51.net/article/239936.htm\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', 'e44eec8518e4eebdc5c8e8ee27d500b8', '2022-11-30 16:02:06', '2022-11-30 16:02:06', NULL, '192.168.56.1', '', '764f9c7a-1874-4c91-985b-0678754a8208', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (24, 'smile-gateway-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 9527\nspring:\n  cloud:\n    gateway:\n      #配置路由路径 id要唯一 uri: 从注册中心动态找服务名称 lb: 为load balance predicates断言path url以配置的匹配，filters过滤 截取第一个例如\n      routes:\n        - id: smile-sys\n          uri: lb://smile-sys\n          predicates:\n            - Path=/smile-sys/**\n          filters:\n            - StripPrefix=1\n        - id: smile-auth-resource\n          uri: lb://smile-auth-resource\n          predicates:\n            - Path=/api/**\n          filters:\n            - StripPrefix=1\n        - id: smile-auth\n          uri: lb://smile-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n      discovery:\n        locator:\n          enabled: true #开启从注册中心动态创建路由的功能\n          lower-case-service-id: true #使用小写服务名，默认是大写\n  security:\n    oauth2:\n      resourceserver:\n        jwt:\n          jwk-set-uri: \'http://localhost:8100/rsa/publicKey\' #配置RSA的公钥访问地址\n  redis:\n    database: 0\n    port: 6379\n    host: localhost\n    password:\n', '6539b6634da3931eead580227e634002', '2022-11-30 16:02:06', '2022-11-30 16:02:06', NULL, '192.168.56.1', '', '764f9c7a-1874-4c91-985b-0678754a8208', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (26, 'smile-openfeign-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 8090\n  servlet:\n    context-path: /openfeign\nlogging:\n  level:\n    com.smile.ability.openfeign.customer: debug\n', '1cf4ead8644898511fbdf876424d3ca0', '2022-11-30 16:02:29', '2022-11-30 16:02:29', NULL, '192.168.56.1', '', '74a54390-4ff2-4f7e-bc44-956281fe4866', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (29, 'smile-sys-dev.yml', 'DEFAULT_GROUP', 'spring:\r\n  datasource:\r\n    type: com.zaxxer.hikari.HikariDataSource #springboot2.x之后默认\r\n    # serverTimezone=GMT%2B8 默认北京时区 useSSL=false 测试不用服务器的验证证书生产需要打开配置\r\n    url: jdbc:mysql://127.0.0.1:3306/smile?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: root\r\n    # 8.0之后用cj\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      #必须设置属性  maximum-pool-size  max-lifetime https://blog.csdn.net/michael_728/article/details/106914946\r\n      #连接池名字\r\n      pool-name: adminHikerPool\r\n      #最小空闲连接 此属性控制 HikariCP 尝试在池中维护的最小空闲连接数。 如果空闲连接低于此值并且池中的总连接数小于 maximumPoolSize，\r\n      #则 HikariCP 将尽最大努力快速有效地添加其他连接。 但是，为了获得最高性能和对峰值需求的响应，\r\n      #我们建议不要设置此值，而是允许 HikariCP 充当固定大小的连接池。 默认值：与 maximumPoolSize 相同 （一般配置和maximumPoolSize相同）\r\n      minimum-idle: 10\r\n      #此属性控制允许池到达的最大大小，包括空闲和正在使用的连接。 基本上，此值将确定数据库后端的最大实际连接数。\r\n      #对此的合理值最好由您的执行环境决定。 当池达到此大小，并且没有可用的空闲连接时，对 getConnection() 的调用将在超时前阻塞最多\r\n      #connectionTimeout 毫秒。 请阅读有关游泳池尺寸的信息。 默认值：10\r\n      maximum-pool-size: 10\r\n      #空闲连接存活最大时间，此属性控制允许连接在池中空闲的最长时间。 此设置仅在 minimumIdle 定义为小于maximumPoolSize 时适用。 一旦池达到 minimumIdle 连接，\r\n      #空闲连接将不会退出。 连接是否空闲退出的最大变化为 +30 秒，平均变化为 +15 秒。 在此超时之前，连接永远不会被空闲。\r\n      #值为 0 表示永远不会从池中删除空闲连接。 允许的最小值为 10000 毫秒（10秒）。 默认值：600000（10分钟） 一般不适用\r\n      idle-timeout: 600000\r\n      #此属性控制从池返回的连接的默认自动提交行为,默认值：true\r\n      auto-commit: true\r\n      #此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟 此属性控制池中连接的最长生命周期。 使用中的连接永远不会退役，\r\n      #只有当它关闭时才会被删除。 在逐个连接的基础上，应用轻微的负衰减以避免池中的大量灭绝。 我们强烈建议设置此值，它应比任何数据库\r\n      #或基础结构强加的连接时间限制短几秒。 值 0 表示没有最大生命周期（无限生命周期），当然主题是 idleTimeout 设置。 默认值：1800000（30分钟）\r\n      max-lifetime: 1800000\r\n      #数据库连接超时时间  此属性控制客户端（即您）等待池中连接的最大毫秒数。 如果在没有连接可用的情况下超过此时间，则将抛出 SQLException。\r\n      #最低可接受的连接超时为 250 毫秒。 默认值：30000（30秒）\r\n      connection-timeout: 30000\r\n  jackson:\r\n    default-property-inclusion: ALWAYS\r\n    \r\nsmile:\r\n  sys:\r\n    #配置白名单路径,专供admin模块服务\r\n    ignore:\r\n      check: \"1\"\r\n      urls:\r\n        - \"/actuator/**\"\r\n        - \"/doc.html\"\r\n        - \"/webjars/**\"\r\n        - \"/swagger-resources/**\"\r\n        - \"/v2/api-docs\"\r\n        - \"/favicon.ico\"\r\n        - \"/user/**\"\r\nserver:\r\n  port: 8110  \r\n#启用knife4j增强模式\r\nknife4j:\r\n  enable: true\r\n  #生产模式屏蔽, true将禁止访问API在线文档, false可以看到文档, 密码失效\r\n  production: false\r\n  basic:\r\n    enable: true\r\n    username: root\r\n    password: root\r\nmybatis-plus:\r\n  configuration:\r\n    # 这个配置会将执行的sql打印出来，在开发或测试的时候可以用，不能输出到文件,假如需要输出到日志文件中那么 https://www.jb51.net/article/239936.htm\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', 'e44eec8518e4eebdc5c8e8ee27d500b8', '2022-11-30 16:02:29', '2022-11-30 16:02:29', NULL, '192.168.56.1', '', '74a54390-4ff2-4f7e-bc44-956281fe4866', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (30, 'smile-gateway-dev.yml', 'DEFAULT_GROUP', 'server:\n  port: 9527\nspring:\n  cloud:\n    gateway:\n      #配置路由路径 id要唯一 uri: 从注册中心动态找服务名称 lb: 为load balance predicates断言path url以配置的匹配，filters过滤 截取第一个例如\n      routes:\n        - id: smile-sys\n          uri: lb://smile-sys\n          predicates:\n            - Path=/smile-sys/**\n          filters:\n            - StripPrefix=1\n        - id: smile-auth-resource\n          uri: lb://smile-auth-resource\n          predicates:\n            - Path=/api/**\n          filters:\n            - StripPrefix=1\n        - id: smile-auth\n          uri: lb://smile-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n      discovery:\n        locator:\n          enabled: true #开启从注册中心动态创建路由的功能\n          lower-case-service-id: true #使用小写服务名，默认是大写\n  security:\n    oauth2:\n      resourceserver:\n        jwt:\n          jwk-set-uri: \'http://localhost:8100/rsa/publicKey\' #配置RSA的公钥访问地址\n  redis:\n    database: 0\n    port: 6379\n    host: localhost\n    password:\n', '6539b6634da3931eead580227e634002', '2022-11-30 16:02:29', '2022-11-30 16:02:29', NULL, '192.168.56.1', '', '74a54390-4ff2-4f7e-bc44-956281fe4866', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (31, 'smile-auth-ddev.yml', 'DEFAULT_GROUP', 'server:\n  port: 8100\nspring:\n  redis:\n    database: 0\n    port: 6379\n    host: redis_6.0.8\nlogging:\n  level:\n    com.smile.api.admin.feign.UserFeignClient: debug', '960563720e6c8cf7b7dd1ab8f36a17f4', '2022-11-30 16:02:29', '2022-11-30 16:02:29', NULL, '192.168.56.1', '', '74a54390-4ff2-4f7e-bc44-956281fe4866', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (32, 'application-ddev.yml', 'DEFAULT_GROUP', 'management:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\nfeign:\n  logger:\n    enable: true\n    level: FULL\n  client:\n    config:\n      ## default 设置的全局超时时间，指定服务名称可以设置单个服务的超时时间\n      default:\n        connectTimeout: 5000\n        readTimeout: 5000\nspring:\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n#这段url ignore配置必须要配置到云配置共享里面去，因为别的服务要用到        \nsmile:\n  gateway:\n    ignore:\n      urls: #配置白名单路径\n        - \"/actuator/**\"\n        - \"/auth/oauth/token\"', '8e623b705e9d8e0eba5ede437acf5218', '2022-11-30 16:04:00', '2022-11-30 16:04:30', NULL, '192.168.56.1', '', '74a54390-4ff2-4f7e-bc44-956281fe4866', 'smile docker 自定义网关的公共共享配置', '', '', 'yaml', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(0) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(0) UNSIGNED NOT NULL,
  `nid` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL,
  `src_ip` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (3, 34, 'smile-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 8100\nspring:\n  redis:\n    database: 0\n    port: 6379\n    host: localhost\nlogging:\n  level:\n    com.smile.api.admin.feign.UserFeignClient: debug\n', '22ce004c31b7c3752908f00b2eb03a77', '2010-05-05 00:00:00', '2022-11-29 15:00:50', NULL, '192.168.56.1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 35, 'smile-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 8100\nspring:\n  redis:\n    database: 0\n    port: 6379\n    host: redis_6.0.8\nlogging:\n  level:\n    com.smile.api.admin.feign.UserFeignClient: debug\n', '76d07745635bc994e2a4d69449788868', '2010-05-05 00:00:00', '2022-11-29 15:35:06', NULL, '192.168.56.1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 36, 'smile-auth-ddev.yml', 'DEFAULT_GROUP', '', '1', 'c4ca4238a0b923820dcc509a6f75849b', '2010-05-05 00:00:00', '2022-11-29 16:36:13', NULL, '192.168.56.1', 'I', '');
INSERT INTO `his_config_info` VALUES (18, 37, 'smile-auth-ddev.yml', 'DEFAULT_GROUP', '', '1', 'c4ca4238a0b923820dcc509a6f75849b', '2010-05-05 00:00:00', '2022-11-29 16:36:54', NULL, '192.168.56.1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 38, 'smile-openfeign-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 8090\n  servlet:\n    context-path: /openfeign\nlogging:\n  level:\n    com.smile.ability.openfeign.customer: debug\n', '1cf4ead8644898511fbdf876424d3ca0', '2010-05-05 00:00:00', '2022-11-30 16:02:06', NULL, '192.168.56.1', 'I', '764f9c7a-1874-4c91-985b-0678754a8208');
INSERT INTO `his_config_info` VALUES (0, 39, 'application-dev.yml', 'DEFAULT_GROUP', '', 'management:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\nfeign:\n  logger:\n    enable: true\n    level: FULL\n  client:\n    config:\n      ## default 设置的全局超时时间，指定服务名称可以设置单个服务的超时时间\n      default:\n        connectTimeout: 5000\n        readTimeout: 5000\nspring:\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n#这段url ignore配置必须要配置到云配置共享里面去，因为别的服务要用到        \nsmile:\n  gateway:\n    ignore:\n      urls: #配置白名单路径\n        - \"/actuator/**\"\n        - \"/auth/oauth/token\"', '8e623b705e9d8e0eba5ede437acf5218', '2010-05-05 00:00:00', '2022-11-30 16:02:06', NULL, '192.168.56.1', 'I', '764f9c7a-1874-4c91-985b-0678754a8208');
INSERT INTO `his_config_info` VALUES (0, 40, 'smile-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 8100\nspring:\n  redis:\n    database: 0\n    port: 6379\n    host: localhost\nlogging:\n  level:\n    com.smile.api.admin.feign.UserFeignClient: debug\n', '22ce004c31b7c3752908f00b2eb03a77', '2010-05-05 00:00:00', '2022-11-30 16:02:06', NULL, '192.168.56.1', 'I', '764f9c7a-1874-4c91-985b-0678754a8208');
INSERT INTO `his_config_info` VALUES (0, 41, 'smile-sys-dev.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    type: com.zaxxer.hikari.HikariDataSource #springboot2.x之后默认\r\n    # serverTimezone=GMT%2B8 默认北京时区 useSSL=false 测试不用服务器的验证证书生产需要打开配置\r\n    url: jdbc:mysql://127.0.0.1:3306/smile?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: root\r\n    # 8.0之后用cj\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      #必须设置属性  maximum-pool-size  max-lifetime https://blog.csdn.net/michael_728/article/details/106914946\r\n      #连接池名字\r\n      pool-name: adminHikerPool\r\n      #最小空闲连接 此属性控制 HikariCP 尝试在池中维护的最小空闲连接数。 如果空闲连接低于此值并且池中的总连接数小于 maximumPoolSize，\r\n      #则 HikariCP 将尽最大努力快速有效地添加其他连接。 但是，为了获得最高性能和对峰值需求的响应，\r\n      #我们建议不要设置此值，而是允许 HikariCP 充当固定大小的连接池。 默认值：与 maximumPoolSize 相同 （一般配置和maximumPoolSize相同）\r\n      minimum-idle: 10\r\n      #此属性控制允许池到达的最大大小，包括空闲和正在使用的连接。 基本上，此值将确定数据库后端的最大实际连接数。\r\n      #对此的合理值最好由您的执行环境决定。 当池达到此大小，并且没有可用的空闲连接时，对 getConnection() 的调用将在超时前阻塞最多\r\n      #connectionTimeout 毫秒。 请阅读有关游泳池尺寸的信息。 默认值：10\r\n      maximum-pool-size: 10\r\n      #空闲连接存活最大时间，此属性控制允许连接在池中空闲的最长时间。 此设置仅在 minimumIdle 定义为小于maximumPoolSize 时适用。 一旦池达到 minimumIdle 连接，\r\n      #空闲连接将不会退出。 连接是否空闲退出的最大变化为 +30 秒，平均变化为 +15 秒。 在此超时之前，连接永远不会被空闲。\r\n      #值为 0 表示永远不会从池中删除空闲连接。 允许的最小值为 10000 毫秒（10秒）。 默认值：600000（10分钟） 一般不适用\r\n      idle-timeout: 600000\r\n      #此属性控制从池返回的连接的默认自动提交行为,默认值：true\r\n      auto-commit: true\r\n      #此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟 此属性控制池中连接的最长生命周期。 使用中的连接永远不会退役，\r\n      #只有当它关闭时才会被删除。 在逐个连接的基础上，应用轻微的负衰减以避免池中的大量灭绝。 我们强烈建议设置此值，它应比任何数据库\r\n      #或基础结构强加的连接时间限制短几秒。 值 0 表示没有最大生命周期（无限生命周期），当然主题是 idleTimeout 设置。 默认值：1800000（30分钟）\r\n      max-lifetime: 1800000\r\n      #数据库连接超时时间  此属性控制客户端（即您）等待池中连接的最大毫秒数。 如果在没有连接可用的情况下超过此时间，则将抛出 SQLException。\r\n      #最低可接受的连接超时为 250 毫秒。 默认值：30000（30秒）\r\n      connection-timeout: 30000\r\n  jackson:\r\n    default-property-inclusion: ALWAYS\r\n    \r\nsmile:\r\n  sys:\r\n    #配置白名单路径,专供admin模块服务\r\n    ignore:\r\n      check: \"1\"\r\n      urls:\r\n        - \"/actuator/**\"\r\n        - \"/doc.html\"\r\n        - \"/webjars/**\"\r\n        - \"/swagger-resources/**\"\r\n        - \"/v2/api-docs\"\r\n        - \"/favicon.ico\"\r\n        - \"/user/**\"\r\nserver:\r\n  port: 8110  \r\n#启用knife4j增强模式\r\nknife4j:\r\n  enable: true\r\n  #生产模式屏蔽, true将禁止访问API在线文档, false可以看到文档, 密码失效\r\n  production: false\r\n  basic:\r\n    enable: true\r\n    username: root\r\n    password: root\r\nmybatis-plus:\r\n  configuration:\r\n    # 这个配置会将执行的sql打印出来，在开发或测试的时候可以用，不能输出到文件,假如需要输出到日志文件中那么 https://www.jb51.net/article/239936.htm\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', 'e44eec8518e4eebdc5c8e8ee27d500b8', '2010-05-05 00:00:00', '2022-11-30 16:02:06', NULL, '192.168.56.1', 'I', '764f9c7a-1874-4c91-985b-0678754a8208');
INSERT INTO `his_config_info` VALUES (0, 42, 'smile-gateway-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 9527\nspring:\n  cloud:\n    gateway:\n      #配置路由路径 id要唯一 uri: 从注册中心动态找服务名称 lb: 为load balance predicates断言path url以配置的匹配，filters过滤 截取第一个例如\n      routes:\n        - id: smile-sys\n          uri: lb://smile-sys\n          predicates:\n            - Path=/smile-sys/**\n          filters:\n            - StripPrefix=1\n        - id: smile-auth-resource\n          uri: lb://smile-auth-resource\n          predicates:\n            - Path=/api/**\n          filters:\n            - StripPrefix=1\n        - id: smile-auth\n          uri: lb://smile-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n      discovery:\n        locator:\n          enabled: true #开启从注册中心动态创建路由的功能\n          lower-case-service-id: true #使用小写服务名，默认是大写\n  security:\n    oauth2:\n      resourceserver:\n        jwt:\n          jwk-set-uri: \'http://localhost:8100/rsa/publicKey\' #配置RSA的公钥访问地址\n  redis:\n    database: 0\n    port: 6379\n    host: localhost\n    password:\n', '6539b6634da3931eead580227e634002', '2010-05-05 00:00:00', '2022-11-30 16:02:06', NULL, '192.168.56.1', 'I', '764f9c7a-1874-4c91-985b-0678754a8208');
INSERT INTO `his_config_info` VALUES (0, 43, 'smile-auth-ddev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 8100\nspring:\n  redis:\n    database: 0\n    port: 6379\n    host: redis_6.0.8\nlogging:\n  level:\n    com.smile.api.admin.feign.UserFeignClient: debug', '960563720e6c8cf7b7dd1ab8f36a17f4', '2010-05-05 00:00:00', '2022-11-30 16:02:06', NULL, '192.168.56.1', 'I', '764f9c7a-1874-4c91-985b-0678754a8208');
INSERT INTO `his_config_info` VALUES (1, 44, 'smile-openfeign-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 8090\n  servlet:\n    context-path: /openfeign\nlogging:\n  level:\n    com.smile.ability.openfeign.customer: debug\n', '1cf4ead8644898511fbdf876424d3ca0', '2010-05-05 00:00:00', '2022-11-30 16:02:16', NULL, '192.168.56.1', 'D', '');
INSERT INTO `his_config_info` VALUES (2, 45, 'application-dev.yml', 'DEFAULT_GROUP', '', 'management:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\nfeign:\n  logger:\n    enable: true\n    level: FULL\n  client:\n    config:\n      ## default 设置的全局超时时间，指定服务名称可以设置单个服务的超时时间\n      default:\n        connectTimeout: 5000\n        readTimeout: 5000\nspring:\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n#这段url ignore配置必须要配置到云配置共享里面去，因为别的服务要用到        \nsmile:\n  gateway:\n    ignore:\n      urls: #配置白名单路径\n        - \"/actuator/**\"\n        - \"/auth/oauth/token\"', '8e623b705e9d8e0eba5ede437acf5218', '2010-05-05 00:00:00', '2022-11-30 16:02:16', NULL, '192.168.56.1', 'D', '');
INSERT INTO `his_config_info` VALUES (3, 46, 'smile-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 8100\nspring:\n  redis:\n    database: 0\n    port: 6379\n    host: localhost\nlogging:\n  level:\n    com.smile.api.admin.feign.UserFeignClient: debug\n', '22ce004c31b7c3752908f00b2eb03a77', '2010-05-05 00:00:00', '2022-11-30 16:02:16', NULL, '192.168.56.1', 'D', '');
INSERT INTO `his_config_info` VALUES (14, 47, 'smile-sys-dev.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    type: com.zaxxer.hikari.HikariDataSource #springboot2.x之后默认\r\n    # serverTimezone=GMT%2B8 默认北京时区 useSSL=false 测试不用服务器的验证证书生产需要打开配置\r\n    url: jdbc:mysql://127.0.0.1:3306/smile?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: root\r\n    # 8.0之后用cj\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      #必须设置属性  maximum-pool-size  max-lifetime https://blog.csdn.net/michael_728/article/details/106914946\r\n      #连接池名字\r\n      pool-name: adminHikerPool\r\n      #最小空闲连接 此属性控制 HikariCP 尝试在池中维护的最小空闲连接数。 如果空闲连接低于此值并且池中的总连接数小于 maximumPoolSize，\r\n      #则 HikariCP 将尽最大努力快速有效地添加其他连接。 但是，为了获得最高性能和对峰值需求的响应，\r\n      #我们建议不要设置此值，而是允许 HikariCP 充当固定大小的连接池。 默认值：与 maximumPoolSize 相同 （一般配置和maximumPoolSize相同）\r\n      minimum-idle: 10\r\n      #此属性控制允许池到达的最大大小，包括空闲和正在使用的连接。 基本上，此值将确定数据库后端的最大实际连接数。\r\n      #对此的合理值最好由您的执行环境决定。 当池达到此大小，并且没有可用的空闲连接时，对 getConnection() 的调用将在超时前阻塞最多\r\n      #connectionTimeout 毫秒。 请阅读有关游泳池尺寸的信息。 默认值：10\r\n      maximum-pool-size: 10\r\n      #空闲连接存活最大时间，此属性控制允许连接在池中空闲的最长时间。 此设置仅在 minimumIdle 定义为小于maximumPoolSize 时适用。 一旦池达到 minimumIdle 连接，\r\n      #空闲连接将不会退出。 连接是否空闲退出的最大变化为 +30 秒，平均变化为 +15 秒。 在此超时之前，连接永远不会被空闲。\r\n      #值为 0 表示永远不会从池中删除空闲连接。 允许的最小值为 10000 毫秒（10秒）。 默认值：600000（10分钟） 一般不适用\r\n      idle-timeout: 600000\r\n      #此属性控制从池返回的连接的默认自动提交行为,默认值：true\r\n      auto-commit: true\r\n      #此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟 此属性控制池中连接的最长生命周期。 使用中的连接永远不会退役，\r\n      #只有当它关闭时才会被删除。 在逐个连接的基础上，应用轻微的负衰减以避免池中的大量灭绝。 我们强烈建议设置此值，它应比任何数据库\r\n      #或基础结构强加的连接时间限制短几秒。 值 0 表示没有最大生命周期（无限生命周期），当然主题是 idleTimeout 设置。 默认值：1800000（30分钟）\r\n      max-lifetime: 1800000\r\n      #数据库连接超时时间  此属性控制客户端（即您）等待池中连接的最大毫秒数。 如果在没有连接可用的情况下超过此时间，则将抛出 SQLException。\r\n      #最低可接受的连接超时为 250 毫秒。 默认值：30000（30秒）\r\n      connection-timeout: 30000\r\n  jackson:\r\n    default-property-inclusion: ALWAYS\r\n    \r\nsmile:\r\n  sys:\r\n    #配置白名单路径,专供admin模块服务\r\n    ignore:\r\n      check: \"1\"\r\n      urls:\r\n        - \"/actuator/**\"\r\n        - \"/doc.html\"\r\n        - \"/webjars/**\"\r\n        - \"/swagger-resources/**\"\r\n        - \"/v2/api-docs\"\r\n        - \"/favicon.ico\"\r\n        - \"/user/**\"\r\nserver:\r\n  port: 8110  \r\n#启用knife4j增强模式\r\nknife4j:\r\n  enable: true\r\n  #生产模式屏蔽, true将禁止访问API在线文档, false可以看到文档, 密码失效\r\n  production: false\r\n  basic:\r\n    enable: true\r\n    username: root\r\n    password: root\r\nmybatis-plus:\r\n  configuration:\r\n    # 这个配置会将执行的sql打印出来，在开发或测试的时候可以用，不能输出到文件,假如需要输出到日志文件中那么 https://www.jb51.net/article/239936.htm\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', 'e44eec8518e4eebdc5c8e8ee27d500b8', '2010-05-05 00:00:00', '2022-11-30 16:02:16', NULL, '192.168.56.1', 'D', '');
INSERT INTO `his_config_info` VALUES (15, 48, 'smile-gateway-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 9527\nspring:\n  cloud:\n    gateway:\n      #配置路由路径 id要唯一 uri: 从注册中心动态找服务名称 lb: 为load balance predicates断言path url以配置的匹配，filters过滤 截取第一个例如\n      routes:\n        - id: smile-sys\n          uri: lb://smile-sys\n          predicates:\n            - Path=/smile-sys/**\n          filters:\n            - StripPrefix=1\n        - id: smile-auth-resource\n          uri: lb://smile-auth-resource\n          predicates:\n            - Path=/api/**\n          filters:\n            - StripPrefix=1\n        - id: smile-auth\n          uri: lb://smile-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n      discovery:\n        locator:\n          enabled: true #开启从注册中心动态创建路由的功能\n          lower-case-service-id: true #使用小写服务名，默认是大写\n  security:\n    oauth2:\n      resourceserver:\n        jwt:\n          jwk-set-uri: \'http://localhost:8100/rsa/publicKey\' #配置RSA的公钥访问地址\n  redis:\n    database: 0\n    port: 6379\n    host: localhost\n    password:\n', '6539b6634da3931eead580227e634002', '2010-05-05 00:00:00', '2022-11-30 16:02:16', NULL, '192.168.56.1', 'D', '');
INSERT INTO `his_config_info` VALUES (18, 49, 'smile-auth-ddev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 8100\nspring:\n  redis:\n    database: 0\n    port: 6379\n    host: redis_6.0.8\nlogging:\n  level:\n    com.smile.api.admin.feign.UserFeignClient: debug', '960563720e6c8cf7b7dd1ab8f36a17f4', '2010-05-05 00:00:00', '2022-11-30 16:02:16', NULL, '192.168.56.1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 50, 'smile-openfeign-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 8090\n  servlet:\n    context-path: /openfeign\nlogging:\n  level:\n    com.smile.ability.openfeign.customer: debug\n', '1cf4ead8644898511fbdf876424d3ca0', '2010-05-05 00:00:00', '2022-11-30 16:02:29', NULL, '192.168.56.1', 'I', '74a54390-4ff2-4f7e-bc44-956281fe4866');
INSERT INTO `his_config_info` VALUES (0, 51, 'application-dev.yml', 'DEFAULT_GROUP', '', 'management:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\nfeign:\n  logger:\n    enable: true\n    level: FULL\n  client:\n    config:\n      ## default 设置的全局超时时间，指定服务名称可以设置单个服务的超时时间\n      default:\n        connectTimeout: 5000\n        readTimeout: 5000\nspring:\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n#这段url ignore配置必须要配置到云配置共享里面去，因为别的服务要用到        \nsmile:\n  gateway:\n    ignore:\n      urls: #配置白名单路径\n        - \"/actuator/**\"\n        - \"/auth/oauth/token\"', '8e623b705e9d8e0eba5ede437acf5218', '2010-05-05 00:00:00', '2022-11-30 16:02:29', NULL, '192.168.56.1', 'I', '74a54390-4ff2-4f7e-bc44-956281fe4866');
INSERT INTO `his_config_info` VALUES (0, 52, 'smile-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 8100\nspring:\n  redis:\n    database: 0\n    port: 6379\n    host: localhost\nlogging:\n  level:\n    com.smile.api.admin.feign.UserFeignClient: debug\n', '22ce004c31b7c3752908f00b2eb03a77', '2010-05-05 00:00:00', '2022-11-30 16:02:29', NULL, '192.168.56.1', 'I', '74a54390-4ff2-4f7e-bc44-956281fe4866');
INSERT INTO `his_config_info` VALUES (0, 53, 'smile-sys-dev.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    type: com.zaxxer.hikari.HikariDataSource #springboot2.x之后默认\r\n    # serverTimezone=GMT%2B8 默认北京时区 useSSL=false 测试不用服务器的验证证书生产需要打开配置\r\n    url: jdbc:mysql://127.0.0.1:3306/smile?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: root\r\n    # 8.0之后用cj\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      #必须设置属性  maximum-pool-size  max-lifetime https://blog.csdn.net/michael_728/article/details/106914946\r\n      #连接池名字\r\n      pool-name: adminHikerPool\r\n      #最小空闲连接 此属性控制 HikariCP 尝试在池中维护的最小空闲连接数。 如果空闲连接低于此值并且池中的总连接数小于 maximumPoolSize，\r\n      #则 HikariCP 将尽最大努力快速有效地添加其他连接。 但是，为了获得最高性能和对峰值需求的响应，\r\n      #我们建议不要设置此值，而是允许 HikariCP 充当固定大小的连接池。 默认值：与 maximumPoolSize 相同 （一般配置和maximumPoolSize相同）\r\n      minimum-idle: 10\r\n      #此属性控制允许池到达的最大大小，包括空闲和正在使用的连接。 基本上，此值将确定数据库后端的最大实际连接数。\r\n      #对此的合理值最好由您的执行环境决定。 当池达到此大小，并且没有可用的空闲连接时，对 getConnection() 的调用将在超时前阻塞最多\r\n      #connectionTimeout 毫秒。 请阅读有关游泳池尺寸的信息。 默认值：10\r\n      maximum-pool-size: 10\r\n      #空闲连接存活最大时间，此属性控制允许连接在池中空闲的最长时间。 此设置仅在 minimumIdle 定义为小于maximumPoolSize 时适用。 一旦池达到 minimumIdle 连接，\r\n      #空闲连接将不会退出。 连接是否空闲退出的最大变化为 +30 秒，平均变化为 +15 秒。 在此超时之前，连接永远不会被空闲。\r\n      #值为 0 表示永远不会从池中删除空闲连接。 允许的最小值为 10000 毫秒（10秒）。 默认值：600000（10分钟） 一般不适用\r\n      idle-timeout: 600000\r\n      #此属性控制从池返回的连接的默认自动提交行为,默认值：true\r\n      auto-commit: true\r\n      #此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟 此属性控制池中连接的最长生命周期。 使用中的连接永远不会退役，\r\n      #只有当它关闭时才会被删除。 在逐个连接的基础上，应用轻微的负衰减以避免池中的大量灭绝。 我们强烈建议设置此值，它应比任何数据库\r\n      #或基础结构强加的连接时间限制短几秒。 值 0 表示没有最大生命周期（无限生命周期），当然主题是 idleTimeout 设置。 默认值：1800000（30分钟）\r\n      max-lifetime: 1800000\r\n      #数据库连接超时时间  此属性控制客户端（即您）等待池中连接的最大毫秒数。 如果在没有连接可用的情况下超过此时间，则将抛出 SQLException。\r\n      #最低可接受的连接超时为 250 毫秒。 默认值：30000（30秒）\r\n      connection-timeout: 30000\r\n  jackson:\r\n    default-property-inclusion: ALWAYS\r\n    \r\nsmile:\r\n  sys:\r\n    #配置白名单路径,专供admin模块服务\r\n    ignore:\r\n      check: \"1\"\r\n      urls:\r\n        - \"/actuator/**\"\r\n        - \"/doc.html\"\r\n        - \"/webjars/**\"\r\n        - \"/swagger-resources/**\"\r\n        - \"/v2/api-docs\"\r\n        - \"/favicon.ico\"\r\n        - \"/user/**\"\r\nserver:\r\n  port: 8110  \r\n#启用knife4j增强模式\r\nknife4j:\r\n  enable: true\r\n  #生产模式屏蔽, true将禁止访问API在线文档, false可以看到文档, 密码失效\r\n  production: false\r\n  basic:\r\n    enable: true\r\n    username: root\r\n    password: root\r\nmybatis-plus:\r\n  configuration:\r\n    # 这个配置会将执行的sql打印出来，在开发或测试的时候可以用，不能输出到文件,假如需要输出到日志文件中那么 https://www.jb51.net/article/239936.htm\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', 'e44eec8518e4eebdc5c8e8ee27d500b8', '2010-05-05 00:00:00', '2022-11-30 16:02:29', NULL, '192.168.56.1', 'I', '74a54390-4ff2-4f7e-bc44-956281fe4866');
INSERT INTO `his_config_info` VALUES (0, 54, 'smile-gateway-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 9527\nspring:\n  cloud:\n    gateway:\n      #配置路由路径 id要唯一 uri: 从注册中心动态找服务名称 lb: 为load balance predicates断言path url以配置的匹配，filters过滤 截取第一个例如\n      routes:\n        - id: smile-sys\n          uri: lb://smile-sys\n          predicates:\n            - Path=/smile-sys/**\n          filters:\n            - StripPrefix=1\n        - id: smile-auth-resource\n          uri: lb://smile-auth-resource\n          predicates:\n            - Path=/api/**\n          filters:\n            - StripPrefix=1\n        - id: smile-auth\n          uri: lb://smile-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n      discovery:\n        locator:\n          enabled: true #开启从注册中心动态创建路由的功能\n          lower-case-service-id: true #使用小写服务名，默认是大写\n  security:\n    oauth2:\n      resourceserver:\n        jwt:\n          jwk-set-uri: \'http://localhost:8100/rsa/publicKey\' #配置RSA的公钥访问地址\n  redis:\n    database: 0\n    port: 6379\n    host: localhost\n    password:\n', '6539b6634da3931eead580227e634002', '2010-05-05 00:00:00', '2022-11-30 16:02:29', NULL, '192.168.56.1', 'I', '74a54390-4ff2-4f7e-bc44-956281fe4866');
INSERT INTO `his_config_info` VALUES (0, 55, 'smile-auth-ddev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 8100\nspring:\n  redis:\n    database: 0\n    port: 6379\n    host: redis_6.0.8\nlogging:\n  level:\n    com.smile.api.admin.feign.UserFeignClient: debug', '960563720e6c8cf7b7dd1ab8f36a17f4', '2010-05-05 00:00:00', '2022-11-30 16:02:29', NULL, '192.168.56.1', 'I', '74a54390-4ff2-4f7e-bc44-956281fe4866');
INSERT INTO `his_config_info` VALUES (28, 56, 'smile-auth-dev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 8100\nspring:\n  redis:\n    database: 0\n    port: 6379\n    host: localhost\nlogging:\n  level:\n    com.smile.api.admin.feign.UserFeignClient: debug\n', '22ce004c31b7c3752908f00b2eb03a77', '2010-05-05 00:00:00', '2022-11-30 16:02:59', NULL, '192.168.56.1', 'D', '74a54390-4ff2-4f7e-bc44-956281fe4866');
INSERT INTO `his_config_info` VALUES (0, 57, 'application-ddev.yml', 'DEFAULT_GROUP', '', '1', 'c4ca4238a0b923820dcc509a6f75849b', '2010-05-05 00:00:00', '2022-11-30 16:04:00', NULL, '192.168.56.1', 'I', '74a54390-4ff2-4f7e-bc44-956281fe4866');
INSERT INTO `his_config_info` VALUES (32, 58, 'application-ddev.yml', 'DEFAULT_GROUP', '', '1', 'c4ca4238a0b923820dcc509a6f75849b', '2010-05-05 00:00:00', '2022-11-30 16:04:30', NULL, '192.168.56.1', 'U', '74a54390-4ff2-4f7e-bc44-956281fe4866');
INSERT INTO `his_config_info` VALUES (27, 59, 'application-dev.yml', 'DEFAULT_GROUP', '', 'management:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\nfeign:\n  logger:\n    enable: true\n    level: FULL\n  client:\n    config:\n      ## default 设置的全局超时时间，指定服务名称可以设置单个服务的超时时间\n      default:\n        connectTimeout: 5000\n        readTimeout: 5000\nspring:\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n#这段url ignore配置必须要配置到云配置共享里面去，因为别的服务要用到        \nsmile:\n  gateway:\n    ignore:\n      urls: #配置白名单路径\n        - \"/actuator/**\"\n        - \"/auth/oauth/token\"', '8e623b705e9d8e0eba5ede437acf5218', '2010-05-05 00:00:00', '2022-11-30 16:04:38', NULL, '192.168.56.1', 'D', '74a54390-4ff2-4f7e-bc44-956281fe4866');
INSERT INTO `his_config_info` VALUES (25, 60, 'smile-auth-ddev.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 8100\nspring:\n  redis:\n    database: 0\n    port: 6379\n    host: redis_6.0.8\nlogging:\n  level:\n    com.smile.api.admin.feign.UserFeignClient: debug', '960563720e6c8cf7b7dd1ab8f36a17f4', '2010-05-05 00:00:00', '2022-11-30 16:05:20', NULL, '192.168.56.1', 'D', '764f9c7a-1874-4c91-985b-0678754a8208');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `resource` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  UNIQUE INDEX `uk_username_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (1, '1', '764f9c7a-1874-4c91-985b-0678754a8208', 'dev', 'smile-dev环境下的开发配置', 'nacos', 1669794226203, 1669794226203);
INSERT INTO `tenant_info` VALUES (2, '1', '74a54390-4ff2-4f7e-bc44-956281fe4866', 'ddev', 'smile项目中搭配docker自定义网络配置', 'nacos', 1669794303472, 1669794303472);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;

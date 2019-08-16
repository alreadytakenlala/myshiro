/*
Navicat MySQL Data Transfer

Source Server         : me
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : permission_manager

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-08-16 16:44:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '',
  `content` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', 'Spring Boot 面试，一个问题就干趴下了！', null);
INSERT INTO `article` VALUES ('2', 'Spring Boot 为什么这么火？', null);
INSERT INTO `article` VALUES ('3', '3年，阅读量100万+, Github Star 15000+', null);
INSERT INTO `article` VALUES ('4', 'Spring Boot 2 (十一)：如何优雅的使用 MyBatis 之 MyBatis-Plus', null);
INSERT INTO `article` VALUES ('5', 'Github 上 Star 最多的个人 Spring Boot 开源学习项目', null);
INSERT INTO `article` VALUES ('6', 'Spring Boot 2 (十)：Spring Boot 中的响应式编程和 WebFlux 入门', null);
INSERT INTO `article` VALUES ('7', '是时候给大家介绍 Spring Boot/Cloud 背后豪华的研发团队了。', null);
INSERT INTO `article` VALUES ('8', 'Spring Boot 2 (九)：【重磅】Spring Boot 2.1.0 权威发布', null);
INSERT INTO `article` VALUES ('9', 'Spring Boot 2 (八)：Spring Boot 集成 Memcached', null);
INSERT INTO `article` VALUES ('10', '为什么说 Java 程序员到了必须掌握 Spring Boot 的时候？', null);
INSERT INTO `article` VALUES ('11', 'Spring Boot 2 版的开源项目云收藏来了！', null);
INSERT INTO `article` VALUES ('12', '一文读懂 Spring Boot、微服务架构和大数据治理三者之间的故事', null);
INSERT INTO `article` VALUES ('13', 'Spring Boot 2 (七)：Spring Boot 如何解决项目启动时初始化资源', null);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `permission` varchar(255) NOT NULL DEFAULT '',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('ac7da722ae0add88c50461728da540', '更新', 'role:update', '2019-08-16 16:33:08');
INSERT INTO `menu` VALUES ('db5534bb3020de2d9134b5243b938b', '添加', 'role:add', '2019-08-16 16:35:19');
INSERT INTO `menu` VALUES ('18d8bbd3fdf1ef73b8818afa945d07', '添加', 'admin:add', '2019-08-16 16:35:55');
INSERT INTO `menu` VALUES ('14d025d15acd47fc908b7dc14f5710', '添加', 'role:add', '2019-08-16 16:39:04');
INSERT INTO `menu` VALUES ('ac11a4317ad7ab80354756d5659ea5', '添加', 'admin:add', '2019-08-16 16:39:25');
INSERT INTO `menu` VALUES ('108a04c4a5531d1748bb9c0e0abdb3', '添加', 'role:add', '2019-08-16 16:40:14');
INSERT INTO `menu` VALUES ('6b865e25b501ec4b2ca359687ca46b', '添加', 'admin:add', '2019-08-16 16:40:29');
INSERT INTO `menu` VALUES ('5aa3192e1af930deefe7ea57675fe3', '更新', 'role:update', '2019-08-16 16:40:41');
INSERT INTO `menu` VALUES ('1f1fe9897f6cffa50dcd45dc88b6de', '添加', 'article:add', '2019-08-16 16:41:28');
INSERT INTO `menu` VALUES ('f4f9f4479d930b98928f88898f89e7', '添加', 'article:add', '2019-08-16 16:41:38');
INSERT INTO `menu` VALUES ('3277b1707cfee85cadc8fe51c521fc', '添加', 'article:add', '2019-08-16 16:41:48');
INSERT INTO `menu` VALUES ('e7c77ddbc19b4fecf29baa32aaa554', '添加', 'article:add', '2019-08-16 16:41:58');
INSERT INTO `menu` VALUES ('37ceca320577bd6a7483b4b1107421', '添加', 'article:add', '2019-08-16 16:42:08');
INSERT INTO `menu` VALUES ('67802cf11d0fae8d6dcf001d0d8554', '添加', 'article:add', '2019-08-16 16:42:21');
INSERT INTO `menu` VALUES ('8fd65787d085bfadb7ebb94ef64e46', '添加', 'article:add', '2019-08-16 16:42:30');
INSERT INTO `menu` VALUES ('a8013418e98305b07ea6ff8e939a3d', '添加', 'article:add', '2019-08-16 16:42:39');
INSERT INTO `menu` VALUES ('29ecae99bc1e9e2c3da4c898ae073a', '添加', 'article:add', '2019-08-16 16:42:47');
INSERT INTO `menu` VALUES ('b3e5168aa6dfb60695542ff8675fb7', '添加', 'article:add', '2019-08-16 16:42:56');
INSERT INTO `menu` VALUES ('c4083d5dfdbd022b46e610a68f5d8a', '添加', 'article:add', '2019-08-16 16:43:04');
INSERT INTO `menu` VALUES ('9f48b1689ca248f83f36681887d74a', '添加', 'article:add', '2019-08-16 16:43:20');
INSERT INTO `menu` VALUES ('ef518acbeb0bdf09faca4f28cba4c8', '添加', 'article:add', '2019-08-16 16:43:30');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `permission` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '文章添加', 'article:add');
INSERT INTO `permission` VALUES ('2', '文章删除', 'artiele:delete');
INSERT INTO `permission` VALUES ('3', '文章查询', 'artiele:select');
INSERT INTO `permission` VALUES ('4', '角色添加', 'role:add');
INSERT INTO `permission` VALUES ('5', '角色删除', 'role:delete');
INSERT INTO `permission` VALUES ('6', '角色更新', 'role:update');
INSERT INTO `permission` VALUES ('7', '角色查询', 'role:select');
INSERT INTO `permission` VALUES ('8', '用户添加', 'admin:add');
INSERT INTO `permission` VALUES ('9', '用户删除', 'admin:delete');
INSERT INTO `permission` VALUES ('10', '用户更新', 'admin:update');
INSERT INTO `permission` VALUES ('11', '用户查询', 'admin:select');
INSERT INTO `permission` VALUES ('12', '菜单查询', 'menu:select');
INSERT INTO `permission` VALUES ('13', '菜单删除', 'menu:delete');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', '来客');
INSERT INTO `role` VALUES ('3', '编辑');
INSERT INTO `role` VALUES ('4', '管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  KEY `rid` (`rid`),
  KEY `pid` (`pid`),
  CONSTRAINT `pid_permission` FOREIGN KEY (`pid`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rid_role` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('1', '2');
INSERT INTO `role_permission` VALUES ('1', '3');
INSERT INTO `role_permission` VALUES ('1', '4');
INSERT INTO `role_permission` VALUES ('1', '5');
INSERT INTO `role_permission` VALUES ('1', '6');
INSERT INTO `role_permission` VALUES ('1', '7');
INSERT INTO `role_permission` VALUES ('1', '8');
INSERT INTO `role_permission` VALUES ('1', '9');
INSERT INTO `role_permission` VALUES ('1', '10');
INSERT INTO `role_permission` VALUES ('1', '11');
INSERT INTO `role_permission` VALUES ('1', '12');
INSERT INTO `role_permission` VALUES ('1', '13');
INSERT INTO `role_permission` VALUES ('2', '3');
INSERT INTO `role_permission` VALUES ('2', '7');
INSERT INTO `role_permission` VALUES ('2', '11');
INSERT INTO `role_permission` VALUES ('2', '12');
INSERT INTO `role_permission` VALUES ('3', '1');
INSERT INTO `role_permission` VALUES ('3', '2');
INSERT INTO `role_permission` VALUES ('3', '3');
INSERT INTO `role_permission` VALUES ('3', '7');
INSERT INTO `role_permission` VALUES ('3', '11');
INSERT INTO `role_permission` VALUES ('3', '12');
INSERT INTO `role_permission` VALUES ('4', '1');
INSERT INTO `role_permission` VALUES ('4', '2');
INSERT INTO `role_permission` VALUES ('4', '3');
INSERT INTO `role_permission` VALUES ('4', '4');
INSERT INTO `role_permission` VALUES ('4', '5');
INSERT INTO `role_permission` VALUES ('4', '6');
INSERT INTO `role_permission` VALUES ('4', '7');
INSERT INTO `role_permission` VALUES ('4', '8');
INSERT INTO `role_permission` VALUES ('4', '9');
INSERT INTO `role_permission` VALUES ('4', '10');
INSERT INTO `role_permission` VALUES ('4', '11');
INSERT INTO `role_permission` VALUES ('4', '12');
INSERT INTO `role_permission` VALUES ('4', '13');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `salt` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'wizard', 'd4c296995344692d10a5ef63035e134d', '123456');
INSERT INTO `user` VALUES ('2', '突然的来客', 'd3630eae5859e23498de4478fdf8980b', 'c3de8d27de2c71bb09e182881c41b5');
INSERT INTO `user` VALUES ('3', '喜欢文章的编辑', 'bd20c725ea8cc7be52f37c7cdc28414e', '403d96e78f18874f08f4912d0f97af');
INSERT INTO `user` VALUES ('4', '后台管理员', '0122a1bcd11f2fbcb54087f964ebfd08', '48d47ec2efad0345331e9bdb714533');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  KEY `uid` (`uid`),
  KEY `rid` (`rid`),
  CONSTRAINT `rid_user` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uid_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');
INSERT INTO `user_role` VALUES ('3', '3');
INSERT INTO `user_role` VALUES ('4', '4');

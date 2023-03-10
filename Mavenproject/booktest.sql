/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : booktest

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2021-03-08 18:25:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `usernumber` varchar(255) CHARACTER SET latin1 NOT NULL,
  `username` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `password` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `age` int(10) DEFAULT '0',
  `classinfo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`usernumber`),
  UNIQUE KEY `usernumber` (`usernumber`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('99999', 'admin', 'admin111', '男', '20', '1', 'ADMIN');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '书本ID',
  `bookname` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '书本名称',
  `author` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '书本作者',
  `publisher` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '出版社',
  `price` int(11) DEFAULT NULL COMMENT '书本价格',
  `category` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '书本类目',
  `status` int(11) DEFAULT '0',
  `bookdesc` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `amount` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='书籍数据库';

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', 'Java核心技术', '凯 S. 霍斯特曼', '机械工业出版社3', '119', '编程类', '0', '《JAVA核心技术》（第8版）是2011年电子工业出版社出版的图书，作者是昊斯特曼、Gary Cornell。本书针对JavaSE6平台进行了全面更新，囊括了Java平台标准版(JavaSE/J2SE)的全部基础知识，提供了大量完整且具有实际意义的应用实例。', '5');
INSERT INTO `book` VALUES ('2', 'C++PrimaerPlus', 'Stephen Prata', '中国邮电出版社', '99', '编程类', '0', '《JAVA核心技术》（第8版）是2011年电子工业出版社出版的图书，作者是昊斯特曼、Gary Cornell。本书针对JavaSE6平台进行了全面更新，囊括了Java平台标准版(JavaSE/J2SE)的全部基础知识，提供了大量完整且具有实际意义的应用实例。', '2');
INSERT INTO `book` VALUES ('3', '算法竞赛入门经典', '刘汝佳', '清华大学出版社', '50', '编程类', '0', '《JAVA核心技术》（第8版）是2011年电子工业出版社出版的图书，作者是昊斯特曼、Gary Cornell。本书针对JavaSE6平台进行了全面更新，囊括了Java平台标准版(JavaSE/J2SE)的全部基础知识，提供了大量完整且具有实际意义的应用实例。', '4');
INSERT INTO `book` VALUES ('4', '概率论与数理统计', '盛骤 谢式千 潘承毅', '高等教育出版社', '38', '数学类', '0', '《JAVA核心技术》（第8版）是2011年电子工业出版社出版的图书，作者是昊斯特曼、Gary Cornell。本书针对JavaSE6平台进行了全面更新，囊括了Java平台标准版(JavaSE/J2SE)的全部基础知识，提供了大量完整且具有实际意义的应用实例。', '7');
INSERT INTO `book` VALUES ('5', '数据结构(C语言版)', '严蔚敏 吴伟民', '清华大学出版社', '29', '编程类', '0', '《JAVA核心技术》（第8版）是2011年电子工业出版社出版的图书，作者是昊斯特曼、Gary Cornell。本书针对JavaSE6平台进行了全面更新，囊括了Java平台标准版(JavaSE/J2SE)的全部基础知识，提供了大量完整且具有实际意义的应用实例。', '0');

-- ----------------------------
-- Table structure for borrowing
-- ----------------------------
DROP TABLE IF EXISTS `borrowing`;
CREATE TABLE `borrowing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookid` int(30) DEFAULT NULL,
  `usernumber` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of borrowing
-- ----------------------------
INSERT INTO `borrowing` VALUES ('2', '2', '99999', '2021-01-04', '1');
INSERT INTO `borrowing` VALUES ('7', '2', '20181840174', '2018-01-04', '1');
INSERT INTO `borrowing` VALUES ('8', '3', '20181840174', '2021-11-04', '1');
INSERT INTO `borrowing` VALUES ('9', '2', '20181840174', '2020-01-04', '1');
INSERT INTO `borrowing` VALUES ('10', '2', '20181840174', '2021-01-04', '1');
INSERT INTO `borrowing` VALUES ('11', '3', '20181840174', '2021-01-04', '1');
INSERT INTO `borrowing` VALUES ('14', '2', '20181840174', '2021-01-04', '1');
INSERT INTO `borrowing` VALUES ('15', '3', '99999', '2021-01-04', '1');
INSERT INTO `borrowing` VALUES ('16', '1', '20181840179', '2021-01-04', '1');
INSERT INTO `borrowing` VALUES ('17', '2', '20181840179', '2021-01-04', '1');
INSERT INTO `borrowing` VALUES ('18', '3', '20181840179', '2021-01-04', '1');
INSERT INTO `borrowing` VALUES ('19', '4', '20181840179', '2021-01-04', '1');
INSERT INTO `borrowing` VALUES ('20', '4', '20181840179', '2021-01-04', '1');
INSERT INTO `borrowing` VALUES ('27', '3', '99999', '2021-01-05', '1');
INSERT INTO `borrowing` VALUES ('31', '4', '99999', '2021-01-05', '1');
INSERT INTO `borrowing` VALUES ('37', '1', '99999', '2021-01-05', '1');
INSERT INTO `borrowing` VALUES ('38', '2', '99999', '2021-01-05', '1');
INSERT INTO `borrowing` VALUES ('39', '3', '99999', '2021-01-05', '1');
INSERT INTO `borrowing` VALUES ('42', '1', '99999', '2021-01-05', '1');
INSERT INTO `borrowing` VALUES ('43', '1', '99999', '2021-01-05', '1');
INSERT INTO `borrowing` VALUES ('44', '1', '99999', '2021-01-05', '1');
INSERT INTO `borrowing` VALUES ('45', '2', '20181840174', '2021-01-05', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `usernumber` varchar(255) COLLATE utf8_bin NOT NULL,
  `username` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `age` int(10) DEFAULT '0',
  `classinfo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`usernumber`),
  UNIQUE KEY `usernumber` (`usernumber`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('20181840174', 'abc1', '123456', '女', '20', '土木工程', 'User');
INSERT INTO `user` VALUES ('20181840177', '而为', '111111111', '男', '20', '财务管理', 'User');
INSERT INTO `user` VALUES ('20181840179', 'abcd', '123123', '男', '12', '计算机', 'User');
INSERT INTO `user` VALUES ('20181840299', 'ww', '111111', '女', '20', '财务管理', 'User');

-- ----------------------------
-- Table structure for usernumber
-- ----------------------------
DROP TABLE IF EXISTS `usernumber`;
CREATE TABLE `usernumber` (
  `usernumber` varchar(255) COLLATE utf8_bin NOT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`usernumber`),
  UNIQUE KEY `usernumber` (`usernumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of usernumber
-- ----------------------------
INSERT INTO `usernumber` VALUES ('20181840174', 'abc1');
INSERT INTO `usernumber` VALUES ('20181840177', '而为');
INSERT INTO `usernumber` VALUES ('20181840179', 'abcd');
INSERT INTO `usernumber` VALUES ('20181840299', 'ww');
INSERT INTO `usernumber` VALUES ('99999', 'admin');

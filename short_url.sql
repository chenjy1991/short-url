/*
 Navicat Premium Data Transfer

 Source Server         : qq_1c2g
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 49.232.39.26:3306
 Source Schema         : short_url

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 29/10/2019 13:41:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for url_mapping
-- ----------------------------
DROP TABLE IF EXISTS `url_mapping`;
CREATE TABLE `url_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `short_url_code` varchar(6) NOT NULL,
  `long_url_path` varchar(511) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url_mapping_short_url_code_uindex` (`short_url_code`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : demo1
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : upload

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 18/06/2023 14:16:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chats
-- ----------------------------
DROP TABLE IF EXISTS `chats`;
CREATE TABLE `chats`  (
  `id` int NOT NULL,
  `from` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `to` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `homeid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chats
-- ----------------------------

-- ----------------------------
-- Table structure for collectioned
-- ----------------------------
DROP TABLE IF EXISTS `collectioned`;
CREATE TABLE `collectioned`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `filetype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 196 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collectioned
-- ----------------------------
INSERT INTO `collectioned` VALUES (97, '3.txt', 'root', 'document');
INSERT INTO `collectioned` VALUES (107, '俞雯艺 85.jpg', 'root', 'image');
INSERT INTO `collectioned` VALUES (115, '3 (1).txt', 'root', 'document');
INSERT INTO `collectioned` VALUES (117, 'index.html', 'root', 'other');
INSERT INTO `collectioned` VALUES (120, 'bootstrap.min.css.map', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (121, 'bootstrap.css.map', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (123, 'bootstrap-theme.min.css.map', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (126, 'bootstrap.css', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (127, 'bootstrap-theme.css', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (128, 'bootstrap.min.js', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (129, 'npm.js', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (130, 'glyphicons-halflings-regular.svg', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (131, 'bootstrap.js', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (132, 'glyphicons-halflings-regular.woff2', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (133, 'glyphicons-halflings-regular.ttf', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (134, 'glyphicons-halflings-regular.woff', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (135, 'glyphicons-halflings-regular.eot', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (136, 'hoedown.dll', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (137, 'api-ms-win-core-winrt-string-l1-1-0.dll', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (138, 'api-ms-win-core-winrt-error-l1-1-0.dll', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (139, 'config.ini', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (140, '俞雯艺 21822101642.jpg', '狗泽是sb', 'image');
INSERT INTO `collectioned` VALUES (141, '林妍伊21822101617.jpg', '狗泽是sb', 'image');
INSERT INTO `collectioned` VALUES (142, '陈钰 21822101605.jpg', '狗泽是sb', 'image');
INSERT INTO `collectioned` VALUES (143, '柯芦尹 21822101614.jpg', '狗泽是sb', 'image');
INSERT INTO `collectioned` VALUES (144, 'network.PNG', '狗泽是sb', 'image');
INSERT INTO `collectioned` VALUES (145, '舒倩缘 21822101624.jpg', '狗泽是sb', 'image');
INSERT INTO `collectioned` VALUES (146, 'capture.PNG', '狗泽是sb', 'image');
INSERT INTO `collectioned` VALUES (147, 'libssh2.dll', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (149, 'libeay32.dll', '狗泽是sb', 'other');
INSERT INTO `collectioned` VALUES (150, 'a.txt', 'root', 'document');
INSERT INTO `collectioned` VALUES (151, 'Qt5Widgets.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (152, 'image1.gif', 'root', 'image');
INSERT INTO `collectioned` VALUES (154, 'quazip.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (155, 'Qt5WinExtras.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (156, 'README.txt', 'root', 'document');
INSERT INTO `collectioned` VALUES (157, 'libcrypto-1_1-x64.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (158, 'ucrtbase.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (160, 'Qt5Svg.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (161, 'Qt5Network.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (170, 'Qt5Xml.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (172, 'vcruntime140_1.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (173, 'Qt5PrintSupport.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (174, 'Qt5OpenGL.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (175, 'hoedown.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (176, 'api-ms-win-core-winrt-string-l1-1-0.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (177, 'api-ms-win-core-winrt-error-l1-1-0.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (178, '俞雯艺 21822101642.jpg', 'root', 'image');
INSERT INTO `collectioned` VALUES (179, 'config.ini', 'root', 'other');
INSERT INTO `collectioned` VALUES (180, 'notepad++.exe', 'root', 'other');
INSERT INTO `collectioned` VALUES (181, 'MD5_Tool.zip', 'root', 'other');
INSERT INTO `collectioned` VALUES (182, 'install.txt', 'root', 'document');
INSERT INTO `collectioned` VALUES (183, 'libsasl.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (184, 'pharcommand.phar', 'root', 'other');
INSERT INTO `collectioned` VALUES (185, 'news.txt', 'root', 'document');
INSERT INTO `collectioned` VALUES (186, 'libssh2.dll', 'root', 'other');
INSERT INTO `collectioned` VALUES (187, 'phar.phar.bat', 'root', 'other');
INSERT INTO `collectioned` VALUES (188, 'php.exe', 'root', 'other');
INSERT INTO `collectioned` VALUES (189, '凋零台词.mp3', 'root', 'audio');
INSERT INTO `collectioned` VALUES (190, 'OMS Client - 题目详情 2021-04-16 16-19-28.mp4', 'root', 'video');
INSERT INTO `collectioned` VALUES (195, 'php.ini-production', 'root', 'other');

-- ----------------------------
-- Table structure for filetable
-- ----------------------------
DROP TABLE IF EXISTS `filetable`;
CREATE TABLE `filetable`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `describle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `upname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `collect` json NULL,
  `filetype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `shouchang` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 223 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of filetable
-- ----------------------------
INSERT INTO `filetable` VALUES (110, '3 (1).txt', '', 'root', '[\"3\"]', 'document', 'false');
INSERT INTO `filetable` VALUES (113, 'index.html', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (118, '2.png', '这是一个头像', 'root', '[]', 'image', 'false');
INSERT INTO `filetable` VALUES (119, 'ex1.sh', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (121, '林妍伊 95.jpg', '', 'root', '[]', 'image', 'false');
INSERT INTO `filetable` VALUES (122, '柯芦尹 90.jpg', '', 'root', NULL, 'image', 'false');
INSERT INTO `filetable` VALUES (123, '俞雯艺 85.jpg', '', 'root', '[\"3\"]', 'image', 'false');
INSERT INTO `filetable` VALUES (124, 'l.cpp', '123', '狗泽是sb', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (127, '2.jpg', '', 'root', '[]', 'image', 'false');
INSERT INTO `filetable` VALUES (128, '3.txt', '不用', 'start1', '[\"3\"]', 'document', 'false');
INSERT INTO `filetable` VALUES (129, 'OMS Client - 题目详情 2021-04-16 16-19-28.mp4', '', 'root', '[\"3\"]', 'video', 'false');
INSERT INTO `filetable` VALUES (130, '12312.txt', '', 'root', '[]', 'document', 'false');
INSERT INTO `filetable` VALUES (131, '凋零台词.mp3', '', 'root', '[\"3\"]', 'audio', 'false');
INSERT INTO `filetable` VALUES (132, 'アイドル-YOASOBI (2).m4a', '乐', 'well', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (133, 'flag', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (134, 'flag.txt', '', 'root', '[]', 'document', 'false');
INSERT INTO `filetable` VALUES (135, 'flag.zip', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (136, 'MobaXterm.ico', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (137, 'kali基础配置.txt', '', 'root', '[]', 'document', 'false');
INSERT INTO `filetable` VALUES (138, 'image1.gif', '', 'root', '[\"3\"]', 'image', 'false');
INSERT INTO `filetable` VALUES (139, 'php伪协议读取Index.php源码.txt', '', 'root', '[]', 'document', 'false');
INSERT INTO `filetable` VALUES (140, 'php序列化.txt', '', 'root', '[]', 'document', 'false');
INSERT INTO `filetable` VALUES (141, 'oms-client.exe', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (142, 'MD5_Tool.zip', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (143, 'notepad++.exe', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (144, 'my_site.log', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (145, 'capture.PNG', '', 'root', '[\"5\"]', 'image', 'false');
INSERT INTO `filetable` VALUES (146, '舒倩缘 21822101624.jpg', '', 'root', '[\"5\"]', 'image', 'false');
INSERT INTO `filetable` VALUES (147, 'network.PNG', '', 'root', '[\"5\"]', 'image', 'false');
INSERT INTO `filetable` VALUES (148, '柯芦尹 21822101614.jpg', '', 'root', '[\"5\"]', 'image', 'false');
INSERT INTO `filetable` VALUES (149, '陈钰 21822101605.jpg', '', 'root', '[\"5\"]', 'image', 'false');
INSERT INTO `filetable` VALUES (150, '林妍伊21822101617.jpg', '', 'root', '[\"5\"]', 'image', 'false');
INSERT INTO `filetable` VALUES (151, '俞雯艺 21822101642.jpg', '', 'root', '[\"5\", \"3\"]', 'image', 'false');
INSERT INTO `filetable` VALUES (153, 'config.ini', '', 'root', '[\"5\", \"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (154, 'api-ms-win-core-winrt-error-l1-1-0.dll', '', 'root', '[\"5\", \"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (155, 'api-ms-win-core-winrt-string-l1-1-0.dll', '', 'root', '[\"5\", \"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (156, 'hoedown.dll', '', 'root', '[\"5\", \"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (157, 'Qt5Concurrent.dll', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (158, 'msvcp140.dll', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (159, 'libssl-1_1-x64.dll', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (160, 'msvcr120.dll', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (161, 'Qt5Multimedia.dll', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (162, 'Qt5PrintSupport.dll', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (163, 'Qt5OpenGL.dll', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (164, 'Qt5Svg.dll', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (165, 'Qt5Network.dll', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (166, 'Qt5Xml.dll', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (167, 'quazip.dll', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (168, 'Qt5WinExtras.dll', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (169, 'README.txt', '', 'root', '[\"3\"]', 'document', 'false');
INSERT INTO `filetable` VALUES (170, 'libcrypto-1_1-x64.dll', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (171, 'ucrtbase.dll', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (172, 'vcruntime140_1.dll', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (173, 'vcruntime140.dll', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (174, 'Snipaste.exe', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (175, 'splog.txt', '', 'root', '[]', 'document', 'false');
INSERT INTO `filetable` VALUES (176, 'Qt5Core.dll', '', 'root', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (178, 'Qt5Widgets.dll', '', 'root', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (179, 'glyphicons-halflings-regular.eot', '', '狗泽是sb', '[\"5\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (180, 'glyphicons-halflings-regular.woff', '', '狗泽是sb', '[\"5\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (181, 'glyphicons-halflings-regular.ttf', '', '狗泽是sb', '[\"5\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (182, 'glyphicons-halflings-regular.woff2', '', '狗泽是sb', '[\"5\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (183, 'bootstrap.js', '', '狗泽是sb', '[\"5\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (184, 'glyphicons-halflings-regular.svg', '', '狗泽是sb', '[\"5\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (185, 'npm.js', '', '狗泽是sb', '[\"5\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (186, 'bootstrap.min.js', '', '狗泽是sb', '[\"5\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (187, 'bootstrap-theme.css', '', '狗泽是sb', '[\"5\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (188, 'bootstrap.css', '', '狗泽是sb', '[\"5\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (191, 'bootstrap-theme.min.css.map', '1655', '狗泽是sb', '[\"5\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (193, 'bootstrap.css.map', '', '狗泽是sb', '[\"5\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (194, 'bootstrap.min.css.map', '', '狗泽是sb', '[\"5\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (195, 'gmodule-2.dll', '', '狗泽是sb', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (196, 'libenchant.dll', '', '狗泽是sb', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (197, 'libsasl.dll', '', '狗泽是sb', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (198, 'install.txt', '', '狗泽是sb', '[\"3\"]', 'document', 'false');
INSERT INTO `filetable` VALUES (199, 'libpq.dll', '', '狗泽是sb', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (200, 'license.txt', '', '狗泽是sb', '[]', 'document', 'false');
INSERT INTO `filetable` VALUES (201, 'pharcommand.phar', '', '狗泽是sb', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (202, 'news.txt', '', '狗泽是sb', '[\"3\"]', 'document', 'false');
INSERT INTO `filetable` VALUES (203, 'libssh2.dll', '9999999999999999999999', '狗泽是sb', '[\"5\", \"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (204, 'phar.phar.bat', '', '狗泽是sb', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (205, 'php.exe', '', '狗泽是sb', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (206, 'php.gif', '', '狗泽是sb', '[]', 'image', 'false');
INSERT INTO `filetable` VALUES (207, 'php.ini', '', '狗泽是sb', '[]', 'other', 'false');
INSERT INTO `filetable` VALUES (208, 'php.ini-production', '', '狗泽是sb', '[\"3\"]', 'other', 'false');
INSERT INTO `filetable` VALUES (213, 'readme-redist-bins.txt', '', '狗泽是sb', '[]', 'document', 'false');
INSERT INTO `filetable` VALUES (221, 'a.txt', '', '狗泽是sb', '[\"3\"]', 'document', 'false');
INSERT INTO `filetable` VALUES (222, '服务器证书.zip', '', 'root', '[]', 'other', 'false');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `admin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '123456', '123456', 'user', '233', 'ace.gif');
INSERT INTO `user` VALUES (2, 'root1', '9b70d6dbfb1457d05e4e2c2fbb42d7db', 'user', '9ec6a06d-61e9-4b41-9bfd-b4f86857218bFILE-SPACE', 'ace.gif');
INSERT INTO `user` VALUES (3, 'root', 'ff9830c42660c1dd1942844f8069b74a', 'admin', 'fe230329-c422-4269-ae7b-6081c8c6d830FILE-SPACE', 'd914e1d2-95d8-4a17-bc32-0b084906eeacAVATAR.jpg');
INSERT INTO `user` VALUES (4, 'start', '63a9f0ea7bb98050796b649e85481845', 'user', 'a00a8892-84cf-49c9-94cb-5b91d0041cb8FILE-SPACE', 'ace.gif');
INSERT INTO `user` VALUES (5, '狗泽是sb', 'dc483e80a7a0bd9ef71d8cf973673924', 'user', '8c4ad6c1-140b-44cf-971c-7e2bcf243780FILE-SPACE', '696a262f-bba4-43b7-8011-438caf9e2ef7AVATAR.jpg');
INSERT INTO `user` VALUES (6, 'test1', '05a671c66aefea124cc08b76ea6d30bb', 'user', '7e8fa084-5562-4ad2-965a-e8fd15a273eaFILE-SPACE', 'ace.gif');
INSERT INTO `user` VALUES (7, 'testuser2', '58dd024d49e1d1b83a5d307f09f32734', 'user', '808f3586-4984-4e7f-811e-09e69390c23fFILE-SPACE', 'ace.gif');
INSERT INTO `user` VALUES (8, 'start1', 'ff9830c42660c1dd1942844f8069b74a', 'user', '485ead85-9d75-48ee-a084-ffb1b5f3ed59FILE-SPACE', 'ace.gif');
INSERT INTO `user` VALUES (9, 'well', 'e10adc3949ba59abbe56e057f20f883e', 'user', '7d8f0dd9-6f7b-4699-b0c2-d2d2b3037cf3FILE-SPACE', 'ace.gif');

SET FOREIGN_KEY_CHECKS = 1;

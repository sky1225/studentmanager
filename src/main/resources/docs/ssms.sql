/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50617
 Source Host           : localhost:3306
 Source Schema         : ssms

 Target Server Type    : MySQL
 Target Server Version : 50617
 File Encoding         : 65001

 Date: 05/11/2019 22:51:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `gradeId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gid_clazz_FK`(`gradeId`) USING BTREE,
  CONSTRAINT `gradeid_clazz_FK` FOREIGN KEY (`gradeId`) REFERENCES `grade` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES (1, '1班', 1);
INSERT INTO `clazz` VALUES (2, '2班', 1);
INSERT INTO `clazz` VALUES (3, '3班', 1);
INSERT INTO `clazz` VALUES (4, '4班', 1);
INSERT INTO `clazz` VALUES (5, '1班', 2);
INSERT INTO `clazz` VALUES (6, '2班', 2);
INSERT INTO `clazz` VALUES (7, '3班', 2);
INSERT INTO `clazz` VALUES (8, '4班', 2);
INSERT INTO `clazz` VALUES (9, '1班', 3);
INSERT INTO `clazz` VALUES (10, '2班', 3);
INSERT INTO `clazz` VALUES (11, '3班', 3);
INSERT INTO `clazz` VALUES (12, '4班', 3);
INSERT INTO `clazz` VALUES (13, '5班', 3);

-- ----------------------------
-- Table structure for clazz_course_teacher
-- ----------------------------
DROP TABLE IF EXISTS `clazz_course_teacher`;
CREATE TABLE `clazz_course_teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clazzId` int(11) NULL DEFAULT NULL,
  `gradeId` int(11) NULL DEFAULT NULL,
  `courseId` int(11) NULL DEFAULT NULL,
  `teacherId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `clazzid_cct_FK`(`clazzId`) USING BTREE,
  INDEX `tid_cct_FK`(`teacherId`) USING BTREE,
  INDEX `courseid_cct_FK`(`courseId`) USING BTREE,
  INDEX `gradeid_cct_FK`(`gradeId`) USING BTREE,
  CONSTRAINT `clazzid_cct_FK` FOREIGN KEY (`clazzId`) REFERENCES `clazz` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teacherid_cct_FK` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of clazz_course_teacher
-- ----------------------------
INSERT INTO `clazz_course_teacher` VALUES (14, 1, 1, 1, 15);
INSERT INTO `clazz_course_teacher` VALUES (15, 2, 1, 1, 15);
INSERT INTO `clazz_course_teacher` VALUES (16, 3, 1, 2, 14);
INSERT INTO `clazz_course_teacher` VALUES (17, 1, 1, 2, 14);
INSERT INTO `clazz_course_teacher` VALUES (18, 1, 1, 3, 10);
INSERT INTO `clazz_course_teacher` VALUES (19, 1, 1, 4, 9);
INSERT INTO `clazz_course_teacher` VALUES (20, 1, 1, 5, 8);
INSERT INTO `clazz_course_teacher` VALUES (21, 2, 1, 5, 8);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '语文');
INSERT INTO `course` VALUES (2, '数学');
INSERT INTO `course` VALUES (3, '英语');
INSERT INTO `course` VALUES (4, '物理');
INSERT INTO `course` VALUES (5, '化学');
INSERT INTO `course` VALUES (6, '生物');
INSERT INTO `course` VALUES (7, '历史');
INSERT INTO `course` VALUES (8, '政治');
INSERT INTO `course` VALUES (9, '计算机');
INSERT INTO `course` VALUES (10, '体育');

-- ----------------------------
-- Table structure for escore
-- ----------------------------
DROP TABLE IF EXISTS `escore`;
CREATE TABLE `escore`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `examId` int(11) NULL DEFAULT NULL,
  `clazzId` int(11) NULL DEFAULT NULL,
  `studentId` int(11) NULL DEFAULT NULL,
  `gradeId` int(11) NULL DEFAULT NULL,
  `courseId` int(11) NULL DEFAULT NULL,
  `score` int(5) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `eid_escore_FK`(`examId`) USING BTREE,
  INDEX `sid_escore_FK`(`studentId`) USING BTREE,
  INDEX `clazzid_escore_FK`(`clazzId`) USING BTREE,
  INDEX `courseid_escore_FK`(`courseId`) USING BTREE,
  INDEX `gradeid_escore_FK`(`gradeId`) USING BTREE,
  CONSTRAINT `clazzid_escore_FK` FOREIGN KEY (`clazzId`) REFERENCES `clazz` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `courseid_escore_FK` FOREIGN KEY (`courseId`) REFERENCES `grade_course` (`courseid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `examid_escore_FK` FOREIGN KEY (`examId`) REFERENCES `exam` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `gradeid_escore_FK` FOREIGN KEY (`gradeId`) REFERENCES `grade_course` (`gradeid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `studentid_escore_FK` FOREIGN KEY (`studentId`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 224 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of escore
-- ----------------------------
INSERT INTO `escore` VALUES (1, 1, 1, 1, 1, 1, 123);
INSERT INTO `escore` VALUES (2, 1, 1, 1, 1, 2, 56);
INSERT INTO `escore` VALUES (3, 1, 1, 1, 1, 3, 67);
INSERT INTO `escore` VALUES (4, 1, 1, 1, 1, 4, 78);
INSERT INTO `escore` VALUES (5, 1, 1, 1, 1, 5, 54);
INSERT INTO `escore` VALUES (6, 1, 1, 2, 1, 1, NULL);
INSERT INTO `escore` VALUES (7, 1, 1, 2, 1, 2, 67);
INSERT INTO `escore` VALUES (8, 1, 1, 2, 1, 3, 120);
INSERT INTO `escore` VALUES (9, 1, 1, 2, 1, 4, 76);
INSERT INTO `escore` VALUES (10, 1, 1, 2, 1, 5, 78);
INSERT INTO `escore` VALUES (11, 1, 1, 3, 1, 1, 67);
INSERT INTO `escore` VALUES (12, 1, 1, 3, 1, 2, 87);
INSERT INTO `escore` VALUES (13, 1, 1, 3, 1, 3, 66);
INSERT INTO `escore` VALUES (14, 1, 1, 3, 1, 4, 56);
INSERT INTO `escore` VALUES (15, 1, 1, 3, 1, 5, 88);
INSERT INTO `escore` VALUES (16, 1, 1, 4, 1, 1, 89);
INSERT INTO `escore` VALUES (17, 1, 1, 4, 1, 2, 34);
INSERT INTO `escore` VALUES (18, 1, 1, 4, 1, 3, 55);
INSERT INTO `escore` VALUES (19, 1, 1, 4, 1, 4, 90);
INSERT INTO `escore` VALUES (20, 1, 1, 4, 1, 5, 90);
INSERT INTO `escore` VALUES (21, 1, 1, 5, 1, 1, 99);
INSERT INTO `escore` VALUES (22, 1, 1, 5, 1, 2, 89);
INSERT INTO `escore` VALUES (23, 1, 1, 5, 1, 3, 142);
INSERT INTO `escore` VALUES (24, 1, 1, 5, 1, 4, 66);
INSERT INTO `escore` VALUES (25, 1, 1, 5, 1, 5, 79);
INSERT INTO `escore` VALUES (26, 1, 1, 6, 1, 1, 109);
INSERT INTO `escore` VALUES (27, 1, 1, 6, 1, 2, 90);
INSERT INTO `escore` VALUES (28, 1, 1, 6, 1, 3, 90);
INSERT INTO `escore` VALUES (29, 1, 1, 6, 1, 4, 78);
INSERT INTO `escore` VALUES (30, 1, 1, 6, 1, 5, 65);
INSERT INTO `escore` VALUES (31, 1, 1, 7, 1, 1, 45);
INSERT INTO `escore` VALUES (32, 1, 1, 7, 1, 2, 45);
INSERT INTO `escore` VALUES (33, 1, 1, 7, 1, 3, 67);
INSERT INTO `escore` VALUES (34, 1, 1, 7, 1, 4, 90);
INSERT INTO `escore` VALUES (35, 1, 1, 7, 1, 5, 88);
INSERT INTO `escore` VALUES (36, 1, 1, 8, 1, 1, 65);
INSERT INTO `escore` VALUES (37, 1, 1, 8, 1, 2, 34);
INSERT INTO `escore` VALUES (38, 1, 1, 8, 1, 3, 98);
INSERT INTO `escore` VALUES (39, 1, 1, 8, 1, 4, 98);
INSERT INTO `escore` VALUES (40, 1, 1, 8, 1, 5, 45);
INSERT INTO `escore` VALUES (41, 1, 1, 9, 1, 1, 67);
INSERT INTO `escore` VALUES (42, 1, 1, 9, 1, 2, 56);
INSERT INTO `escore` VALUES (43, 1, 1, 9, 1, 3, 69);
INSERT INTO `escore` VALUES (44, 1, 1, 9, 1, 4, 67);
INSERT INTO `escore` VALUES (45, 1, 1, 9, 1, 5, 97);
INSERT INTO `escore` VALUES (46, 1, 1, 10, 1, 1, NULL);
INSERT INTO `escore` VALUES (47, 1, 1, 10, 1, 2, 123);
INSERT INTO `escore` VALUES (48, 1, 1, 10, 1, 3, 135);
INSERT INTO `escore` VALUES (49, 1, 1, 10, 1, 4, 99);
INSERT INTO `escore` VALUES (50, 1, 1, 10, 1, 5, 39);
INSERT INTO `escore` VALUES (51, 1, 1, 11, 1, 1, 34);
INSERT INTO `escore` VALUES (52, 1, 1, 11, 1, 2, 76);
INSERT INTO `escore` VALUES (53, 1, 1, 11, 1, 3, 55);
INSERT INTO `escore` VALUES (54, 1, 1, 11, 1, 4, 44);
INSERT INTO `escore` VALUES (55, 1, 1, 11, 1, 5, 66);
INSERT INTO `escore` VALUES (56, 1, 1, 12, 1, 1, 67);
INSERT INTO `escore` VALUES (57, 1, 1, 12, 1, 2, 90);
INSERT INTO `escore` VALUES (58, 1, 1, 12, 1, 3, 99);
INSERT INTO `escore` VALUES (59, 1, 1, 12, 1, 4, 67);
INSERT INTO `escore` VALUES (60, 1, 1, 12, 1, 5, 98);
INSERT INTO `escore` VALUES (61, 1, 1, 13, 1, 1, 55);
INSERT INTO `escore` VALUES (62, 1, 1, 13, 1, 2, 87);
INSERT INTO `escore` VALUES (63, 1, 1, 13, 1, 3, 68);
INSERT INTO `escore` VALUES (64, 1, 1, 13, 1, 4, 76);
INSERT INTO `escore` VALUES (65, 1, 1, 13, 1, 5, 45);
INSERT INTO `escore` VALUES (66, 1, 2, 14, 1, 1, NULL);
INSERT INTO `escore` VALUES (67, 1, 2, 14, 1, 2, NULL);
INSERT INTO `escore` VALUES (68, 1, 2, 14, 1, 3, NULL);
INSERT INTO `escore` VALUES (69, 1, 2, 14, 1, 4, NULL);
INSERT INTO `escore` VALUES (70, 1, 2, 14, 1, 5, NULL);
INSERT INTO `escore` VALUES (71, 1, 2, 15, 1, 1, NULL);
INSERT INTO `escore` VALUES (72, 1, 2, 15, 1, 2, NULL);
INSERT INTO `escore` VALUES (73, 1, 2, 15, 1, 3, NULL);
INSERT INTO `escore` VALUES (74, 1, 2, 15, 1, 4, NULL);
INSERT INTO `escore` VALUES (75, 1, 2, 15, 1, 5, NULL);
INSERT INTO `escore` VALUES (76, 1, 2, 16, 1, 1, NULL);
INSERT INTO `escore` VALUES (77, 1, 2, 16, 1, 2, NULL);
INSERT INTO `escore` VALUES (78, 1, 2, 16, 1, 3, NULL);
INSERT INTO `escore` VALUES (79, 1, 2, 16, 1, 4, NULL);
INSERT INTO `escore` VALUES (80, 1, 2, 16, 1, 5, NULL);
INSERT INTO `escore` VALUES (81, 1, 2, 17, 1, 1, NULL);
INSERT INTO `escore` VALUES (82, 1, 2, 17, 1, 2, NULL);
INSERT INTO `escore` VALUES (83, 1, 2, 17, 1, 3, NULL);
INSERT INTO `escore` VALUES (84, 1, 2, 17, 1, 4, NULL);
INSERT INTO `escore` VALUES (85, 1, 2, 17, 1, 5, NULL);
INSERT INTO `escore` VALUES (86, 1, 2, 18, 1, 1, NULL);
INSERT INTO `escore` VALUES (87, 1, 2, 18, 1, 2, NULL);
INSERT INTO `escore` VALUES (88, 1, 2, 18, 1, 3, NULL);
INSERT INTO `escore` VALUES (89, 1, 2, 18, 1, 4, NULL);
INSERT INTO `escore` VALUES (90, 1, 2, 18, 1, 5, NULL);
INSERT INTO `escore` VALUES (91, 1, 2, 19, 1, 1, NULL);
INSERT INTO `escore` VALUES (92, 1, 2, 19, 1, 2, NULL);
INSERT INTO `escore` VALUES (93, 1, 2, 19, 1, 3, NULL);
INSERT INTO `escore` VALUES (94, 1, 2, 19, 1, 4, NULL);
INSERT INTO `escore` VALUES (95, 1, 2, 19, 1, 5, NULL);
INSERT INTO `escore` VALUES (96, 1, 2, 20, 1, 1, NULL);
INSERT INTO `escore` VALUES (97, 1, 2, 20, 1, 2, NULL);
INSERT INTO `escore` VALUES (98, 1, 2, 20, 1, 3, NULL);
INSERT INTO `escore` VALUES (99, 1, 2, 20, 1, 4, NULL);
INSERT INTO `escore` VALUES (100, 1, 2, 20, 1, 5, NULL);
INSERT INTO `escore` VALUES (101, 1, 2, 21, 1, 1, NULL);
INSERT INTO `escore` VALUES (102, 1, 2, 21, 1, 2, NULL);
INSERT INTO `escore` VALUES (103, 1, 2, 21, 1, 3, NULL);
INSERT INTO `escore` VALUES (104, 1, 2, 21, 1, 4, NULL);
INSERT INTO `escore` VALUES (105, 1, 2, 21, 1, 5, NULL);
INSERT INTO `escore` VALUES (106, 1, 2, 22, 1, 1, NULL);
INSERT INTO `escore` VALUES (107, 1, 2, 22, 1, 2, NULL);
INSERT INTO `escore` VALUES (108, 1, 2, 22, 1, 3, NULL);
INSERT INTO `escore` VALUES (109, 1, 2, 22, 1, 4, NULL);
INSERT INTO `escore` VALUES (110, 1, 2, 22, 1, 5, NULL);
INSERT INTO `escore` VALUES (111, 1, 3, 23, 1, 1, NULL);
INSERT INTO `escore` VALUES (112, 1, 3, 23, 1, 2, NULL);
INSERT INTO `escore` VALUES (113, 1, 3, 23, 1, 3, NULL);
INSERT INTO `escore` VALUES (114, 1, 3, 23, 1, 4, NULL);
INSERT INTO `escore` VALUES (115, 1, 3, 23, 1, 5, NULL);
INSERT INTO `escore` VALUES (116, 1, 3, 24, 1, 1, NULL);
INSERT INTO `escore` VALUES (117, 1, 3, 24, 1, 2, NULL);
INSERT INTO `escore` VALUES (118, 1, 3, 24, 1, 3, NULL);
INSERT INTO `escore` VALUES (119, 1, 3, 24, 1, 4, NULL);
INSERT INTO `escore` VALUES (120, 1, 3, 24, 1, 5, NULL);
INSERT INTO `escore` VALUES (121, 1, 3, 25, 1, 1, NULL);
INSERT INTO `escore` VALUES (122, 1, 3, 25, 1, 2, NULL);
INSERT INTO `escore` VALUES (123, 1, 3, 25, 1, 3, NULL);
INSERT INTO `escore` VALUES (124, 1, 3, 25, 1, 4, NULL);
INSERT INTO `escore` VALUES (125, 1, 3, 25, 1, 5, NULL);
INSERT INTO `escore` VALUES (126, 1, 3, 26, 1, 1, NULL);
INSERT INTO `escore` VALUES (127, 1, 3, 26, 1, 2, NULL);
INSERT INTO `escore` VALUES (128, 1, 3, 26, 1, 3, NULL);
INSERT INTO `escore` VALUES (129, 1, 3, 26, 1, 4, NULL);
INSERT INTO `escore` VALUES (130, 1, 3, 26, 1, 5, NULL);
INSERT INTO `escore` VALUES (131, 1, 3, 27, 1, 1, NULL);
INSERT INTO `escore` VALUES (132, 1, 3, 27, 1, 2, NULL);
INSERT INTO `escore` VALUES (133, 1, 3, 27, 1, 3, NULL);
INSERT INTO `escore` VALUES (134, 1, 3, 27, 1, 4, NULL);
INSERT INTO `escore` VALUES (135, 1, 3, 27, 1, 5, NULL);
INSERT INTO `escore` VALUES (136, 1, 3, 28, 1, 1, NULL);
INSERT INTO `escore` VALUES (137, 1, 3, 28, 1, 2, NULL);
INSERT INTO `escore` VALUES (138, 1, 3, 28, 1, 3, NULL);
INSERT INTO `escore` VALUES (139, 1, 3, 28, 1, 4, NULL);
INSERT INTO `escore` VALUES (140, 1, 3, 28, 1, 5, NULL);
INSERT INTO `escore` VALUES (141, 1, 4, 29, 1, 1, NULL);
INSERT INTO `escore` VALUES (142, 1, 4, 29, 1, 2, NULL);
INSERT INTO `escore` VALUES (143, 1, 4, 29, 1, 3, NULL);
INSERT INTO `escore` VALUES (144, 1, 4, 29, 1, 4, NULL);
INSERT INTO `escore` VALUES (145, 1, 4, 29, 1, 5, NULL);
INSERT INTO `escore` VALUES (146, 1, 4, 30, 1, 1, NULL);
INSERT INTO `escore` VALUES (147, 1, 4, 30, 1, 2, NULL);
INSERT INTO `escore` VALUES (148, 1, 4, 30, 1, 3, NULL);
INSERT INTO `escore` VALUES (149, 1, 4, 30, 1, 4, NULL);
INSERT INTO `escore` VALUES (150, 1, 4, 30, 1, 5, NULL);
INSERT INTO `escore` VALUES (151, 1, 4, 31, 1, 1, NULL);
INSERT INTO `escore` VALUES (152, 1, 4, 31, 1, 2, NULL);
INSERT INTO `escore` VALUES (153, 1, 4, 31, 1, 3, NULL);
INSERT INTO `escore` VALUES (154, 1, 4, 31, 1, 4, NULL);
INSERT INTO `escore` VALUES (155, 1, 4, 31, 1, 5, NULL);
INSERT INTO `escore` VALUES (156, 1, 4, 32, 1, 1, NULL);
INSERT INTO `escore` VALUES (157, 1, 4, 32, 1, 2, NULL);
INSERT INTO `escore` VALUES (158, 1, 4, 32, 1, 3, NULL);
INSERT INTO `escore` VALUES (159, 1, 4, 32, 1, 4, NULL);
INSERT INTO `escore` VALUES (160, 1, 4, 32, 1, 5, NULL);
INSERT INTO `escore` VALUES (161, 1, 4, 33, 1, 1, NULL);
INSERT INTO `escore` VALUES (162, 1, 4, 33, 1, 2, NULL);
INSERT INTO `escore` VALUES (163, 1, 4, 33, 1, 3, NULL);
INSERT INTO `escore` VALUES (164, 1, 4, 33, 1, 4, NULL);
INSERT INTO `escore` VALUES (165, 1, 4, 33, 1, 5, NULL);
INSERT INTO `escore` VALUES (166, 1, 4, 34, 1, 1, NULL);
INSERT INTO `escore` VALUES (167, 1, 4, 34, 1, 2, NULL);
INSERT INTO `escore` VALUES (168, 1, 4, 34, 1, 3, NULL);
INSERT INTO `escore` VALUES (169, 1, 4, 34, 1, 4, NULL);
INSERT INTO `escore` VALUES (170, 1, 4, 34, 1, 5, NULL);
INSERT INTO `escore` VALUES (171, 1, 4, 35, 1, 1, NULL);
INSERT INTO `escore` VALUES (172, 1, 4, 35, 1, 2, NULL);
INSERT INTO `escore` VALUES (173, 1, 4, 35, 1, 3, NULL);
INSERT INTO `escore` VALUES (174, 1, 4, 35, 1, 4, NULL);
INSERT INTO `escore` VALUES (175, 1, 4, 35, 1, 5, NULL);
INSERT INTO `escore` VALUES (176, 1, 4, 36, 1, 1, NULL);
INSERT INTO `escore` VALUES (177, 1, 4, 36, 1, 2, NULL);
INSERT INTO `escore` VALUES (178, 1, 4, 36, 1, 3, NULL);
INSERT INTO `escore` VALUES (179, 1, 4, 36, 1, 4, NULL);
INSERT INTO `escore` VALUES (180, 1, 4, 36, 1, 5, NULL);
INSERT INTO `escore` VALUES (181, 1, 4, 37, 1, 1, NULL);
INSERT INTO `escore` VALUES (182, 1, 4, 37, 1, 2, NULL);
INSERT INTO `escore` VALUES (183, 1, 4, 37, 1, 3, NULL);
INSERT INTO `escore` VALUES (184, 1, 4, 37, 1, 4, NULL);
INSERT INTO `escore` VALUES (185, 1, 4, 37, 1, 5, NULL);
INSERT INTO `escore` VALUES (186, 1, 4, 38, 1, 1, NULL);
INSERT INTO `escore` VALUES (187, 1, 4, 38, 1, 2, NULL);
INSERT INTO `escore` VALUES (188, 1, 4, 38, 1, 3, NULL);
INSERT INTO `escore` VALUES (189, 1, 4, 38, 1, 4, NULL);
INSERT INTO `escore` VALUES (190, 1, 4, 38, 1, 5, NULL);
INSERT INTO `escore` VALUES (191, 1, 4, 39, 1, 1, NULL);
INSERT INTO `escore` VALUES (192, 1, 4, 39, 1, 2, NULL);
INSERT INTO `escore` VALUES (193, 1, 4, 39, 1, 3, NULL);
INSERT INTO `escore` VALUES (194, 1, 4, 39, 1, 4, NULL);
INSERT INTO `escore` VALUES (195, 1, 4, 39, 1, 5, NULL);
INSERT INTO `escore` VALUES (196, 1, 4, 40, 1, 1, NULL);
INSERT INTO `escore` VALUES (197, 1, 4, 40, 1, 2, NULL);
INSERT INTO `escore` VALUES (198, 1, 4, 40, 1, 3, NULL);
INSERT INTO `escore` VALUES (199, 1, 4, 40, 1, 4, NULL);
INSERT INTO `escore` VALUES (200, 1, 4, 40, 1, 5, NULL);
INSERT INTO `escore` VALUES (201, 1, 4, 41, 1, 1, NULL);
INSERT INTO `escore` VALUES (202, 1, 4, 41, 1, 2, NULL);
INSERT INTO `escore` VALUES (203, 1, 4, 41, 1, 3, NULL);
INSERT INTO `escore` VALUES (204, 1, 4, 41, 1, 4, NULL);
INSERT INTO `escore` VALUES (205, 1, 4, 41, 1, 5, NULL);
INSERT INTO `escore` VALUES (206, 1, 4, 42, 1, 1, NULL);
INSERT INTO `escore` VALUES (207, 1, 4, 42, 1, 2, NULL);
INSERT INTO `escore` VALUES (208, 1, 4, 42, 1, 3, NULL);
INSERT INTO `escore` VALUES (209, 1, 4, 42, 1, 4, NULL);
INSERT INTO `escore` VALUES (210, 1, 4, 42, 1, 5, NULL);
INSERT INTO `escore` VALUES (211, 2, 1, 1, 1, 1, 78);
INSERT INTO `escore` VALUES (212, 2, 1, 2, 1, 1, 89);
INSERT INTO `escore` VALUES (213, 2, 1, 3, 1, 1, 45);
INSERT INTO `escore` VALUES (214, 2, 1, 4, 1, 1, 67);
INSERT INTO `escore` VALUES (215, 2, 1, 5, 1, 1, 132);
INSERT INTO `escore` VALUES (216, 2, 1, 6, 1, 1, 123);
INSERT INTO `escore` VALUES (217, 2, 1, 7, 1, 1, 45);
INSERT INTO `escore` VALUES (218, 2, 1, 8, 1, 1, 65);
INSERT INTO `escore` VALUES (219, 2, 1, 9, 1, 1, 78);
INSERT INTO `escore` VALUES (220, 2, 1, 10, 1, 1, 144);
INSERT INTO `escore` VALUES (221, 2, 1, 11, 1, 1, 44);
INSERT INTO `escore` VALUES (222, 2, 1, 12, 1, 1, 65);
INSERT INTO `escore` VALUES (223, 2, 1, 13, 1, 1, 87);

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `time` date NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `type` tinyint(2) NULL DEFAULT 1,
  `gradeId` int(11) NULL DEFAULT NULL,
  `clazzId` int(11) NULL DEFAULT NULL,
  `courseId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gid_exam_FK`(`gradeId`) USING BTREE,
  INDEX `clazzid_exam_FK`(`clazzId`) USING BTREE,
  CONSTRAINT `gradeid_exam_FK` FOREIGN KEY (`gradeId`) REFERENCES `grade` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (1, '朝阳中学第三次会考', '2016-01-15', '请科任老师尽快登记成绩', 1, 1, 0, 0);
INSERT INTO `exam` VALUES (2, '1班语文测试', '2016-01-15', '第五单元测试', 2, 1, 1, 1);

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES (1, '2013级');
INSERT INTO `grade` VALUES (2, '2014级');
INSERT INTO `grade` VALUES (3, '2015级');

-- ----------------------------
-- Table structure for grade_course
-- ----------------------------
DROP TABLE IF EXISTS `grade_course`;
CREATE TABLE `grade_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gradeId` int(11) NULL DEFAULT NULL,
  `courseId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gid_gc_FK`(`gradeId`) USING BTREE,
  INDEX `cid_gc_FK`(`courseId`) USING BTREE,
  CONSTRAINT `courseid_gc_FK` FOREIGN KEY (`courseId`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `gradeid_gc_FK` FOREIGN KEY (`gradeId`) REFERENCES `grade` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of grade_course
-- ----------------------------
INSERT INTO `grade_course` VALUES (1, 1, 1);
INSERT INTO `grade_course` VALUES (2, 1, 2);
INSERT INTO `grade_course` VALUES (3, 1, 3);
INSERT INTO `grade_course` VALUES (4, 1, 4);
INSERT INTO `grade_course` VALUES (5, 1, 5);
INSERT INTO `grade_course` VALUES (6, 2, 10);
INSERT INTO `grade_course` VALUES (7, 2, 9);
INSERT INTO `grade_course` VALUES (8, 2, 8);
INSERT INTO `grade_course` VALUES (9, 2, 1);
INSERT INTO `grade_course` VALUES (10, 3, 2);
INSERT INTO `grade_course` VALUES (11, 3, 5);
INSERT INTO `grade_course` VALUES (12, 3, 7);
INSERT INTO `grade_course` VALUES (13, 3, 8);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `clazzId` int(11) NULL DEFAULT NULL,
  `gradeId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `number_student_FK`(`number`) USING BTREE,
  INDEX `cid_stu_FK`(`clazzId`) USING BTREE,
  INDEX `grade_student_FK`(`gradeId`) USING BTREE,
  CONSTRAINT `clazzid_student_FK` FOREIGN KEY (`clazzId`) REFERENCES `clazz` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `grade_student_FK` FOREIGN KEY (`gradeId`) REFERENCES `grade` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '201301001', '蒙奇D路飞', '男', '18345345612', '252345', 1, 1);
INSERT INTO `student` VALUES (2, '201301002', '妮可罗宾', '女', '16342346345', '345745', 1, 1);
INSERT INTO `student` VALUES (3, '201301003', '罗罗诺亚卓洛', '男', '16234574564', '734577', 1, 1);
INSERT INTO `student` VALUES (4, '201301004', '托尼托尼乔巴', '男', '15436574765', '3546634', 1, 1);
INSERT INTO `student` VALUES (5, '201301005', '娜美', '女', '15346235622', '7453256', 1, 1);
INSERT INTO `student` VALUES (6, '201301006', '山治', '男', '16234514532', '8456257', 1, 1);
INSERT INTO `student` VALUES (7, '201301007', '布鲁克', '男', '16345234664', '7257346', 1, 1);
INSERT INTO `student` VALUES (8, '201301008', '乌索普', '男', '16236457676', '8345756', 1, 1);
INSERT INTO `student` VALUES (9, '201301009', '弗兰奇', '男', '17346734768', '23563457', 1, 1);
INSERT INTO `student` VALUES (10, '201301010', '娜菲鲁塔利薇薇', '女', '11452356234', '235345', 1, 1);
INSERT INTO `student` VALUES (11, '201301011', '小鱿', '男', '17632878467', '6235745', 1, 1);
INSERT INTO `student` VALUES (12, '201301012', '梅里号', '男', '15235543456', '2352346', 1, 1);
INSERT INTO `student` VALUES (13, '201301013', '阳光号', '男', '15232342355', '45236', 1, 1);
INSERT INTO `student` VALUES (14, '201302001', '马歇尔蒂奇', '男', '13928398784', '89872874', 2, 1);
INSERT INTO `student` VALUES (15, '201302002', '范奥卡', '男', '13984728784', '89878372', 2, 1);
INSERT INTO `student` VALUES (16, '201302003', '基萨斯巴加斯', '男', '13787287843', '99893727', 2, 1);
INSERT INTO `student` VALUES (17, '201302004', '毒Q', '男', '18787238748', '89387823', 2, 1);
INSERT INTO `student` VALUES (18, '201302005', '雨之希留', '男', '18398782744', '82094987', 2, 1);
INSERT INTO `student` VALUES (19, '201302006', '卡特琳娜', '女', '16392784264', '9793845', 2, 1);
INSERT INTO `student` VALUES (20, '201302007', '圣胡安恶狼', '男', '12787467593', '89874873', 2, 1);
INSERT INTO `student` VALUES (21, '201302008', '巴克斯乔特', '男', '15249797297', '89923832', 2, 1);
INSERT INTO `student` VALUES (22, '201302009', '阿巴罗', '男', '12746763859', '98791235', 2, 1);
INSERT INTO `student` VALUES (23, '201303001', '汉库克', '女', '15234235688', '12575643', 3, 1);
INSERT INTO `student` VALUES (24, '201303002', '桑达索尼娅', '女', '15236386674', '2456568', 3, 1);
INSERT INTO `student` VALUES (25, '201303003', '玛丽哥鲁德', '女', '12364573467', '2634681', 3, 1);
INSERT INTO `student` VALUES (26, '201303004', '玛格丽特', '女', '16353462367', '23467436', 3, 1);
INSERT INTO `student` VALUES (27, '201303005', '艾弗兰德拉', '女', '11345235678', '2352366', 3, 1);
INSERT INTO `student` VALUES (28, '201303006', '贝拉董娜', '女', '14523462567', '7912635', 3, 1);
INSERT INTO `student` VALUES (29, '201304001', '白胡子', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (30, '201304002', '马尔高', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (31, '201304003', '艾斯', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (32, '201304004', '乔兹', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (33, '201304005', '萨奇', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (34, '201304006', '比斯塔', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (35, '201304007', '布拉曼克', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (36, '201304008', '拉克约', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (37, '201304009', '那谬尔', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (38, '201304010', '布伦海姆', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (39, '201304011', '库利艾尔', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (40, '201304012', '金古多', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (41, '201304013', '佛萨', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (42, '201304014', '斯比多基尔', '男', NULL, NULL, 4, 1);
INSERT INTO `student` VALUES (43, '201401001', '日向雏田', '女', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (44, '201401002', '李洛克', '男', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (45, '201401003', '日向花火', '女', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (46, '201401004', '奈良鹿丸', '男', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (47, '201401005', '日向宁次', '男', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (48, '201401006', '佐井', '男', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (49, '201401007', '山中井野', '女', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (50, '201401008', '秋道丁次', '男', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (51, '201401009', '犬冢牙', '男', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (52, '201401010', '野原琳', '女', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (53, '201401011', '天天', '女', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (54, '201401012', '木叶丸', '男', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (55, '201401013', '赤丸', '男', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (56, '201401014', '漩涡鸣人', '男', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (57, '201401015', '佐助', '男', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (58, '201401016', '春野樱', '女', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (59, '201401017', '油女志乃', '男', NULL, NULL, 5, 2);
INSERT INTO `student` VALUES (60, '201402001', '宇智波带土', '男', NULL, NULL, 6, 2);
INSERT INTO `student` VALUES (61, '201402002', '长门', '男', NULL, NULL, 6, 2);
INSERT INTO `student` VALUES (62, '201402003', '绝', '男', NULL, NULL, 6, 2);
INSERT INTO `student` VALUES (63, '201402004', '角都', '男', NULL, NULL, 6, 2);
INSERT INTO `student` VALUES (64, '201402005', '迪达拉', '男', NULL, NULL, 6, 2);
INSERT INTO `student` VALUES (65, '201402006', '小南', '女', NULL, NULL, 6, 2);
INSERT INTO `student` VALUES (66, '201402007', '大蛇丸', '男', NULL, NULL, 6, 2);
INSERT INTO `student` VALUES (67, '201402008', '飞段', '男', NULL, NULL, 6, 2);
INSERT INTO `student` VALUES (68, '201402009', '蝎', '男', '', '', 6, 2);
INSERT INTO `student` VALUES (69, '201402010', '弥彦', '男', NULL, NULL, 6, 2);
INSERT INTO `student` VALUES (70, '201402011', '千柿鬼鲛', '男', NULL, NULL, 6, 2);

-- ----------------------------
-- Table structure for system
-- ----------------------------
DROP TABLE IF EXISTS `system`;
CREATE TABLE `system`  (
  `id` int(11) NOT NULL,
  `schoolName` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `forbidTeacher` tinyint(2) NULL DEFAULT NULL,
  `forbidStudent` tinyint(2) NULL DEFAULT NULL,
  `noticeTeacher` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `noticeStudent` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system
-- ----------------------------
INSERT INTO `system` VALUES (1, '朝阳中学', 0, 0, '请各科任教师尽快登记此次期末考试成绩！！', '寒假于1月18日开始放假，2月20日开学，2月21日正式行课！！！同学们寒假快乐，新年快乐！！！');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `number_teacher_FK`(`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '2001', '卡卡西', '男', '18987831233', '63456345');
INSERT INTO `teacher` VALUES (2, '2002', '卡普', '男', '13927387432', '65686786');
INSERT INTO `teacher` VALUES (3, '2003', '战国', '男', '11389823821', '1233456');
INSERT INTO `teacher` VALUES (4, '2004', '青雉', '男', '15234523454', '7456345');
INSERT INTO `teacher` VALUES (5, '2005', '爱德华纽盖特', '男', '16234243242', '34634534');
INSERT INTO `teacher` VALUES (6, '2006', '香克斯', '男', '16345475689', '35464573');
INSERT INTO `teacher` VALUES (7, '2007', '波风水门', '男', '15234234234', '35683673');
INSERT INTO `teacher` VALUES (8, '2008', '纲手', '女', '14352341231', '73456236');
INSERT INTO `teacher` VALUES (9, '2009', '大筒木辉夜', '女', '13452342342', '234523455');
INSERT INTO `teacher` VALUES (10, '2010', '漩涡玖辛奈', '女', '14423423543', '734562356');
INSERT INTO `teacher` VALUES (14, '2011', '夕日红', '女', '15234234523', '7243821');
INSERT INTO `teacher` VALUES (15, '2012', '鹰眼米霍克', '男', '15236345346', '8345632');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '111111',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `type` tinyint(1) NULL DEFAULT 2,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account_user_FK`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '111111', '管理员', 1);
INSERT INTO `user` VALUES (2, '2001', '111111', '卡卡西', 3);
INSERT INTO `user` VALUES (3, '2002', '111111', '卡普', 3);
INSERT INTO `user` VALUES (4, '2003', '111111', '战国', 3);
INSERT INTO `user` VALUES (5, '2004', '111111', '青雉', 3);
INSERT INTO `user` VALUES (6, '2005', '111111', '爱德华纽盖特', 3);
INSERT INTO `user` VALUES (7, '2006', '111111', '香克斯', 3);
INSERT INTO `user` VALUES (8, '2007', '111111', '波风水门', 3);
INSERT INTO `user` VALUES (9, '2008', '111111', '纲手', 3);
INSERT INTO `user` VALUES (10, '2009', '111111', '大筒木辉夜', 3);
INSERT INTO `user` VALUES (11, '2010', '111111', '漩涡玖辛奈', 3);
INSERT INTO `user` VALUES (15, '2011', '111111', '夕日红', 3);
INSERT INTO `user` VALUES (16, '2012', '111111', '鹰眼米霍克', 3);
INSERT INTO `user` VALUES (17, '201301001', '111111', '蒙奇D路飞', 2);
INSERT INTO `user` VALUES (18, '201301002', '111111', '妮可罗宾', 2);
INSERT INTO `user` VALUES (19, '201301003', '111111', '罗罗诺亚卓洛', 2);
INSERT INTO `user` VALUES (20, '201301004', '111111', '托尼托尼乔巴', 2);
INSERT INTO `user` VALUES (21, '201301005', '111111', '娜美', 2);
INSERT INTO `user` VALUES (22, '201301006', '111111', '山治', 2);
INSERT INTO `user` VALUES (23, '201301007', '111111', '布鲁克', 2);
INSERT INTO `user` VALUES (24, '201301008', '111111', '乌索普', 2);
INSERT INTO `user` VALUES (25, '201301009', '111111', '弗兰奇', 2);
INSERT INTO `user` VALUES (26, '201301010', '111111', '娜菲鲁塔利薇薇', 2);
INSERT INTO `user` VALUES (27, '201301011', '111111', '小鱿', 2);
INSERT INTO `user` VALUES (28, '201301012', '111111', '梅里号', 2);
INSERT INTO `user` VALUES (29, '201301013', '111111', '阳光号', 2);
INSERT INTO `user` VALUES (30, '201302001', '111111', '马歇尔蒂奇', 2);
INSERT INTO `user` VALUES (31, '201302002', '111111', '范奥卡', 2);
INSERT INTO `user` VALUES (32, '201302003', '111111', '基萨斯巴加斯', 2);
INSERT INTO `user` VALUES (33, '201302004', '111111', '毒Q', 2);
INSERT INTO `user` VALUES (34, '201302005', '111111', '雨之希留', 2);
INSERT INTO `user` VALUES (35, '201302006', '111111', '卡特琳娜', 2);
INSERT INTO `user` VALUES (36, '201302007', '111111', '圣胡安恶狼', 2);
INSERT INTO `user` VALUES (37, '201302008', '111111', '巴克斯乔特', 2);
INSERT INTO `user` VALUES (38, '201302009', '111111', '阿巴罗', 2);
INSERT INTO `user` VALUES (39, '201303001', '111111', '汉库克', 2);
INSERT INTO `user` VALUES (40, '201303002', '111111', '桑达索尼娅', 2);
INSERT INTO `user` VALUES (41, '201303003', '111111', '玛丽哥鲁德', 2);
INSERT INTO `user` VALUES (42, '201303004', '111111', '玛格丽特', 2);
INSERT INTO `user` VALUES (43, '201303005', '111111', '艾弗兰德拉', 2);
INSERT INTO `user` VALUES (44, '201303006', '111111', '贝拉董娜', 2);
INSERT INTO `user` VALUES (45, '201304001', '111111', '白胡子', 2);
INSERT INTO `user` VALUES (46, '201304002', '111111', '马尔高', 2);
INSERT INTO `user` VALUES (47, '201304003', '111111', '艾斯', 2);
INSERT INTO `user` VALUES (48, '201304004', '111111', '乔兹', 2);
INSERT INTO `user` VALUES (49, '201304005', '111111', '萨奇', 2);
INSERT INTO `user` VALUES (50, '201304006', '111111', '比斯塔', 2);
INSERT INTO `user` VALUES (51, '201304007', '111111', '布拉曼克', 2);
INSERT INTO `user` VALUES (52, '201304008', '111111', '拉克约', 2);
INSERT INTO `user` VALUES (53, '201304009', '111111', '那谬尔', 2);
INSERT INTO `user` VALUES (54, '201304010', '111111', '布伦海姆', 2);
INSERT INTO `user` VALUES (55, '201304011', '111111', '库利艾尔', 2);
INSERT INTO `user` VALUES (56, '201304012', '111111', '金古多', 2);
INSERT INTO `user` VALUES (57, '201304013', '111111', '佛萨', 2);
INSERT INTO `user` VALUES (58, '201304014', '111111', '斯比多基尔', 2);
INSERT INTO `user` VALUES (59, '201401001', '111111', '日向雏田', 2);
INSERT INTO `user` VALUES (60, '201401002', '111111', '李洛克', 2);
INSERT INTO `user` VALUES (61, '201401003', '111111', '日向花火', 2);
INSERT INTO `user` VALUES (62, '201401004', '111111', '奈良鹿丸', 2);
INSERT INTO `user` VALUES (63, '201401005', '111111', '日向宁次', 2);
INSERT INTO `user` VALUES (64, '201401006', '111111', '佐井', 2);
INSERT INTO `user` VALUES (65, '201401007', '111111', '山中井野', 2);
INSERT INTO `user` VALUES (66, '201401008', '111111', '秋道丁次', 2);
INSERT INTO `user` VALUES (67, '201401009', '111111', '犬冢牙', 2);
INSERT INTO `user` VALUES (68, '201401010', '111111', '野原琳', 2);
INSERT INTO `user` VALUES (69, '201401011', '111111', '天天', 2);
INSERT INTO `user` VALUES (70, '201401012', '111111', '木叶丸', 2);
INSERT INTO `user` VALUES (71, '201401013', '111111', '赤丸', 2);
INSERT INTO `user` VALUES (72, '201401014', '111111', '漩涡鸣人', 2);
INSERT INTO `user` VALUES (73, '201401015', '111111', '佐助', 2);
INSERT INTO `user` VALUES (74, '201401016', '111111', '春野樱', 2);
INSERT INTO `user` VALUES (75, '201401017', '111111', '油女志乃', 2);
INSERT INTO `user` VALUES (76, '201402001', '111111', '宇智波带土', 2);
INSERT INTO `user` VALUES (77, '201402002', '111111', '长门', 2);
INSERT INTO `user` VALUES (78, '201402003', '111111', '绝', 2);
INSERT INTO `user` VALUES (79, '201402004', '111111', '角都', 2);
INSERT INTO `user` VALUES (80, '201402005', '111111', '迪达拉', 2);
INSERT INTO `user` VALUES (81, '201402006', '111111', '小南', 2);
INSERT INTO `user` VALUES (82, '201402007', '111111', '大蛇丸', 2);
INSERT INTO `user` VALUES (83, '201402008', '111111', '飞段', 2);
INSERT INTO `user` VALUES (84, '201402009', '111111', '蝎', 2);
INSERT INTO `user` VALUES (85, '201402010', '111111', '弥彦', 2);
INSERT INTO `user` VALUES (86, '201402011', '111111', '千柿鬼鲛', 2);

SET FOREIGN_KEY_CHECKS = 1;

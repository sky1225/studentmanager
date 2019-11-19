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

 Date: 19/11/2019 11:38:20
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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES (14, '1班', 4);
INSERT INTO `clazz` VALUES (15, '2班', 4);
INSERT INTO `clazz` VALUES (16, '3班', 4);
INSERT INTO `clazz` VALUES (17, '1班', 5);

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
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of clazz_course_teacher
-- ----------------------------
INSERT INTO `clazz_course_teacher` VALUES (22, 14, 4, 11, 16);
INSERT INTO `clazz_course_teacher` VALUES (23, 14, 4, 13, 17);
INSERT INTO `clazz_course_teacher` VALUES (24, 16, 4, 20, 18);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (11, '数学');
INSERT INTO `course` VALUES (13, '软件体系结构');
INSERT INTO `course` VALUES (14, 'C语言');
INSERT INTO `course` VALUES (15, '统一建模语言UML');
INSERT INTO `course` VALUES (16, '软件需求分析');
INSERT INTO `course` VALUES (17, '网络及其计算');
INSERT INTO `course` VALUES (18, '工程经济学');
INSERT INTO `course` VALUES (19, '软件构造');
INSERT INTO `course` VALUES (20, '软件安全');
INSERT INTO `course` VALUES (21, 'C');

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
) ENGINE = InnoDB AUTO_INCREMENT = 228 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of escore
-- ----------------------------
INSERT INTO `escore` VALUES (226, 3, 14, 92, 4, 11, 100);
INSERT INTO `escore` VALUES (227, 3, 14, 93, 4, 11, 98);

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (3, '2017级1班数学测试', '2019-11-18', '', 2, 4, 14, 11);
INSERT INTO `exam` VALUES (4, 'hfut软件安全考试', '2019-11-17', '', 1, 4, 0, 20);
INSERT INTO `exam` VALUES (5, '3班软件安全', '2019-11-17', '', 2, 4, 16, 20);
INSERT INTO `exam` VALUES (6, '2017级2班数学', '2019-11-20', '', 2, 4, 15, 11);
INSERT INTO `exam` VALUES (7, '2017级1班网络及其计算考试', '2019-11-28', '', 2, 4, 14, 17);

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES (4, '2017级');
INSERT INTO `grade` VALUES (5, '2018级');
INSERT INTO `grade` VALUES (6, '2019级');

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
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of grade_course
-- ----------------------------
INSERT INTO `grade_course` VALUES (14, 4, 11);
INSERT INTO `grade_course` VALUES (15, 4, 20);
INSERT INTO `grade_course` VALUES (16, 4, 17);

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
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (83, '2017214919', '余永康', '男', '', '', 16, 4);
INSERT INTO `student` VALUES (85, '2017214917', '李松昊', '女', '', '', 16, 4);
INSERT INTO `student` VALUES (86, '2017214909', '李帮耀', '男', '', '', 16, 4);
INSERT INTO `student` VALUES (87, '2017214906', '吴坤', '男', '', '', 16, 4);
INSERT INTO `student` VALUES (88, '2017214921', '杨云朝', '男', '', '', 16, 4);
INSERT INTO `student` VALUES (89, '2017214907', '王乐恒', '男', '', '', 16, 4);
INSERT INTO `student` VALUES (90, '2017214908', '方明辉', '男', '', '', 16, 4);
INSERT INTO `student` VALUES (91, '2017214814', 'wzx', '男', '', '', 17, 5);
INSERT INTO `student` VALUES (92, '2014214801', '冉子硕', '男', '', '', 14, 4);
INSERT INTO `student` VALUES (93, '2017214802', '侯圣明', '男', '', '', 14, 4);
INSERT INTO `student` VALUES (94, '2017214866', '荣洪成', '男', '', '', 15, 4);
INSERT INTO `student` VALUES (95, '2017214869', '李恒', '男', '', '', 15, 4);

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
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (16, '001', '张慧', '女', '', '');
INSERT INTO `teacher` VALUES (17, '002', '杨志林', '男', '', '');
INSERT INTO `teacher` VALUES (18, '003', 'hfut', '男', '', '');

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
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 'admin', 1);
INSERT INTO `user` VALUES (90, '001', '111111', '张慧', 3);
INSERT INTO `user` VALUES (93, '2017214919', '111111', '余永康', 2);
INSERT INTO `user` VALUES (95, '2017214917', '111111', '李松昊', 2);
INSERT INTO `user` VALUES (96, '2017214909', '111111', '李帮耀', 2);
INSERT INTO `user` VALUES (97, '2017214906', '111111', '吴坤', 2);
INSERT INTO `user` VALUES (98, '2017214921', '111111', '杨云朝', 2);
INSERT INTO `user` VALUES (99, '2017214907', '111111', '王乐恒', 2);
INSERT INTO `user` VALUES (100, '2017214908', '111111', '方明辉', 2);
INSERT INTO `user` VALUES (101, '002', '111111', '杨志林', 3);
INSERT INTO `user` VALUES (102, '2017214814', '111111', 'wzx', 2);
INSERT INTO `user` VALUES (103, '003', '111111', 'hfut', 3);
INSERT INTO `user` VALUES (104, '2014214801', '111111', '冉子硕', 2);
INSERT INTO `user` VALUES (105, '2017214802', '111111', '侯圣明', 2);
INSERT INTO `user` VALUES (106, '2017214866', '111111', '荣洪成', 2);
INSERT INTO `user` VALUES (107, '2017214869', '111111', '李恒', 2);

SET FOREIGN_KEY_CHECKS = 1;

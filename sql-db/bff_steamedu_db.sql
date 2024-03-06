/*
 Navicat Premium Data Transfer

 Source Server         : bff_db
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : bff_steamedu_db

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 06/03/2024 21:25:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`
(
    `course_id`         int(0)                                                        NOT NULL AUTO_INCREMENT COMMENT '课程id',
    `spu_id`            int(0)                                                        NOT NULL COMMENT '商品id',
    `location`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '上课地点',
    `longitude`         decimal(9, 6)                                                 NOT NULL COMMENT '经度',
    `latitude`          decimal(9, 6)                                                 NOT NULL COMMENT '纬度',
    `course_type`       tinyint(0)                                                    NOT NULL COMMENT '课程类型（0代表线上课，1代表线下课，2代表自适应课程）',
    `start_time`        datetime(0)                                                   NOT NULL COMMENT '课程开始时间',
    `class_duration`    int(0)                                                        NOT NULL COMMENT '每节课时长',
    `total_classes`     int(0)                                                        NOT NULL COMMENT '课时数',
    `end_time`          datetime(0)                                                   NULL     DEFAULT NULL COMMENT '课程结束时间',
    `completion_status` tinyint(0)                                                    NOT NULL DEFAULT 0 COMMENT '结课状态（0代表未结课、1代表已结课）',
    `create_time`       datetime(0)                                                   NULL     DEFAULT NULL COMMENT '课程创建时间',
    `status`            tinyint(0)                                                    NULL     DEFAULT 1 COMMENT '状态（0代表已删除、1代表正常）',
    PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_teacher_rel
-- ----------------------------
DROP TABLE IF EXISTS `course_teacher_rel`;
CREATE TABLE `course_teacher_rel`
(
    `ct_rel_id`       int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `course_id`       int(0) NOT NULL COMMENT '课程ID',
    `teacher_user_id` int(0) NOT NULL COMMENT '教师ID',
    `teacher_role`    int(0) NOT NULL COMMENT '教师在课程中的角色（0代表主讲，1表示助教）',
    PRIMARY KEY (`ct_rel_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info`
(
    `id`           int(0)                                                        NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`      int(0)                                                        NOT NULL COMMENT '用户ID',
    `student_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生姓名',
    `gender`       tinyint(0)                                                    NULL DEFAULT NULL COMMENT '性别（0代表女，1代表男）',
    `school`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学校',
    `birthday`     date                                                          NULL DEFAULT NULL COMMENT '生日',
    `contact`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '联系方式',
    `photo`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '照片',
    `grade`        varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '年级',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_courses
-- ----------------------------
DROP TABLE IF EXISTS `user_courses`;
CREATE TABLE `user_courses`
(
    `id`        int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`   int(0) NOT NULL COMMENT '用户id',
    `course_id` int(0) NOT NULL COMMENT '课程id',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_unique_user_course` (`user_id`, `course_id`) USING BTREE COMMENT '唯一索引，确保每个用户对每门课程记录的唯一性'
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户的课程表'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

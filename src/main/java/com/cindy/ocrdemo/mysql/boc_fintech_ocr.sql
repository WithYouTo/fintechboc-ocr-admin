/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : boc_fintech_ocr

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 21/07/2022 15:11:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ocr_invoice
-- ----------------------------
DROP TABLE IF EXISTS `ocr_invoice`;
CREATE TABLE `ocr_invoice`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名称',
  `apply_date` date NULL DEFAULT NULL COMMENT '申请日期',
  `net_img_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传图片路径',
  `invoice_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发票类型   invoice(增值税发票) train(火车票) taxi(出租车票)',
  `invoice_amount` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发票总额',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '待审核' COMMENT '默认为待审核，审核通过，审核失败',
  `audit_id` bigint NULL DEFAULT NULL COMMENT '审核人的用户id',
  `audit_date` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `local_img_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '本机图片路径',
  `create_emp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_emp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '发票报销申请单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ocr_invoice
-- ----------------------------
INSERT INTO `ocr_invoice` VALUES (22, 12345683, 'admin', '2022-07-21', 'http://192.168.26.2:8060/20220721/36d1be30e24348bc9462c376b890576d.jpg', 'invoice', NULL, NULL, '待审核', NULL, NULL, 'D:/data//20220721/36d1be30e24348bc9462c376b890576d.jpg', 'admin', '2022-07-21 11:46:33', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (23, 12345683, 'admin', '2022-07-21', 'http://192.168.26.2:8060/20220721/61b52b9a183e4f67b1e26ab0992bc35c.jpg', 'taxi', NULL, NULL, '待审核', NULL, NULL, 'D:/data//20220721/61b52b9a183e4f67b1e26ab0992bc35c.jpg', 'admin', '2022-07-21 14:07:56', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (24, 12345683, 'admin', '2022-07-21', 'http://192.168.26.2:8060/20220721/efecffcb93f54b37943e650a16c341c3.jpg', 'taxi', NULL, NULL, '待审核', NULL, NULL, 'D:/data//20220721/efecffcb93f54b37943e650a16c341c3.jpg', 'admin', '2022-07-21 14:09:31', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (25, 12345683, 'admin', '2022-07-21', 'http://192.168.26.2:8060/20220721/7360e9db84a340be86a24d78d311912c.jpg', 'train', NULL, NULL, '待审核', NULL, NULL, 'D:/data//20220721/7360e9db84a340be86a24d78d311912c.jpg', 'admin', '2022-07-21 14:12:22', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (26, 12345683, 'admin', '2022-07-21', 'http://192.168.26.2:8060/20220721/d1c544bc4f1a41b087a1c063e2c0c9a4.jpg', 'train', '￥134.5元', NULL, '待审核', NULL, NULL, 'D:/data//20220721/d1c544bc4f1a41b087a1c063e2c0c9a4.jpg', 'admin', '2022-07-21 14:56:47', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (27, 12345683, 'admin', '2022-07-21', 'http://192.168.26.2:8060/20220721/a0c4328484f94affa179c3663af1df71.jpg', 'taxi', '￥42.50元', NULL, '待审核', NULL, NULL, 'D:/data//20220721/a0c4328484f94affa179c3663af1df71.jpg', 'admin', '2022-07-21 14:57:20', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (28, 12345683, 'admin', '2022-07-21', 'http://192.168.26.2:8060/20220721/489b254563fb4a049ea4ebcd9e6cfdbc.jpg', 'invoice', '￥825.15', NULL, '待审核', NULL, NULL, 'D:/data//20220721/489b254563fb4a049ea4ebcd9e6cfdbc.jpg', 'admin', '2022-07-21 14:57:29', NULL, NULL);

-- ----------------------------
-- Table structure for ocr_invoice_detail
-- ----------------------------
DROP TABLE IF EXISTS `ocr_invoice_detail`;
CREATE TABLE `ocr_invoice_detail`  (
  `detail_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `invoice_id` bigint NOT NULL COMMENT '关联主表ocr_invoice中的id',
  `invoice_payer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '购货方名称',
  `invoice_rate_payer_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '购货方纳税人识别号',
  `invoice_payer_addr_tell` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '购货方地址和电话',
  `invoice_payer_bank_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '购货方开户行及账号',
  `cryptographic_area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码区',
  `invoice_daima` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发票代码',
  `invoice_haoma` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发票号码',
  `invoice_issue_date` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开票日期',
  `invoice_goods_list` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '货物或服务名称',
  `unit_price` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单价',
  `invoice_price_list` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '金额',
  `invoice_tax_rate_list` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '税率',
  `invoice_tax_list` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '税额',
  `invoice_total` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '合计金额',
  `invoice_tax_total` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '合计税率',
  `invoice_total_cover_tax` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '价税合计大写总额',
  `invoice_total_cover_tax_digits` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '价税合计小写总额',
  `invoice_seller_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '销售方名称',
  `invoice_seller_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '销售方纳税人识别号',
  `invoice_seller_addr_tell` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '销售方地址和电话',
  `invoice_seller_bank_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '销售方开户行及账号',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_emp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_emp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '增值税发票报销明细内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ocr_invoice_detail
-- ----------------------------
INSERT INTO `ocr_invoice_detail` VALUES (13, 22, '深圳市腾讯计算机系统有限公司', '440300708461136', '深圳市南山区高新区高新南一路飞亚达大厦5-10楼0755-86013388', '招商银行深圳分行振兴支行817282299619961', '*7-0<84019---5+68315-99->/51\n78312-072<3<729-+4<6*315-094\n->/5>18493/1-60*6-43/90<--78', '4403152130', '14998456', '2016年04月11日', '技术服务费', 'fsdfsdfsdfsdfs', 'dsfsdfsdfsdfdsf', '6%', '46.71', '￥778.44', '￥46.71', '捌佰贰拾伍圆壹角伍分', '￥825.15', '深圳市游戏科技有限公司', '440300094040109', '深圳市南山区高新南一道3号赋安科技大楼A座301室0755-86315454', '浦发行深圳科技园支行79210154740015474', 'hahahahhahaa', 'admin', '2022-07-21 11:46:33', NULL, '2022-07-21 11:48:25');
INSERT INTO `ocr_invoice_detail` VALUES (14, 28, '深圳市腾讯计算机系统有限公司', '440300708461136', '深圳市南山区高新区高新南一路飞亚达大厦5-10楼0755-86013388', '招商银行深圳分行振兴支行817282299619961', '*7-0<84019---5+68315-99->/51\n78312-072<3<729-+4<6*315-094\n->/5>18493/1-60*6-43/90<--78', '4403152130', '14998456', '2016年04月11日', '技术服务费', NULL, NULL, '6%', '46.71', '￥778.44', '￥46.71', '捌佰贰拾伍圆壹角伍分', '￥825.15', '深圳市游戏科技有限公司', '440300094040109', '深圳市南山区高新南一道3号赋安科技大楼A座301室0755-86315454', '浦发行深圳科技园支行79210154740015474', NULL, 'admin', '2022-07-21 14:57:29', NULL, NULL);

-- ----------------------------
-- Table structure for ocr_role
-- ----------------------------
DROP TABLE IF EXISTS `ocr_role`;
CREATE TABLE `ocr_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ocr_role
-- ----------------------------

-- ----------------------------
-- Table structure for ocr_taxi_detail
-- ----------------------------
DROP TABLE IF EXISTS `ocr_taxi_detail`;
CREATE TABLE `ocr_taxi_detail`  (
  `taxi_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `invoice_id` bigint NOT NULL COMMENT 'invoice表中主键id',
  `taxi_daima` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发票代码',
  `taxi_haoma` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发票号码',
  `car_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车牌号',
  `taxi_date` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '乘车日期',
  `taxi_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '乘车时间区间',
  `amount` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '乘车金额',
  `price` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单价',
  `mileage` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '里程',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_emp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_emp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`taxi_id`, `invoice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '出租车发票报销明细内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ocr_taxi_detail
-- ----------------------------
INSERT INTO `ocr_taxi_detail` VALUES (1, 0, '144031770219', '31143920', 'BQ4J63', '2017年10月30日', '11:01\n11:20', '￥42.50元', '2.60元', '13.26km', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ocr_taxi_detail` VALUES (2, 0, '137021968052', '29018806', 'BT-1417', '2019-06-12', '21:55-22:21', '54.00元', '3.55', '16.9km', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ocr_taxi_detail` VALUES (3, 24, '144031770219', '31143920', 'BQ4J63', '2017年10月30日', '11:01-11:20', '￥42.50元', '2.60元', '13.26km', 'hahahahaaha', 'admin', '2022-07-21 14:09:31', 'admin', '2022-07-21 14:11:02');
INSERT INTO `ocr_taxi_detail` VALUES (4, 27, '144031770219', '31143920', 'BQ4J63', '2017年10月30日', '11:01\n11:20', '￥42.50元', '2.60元', '13.26km', NULL, 'admin', '2022-07-21 14:57:21', NULL, NULL);

-- ----------------------------
-- Table structure for ocr_train_detail
-- ----------------------------
DROP TABLE IF EXISTS `ocr_train_detail`;
CREATE TABLE `ocr_train_detail`  (
  `train_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `invoice_id` bigint NOT NULL COMMENT 'invoice主键id',
  `ticket_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '票号',
  `ticket_check` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '检票口',
  `station_from` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出发站',
  `station_to` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '到达站',
  `go_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发车日期',
  `goTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开车时间',
  `train` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车次',
  `seat` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '座位号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `identity` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `amount` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '票价',
  `seat_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '席别',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_emp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_emp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`train_id`, `invoice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '火车票发票报销明细内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ocr_train_detail
-- ----------------------------
INSERT INTO `ocr_train_detail` VALUES (1, 0, '237S080148', '沪A售', '上海', '成都', '2012年10月14日0', NULL, 'K696次', '16车088号', NULL, NULL, '￥267:00元', '新空调硬座', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ocr_train_detail` VALUES (2, 0, '17C060124', '检票:A5', '南京南站', '上海虹桥站', '2017年12月23日', '10:33开', 'G5', '15年18A号', '周周', NULL, '￥134.5元', '二等座', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ocr_train_detail` VALUES (3, 25, '17C060124', '检票站A5', '南京南站', '上海虹桥站', '2017年12月23日', '10:33开', 'G5', '15年18A号', '周周', 'dsfsdfsdfsdfsf', '￥134.5元', '二等座', 'fsdfjsdklfjsdlfjsldjflsfjsldf', 'admin', '2022-07-21 14:12:22', 'admin', '2022-07-21 14:57:10');
INSERT INTO `ocr_train_detail` VALUES (4, 26, '17C060124', '检票:A5', '南京南站', '上海虹桥站', '2017年12月23日', '10:33开', 'G5', '15年18A号', '周周', NULL, '￥134.5元', '二等座', NULL, 'admin', '2022-07-21 14:56:47', NULL, NULL);

-- ----------------------------
-- Table structure for ocr_user
-- ----------------------------
DROP TABLE IF EXISTS `ocr_user`;
CREATE TABLE `ocr_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` tinyint NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12345687 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ocr_user
-- ----------------------------
INSERT INTO `ocr_user` VALUES (12345683, 'admin', '$1$BOC$hbX.geEZki5s6sNgZrrQ51', '$1$BOC-FINTECH-SALT', NULL, NULL, NULL, NULL, '2022-07-19 16:09:49');
INSERT INTO `ocr_user` VALUES (12345685, 'test01', '$1$BOC$hbX.geEZki5s6sNgZrrQ51', '$1$BOC-FINTECH-SALT', NULL, NULL, NULL, NULL, '2022-07-20 11:26:12');
INSERT INTO `ocr_user` VALUES (12345686, 'test02', '$1$BOC$hbX.geEZki5s6sNgZrrQ51', '$1$BOC-FINTECH-SALT', NULL, NULL, NULL, NULL, '2022-07-20 11:26:52');

-- ----------------------------
-- Table structure for ocr_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ocr_user_role`;
CREATE TABLE `ocr_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ocr_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

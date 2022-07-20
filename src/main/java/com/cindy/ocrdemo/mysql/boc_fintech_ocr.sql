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

 Date: 20/07/2022 16:20:25
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
  `img_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传图片路径',
  `img_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片名称',
  `invoice_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发票类型   invoice(增值税发票) train(火车票) taxi(出租车票)',
  `invoice_amount` decimal(8, 2) NULL DEFAULT NULL COMMENT '发票总额',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '待审核' COMMENT '默认为待审核，审核通过，审核失败',
  `audit_id` bigint NULL DEFAULT NULL COMMENT '审核人的用户id',
  `audit_date` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '发票报销申请单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ocr_invoice
-- ----------------------------
INSERT INTO `ocr_invoice` VALUES (7, 1, '曾欣', '2022-07-08', 'xxxx', NULL, 'invoice', NULL, '测试数据', '待审核', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (8, 1, '曾欣', '2022-07-08', 'xxxx', NULL, 'invoice', NULL, '测试数据', '待审核', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (9, 1, '曾欣', '2022-07-08', 'xxxx', NULL, 'invoice', NULL, '测试数据', '待审核', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (10, 1, '曾欣', '2022-07-08', 'xxxx', NULL, 'invoice', NULL, '测试数据', '待审核', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (11, 1, '曾欣', '2022-07-09', 'xxxx', NULL, 'invoice', NULL, '测试数据', '待审核', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (12, 1, '曾欣', '2022-07-09', '火车票', NULL, 'traIn', NULL, '火车票测试数据', '待审核', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (13, 1, '曾欣', '2022-07-09', '嘿嘿', NULL, 'taxi', NULL, '出租车测试数据', '待审核', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (14, 1, '曾欣', '2022-07-09', '嘿嘿', NULL, 'taxi', NULL, '出租车测试数据', '待审核', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (15, 1, '曾欣', '2022-07-09', '嘿嘿', NULL, 'taxi', NULL, '出租车测试数据', '待审核', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (16, 1, '曾欣', '2022-07-09', '嘿嘿', NULL, 'taxi', NULL, '出租车测试数据', '待审核', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (17, 1, '曾欣', '2022-07-09', '火车票', NULL, 'traIn', NULL, '火车票测试数据', '待审核', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (18, 1, '曾欣', '2022-07-09', 'xxxx', NULL, 'invoice', NULL, '测试数据', '待审核', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (19, 1, '曾欣', '2022-07-09', 'xxxx', NULL, 'invoice', NULL, '测试数据', '待审核', NULL, NULL);
INSERT INTO `ocr_invoice` VALUES (20, 1, '曾欣', '2022-07-19', 'xxxx', NULL, 'invoice', NULL, '测试数据', '待审核', NULL, NULL);

-- ----------------------------
-- Table structure for ocr_invoice_detail
-- ----------------------------
DROP TABLE IF EXISTS `ocr_invoice_detail`;
CREATE TABLE `ocr_invoice_detail`  (
  `detail_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
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
  PRIMARY KEY (`detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '增值税发票报销明细内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ocr_invoice_detail
-- ----------------------------
INSERT INTO `ocr_invoice_detail` VALUES (4, '深圳市腾讯计算机系统有限公司', '440300708461136', '深圳市南山区高新区高新南一路飞亚达大厦5-10楼0755-86013388', '招商银行深圳分行振兴支行817282299619961', '*7-0<84019---5+68315-99->/51\n78312-072<3<729-+4<6*315-094\n->/5>18493/1-60*6-43/90<--78', '4403152130', '14998456', '2016年04月11日', '技术服务费', NULL, NULL, '6%', '46.71', '￥778.44', '￥46.71', '捌佰贰拾伍圆壹角伍分', '￥825.15', '深圳市游戏科技有限公司', '440300094040109', '深圳市南山区高新南一道3号赋安科技大楼A座301室0755-86315454', '浦发行深圳科技园支行79210154740015474', NULL);
INSERT INTO `ocr_invoice_detail` VALUES (5, '深圳市腾讯计算机系统有限公司', '440300708461136', '深圳市南山区高新区高新南一路飞亚达大厦5-10楼0755-86013388', '招商银行深圳分行振兴支行817282299619961', '*7-0<84019---5+68315-99->/51\n78312-072<3<729-+4<6*315-094\n->/5>18493/1-60*6-43/90<--78', '4403152130', '14998456', '2016年04月11日', '技术服务费', NULL, NULL, '6%', '46.71', '￥778.44', '￥46.71', '捌佰贰拾伍圆壹角伍分', '￥825.15', '深圳市游戏科技有限公司', '440300094040109', '深圳市南山区高新南一道3号赋安科技大楼A座301室0755-86315454', '浦发行深圳科技园支行79210154740015474', NULL);
INSERT INTO `ocr_invoice_detail` VALUES (6, '张家港市百度电器贸易有限公司', '320582691324767', '张家港市经济开发区(国泰北路1号)0512-58901290', '农行张家港市南丰支行526301040005933', '*7*77*4012615/98282+\n-0<9-0419177/+0>64*7-\n2-<5717+<3/92<6/7893+\n7<*<993576>828796>>><1648650', '3200092140', '16486500', '2009年10月27日', '长虹彩电', '1623.9316239', NULL, '17%', '1380.34', '￥8119.66', '￥1380.34', '玖仟伍佰圆整', '￥9500.00', '四川长虹电器股份有限公司无锡销售分公司', '320200836007050', '无锡市解放东路931号东林苑5-80182732903', '工行南长支行1103020909000010567', NULL);
INSERT INTO `ocr_invoice_detail` VALUES (7, '中国地质大学(武汉)', ':12100000441626770L', NULL, NULL, '7>2**1-6359826-42-<*/867993\n73/>34+1741<-6660<6*2853>+3\n<+-<6-82+049/3952<310+<8/->\n69/>11>>34<065969177*+6+-30', NULL, NULL, '2022年04月25日', '*印刷品*数据集隐私保护技术研究\n*印刷品*数据集隐私保护技术研究\n*印刷品*面向云计算环境的访问控制\n技术\n印刷品*面向云计算环境的访问控制\n技术', '120.00\n79.00', NULL, '免税\n免税\n免税\n免税', '***', '￥99.20', NULL, '玖拾玖圆贰角', '￥99.20', '北京京东世纪信息技术有限公司', ':91110302562134916R', '北京市北京经济技术开发区科创十一街18号院C座2层215室62648622', ':招商银行股份有限公司北京青年路支行110907597010206', NULL);
INSERT INTO `ocr_invoice_detail` VALUES (8, '中国地质大学(武汉)', ':12100000441626770L', NULL, NULL, '7>2**1-6359826-42-<*/867993\n73/>34+1741<-6660<6*2853>+3\n<+-<6-82+049/3952<310+<8/->\n69/>11>>34<065969177*+6+-30', NULL, NULL, '2022年04月25日', '*印刷品*数据集隐私保护技术研究\n*印刷品*数据集隐私保护技术研究\n*印刷品*面向云计算环境的访问控制\n技术\n印刷品*面向云计算环境的访问控制\n技术', '120.00\n79.00', NULL, '免税\n免税\n免税\n免税', '***', '￥99.20', NULL, '玖拾玖圆贰角', '￥99.20', '北京京东世纪信息技术有限公司', ':91110302562134916R', '北京市北京经济技术开发区科创十一街18号院C座2层215室62648622', ':招商银行股份有限公司北京青年路支行110907597010206', NULL);
INSERT INTO `ocr_invoice_detail` VALUES (9, '晋中保利房地产开发有限公司', '911407023304928370', '山西省晋中市榆次区张庆工业园区0351-7825328', '交设银行股份有限公司太原新建南路支行141000614019170291546', '250>64-88/>181/99570>/11*6-\n<-0<518>//-4>**46412*-30<52\n5018319><2*47<>*81/8+76*>-5\n0<63/92<//->/*-*64*4+*64555', '4300161130', '01337392', '2016年10月26日', '工程款', NULL, NULL, '11%', '1218918.92', '￥11081081.08', '￥1218918.92', '壹仟贰佰叁拾万圈整', '￥12300000.00', '中国建筑第五工程局有限公司', '91430000183764483Y', '长沙市雨花区中意一路158号0731-85699953', '中医治疗银行新街有限公司长沙升汽子支行63901531061052500762', NULL);
INSERT INTO `ocr_invoice_detail` VALUES (11, '晋中保利房地产开发有限公司', '911407023304928370', '山西省晋中市榆次区张庆工业园区0351-7825328', '交设银行股份有限公司太原新建南路支行141000614019170291546', '250>64-88/>181/99570>/11*6-\n<-0<518>//-4>**46412*-30<52\n5018319><2*47<>*81/8+76*>-5\n0<63/92<//->/*-*64*4+*64555', '4300161130', '01337392', '2016年10月26日', '工程款', NULL, NULL, '11%', '1218918.92', '￥11081081.08', '￥1218918.92', '壹仟贰佰叁拾万圈整', '￥12300000.00', '中国建筑第五工程局有限公司', '91430000183764483Y', '长沙市雨花区中意一路158号0731-85699953', '中医治疗银行新街有限公司长沙升汽子支行63901531061052500762', NULL);

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
  `taxi_daima` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发票代码',
  `taxi_haoma` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发票号码',
  `car_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车牌号',
  `taxi_date` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '乘车日期',
  `taxi_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '乘车时间区间',
  `amount` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '乘车金额',
  `price` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单价',
  `mileage` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '里程',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`taxi_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '出租车发票报销明细内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ocr_taxi_detail
-- ----------------------------
INSERT INTO `ocr_taxi_detail` VALUES (1, '144031770219', '31143920', 'BQ4J63', '2017年10月30日', '11:01\n11:20', '￥42.50元', '2.60元', '13.26km', NULL);
INSERT INTO `ocr_taxi_detail` VALUES (2, '137021968052', '29018806', 'BT-1417', '2019-06-12', '21:55-22:21', '54.00元', '3.55', '16.9km', NULL);

-- ----------------------------
-- Table structure for ocr_train_detail
-- ----------------------------
DROP TABLE IF EXISTS `ocr_train_detail`;
CREATE TABLE `ocr_train_detail`  (
  `train_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
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
  PRIMARY KEY (`train_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '火车票发票报销明细内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ocr_train_detail
-- ----------------------------
INSERT INTO `ocr_train_detail` VALUES (1, '237S080148', '沪A售', '上海', '成都', '2012年10月14日0', NULL, 'K696次', '16车088号', NULL, NULL, '￥267:00元', '新空调硬座', NULL);
INSERT INTO `ocr_train_detail` VALUES (2, '17C060124', '检票:A5', '南京南站', '上海虹桥站', '2017年12月23日', '10:33开', 'G5', '15年18A号', '周周', NULL, '￥134.5元', '二等座', NULL);

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

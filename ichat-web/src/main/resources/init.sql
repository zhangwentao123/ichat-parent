CREATE TABLE `activity_conf` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键id',
  `create_user` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_user` varchar(32) NOT NULL DEFAULT '' COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  `code` varchar(32) DEFAULT NULL COMMENT '活动代码',
  `name` varchar(32) DEFAULT NULL COMMENT '活动名称',
  `begin_time` datetime DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '活动结束时间',
  `from_app` varchar(32) DEFAULT NULL COMMENT '所属应用',
  `activity_url` varchar(200) DEFAULT NULL COMMENT '活动地址',
  `activity_img` varchar(200) DEFAULT NULL COMMENT '活动图片地址',
  `status` varchar(1) DEFAULT NULL,
  `obtain_coupon` varchar(1) DEFAULT NULL COMMENT '获取加息券',
  `obtain_privilege` varchar(1) DEFAULT NULL COMMENT '获取特权码',
  `obtain_exchange` varchar(1) DEFAULT NULL COMMENT '获取兑换码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动'
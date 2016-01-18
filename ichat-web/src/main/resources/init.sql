CREATE TABLE `finance_roles` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `role_name` varchar(255) NOT NULL DEFAULT '' COMMENT '角色名称',
  `code` varchar(100) NOT NULL DEFAULT '' COMMENT '角色代码',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `create_user` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  `update_user` varchar(100) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';


CREATE TABLE `finance_roles_resources` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `role_id` varchar(32) NOT NULL DEFAULT '' COMMENT '角色ID',
  `resources_id` varchar(32) NOT NULL DEFAULT '' COMMENT '资源ID',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `create_user` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  `update_user` varchar(100) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `ROLE_RESOURCES_ID` (`role_id`,`resources_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源表';


CREATE TABLE `finance_resources` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `parent_id` varchar(32) NOT NULL DEFAULT '' COMMENT '父资源ID',
  `modules_name` varchar(50) NOT NULL DEFAULT '' COMMENT '模块名称',
  `modules_code` varchar(50) NOT NULL DEFAULT '' COMMENT '模块代码',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `description` text COMMENT '描述',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '链接',
  `controller` varchar(50) NOT NULL DEFAULT '' COMMENT '类名(PHP端需要)',
  `action` varchar(50) NOT NULL DEFAULT '' COMMENT '方法(PHP端需要)',
  `content` text COMMENT '内容',
  `status` varchar(50) NOT NULL DEFAULT '' COMMENT '状态',
  `type` varchar(50) NOT NULL DEFAULT '' COMMENT '类型(菜单/按钮/API)',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `remark` text COMMENT '标注',
  `title_name` varchar(100) NOT NULL DEFAULT '' COMMENT '标题名称',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `create_user` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  `update_user` varchar(100) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `PARENT_ID` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='理财财务系统资源表';

CREATE TABLE `finance_users_roles` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `role_id` varchar(32) NOT NULL DEFAULT '' COMMENT '角色ID',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `create_user` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  `update_user` varchar(100) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `ROLE_USER_ID` (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';


CREATE TABLE `finance_users_session` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '验证码',
  `channel` varchar(32) NOT NULL DEFAULT '',
  `product` varchar(32) NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `create_user` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  `update_user` varchar(100) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `CODE` (`code`),
  KEY `IDX_USER_ID` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录验证表'
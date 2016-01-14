/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/10/29 14:52:38                          */
/*==============================================================*/

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `T_EASYCAR_ADMIN`;

DROP TABLE IF EXISTS T_REGIONS;

CREATE TABLE `T_EASYCAR_ADMIN` (
	`ID` VARCHAR(32) NOT NULL,
	`CREATE_USER` VARCHAR(50) NOT NULL,
	`UPDATE_USER` VARCHAR(50) NOT NULL,
	`CREATE_TIME` DATETIME DEFAULT NULL,
	`UPDATE_TIME` DATETIME DEFAULT NULL,
	`DEPARTMENT` VARCHAR(255) DEFAULT NULL,
	`EMAIL` VARCHAR(255) NOT NULL,
	`IS_ACCOUNT_ENABLED` BIT(1) NOT NULL,
	`IS_ACCOUNT_EXPIRED` BIT(1) NOT NULL,
	`IS_ACCOUNT_LOCKED` BIT(1) NOT NULL,
	`IS_CREDENTIALS_EXPIRED` BIT(1) NOT NULL,
	`LOCKED_DATE` DATETIME DEFAULT NULL,
	`LOGIN_DATE` DATETIME DEFAULT NULL,
	`LOGIN_FAILURE_COUNT` INT(11) NOT NULL,
	`LOGIN_IP` VARCHAR(255) DEFAULT NULL,
	`NAME` VARCHAR(255) DEFAULT NULL,
	`PASSWORD` VARCHAR(255) NOT NULL,
	`USERNAME` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`ID`),
	UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

alter table T_EASYCAR_ADMIN comment '管理员用户表';

create table T_REGIONS
(
   ID                   varchar(50) not null comment '主键',
   PARENT_REGION_ID     varchar(50) not null comment '父区域ID',
   REGION_PATH          varchar(255) comment '区域路径',
   REGION_GRADE         varchar(255) comment '地区类型',
   LOCAL_NAME           varchar(100) comment '地区名称',
   CREATE_TIME          datetime not null comment '创建时间',
   CREATE_USER          varchar(100) not null comment '创建人',
   UPDATE_TIME          datetime not null comment '最后更新时间',
   UPDATE_USER          varchar(100) not null comment '更新人'
);
alter table T_REGIONS comment '地区表';
/*
SQLyog Trial v13.2.0 (64 bit)
MySQL - 8.0.22 : Database - travel_wdq
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`travel_wdq` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `travel_wdq`;

/*Table structure for table `attractions` */

DROP TABLE IF EXISTS `attractions`;

CREATE TABLE `attractions` (
  `id` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `attractions_name` varchar(255) DEFAULT NULL,
  `attractions_address` varchar(255) DEFAULT NULL,
  `attractions_describe` varchar(255) DEFAULT NULL,
  `attractions_status` int DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `attractions` */

insert  into `attractions`(`id`,`image`,`attractions_name`,`attractions_address`,`attractions_describe`,`attractions_status`,`create_date`) values 
('1','MY_jingdian_01','清凉寨景区','蔡店街道西北部','景区总面积6000余亩...',0,'2026-05-28 20:00:09'),
('9','MY_jingdian_06','桐乡乌镇古镇旅游区','浙江省嘉兴桐乡市乌镇石佛南路18号','乌镇是典型的江南水乡古镇...',0,'2026-05-05 20:00:09');

/*Table structure for table `hotel` */

DROP TABLE IF EXISTS `hotel`;

CREATE TABLE `hotel` (
  `id` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `hotel_name` varchar(255) DEFAULT NULL,
  `hotel_address` varchar(255) DEFAULT NULL,
  `hotel_describe` varchar(255) DEFAULT NULL,
  `hotel_status` int DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hotel` */

insert  into `hotel`(`id`,`image`,`hotel_name`,`hotel_address`,`hotel_describe`,`hotel_status`,`create_date`) values 
('1','MY_kezhan_01','吉安国际酒店111','吉安县城庐陵大道庐陵广场111','酒店功能完善...',1,'2026-02-02 19:36:37'),
('11','MY_kezhan_01','海澜大酒店','江阴市苏南工业重镇新桥镇海澜工业园内','海澜大酒店是一家商务、会议型豪华酒店...',0,'2026-05-22 19:37:04'),
('29','MY_kezhan_04','米兰酒店','广西省南宁市','酒店按欧式风格设计装修...',0,'2026-05-04 19:37:49');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`) values 
(1,'admin','admin');

/*Table structure for table `travel_route` */

DROP TABLE IF EXISTS `travel_route`;

CREATE TABLE `travel_route` (
  `id` varchar(255) NOT NULL,
  `route_name` varchar(255) DEFAULT NULL,
  `route_describe` varchar(255) DEFAULT NULL,
  `route_status` int NOT NULL DEFAULT '0',
  `route_address` varchar(255) DEFAULT NULL,
  `collect_number` int NOT NULL DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `travel_route` */

insert  into `travel_route`(`id`,`route_name`,`route_describe`,`route_status`,`route_address`,`collect_number`,`create_date`,`update_date`) values 
('10','台湾高雄旅游路线','D1捷运美丽岛站光之穹顶...',0,'台湾高雄旅',0,'2026-04-19 09:37:40',NULL),
('11','澳门旅游路线','D1大三巴牌坊...',0,'澳门',0,'2026-06-19 09:37:47',NULL),
('15','河北北戴河旅游路线','D1老虎石海上公园...',0,'河北北戴河',0,'2026-01-01 09:38:05',NULL);

/*Table structure for table `travel_strategy` */

DROP TABLE IF EXISTS `travel_strategy`;

CREATE TABLE `travel_strategy` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `strategy_describe` varchar(255) DEFAULT NULL,
  `strategy_status` int DEFAULT NULL COMMENT '0审核通过,1未审核,2审核未通过',
  `create_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `error_message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `travel_strategy_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `travel_strategy` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`name`) values 
('0bc85e2aa9ac44fbb6cb415271bb5014','qwe','1234','老杜'),
('123123123','user','123456','老杨');

/*Table structure for table `user_attractions` */

DROP TABLE IF EXISTS `user_attractions`;

CREATE TABLE `user_attractions` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `attractions_id` varchar(255) NOT NULL,
  `user_attractions_describe` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `attractions_id` (`attractions_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_attractions_ibfk_1` FOREIGN KEY (`attractions_id`) REFERENCES `attractions` (`id`),
  CONSTRAINT `user_attractions_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_attractions` */

/*Table structure for table `user_hotel` */

DROP TABLE IF EXISTS `user_hotel`;

CREATE TABLE `user_hotel` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `hotel_id` varchar(255) NOT NULL,
  `user_hotel_describe` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_hotel_ibfk_1` (`user_id`),
  KEY `user_hotel_ibfk_2` (`hotel_id`),
  CONSTRAINT `user_hotel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_hotel_ibfk_2` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_hotel` */

/*Table structure for table `user_route` */

DROP TABLE IF EXISTS `user_route`;

CREATE TABLE `user_route` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `route_id` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `route_id` (`route_id`),
  CONSTRAINT `user_route_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_route_ibfk_2` FOREIGN KEY (`route_id`) REFERENCES `travel_route` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_route` */

insert  into `user_route`(`id`,`user_id`,`route_id`,`create_date`) values 
('51265f387283416d8f455cd16a062346','0bc85e2aa9ac44fbb6cb415271bb5014','10','2026-05-29 15:24:22');

/*Table structure for table `user_strategy` */

DROP TABLE IF EXISTS `user_strategy`;

CREATE TABLE `user_strategy` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `strategy_id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_hotel_ibfk_1` (`user_id`),
  KEY `user_hotel_ibfk_2` (`strategy_id`),
  CONSTRAINT `user_strategy_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_strategy_ibfk_2` FOREIGN KEY (`strategy_id`) REFERENCES `travel_strategy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_strategy` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

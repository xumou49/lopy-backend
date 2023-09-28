CREATE SCHEMA `lopy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;

USE `lopy`;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `platform` tinyint(4) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Which platform that user logins',
  `phone` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `locale` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `device` tinyint(4) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



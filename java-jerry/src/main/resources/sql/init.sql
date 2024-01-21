CREATE SCHEMA `lopy_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;

USE `lopy_db`;

drop table if exists c_seating;

drop table if exists c_menu;

drop table if exists c_menu_category;

drop table if exists c_menu_item;

drop table if exists c_order_item;

drop table if exists c_customer;

drop table if exists c_restaurateur;

drop table if exists c_user;

drop table if exists c_orders;

drop table if exists c_restaurant;

drop table if exists c_promotion;

drop table if exists c_restaurant_promotion;

CREATE TABLE `c_user` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                          `open_id` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'open_id',
                          `hashed_password` varbinary(64) DEFAULT NULL COMMENT 'hashed_password',
                          `email` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'email',
                          `type` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'type',
                          `login_platform` tinyint(4) DEFAULT NULL COMMENT 'login_platform',
                          `phone_number` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `device` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `locale` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `deleted` tinyint(4) DEFAULT NULL,
                          `create_date` datetime DEFAULT NULL COMMENT 'create_date',
                          `modify_date` datetime DEFAULT NULL COMMENT 'modify_date',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `c_restaurateur` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                  `user_id` bigint(20) DEFAULT NULL COMMENT 'user_id',
                                  `create_date` datetime DEFAULT NULL COMMENT 'create_date',
                                  `modify_date` datetime DEFAULT NULL COMMENT 'modify_date',
                                  PRIMARY KEY (`id`),
                                  KEY `user_id` (`user_id`),
                                  CONSTRAINT `c_restaurateur_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `c_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='c_restaurateur';

CREATE TABLE `c_customer` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `user_id` bigint(20) DEFAULT NULL COMMENT 'user_id',
                              `create_date` datetime DEFAULT NULL COMMENT 'create_date',
                              `modify_date` datetime DEFAULT NULL COMMENT 'modify_date',
                              PRIMARY KEY (`id`),
                              KEY `user_id` (`user_id`),
                              CONSTRAINT `c_customer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `c_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='c_customer';

CREATE TABLE `c_restaurant` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                `name` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'name',
                                `address` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'address',
                                `labels` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'labels',
                                `restaurateur_id` bigint(20) DEFAULT NULL COMMENT 'restaurateur_id',
                                `operating_hours` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'operating_hours',
                                `contact_details` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'contact_details',
                                `image_url` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `create_date` datetime DEFAULT NULL COMMENT 'create_date',
                                `modify_date` datetime DEFAULT NULL COMMENT 'modify_date',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='c_restaurant';

CREATE TABLE `c_menu` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                          `restaurant_id` bigint(20) DEFAULT NULL COMMENT 'restaurant_id',
                          `menu_name` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'menu_name',
                          `status` tinyint(4) DEFAULT NULL COMMENT 'status [0 -> disable；1 -> enable;]',
                          `create_date` datetime DEFAULT NULL COMMENT 'create_date',
                          `modify_date` datetime DEFAULT NULL COMMENT 'modify_date',
                          PRIMARY KEY (`id`),
                          KEY `restaurant_id` (`restaurant_id`),
                          CONSTRAINT `c_menu_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `c_restaurant` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='c_menu';

CREATE TABLE `c_menu_category` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                   `restaurant_id` bigint(20) DEFAULT NULL COMMENT 'restaurateur_id',
                                   `category_name` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'category_name',
                                   `create_date` datetime DEFAULT NULL COMMENT 'create_date',
                                   `modify_date` datetime DEFAULT NULL COMMENT 'modify_date',
                                   PRIMARY KEY (`id`),
                                   KEY `restaurant_id` (`restaurant_id`),
                                   CONSTRAINT `c_menu_category_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `c_restaurant` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='c_menu_category';

CREATE TABLE `c_menu_item` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `restaurant_id` bigint(20) DEFAULT NULL COMMENT 'restaurateur_id',
                               `item_name` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'item_name',
                               `description` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'description',
                               `price` double DEFAULT NULL COMMENT 'price',
                               `create_date` datetime DEFAULT NULL COMMENT 'create_date',
                               `modify_date` datetime DEFAULT NULL COMMENT 'modify_date',
                               PRIMARY KEY (`id`),
                               KEY `restaurant_id` (`restaurant_id`),
                               CONSTRAINT `c_menu_item_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `c_restaurant` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='c_menu_item';

CREATE TABLE `c_order` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                           `restaurant_id` bigint(20) DEFAULT NULL COMMENT 'restaurant_id',
                           `user_id` bigint(20) DEFAULT NULL COMMENT 'user_id',
                           `status` tinyint(4) DEFAULT NULL COMMENT '(0/1/2/3, pending, preparing, completed, void)',
                           `stripe_invoice_id` bigint(20) DEFAULT NULL COMMENT 'stripe_invoice_id',
                           `total_cost` double DEFAULT NULL COMMENT 'total_cost',
                           `taxes` double DEFAULT NULL COMMENT 'taxes',
                           `discounts` double DEFAULT NULL COMMENT 'discounts',
                           `void_remark` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'void_remark',
                           `create_date` datetime DEFAULT NULL COMMENT 'create_date',
                           `modify_date` datetime DEFAULT NULL COMMENT 'modify_date',
                           PRIMARY KEY (`id`),
                           KEY `restaurant_id` (`restaurant_id`),
                           CONSTRAINT `c_orders_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `c_restaurant` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='c_orders';

CREATE TABLE `c_order_item` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                `order_id` bigint(20) DEFAULT NULL COMMENT 'order_id',
                                `quantity` int(11) DEFAULT NULL COMMENT 'quantity',
                                `item_price` double DEFAULT NULL COMMENT 'item_price',
                                `create_date` datetime DEFAULT NULL COMMENT 'create_date',
                                `modify_date` datetime DEFAULT NULL COMMENT 'modify_date',
                                PRIMARY KEY (`id`),
                                KEY `order_id` (`order_id`),
                                CONSTRAINT `c_order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `c_order` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='c_order_item';

CREATE TABLE `c_promotion` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `name` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `discount` double DEFAULT NULL,
                               `modify_date` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `c_restaurant_promotion` (
                                          `restaurant_id` bigint(20) NOT NULL,
                                          `promotion_id` bigint(20) NOT NULL,
                                          `start_date` datetime DEFAULT NULL,
                                          `end_date` datetime DEFAULT NULL,
                                          `create_date` datetime DEFAULT NULL,
                                          PRIMARY KEY (`restaurant_id`,`promotion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `c_seating` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `restaurant_id` bigint(20) DEFAULT NULL COMMENT 'restaurateur_id',
                             `table_number` int(11) DEFAULT NULL COMMENT 'table_number',
                             `seating_capacity` int(11) DEFAULT NULL COMMENT 'seating_capacity',
                             `status` tinyint(4) DEFAULT NULL COMMENT 'status',
                             `create_date` datetime DEFAULT NULL COMMENT 'create_date',
                             `modify_date` datetime DEFAULT NULL COMMENT 'modify_date',
                             PRIMARY KEY (`id`),
                             KEY `restaurant_id` (`restaurant_id`),
                             CONSTRAINT `c_seating_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `c_restaurant` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='c_seating';


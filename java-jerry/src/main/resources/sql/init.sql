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

/*==============================================================*/
/* Table: c_restaurant                                             */
/*==============================================================*/
create table c_restaurant
(
    id              bigint not null auto_increment comment 'id',
    name            varchar(300) comment 'name',
    address         varchar(300) comment 'address',
    labels          varchar(300) comment 'labels',
    restaurateur_id bigint comment 'restaurateur_id',
    operating_hours varchar(300) comment 'operating_hours',
    contact_details varchar(300) comment 'contact_details',
    create_date     datetime default null comment 'create_date',
    modify_date     datetime default null comment 'modify_date',
    primary key (id)
);

alter table c_restaurant
    comment 'c_restaurant';

/*==============================================================*/
/* Table: c_seating                                        */
/*==============================================================*/
create table c_seating
(
    id               bigint not null auto_increment comment 'id',
    restaurant_id    bigint comment 'restaurateur_id',
    table_number     int comment 'table_number',
    seating_capacity int comment 'seating_capacity',
    status           tinyint comment 'status',
    create_date      datetime default null comment 'create_date',
    modify_date      datetime default null comment 'modify_date',
    primary key (id),
    foreign key (restaurant_id) references c_restaurant (id) on delete cascade
);


alter table c_seating
    comment 'c_seating';

/*==============================================================*/
/* Table: c_menu                             */
/*==============================================================*/
create table c_menu
(
    id            bigint not null auto_increment comment 'id',
    restaurant_id bigint comment 'restaurant_id',
    menu_name     varchar(300) comment 'menu_name',
    status        tinyint comment 'status [0 -> disable；1 -> enable;]',
    create_date   datetime default null comment 'create_date',
    modify_date   datetime default null comment 'modify_date',
    primary key (id),
    foreign key (restaurant_id) references c_restaurant (id) on delete cascade
);


alter table c_menu
    comment 'c_menu';

/*==============================================================*/
/* Table: c_menu_category                                */
/*==============================================================*/
create table c_menu_category
(
    id            bigint not null auto_increment comment 'id',
    restaurant_id bigint comment 'restaurateur_id',
    category_name varchar(300) comment 'category_name',
    create_date   datetime default null comment 'create_date',
    modify_date   datetime default null comment 'modify_date',
    primary key (id),
    foreign key (restaurant_id) references c_restaurant (id) on delete cascade
);

alter table c_menu_category
    comment 'c_menu_category';

/*==============================================================*/
/* Table: c_menu_item                               */
/*==============================================================*/
create table c_menu_item
(
    id            bigint not null auto_increment comment 'id',
    restaurant_id bigint comment 'restaurateur_id',
    item_name     varchar(300) comment 'item_name',
    description   varchar(300) comment 'description',
    price         double comment 'price',
    create_date   datetime default null comment 'create_date',
    modify_date   datetime default null comment 'modify_date',
    primary key (id),
    foreign key (restaurant_id) references c_restaurant (id) on delete cascade
);

alter table c_menu_item
    comment 'c_menu_item';

/*==============================================================*/
/* Table: c_orders                                     */
/*==============================================================*/
create table c_orders
(
    id                bigint not null auto_increment comment 'id',
    restaurant_id     bigint comment 'restaurant_id',
    user_id           bigint comment 'user_id',
    status            tinyint comment '(0/1/2/3, pending, preparing, completed, void)',
    stripe_invoice_id bigint comment 'stripe_invoice_id',
    total_cost        double comment 'total_cost',
    taxes             double comment 'taxes',
    discounts         double comment 'discounts',
    void_remark       varchar(300) comment 'void_remark',
    create_date       datetime default null comment 'create_date',
    modify_date       datetime default null comment 'modify_date',
    primary key (id),
    foreign key (restaurant_id) references c_restaurant (id) on delete no action
);


alter table c_orders
    comment 'c_orders';

/*==============================================================*/
/* Table: c_order_item                                      */
/*==============================================================*/
create table c_order_item
(
    id          bigint not null auto_increment comment 'id',
    order_id    bigint comment 'order_id',
    quantity    int comment 'quantity',
    item_price  double comment 'item_price',
    create_date datetime default null comment 'create_date',
    modify_date datetime default null comment 'modify_date',
    primary key (id),
    foreign key (order_id) references c_orders (id) on delete cascade
);

alter table c_order_item
    comment 'c_order_item';

/*==============================================================*/
/* Table: c_user                                       */
/*==============================================================*/
create table c_user
(
    id              bigint not null auto_increment comment 'id',
    open_id         varchar(256) comment 'open_id',
    hashed_password varbinary(64) comment 'hashed_password',
    email           varchar(300) comment 'email',
    type            varchar(16) comment 'type',
    login_platform  tinyint comment 'login_platform',
    phone_number    varchar(300),
    device          varchar(300),
    locale          varchar(300),
    deleted         tinyint  default null,
    create_date     datetime default null comment 'create_date',
    modify_date     datetime default null comment 'modify_date',
    primary key (id)
);

alter table c_user
    comment 'c_user';

/*==============================================================*/
/* Table: c_customer                                       */
/*==============================================================*/
create table c_customer
(
    id          bigint not null auto_increment comment 'id',
    user_id     bigint comment 'user_id',
    create_date datetime default null comment 'create_date',
    modify_date datetime default null comment 'modify_date',
    primary key (id),
    foreign key (user_id) references c_user (id) on delete cascade
);

alter table c_customer
    comment 'c_customer';

/*==============================================================*/
/* Table: c_restaurateur                                       */
/*==============================================================*/
create table c_restaurateur
(
    id          bigint not null auto_increment comment 'id',
    user_id     bigint comment 'user_id',
    create_date datetime default null comment 'create_date',
    modify_date datetime default null comment 'modify_date',
    primary key (id),
    foreign key (user_id) references c_user (id) on delete cascade
);

alter table c_restaurateur
    comment 'c_restaurateur';
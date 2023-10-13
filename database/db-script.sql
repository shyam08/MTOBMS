drop database sapdb1;
create database sapdb1;
use sapdb1;


create table `country`(
                          `id` integer auto_increment,
                          `name` varchar(30) not null,
                          `code` varchar(5),
                          primary key (`id`),
                          unique index idx_unq_nam (`name`)
);

insert into `country` (`id`,`name`,`code`) values(1,'india','+91');


create table `city`(
                       `id` integer auto_increment,
                       `name` varchar(15) not null,
                       `district` varchar(25),
                       `state` varchar(15),
                       `country_id` integer not null,
                       primary key (`id`),
                       foreign key (`country_id`) references country(`id`),
                       unique index idx_unq_nam (`name`)
);

insert into `city` (`id`,`name`,`district`,`state`,`country_id`) values(1,'Noida','Gautam budh nagar','Uttar Pradesh',1);
insert into `city` (`id`,`name`,`district`,`state`,`country_id`) values(2,'Greater Noida','Gautam budh nagar','Uttar Pradesh',1);


create table `theatre`(
                          `id` integer auto_increment,
                          `name` varchar(20) not null,
                          `city_id` integer not null,
                          `lat` decimal(10,8) not null,
                          `lng` decimal(11,8) not null,
                          `contact_number` long not null,
                          `area` varchar(40),
                          primary key (`id`),
                          foreign key (`city_id`) references city(`id`),
                          unique index idx_unq_nam (`name`)
);

insert into `theatre` (`id`,`name`,`city_id`,`lat`,`lng`,`contact_number`,`area`) values(1,'Wave cinema',1,28.579329,77.391029,9962678921,'wave mall,sector 18');

select * from theatre;
delete from theatre where name ='theatreTest';

create table `audi`(
                       `id` integer auto_increment,
                       `name` varchar(20) not null,
                       `front_seats` integer not null,
                       `middle_seats` integer not null,
                       `back_seats` integer not null,
                       `total_seats` integer not null,
                       `theatr_id` integer not null,
                       primary key (`id`),
                       foreign key (`theatr_id`) references theatre(`id`),
                       unique index idx_unq_nam_trid (`name`,`theatr_id`)
);

insert into `audi` (`id`,`name`,`front_seats`,`middle_seats`,`back_seats`,`total_seats`,`theatr_id`) values(1,'Audi 1',50,30,10,90,1);
insert into `audi` (`id`,`name`,`front_seats`,`middle_seats`,`back_seats`,`total_seats`,`theatr_id`) values(2,'Audi 2',50,40,10,100,1);
insert into `audi` (`id`,`name`,`front_seats`,`middle_seats`,`back_seats`,`total_seats`,`theatr_id`) values(3,'Audi 3',50,30,10,90,1);
insert into `audi` (`id`,`name`,`front_seats`,`middle_seats`,`back_seats`,`total_seats`,`theatr_id`) values(4,'Audi 4',50,40,10,100,1);


create table `movie_variant`(
                                `id` integer auto_increment,
                                `type` varchar(10) not null, /* hindi, english, 3D, tamil, punjabi etc. */
                                primary key (`id`),
                                unique index idx_unq_typ (`type`)
);
insert into `movie_variant` (`id`,`type`) values (1,'Hindi');
insert into `movie_variant` (`id`,`type`) values (2,'English');
insert into `movie_variant` (`id`,`type`) values (3,'Tamil');
insert into `movie_variant` (`id`,`type`) values (4,'Punjabi');
insert into `movie_variant` (`id`,`type`) values (5,'3D');
insert into `movie_variant` (`id`,`type`) values (6,'Animation');



create table `movie`(
                        `id` integer auto_increment,
                        `name` varchar(20) not null,
                        `variant_id` integer not null,
                        `description` varchar(50),
                        `duration_hour` integer not null, /* movie duartion in terms of hour*/
                        `duration_mint` integer not null, /* movie duartion in terms of minutes*/
                        primary key (`id`),
                        foreign key (`variant_id`) references movie_variant(`id`),
                        unique index idx_unq_nam_vrid (`name`,`variant_id`)
);

insert into `movie` (`id`,`name`,`variant_id`,`description`,`duration_hour`,`duration_mint`) values (1,'Pathaan',1,'life of spy',2,30);
insert into `movie` (`id`,`name`,`variant_id`,`description`,`duration_hour`,`duration_mint`) values (2,'Pathaan',2,'life of spy',2,30);
insert into `movie` (`id`,`name`,`variant_id`,`description`,`duration_hour`,`duration_mint`) values (3,'Gadar 2',1,'ek prem katha',3,00);
insert into `movie` (`id`,`name`,`variant_id`,`description`,`duration_hour`,`duration_mint`) values (4,'Heart of stone',1,'action, thriller',2,30);



/*its inventory table for seats*/
create table `movie_shows`(
                              `id` integer auto_increment, /* this is show-id*/
                              `movie_id` integer not null,
                              `audi_id` integer not null,
                              `blocked_seats` integer not null default 0, /* seats after blocking before payment*/
                              `booked_seats` integer not null default 0, /* seats booked after payment*/
                              `show_time` time not null, /* hh:mm:ss */
                              `show_date` date not null, /* YYYY-MM-DD */
                              primary key (`id`),
                              foreign key (`movie_id`) references movie(`id`),
                              foreign key (`audi_id`) references audi(`id`),
                              unique index idx_unq_mvid_adid (`audi_id`,`show_time`,`show_date`)
);

insert into `movie_shows` (`id`,`movie_id`,`audi_id`,`show_time`,`show_date`,`blocked_seats`,`booked_seats`) values (1,'1',1,'10:30:00','2023-08-20',0,0);
insert into `movie_shows` (`id`,`movie_id`,`audi_id`,`show_time`,`show_date`,`blocked_seats`,`booked_seats`) values (2,'1',1,'14:00:00','2023-08-20',0,0);
insert into `movie_shows` (`id`,`movie_id`,`audi_id`,`show_time`,`show_date`,`blocked_seats`,`booked_seats`) values (3,'1',1,'18:30:00','2023-08-20',0,0);
insert into `movie_shows` (`id`,`movie_id`,`audi_id`,`show_time`,`show_date`,`blocked_seats`,`booked_seats`) values (4,'3',2,'10:30:00','2023-08-20',0,0);
insert into `movie_shows` (`id`,`movie_id`,`audi_id`,`show_time`,`show_date`,`blocked_seats`,`booked_seats`) values (5,'3',2,'14:00:00','2023-08-20',0,0);
insert into `movie_shows` (`id`,`movie_id`,`audi_id`,`show_time`,`show_date`,`blocked_seats`,`booked_seats`) values (6,'3',2,'18:30:00','2023-08-20',0,0);
insert into `movie_shows` (`id`,`movie_id`,`audi_id`,`show_time`,`show_date`,`blocked_seats`,`booked_seats`) values (7,'4',3,'10:30:00','2023-08-20',0,0);
insert into `movie_shows` (`id`,`movie_id`,`audi_id`,`show_time`,`show_date`,`blocked_seats`,`booked_seats`) values (8,'4',3,'18:00:00','2023-08-20',0,0);

select (ad.total_seats - (ms.blocked_seats + booked_seats)) as free_seats from movie_shows ms join audi ad on ms.audi_id=ad.id where ms.id=1;

create table `movie_price`(
                              `id` integer auto_increment,
                              `movie_id` integer not null,
                              `movi_base_charges` double not null,
                              `theatr_id` integer not null,
                              `theatr_charges` double default 0,
                              `charges_1` double not null default 0,
                              `charges_2` double not null default 0,
                              `charges_3` double not null default 0,
                              primary key (`id`),
                              foreign key (`movie_id`) references movie(`id`),
                              foreign key (`theatr_id`) references theatre(`id`),
                              unique index idx_unq_mvid_trid (`movie_id`,`theatr_id`)
);

insert into `movie_price` (`id`,`movie_id`,`movi_base_charges`,`theatr_id`,`theatr_charges`,`charges_1`,`charges_2`,`charges_3`) values (1,1,150,1,60,0,0,0);


create table `tax_class`(
                            `id` integer auto_increment,
                            `name` varchar(15) not null,
                            `cgst_prcntg` float default 0,
                            `sgst_prcntg` float default 0,
                            `gst_prcntg` float default 0,
                            `others_prcntg` float default 0,
                            primary key (`id`),
                            unique index idx_unq_name (`name`)
);

insert into `tax_class` (`id`,`name`,`cgst_prcntg`,`sgst_prcntg`,`gst_prcntg`,`others_prcntg`) values (1,'tax_class_1',3,2,2,0);
insert into `tax_class` (`id`,`name`,`cgst_prcntg`,`sgst_prcntg`,`gst_prcntg`,`others_prcntg`) values (2,'tax_class_2',5,5,4,0.01);


-- drop database mms_cart;
-- create database mms_cart;
-- use mms_cart;



/* create seat_lock_1 to seat_lock_..N */
create table `seat_lock_0`(
                              `id` BigInt(15), /*show_id and seat_id*/
                              `insert_time` time not null, /* hh:mm:ss */
                              `lock_owner` varchar(45) not null, /* cart_id is lock owner */
                              primary key (`id`)
);

create table `seat_lock_1`(
                              `id` BigInt(15), /*show_id and seat_id*/
                              `insert_time` time not null, /* hh:mm:ss */
                              `lock_owner` varchar(45) not null, /* cart_id is lock owner */
                              primary key (`id`)
);

create table `seat_lock_2`(
                              `id` BigInt(15), /*show_id and seat_id*/
                              `insert_time` time not null, /* hh:mm:ss */
                              `lock_owner` varchar(45) not null, /* cart_id is lock owner */
                              primary key (`id`)
);

create table `seat_lock_metadata`(
                                     `id` integer auto_increment,
                                     `locks_availble` integer not null default 1, /* total locks table available */
                                     primary key (`id`)
);

insert into `seat_lock_metadata` (`id`,`locks_availble`) values (1,3);



-- drop database mms_order;
-- create database mms_order;
-- use mms_order;

create table `order_detail`(
                               `id` bigint auto_increment,
                               `show_id` integer not null,
                               `user_id` integer not null,
                               `cart_id` varchar(20) not null,
                               `seats`  varchar(50) not null, /* [[row,seat], [row,seat]] */
                               `promo_id` integer,
                               `total_amt` double,
                               `discount_amt` double,
                               `tax_amt` double,
                               `payable_amt` double,
                               `created` timestamp not null DEFAULT CURRENT_TIMESTAMP,
                               `updated` timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               primary key (`id`)
);

create table `orders`(
                         `id` bigint auto_increment, /* order id */
                         `ordr_dtl_id` bigint not null,
                         `next_action` tinyint not null, /* 1- initiatePayment, 2- paymentConfirmation, 3- none */
                         `order_payment_id` varchar(30), /* payment trx id of order */
                         `status` tinyint not null, /*0-pending,1-processing, 2-success, 3-failed*/
                         `reason` varchar(50),
                         `created` timestamp not null DEFAULT CURRENT_TIMESTAMP,
                         `updated` timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         primary key (`id`),
                         foreign key (`ordr_dtl_id`) references order_detail(`id`),
                         unique index idx_unq_pmnt_id (`order_payment_id`)
);


create table `seats_allocation_status`(
                                          `show_id` integer not null,
                                          `seat_id` integer not null,
                                          `status` tinyint not null, /* 0- blocked, 1- booked */
                                          primary key (`show_id`,`seat_id`),
                                          index idx_shwid (`show_id`)
);

insert into `seats_allocation_status` (`show_id`,`seat_id`,`status`) values (1,1,0);
insert into `seats_allocation_status` (`show_id`,`seat_id`,`status`) values (1,2,0);
insert into `seats_allocation_status` (`show_id`,`seat_id`,`status`) values (2,1,0);


-- drop database mms_payment;
-- create database mms_payment;
-- use mms_payment;

create table `payment_gw`(
                             `id` integer auto_increment,
                             `name` varchar(30) not null, /* paytm, phonepe, gpay, billdesk, paypal etc. */
                             `code` varchar(30) not null, /* unique code of gateway */
                             primary key (`id`)
);

insert into `payment_gw` (`id`,`name`,`code`) values (1,'paytmWallet','ptmwl-129');
insert into `payment_gw` (`id`,`name`,`code`) values (2,'paytmUpi','ptmupi-678');
insert into `payment_gw` (`id`,`name`,`code`) values (3,'phonepeUpi','phupi568');

create table `payment_option`(
                                 `id` integer auto_increment,
                                 `name` varchar(30) not null, /* netbanking, credit card, debit card, wallet, cash, UPI etc. */
                                 `code` varchar(30) not null, /* unique code of gateway */
                                 `payment_gw_id` integer not null,
                                 primary key (`id`),
                                 foreign key (`payment_gw_id`) references payment_gw(`id`),
                                 unique index idx_unq_pmgwid_cod (`code`,`payment_gw_id`)
);


create table `gw_transaction`(
                                 `id` bigint auto_increment,
                                 `order_payment_id` varchar(30) not null, /*  mms_order.orders.order_payment_id */
                                 `amt` double not null,
                                 `payment_gateway_id` integer, /* payment_gw.id */
                                 `payment_option_id` integer, /* payment_option.id */
                                 `request` text,
                                 `response` text,
                                 `status` tinyint not null, /*0-pending,1-processing, 2-success, 3-failed*/
                                 `retry` tinyint default 0,
                                 `reason` varchar(50),
                                 `created` timestamp not null DEFAULT CURRENT_TIMESTAMP,
                                 `updated` timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 primary key (`id`),
                                 foreign key (`payment_gateway_id`) references payment_gw(`id`),
                                 unique index idx_unq_pmnt_id (`order_payment_id`)
);


-- drop database mms_promo;
-- create database mms_promo;
-- use mms_promo;

create table `special_promo`(
                                `id` integer auto_increment,
                                `name` varchar(20) not null,
                                `decription` varchar(50) not null,
                                `start_date` date not null,
                                `end_date` date not null,
                                `type` tinyint not null, /* 0-flat, 1-percentage*/
                                `discount` integer not null, /* discount value 10, 20*/
                                `created` timestamp not null DEFAULT CURRENT_TIMESTAMP,
                                `updated` timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                primary key (`id`)
);


-- drop database mms_user;
-- create database mms_user;
-- use mms_user;

create table `customer`(
                           `id` integer auto_increment,
                           `phone_number` bigint not null,
                           `email` varchar(40),
                           `first_name` varchar(20),
                           `last_name` varchar(20),
                           `created` timestamp not null DEFAULT CURRENT_TIMESTAMP,
                           `update` timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           primary key (`id`),
                           unique index idx_unq_phnum (`phone_number`)
);


insert into customer (`id`,`phone_number`,`email`,`first_name`,`last_name`,`created`) values (1,702020202,'ramesh@xyz.com','Ramesh','Kumar','1999-12-31 00:00:00');
insert into customer (`id`,`phone_number`,`email`,`first_name`,`last_name`,`created`) values (2,777768902,'sunil@xyz.com','sunil','Kumar','1999-12-31 00:00:00');
insert into customer (`id`,`phone_number`,`email`,`first_name`,`last_name`,`created`) values (3,788889020,'ravi@xyz.com','ravi','Kumar','1999-12-31 00:00:00');

insert into seat_lock_0 (`id`,`insert_time`,`lock_owner`) values (1,'1999-12-31 00:00:00','locked');
insert into seat_lock_1 (`id`,`insert_time`,`lock_owner`) values (1,'1999-12-31 00:00:00','locked');
insert into seat_lock_2 (`id`,`insert_time`,`lock_owner`) values (1,'1999-12-31 00:00:00','locked');

insert into audi (back_seats,front_seats,middle_seats,name,theatr_id,total_seats) values (2,3,4,'PVR',1,10);


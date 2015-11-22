/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     2010-8-20 15:00:15                           */
/*==============================================================*/


/*==============================================================*/
/* Table: "example_order"                                       */
/*==============================================================*/
create table "example_order"  (
   "id"                 number                          not null,
   "userId"             number,
   "orderName"          varchar(50),
   "status"             number(2),
   "created"            date,
   "modified"           date,
   constraint PK_EXAMPLE_ORDER primary key ("id")
);

/**
 *mysql
 */
CREATE TABLE example_order  (
   `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
   `userId`             INT UNSIGNED NOT NULL,
   `orderName`          varchar(50),
   `status`             int unsigned not null ,
   `created`            DATE NOT NULL,
   `modified`           DATE NOT NULL
);

/*==============================================================*/
/* Table: "example_user"                                        */
/*==============================================================*/
create table "example_user"  (
   "id"                 number                          not null,
   "userAccount"        varchar2(60),
   "userType"           number(2),
   "created"            date,
   "modified"           date,
   constraint PK_EXAMPLE_USER primary key ("id")
);



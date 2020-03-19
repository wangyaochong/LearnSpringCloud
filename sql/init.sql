create database if not exists learnspringcloud character set utf8 collate utf8_bin;
create database if not exists seata_order character set utf8 collate utf8_bin;
create database if not exists seata_storage character set utf8 collate utf8_bin;
create database if not exists seata_account character set utf8 collate utf8_bin;

drop table if exists learnspringcloud.`payment`;
create table learnspringcloud.`payment`
(
    `id`     bigint(20) not null auto_increment primary key comment 'ID',
    `serial` varchar(200) default ''
) engine = InnoDB
  auto_increment = 1
  default charset = utf8;



drop table if exists seata_order.`t_order`;
create table seata_order.`t_order`
(
    `id`         bigint not null auto_increment primary key,
    `user_id`    bigint         default null comment '用户id',
    `product_id` bigint         default null comment '产品id',
    `count`      int            default null comment '数量',
    `money`      decimal(18, 2) default null comment '金额',
    `status`     int            default null comment '订单状态:0创建中，1已完成'
) engine = InnoDB
  auto_increment = 1
  default charset = utf8;

drop table if exists seata_storage.`t_storage`;
create table seata_storage.`t_storage`
(
    `id`         bigint not null auto_increment primary key,
    `product_id` bigint default null comment '产品id',
    `total`      int    default null comment '总库存',
    `used`       int    default null comment '已用库存',
    `residue`    int    default null comment '剩余库存'
) engine = InnoDB
  auto_increment = 1
  default charset = utf8;
insert into seata_storage.t_storage(`id`, `product_id`, `total`, `used`, `residue`) value (1, 1, 100, 0, 100);


drop table if exists seata_account.`t_account`;
create table seata_account.`t_account`
(
    `id`      bigint not null auto_increment primary key,
    `user_id` bigint         default null comment '用户id',
    `total`   decimal(18, 2) default null comment '总额度',
    `used`    decimal(18, 2) default null comment '已用额度',
    `residue` decimal(18, 2) default null comment '剩余库存'
) engine = InnoDB
  auto_increment = 1
  default charset = utf8;
insert into seata_account.t_account(`id`, `user_id`, `total`, `used`, `residue`) value (1, 1, 1000, 0, 1000);



create table if not exists seata_account.undo_log
(
    id            bigint auto_increment comment 'increment id'
        primary key,
    branch_id     bigint       not null comment 'branch transaction id',
    xid           varchar(100) not null comment 'global transaction id',
    context       varchar(128) not null comment 'undo_log context,such as serialization',
    rollback_info longblob     not null comment 'rollback info',
    log_status    int          not null comment '0:normal status,1:defense status',
    log_created   datetime     not null comment 'create datetime',
    log_modified  datetime     not null comment 'modify datetime',
    constraint ux_undo_log
        unique (xid, branch_id)
)
    comment 'AT transaction mode undo table' charset = utf8;

create table if not exists seata_storage.undo_log
(
    id            bigint auto_increment comment 'increment id'
        primary key,
    branch_id     bigint       not null comment 'branch transaction id',
    xid           varchar(100) not null comment 'global transaction id',
    context       varchar(128) not null comment 'undo_log context,such as serialization',
    rollback_info longblob     not null comment 'rollback info',
    log_status    int          not null comment '0:normal status,1:defense status',
    log_created   datetime     not null comment 'create datetime',
    log_modified  datetime     not null comment 'modify datetime',
    constraint ux_undo_log
        unique (xid, branch_id)
)
    comment 'AT transaction mode undo table' charset = utf8;

create table if not exists seata_order.undo_log
(
    id            bigint auto_increment comment 'increment id'
        primary key,
    branch_id     bigint       not null comment 'branch transaction id',
    xid           varchar(100) not null comment 'global transaction id',
    context       varchar(128) not null comment 'undo_log context,such as serialization',
    rollback_info longblob     not null comment 'rollback info',
    log_status    int          not null comment '0:normal status,1:defense status',
    log_created   datetime     not null comment 'create datetime',
    log_modified  datetime     not null comment 'modify datetime',
    constraint ux_undo_log
        unique (xid, branch_id)
)
    comment 'AT transaction mode undo table' charset = utf8;
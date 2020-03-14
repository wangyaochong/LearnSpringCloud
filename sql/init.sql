create database learnspringcloud character set utf8 collate utf8_bin;
use learnspringcloud;
create table `payment`
(
    `id`     bigint(20) not null auto_increment primary key comment 'ID',
    `serial` varchar(200) default ''
) engine = InnoDB
  auto_increment = 1
  default charset = utf8;
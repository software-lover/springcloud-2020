
create table `payment`(
    `id` bigint(20) not null auto_increment primary key comment 'ID',
    `serial` varchar(200) default ''
) engine=InnoDB auto_increment=1 default charset=uf8
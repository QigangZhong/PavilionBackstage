drop table user;
drop table role;
drop table menu;
drop table role_menu;
drop table user_role;

create table user
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username nvarchar(50) NOT NULL,
  password  varchar(50) NOT NULL,
  mobile varchar(20),
  email varchar(128),
  nickname nvarchar(50),
  avatar varchar(256),
  create_time datetime,
  last_update_time datetime
);
insert into user (username, password, mobile, email, nickname,avatar, create_time, last_update_time)
VALUES ('zhongqigang','81a5678baf55573f32cfb98577ec7772','13057271932','zhongqigang@benlai.com','刚子','http://t.cn/RCzsdCq',
        datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));

create table role
(
  id Integer PRIMARY KEY AUTOINCREMENT,
  name nvarchar(50),
  description nvarchar(256),
  create_time datetime,
  last_update_time datetime
);
insert into role VALUES (0,'普通用户','普通用户',datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));
insert into role VALUES (null,'管理员','管理员',datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));
insert into role VALUES (null,'财务人员','财务人员',datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));

create table user_role
(
  user_id INTEGER NOT NULL ,
  role_id INTEGER NOT NULL
);
insert into user_role values(1,1);

create table menu
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  url varchar(128),
  method varchar(10),
  description nvarchar(128),
  create_time datetime,
  last_update_time datetime
);
insert into menu values(null,'/upload','get','上传文件',datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));

create table role_menu
(
  role_id INTEGER NOT NULL DEFAULT 0,
  menu_id INTEGER NOT NULL DEFAULT 0
);
insert into role_menu values(1,1);






--材料表
create table material
(
  id INTEGER PRIMARY KEY AUTOINCREMENT ,
  cpscode varchar(128), --子件编码
  cinvname nvarchar(128), --子件名称
  cinvstd nvarchar(512), --规格型号
  ipsquantity int, --基本用量分子
  type varchar(50), --类型
  create_time datetime,
  last_update_time datetime
);
insert into material values(NULL,'3E0515','绿光模块','规格型号',2,'L',datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));

create table material_price
(
  id INTEGER PRIMARY KEY AUTOINCREMENT ,
  material_id INTEGER not NULL ,
  unit int,
  price decimal(19,6),
  create_time datetime NOT NULL,
  last_update_time datetime
);
insert into material_price values(NULL ,1,1,10.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));
insert into material_price values(NULL ,1,10,9.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));
insert into material_price values(NULL ,1,50,8.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));
insert into material_price values(NULL ,1,100,7.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));
insert into material_price values(NULL ,1,500,6.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));
insert into material_price values(NULL ,1,1000,5.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));
insert into material_price values(NULL ,1,1500,4.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));
insert into material_price values(NULL ,1,2000,3.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));





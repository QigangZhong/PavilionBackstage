drop table IF EXISTS user;
drop table IF EXISTS role;
drop table IF EXISTS menu;
drop table IF EXISTS role_menu;
drop table IF EXISTS user_role;
drop table IF EXISTS cost;
drop table IF EXISTS material;
drop table IF EXISTS material_price;

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
  type varchar(50), --类型
  ipsquantity int, --基本用量分子
  total_price decimal(19,6),--根据基本用量分子以及阶梯价格计算出来的总价
  create_time datetime,
  last_update_time datetime,
  deleted int NOT NULL default 0
);
insert into material values(NULL,'3E0515','绿光模块','EVO-53X-30 20*34.04*29.3mm 3.3W','L',2,0,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'),0);

create table material_price
(
  id INTEGER PRIMARY KEY AUTOINCREMENT ,
  material_id INTEGER not NULL ,
  unit int,
  price decimal(19,6),
  create_time datetime NOT NULL,
  last_update_time datetime,
  deleted int NOT NULL default 0
);
insert into material_price values(NULL ,1,1,10.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'),0);
insert into material_price values(NULL ,1,10,9.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'),0);
insert into material_price values(NULL ,1,50,8.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'),0);
insert into material_price values(NULL ,1,100,7.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'),0);
insert into material_price values(NULL ,1,500,6.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'),0);
insert into material_price values(NULL ,1,1000,5.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'),0);
insert into material_price values(NULL ,1,1500,4.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'),0);
insert into material_price values(NULL ,1,2000,3.00,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'),0);

--更新材料总价
update material set total_price=(material.ipsquantity* (select material_price.price from material_price where material_price.unit<=material.ipsquantity ORDER BY material_price.unit desc));




create table cost
(
  id INTEGER PRIMARY KEY AUTOINCREMENT ,
  labor decimal(19,6), --人工
  wear decimal(19,6), --耗损%
  management decimal(19,6), --管理%
  sale decimal(19,6), --销售%
  create_time datetime,
  last_update_time datetime
);

insert into cost values(null,200,3,10,10,datetime(current_timestamp,'localtime'),datetime(current_timestamp,'localtime'));



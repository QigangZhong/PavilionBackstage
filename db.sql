create table user
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username nvarchar(50) NOT NULL,
  password  varchar(50) NOT NULL,
  mobile varchar(20),
  email varchar(128),
  nickname nvarchar(50),
  role_id int not null,
  create_time datetime,
  last_update_time datetime
);
insert into user (username, password, mobile, email, nickname, role_id, create_time, last_update_time)
VALUES ('zhongqigang','zqg261607','13057271932','zhongqigang@benlai.com','刚子',1,
        datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));

create table role
(
  id Integer PRIMARY KEY AUTOINCREMENT,
  name nvarchar(50),
  description nvarchar(256),
  create_time datetime,
  last_update_time datetime
);
insert into role VALUES (null,'管理员','管理员',datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));
insert into role VALUES (null,'财务人员','财务人员',datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'));

create table menu
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  url varchar(128),
  description nvarchar(128),
  create_time datetime,
  last_update_time datetime
);

create table role_menu
(
  id INTEGER PRIMARY KEY AUTOINCREMENT ,
  role_id INTEGER NOT NULL,
  menu_id INTEGER NOT NULL
);




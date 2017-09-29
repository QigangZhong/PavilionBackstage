create table user
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username nvarchar(50) NOT NULL,
  password  varchar(50) NOT NULL,
  mobile varchar(20),
  email varchar(128),
  nickname nvarchar(50),
  roleid int,
  createtime datetime,
  lastupdatetime datetime,
  isdeleted int
);

insert into user (username, password, mobile, email, nickname, roleid, createtime, lastupdatetime, isdeleted)
VALUES ('zhangsan','123456','13057271932','zhangsan@abc.com','zhangsan',1,datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'),0)
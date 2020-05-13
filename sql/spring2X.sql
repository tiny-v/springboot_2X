create database if not exists spring2X;

use spring2X;

/**角色表**/
create table t_role(
	id int(12) not null auto_increment,
	role_name varchar(60) not null,
	note varchar(60),
	primary key(id)
)

/**用户表**/
create table t_user(
	id int(12) not null auto_increment,
	user_name varchar(60) not null,
	pwd varchar(100) not null,
	/** 是否可用， 1表示可用， 0 表示不可用 **/
	avaliable INT(1) DEFAULT 1 CHECK(avaliable in (0, 1)),
	note varchar(256),
	primary key(id),
	unique(user_name)
)

/**用户角色表**/
create table t_user_role(
	id int(12) not null auto_increment,
	role_id int(12) not null,
	user_id int(12) not null,
	primary key(id),
	unique(role_id, user_id)
)

insert into t_role(role_name, note) values('Admin', '管理员'), ('User', '用户');

insert into t_user(user_name, pwd, avaliable, note) values('tiny_v', '$2a$10$irTt7Ooy6KopN4u0Mymw0.uqSSzgrr4NJk/MtJi6gqUAmf7qsbvW6', 1, '测试账号');

insert into t_user_role(role_id, user_id) values(1, 1);
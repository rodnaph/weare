

drop table if exists users;
drop table if exists activity;
drop table if exists sources;

create table users (
	id int unsigned not null auto_increment,
	title varchar(255) not null,
	email varchar(255) not null,
	pass char(32) not null,
	date_created datetime not null,
	unique ( title ),
	unique ( email ),
	primary key ( id )
);

create table activity (
	id int unsigned not null auto_increment,
	users_id int unsigned not null,
	title varchar(255) not null,
	description varchar(500) null,
	url varchar(500) null,
	date_imported datetime not null,
	date_authored datetime not null,
	primary key ( id )
);

create table sources (
	id int unsigned not null auto_increment,
	users_id int unsigned not null,
	type varchar(50) not null,
	value varchar(500) not null,
	date_created datetime not null,
	primary key ( id )
);

insert into users ( title, email, pass, date_created ) values ( 'test', 'test@example.com', password('pass'), '2012-09-01 21:17:00' );

insert into sources ( users_id, type, value, date_created ) values ( 1, 'github', 'rodnaph', '2012-09-01 21:18:00' );


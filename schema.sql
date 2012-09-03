
drop table if exists groups;
drop table if exists users;
drop table if exists user_groups;
drop table if exists activity;
drop table if exists sources;

create table groups (
    id int unsigned not null auto_increment,
    title varchar(100) not null,
    date_created datetime not null,
    primary key ( id )
);

create table users (
	id int unsigned not null auto_increment,
	name varchar(255) not null,
	email varchar(255) not null,
	pass char(40) not null,
	date_created datetime not null,
	unique ( email ),
	primary key ( id )
);

create table user_groups (
    id int unsigned not null auto_increment,
    user_id int unsigned not null,
    group_id int unsigned not null,
    unique ( user_id, group_id ),
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

insert into groups ( title, date_created ) values ( 'Default Group', '2012-09-02 16:47:00' );

insert into users ( name, email, pass, date_created ) values ( 'Test User', 'test@example.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', '2012-09-01 21:17:00' );

insert into user_groups ( user_id, group_id ) values ( 1, 1 );

insert into sources ( users_id, type, value, date_created ) values ( 1, 'github', 'rodnaph', '2012-09-01 21:18:00' );


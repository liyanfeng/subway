create database subway

use subway
go

create table subway_user(
	id int primary key identity(1,1),
	name varchar(50),
	username varchar(50),
	pwd varchar(60),
	major varchar(30),
	userowner varchar(30),
	authen int,
)

create table fault(
	id int primary key identity(1,1),
	pdate datetime default getdate(),
	code varchar(20),
	grade varchar(20),
	major varchar(30),
	userowner varchar(30),
	finder varchar(50),
	accepter varchar(50),
	acceptTime datetime,
	ptime datetime,
	backtime datetime,
	place varchar(50),
	present varchar(2000),
	process varchar(2000),
	reqModify varchar(20),
	reqback varchar(20),
	subwaystate varchar(20),
	cause varchar(50),
	isConfirm varchar(20),
	confirmPeople varchar(50),
	generatePeople varchar(50),
	device varchar(30),
	causeAnalyse varchar(1000),
	analyseConfirmPeo varchar(50),
	causeConfirmPeo varchar(50),
)

create table syslog(
	id int primary key identity(1,1),
	name varchar(50),
	logTime datetime default getdate(),
	oper varchar(100),
	content varchar(200),
)
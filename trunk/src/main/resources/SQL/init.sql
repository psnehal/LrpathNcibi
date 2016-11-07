drop table ws.TaskLog
create table ws.TaskLog
(
    id int identity(1,1) primary key,
    logEntry text,
    taskUuid varchar(256),
    entryTimestamp datetime
)

drop table ws.Task
create table ws.Task
(
    id int identity(1,1) primary key,
    uuid varchar(256),
    status varchar(16),
    type varchar(16)
)

drop table ws.Splitter
create table ws.Splitter
(
    id int identity(1,1) primary key,
    uuid varchar(256),
    sentences text
)

drop table ws.LRPath
create table ws.LRPath
(
    id int identity(1,1) primary key,
    uuid varchar(256),
    db text,
    ming int,
    maxg int,
    oddsmax float,
    oddsmin float,
    sigcutoff float,
    species varchar(6),
    application varchar(32),
    geneids text,
    sigvals text,
    direction text,
    email varchar(128),
    conceptname varchar(150)
)

drop table ws.ThinkbackArgs
create table ws.ThinkbackArgs
(
	id int identity(1,1) primary key,
	uuid varchar(256),
	template text,
	dataset text,
	classfile text,
	chipfile text
)

drop table ws.Queue
create table ws.Queue
(
	id int identity(1,1) primary key,
	name varchar(32),
	status varchar(32)
)

drop table ws.QMessage
create table ws.QMessage
(
	id int identity(1,1) primary key,
	message nvarchar(2000),
	queueid int foreign key references ws.Queue(id)
)

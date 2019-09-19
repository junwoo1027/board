
select * from mem;
    
    create table mem(
		memberid varchar(50) primary key,
        name varchar(50) not null,
        password varchar(10) not null,
        regdate datetime not null
        )engine=InnoDB default character set =utf8;

create table article(
	article_no int auto_increment primary key,
    write_id varchar(50) not null,
    writer_name varchar(50) not null,
    title varchar(255) not null,
    regdate datetime not null,
    moddate datetime not null,
    read_cnt int
)engine=InnoDB default character set=utf8;


create table content(
	article_no int primary key,
    content text
)engine=InnoDB default character set=utf8;


create table comment(
	comment_no int auto_increment primary key,
    article_no int not null,
    content varchar(200) not null,
    writer_id varchar(50) not null
    )
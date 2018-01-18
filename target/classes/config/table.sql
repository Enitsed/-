create table mem(
	mem_num number,
	mem_id varchar2(20),
	mem_pwd varchar2(20),
	mem_sex varchar2(10),
	mem_name varchar2(100),
	mem_address varchar2(300)	
);

insert into mem values(1,'aa','aa','남','홍길동','서울시');
select * from mem

create sequence mem_num_seq
start with 1
increment by 1
nocache
nocycle;

drop sequence mem_num_seq 

drop table mem
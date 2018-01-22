<<<<<<< HEAD
--------------------------------------------------------
---회원 테이블----------------------------------------------
create table mem(
	mem_num number primary key, --회원번호
	mem_id varchar2(20),		   --회원아이디
	mem_pw varchar2(20),		   --회원비밀번호
	mem_sex varchar2(10),		   --회원성별
	mem_name varchar2(100),		   --회원이름
	mem_email varchar2(20),		   --회원이메일
	mem_address varchar2(300)	   --회원주소
);
drop table mem
drop sequence mem_seq
select * from mem
--회원 테이블 시퀀스
create sequence mem_seq
start with 1
increment by 1
nocache
nocycle;

insert into mem values(2,'testID','pw','남','홍길동','test@naver.com','서울특별시')
insert into mem values(mem_seq.nextval,'a','b','c','d','e','f')
--alter table mem add(mem_email varchar2(20));
--select * from mem
--drop table mem
--drop sequence mem_seq
--------------------------------------------------------
---회원 등급 테이블-------------------------------------------
create table grade(
	mem_num number,      --회원번호
	mem_grade varchar2(100), --회원등급
	constraint grade_mem_num_fk foreign key(mem_num) references mem(mem_num) on delete cascade
	--grade테이블의 mem_num 외래키, 부모(mem_num)삭제시 다 삭제되는 제약조건
);
    		select * from movie where movie_num > 0 AND movie_num <=3


--select * from grade
--drop table grade

--------------------------------------------------------
---영화 테이블----------------------------------------------
create table movie(
	movie_num number primary key,	--영화번호
	movie_kor_title varchar2(500),		--영화한글제목
	movie_eng_title varchar2(500),		--영화영어제목
	movie_opening_date date,			--영화개봉일
	movie_summary varchar2(1000),		--영화줄거리
	movie_production_date date,			--영화제작일
	movie_image varchar2(500),			--이미지
	movie_url varchar2(500)				--url
);
insert into movie values(1,'신과함께','Along with the GODS','2017-12-20','슬픈영화ㅠㅠ','2017-1-1','없음','없음');
insert into movie values(2,'코코','COCO','2017-1-1','애니메이션','2017-1-15','없음','없음');
select * from movie
--영화테이블 시퀀스
create sequence movie_seq
start with 1
increment by 1
nocache
nocycle;

--select * from movie
--drop table movie
--drop sequence movie_seq

-----------------영화 코멘트

create table comment2(
	comment_num number primary key,
	movie_num number,
	replytext varchar2(1000),
	mem_num number,
	mem_id varchar2(20),
	regdate date,
	constraint comment2_movie_num_fk foreign key(movie_num) references movie(movie_num),
	constraint comment2_mem_num_fk foreign key(mem_num) references mem(mem_num)
);
create sequence comment2_num_seq
start with 1
increment by 1
nocache
nocycle;

insert into comment2 values(1,1,'테스트',2,'testID','2017-01-19')
insert into comment2 values(2,2,'테스트2',2,'testID','2017-01-23')
insert into comment2 values(3,2,'테스트3',2,'testID','2017-01-24')
insert into comment2 values(4,2,'테스트4',3,'bbbb','2017-01-25')

select * from comment2
drop table comment2
drop sequence comment2_num_seq
----------------comment
--------------------------------------------------------
---평점 테이블----------------------------------------------
create table rating(
	mem_num number,			--회원번호
	movie_num number(10),		--영화번호
	coment varchar2(1000),		--코멘트
	star_point number(10),		--별점
	write_date date,			--작성날짜
	constraint rating_mem_num_fk foreign key(mem_num) references mem(mem_num) on delete cascade,
	--rating테이블 mem_num 외래키, 부모(mem_num)삭제시 다 삭제되는 제약조건
	constraint rating_movie_num_fk foreign key(movie_num) references movie(movie_num)
	--rating테이블 movie_num 외래키 제약조건
);
	
--select * from rating
--drop table rating

--------------------------------------------------------
---카테고리 테이블----------------------------------------------
create table category(
	movie_category varchar2(500) primary key,	--영화카테고리
	movie_num number,						--영화번호
	constraint category_movie_num_fk foreign key(movie_num) references movie(movie_num)
	--category테이블 movie_num 외래키 제약조건
);

--select * from category
--drop table category


--------------------------------------------------------
---배우 테이블----------------------------------------------
create table actor(
	actor_num number primary key,	--배우번호
	actor_name varchar2(100)			--배우이름
);

--배우테이블 시퀀스
create sequence actor_seq
start with 1
increment by 1
nocache
nocycle;

--select * from actor
--drop table actor
--drop sequence actor_seq

--------------------------------------------------------
---영화배우 테이블-------------------------------------------
create table movie_actor(
	movie_actor_num number primary key,		--영화배우 번호
	movie_num number,						--영화번호
	actor_num number,						--배우번호
	constraint movie_actor_movie_num_fk foreign key(movie_num) references movie(movie_num),
	--movie_actor테이블의 movie_num 외래키 제약조건
	constraint movie_actor_actor_num_fk foreign key(actor_num) references actor(actor_num)
	--movie_actor테이블의 actor_num 외래키 제약조건
);

--select * from movie_actor
--drop table movie_actor

--------------------------------------------------------
---게시판 테이블--------------------------------------------
create table board(
	board_num number primary key,	--게시글번호
	mem_num number,					--회원번호
	board_writer varchar2(10),			--작성자
	board_name varchar2(10),			--제목
	board_content varchar2(500),		--내용
	board_hits number(10),				--조회수
	board_relnum number(10),			--관련글번호
	board_reply_level number,		--답글레벨
	board_reply_step number,		--답글단계
	board_date date,					--작성일
	board_reply_amount number,		--댓글개수
	constraint board_mem_num_fk foreign key(mem_num) references mem(mem_num) on delete cascade
	--board테이블 mem_num 외래키, 부모(mem_num)삭제시 다 삭제되는 제약조건
);
--게시판테이블 시퀀스
create sequence board_seq
start with 1
increment by 1
nocache
nocycle;

--select * from board
--drop table board
--drop sequence board_seq

--------------------------------------------------------
---댓글 테이블----------------------------------------------
create table reply(
	board_num number(10),			--게시글번호
	mem_num number,				--회원번호
	reply_num number(10),			--댓글번호
	reply_content varchar2(1000),	--내용
	reply_writer varchar2(100),		--작성자
	constraint reply_board_num_fk foreign key(board_num) references board(board_num) on delete cascade,
	--reply테이블 board_num 외래키, 부모(board_num)삭제시 다 삭제되는 제약조건
	constraint reply_mem_num_fk foreign key(mem_num) references mem(mem_num) on delete cascade
	--reply테이블 mem_num 외래키, 부모(mem_num)삭제시 다 삭제되는 제약조건
);

--select * from reply
--drop table reply
















=======
drop sequence mem_num_seq 

drop table mem
>>>>>>> sxdf

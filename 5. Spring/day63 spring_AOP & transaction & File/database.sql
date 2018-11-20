-- 댓글 수 column 추가
alter table tbl_board add (replycnt number default 0);

-- 기존 댓글 존재 시 반영하기
update tbl_board set replycnt = (select count(rno) from tbl_reply where tbl_reply.bno = tbl_board.bno);

-- 파일 업로드
create table tbl_attach (
  uuid varchar2(100) not null,
  uploadPath varchar2(200) not null,
  fileName varchar2(100) not null,
  filetype char(1) default 'I',
  bno number(10,0)
);

alter table tbl_attach add constraint pk_attach primary key (uuid);

alter table tbl_attach add constraint fk_board_attach foreign key (bno) references tbl_board(bno);

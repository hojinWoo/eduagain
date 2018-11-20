-- 댓글 수 column 추가
alter table tbl_board add (replycnt number default 0);

-- 기존 댓글 존재 시 반영하기
update tbl_board set replycnt = (select count(rno) from tbl_reply where tbl_reply.bno = tbl_board.bno);

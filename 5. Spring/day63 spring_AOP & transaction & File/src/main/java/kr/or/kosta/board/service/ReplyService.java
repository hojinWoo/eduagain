package kr.or.kosta.board.service;

import java.util.List;

import kr.or.kosta.board.domain.Criteria;
import kr.or.kosta.board.domain.ReplyPageDTO;
import kr.or.kosta.board.domain.ReplyVO;

public interface ReplyService {

	public int register(ReplyVO vo);

	public ReplyVO get(Long rno);

	public int modify(ReplyVO vo);

	public int remove(Long rno);

	public List<ReplyVO> getList(Criteria cri, Long bno);
	
	public ReplyPageDTO getListPage(Criteria cri, Long bno);

}

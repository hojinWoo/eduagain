package kr.or.kosta.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.kosta.board.domain.Criteria;
import kr.or.kosta.board.domain.ReplyVO;

public interface ReplyMapper {

	public int insert(ReplyVO vo);

	public ReplyVO read(Long bno);

	public int delete(Long bno);

	public int update(ReplyVO reply);

	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);

	public int getCountByBno(Long bno);
}

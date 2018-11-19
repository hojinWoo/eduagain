package kr.or.kosta.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.or.kosta.board.domain.BoardVO;
import kr.or.kosta.board.domain.Criteria;

public interface BoardMapper {

	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);

	public void insert(BoardVO board);

	public Integer insertSelectKey(BoardVO board);

	public BoardVO read(Long bno);

	public int delete(Long bno);

	public int update(BoardVO board);

	public int getTotalCount(Criteria cri);
}
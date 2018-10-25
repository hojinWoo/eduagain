package kr.or.kosta.shoppingmall.user.service;

import kr.or.kosta.shoppingmall.user.domain.User;
import java.util.List;

/**
 * 고객의 요구사항 반영한 도메인별 비즈니스 method 선언
 * 복잡한 트랜잭선 쳐리, 예외처리 등
 * @author hojin
 */
public interface UserService {
	/* 회원 검색 */
	public User search(String id) throws Exception;
	/* 회원 목록 */
	public List<User> list() throws Exception;
}

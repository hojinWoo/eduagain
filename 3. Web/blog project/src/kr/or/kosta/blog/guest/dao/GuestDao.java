package kr.or.kosta.blog.guest.dao;

import java.util.List;

/**
 * Dao 패턴 적용을 위한 방명록 인터페이스 선언
 */
public interface GuestDao {
	public void create(Guest guest) throws Exception;
	
	public List<Guest> listAll(String id) throws Exception;
}

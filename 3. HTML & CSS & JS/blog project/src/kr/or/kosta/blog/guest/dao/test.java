package kr.or.kosta.blog.guest.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.kosta.blog.DaoFactory;
import kr.or.kosta.blog.guest.domain.GuestJdbcDaoFactory;

public class test {
	public static void main(String[] args) {
		DaoFactory factory = new GuestJdbcDaoFactory();
		GuestDao dao = factory.getGuestDao();
		try {
			System.out.println("**** 전체목록 테스트 ****");
			List<Guest> list = dao.listAll("aa");
			for(Guest guest : list) {
				System.out.println(guest);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println();
			SQLException ex = (SQLException)e;
			System.out.println(ex.getErrorCode());
		}
	}
}

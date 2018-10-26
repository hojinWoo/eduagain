package kr.or.kosta.blog.user.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.kosta.blog.factory.DaoFactory;
import kr.or.kosta.blog.user.domain.UserJdbcDaoFactory;

public class UserDaoTest {

	public static void main(String[] args) {
		DaoFactory factory = new UserJdbcDaoFactory();
		UserDao dao = factory.getUserDao();
		try {
			
			System.out.println("**** 전체목록 테스트 ****");
			List<User> list =  dao.listAll();
			for (User user : list) {
				System.out.println(user);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println();
			SQLException ex = (SQLException)e;
			System.out.println(ex.getErrorCode());
		}
		
		
		

	}

}

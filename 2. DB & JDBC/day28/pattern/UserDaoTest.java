package pattern;

import java.sql.SQLException;

public class UserDaoTest {
	public static void main(String[] args) {
		UserDao dao = new jdbcUserDao();
		
		User user = new User();
		user.setId("sss");
		user.setName("hj");
		user.setEmail("ss@.");
		user.setPasswd("1111");
		
		try {
			dao.create(user);
			System.out.println("회원가입 완료");
			user = new User();
			user = dao.read("sss");
			System.out.println(user.toString());
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			e.printStackTrace();
//			SQLException ex = (SQLException)e;
//			System.out.println(ex.getErrorCode());
		}
	}
}

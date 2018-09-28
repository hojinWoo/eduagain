package pattern;

import java.util.List;
import java.util.Map;

public class UserDaoTest {
	public static void main(String[] args) {
		UserDao dao = new jdbcUserDao();
		
		User user = new User();
		user.setId("sss");
		user.setName("hj");
		user.setEmail("ss@.");
		user.setPasswd("1111");
		
		try {
//			dao.create(user);
//			System.out.println("회원가입 완료");
			
//			user = new User();
//			user = dao.read("sss");
			
//			user.setName("sdf");
//			dao.update(user);
//			
//			user = dao.read("sss");
//			System.out.println(user.toString());
			
//			dao.delete("dd");
			
//			System.out.println(dao.listAll());
			
//			System.out.println(dao.certify("dd", "1234"));
			
			List<Map<String, String>> list = dao.employeeList();
			for (Map<String, String> map : list) {
				System.out.println(map);
			}
			
			
			
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			e.printStackTrace();
//			SQLException ex = (SQLException)e;
//			System.out.println(ex.getErrorCode());
		}
	}
}

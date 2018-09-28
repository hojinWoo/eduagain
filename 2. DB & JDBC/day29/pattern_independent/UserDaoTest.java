package pattern_independent;

import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;

public class UserDaoTest {
	
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER_ID = "hr";
	private static final String USER_PW = "hr";
	
	public static void main(String[] args) {
		
		jdbcUserDao dao = new jdbcUserDao();

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DRIVER_NAME);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(USER_ID);
		dataSource.setPassword(USER_PW);
		dataSource.setInitialSize(5);
		dataSource.setMaxTotal(10);
		dataSource.setMaxIdle(7);
		
		dao.setDatasource(dataSource);
		
		try {
			System.out.println("전체 test");
			List<User> list = dao.listAll();
			for (User user : list) {
				System.out.println(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * DAO 생성하는 Factory +  Connection 할당 코드가 최종 목적
		 * 나중에는 Spring에서 다시 다룰 것
		 * */
	}
}

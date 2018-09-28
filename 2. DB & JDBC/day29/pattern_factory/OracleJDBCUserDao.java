package pattern_factory;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.dbcp2.BasicDataSource;

public class OracleJDBCUserDao extends jdbcUserDao {
	
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER_ID = "hr";
	private static final String USER_PW = "hr";
	
	@Override
	public Connection getConnection() throws Exception {
		//connection pool 적용하여 connection 생성 (서버쪽에서 사용이기 때문에 필요)
		//Connection Pool이란 클라이언트의 요청 시점에 Connection을 연결하는 것이 아니라 미리 일정수의 Connection 을 만들어 놓고 필요한 애플리케이션에 전달하여 이용하도록 하는 풀링 방법.
//		return UserConnectionPool.getInstance().getConnection(); //UserConnectionPool
		
		//ApacheDataSourceExample
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DRIVER_NAME);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(USER_ID);
		dataSource.setPassword(USER_PW);
		dataSource.setInitialSize(5);
		dataSource.setMaxTotal(10);
		dataSource.setMaxIdle(7); //다시 줄어들 때 정해지는 max 설정
		return dataSource.getConnection();
	}

}

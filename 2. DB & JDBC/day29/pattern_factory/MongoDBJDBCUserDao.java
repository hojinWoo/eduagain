package pattern_factory;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * sample용
 * @author hojin
 *
 */
public class MongoDBJDBCUserDao extends jdbcUserDao {
	@Override
	public Connection getConnection() throws Exception {
		// MongoDB에 맞게 Connection 생성 (가정)
		return null;
	}
}

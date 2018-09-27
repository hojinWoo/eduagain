package pattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class jdbcUserDao implements UserDao{
	
	private static final String driver = "oracle.jdbc.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String username = "hr";
	private static final String password = "hr";

	@Override
	public void create(User user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO users \r\n" + 
					"VALUES     (?, \r\n" + 
					"            ?, \r\n" + 
					"            ?, \r\n" + 
					"            ?, \r\n" + 
					"            sysdate) ";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPasswd());
			pstmt.setString(4, user.getEmail());
			
			int count = pstmt.executeUpdate();
			System.out.println(count+"행이 추가되었습니다.");
		}finally {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}
	}

	@Override
	public User read(String id) throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT * \r\n" + 
					"FROM   users \r\n" + 
					"WHERE  id = '" + id + "'";
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			user = new User();
			if(rs.next()) {
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPasswd(rs.getString("passwd"));
				user.setRegdate(rs.getString("regdate"));
			}
		}finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(con != null) con.close();
		}
		return user;
	}

	@Override
	public void update(User user) throws Exception {
		
	}

	@Override
	public void delete(String id) throws Exception {
		
	}

	@Override
	public List<User> listAll() throws Exception {
		return null;
	}

	@Override
	public User certify(String id, String passwd) throws Exception {
		return null;
	}

	@Override
	public Connection getConnection() throws Exception {
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, username, password);
	}
}

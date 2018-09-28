package pattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE users \r\n" + 
					"SET    name = ?, \r\n" + 
					"       passwd = ?, \r\n" + 
					"       email = ? \r\n" + 
					"WHERE  id = ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPasswd());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getId());
			
			int count = pstmt.executeUpdate();
			System.out.println(count+"행이 변경되었습니다.");
		}finally {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}
	}

	@Override
	public void delete(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE users \r\n" + 
					"WHERE  id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, id);
			
			int count = pstmt.executeUpdate();
			System.out.println(count+"행이 삭제되었습니다.");
		}finally {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}
	}

	@Override
	public List<User> listAll() throws Exception {
		List<User> list = new ArrayList<User>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * \r\n" + 
					"FROM   users ";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			User user = null;
			while(rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPasswd(rs.getString("passwd"));
				user.setRegdate(rs.getString("regdate"));
				list.add(user);
			}
			return list;
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}
		
	}

	@Override
	public User certify(String id, String passwd) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * \r\n" + 
					"FROM   users \r\n" + 
					"WHERE  id = ? \r\n" + 
					"       AND passwd = ? ";
		try {
			User user = null;
			
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPasswd(rs.getString("passwd"));
				user.setRegdate(rs.getString("regdate"));
			}
			return user;
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}
	}
	
	@Override
	public List<Map<String, String>> employeeList() throws Exception {
		//join할 때마다 class를 만드는 것이 아닌 Map을 사용해서 저장해서 보낸다.!!! Map을 잘 사용해야 한다.
		List<Map<String, String>> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT e.employee_id     eid, \r\n" + 
				"       e.last_name       ename, \r\n" + 
				"       e.salary          salary, \r\n" + 
				"       d.department_name dname, \r\n" + 
				"       l.city            city \r\n" + 
				"		FROM   employees e \r\n" + 
				"       	LEFT OUTER JOIN departments d \r\n" + 
				"                    ON e.department_id = d.department_id \r\n" + 
				"       	LEFT OUTER JOIN locations l \r\n" + 
				"                    ON d.location_id = l.location_id \r\n" + 
				"		ORDER  BY eid ASC";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columncount = rsmd.getColumnCount();
			Map<String, String> row = null;
			String columnName = null;
			String columnValue = null;
			while(rs.next()) {
				row = new HashMap<>();
				for (int i = 1; i <= columncount; i++) {
					columnName = rsmd.getColumnLabel(i);
					columnValue = rs.getString(i);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}
			
			return list;
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}
	}

	@Override
	public Connection getConnection() throws Exception {
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, username, password);
	}


}

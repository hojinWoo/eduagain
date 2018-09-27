

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import oracle.jdbc.OracleDriver;

public class DMLExample2 {
	static String driver = "oracle.jdbc.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String username = "hr";
	static String password = "hr";

	public static void main(String[] args) {
		create("KOSTA2", 0, 0);
	}

	public static void create(String departmentName, int managerId, int locationId) {
		
		String sql = "INSERT INTO departments \r\n" + 
				"            (department_id, \r\n" + 
				"             department_name, \r\n" + 
				"             manager_id, \r\n" + 
				"             location_id) \r\n" + 
				"VALUES     (departments_seq.NEXTVAL, \r\n" + 
				"           ?, \r\n" + 
				"           ?, \r\n" + 
				"           ?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver).newInstance();

			con = DriverManager.getConnection(url, username, password);
			
			//SQL 전처리
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, departmentName);
			if(managerId != 0) {
				pstmt.setInt(2, managerId);
			}else {
				pstmt.setNull(2, Types.INTEGER); 
			}
			
			if(locationId != 0) {
				pstmt.setInt(3, locationId);
			}else {
				pstmt.setNull(3, Types.INTEGER); 
			}
			int count = pstmt.executeUpdate(); // 매개변수X
			System.out.println(count + "행이 추가되었습니다.");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

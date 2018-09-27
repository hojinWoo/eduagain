import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DMLExample2 {
	static String driver = "oracle.jdbc.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String username = "hr";
	static String password = "hr";
	static String sql = "";

	public static void create(String departmentName, int managerId, int locationId) {
		
		sql = "INSERT INTO departments \r\n" + 
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
	
	public static void create2(String departmentName, int managerId, int locationId) {
		create2(new Department(0, departmentName, managerId, locationId));
	}
	
	//DTO(VO)사용, data 전달 목적
	public static void create2(Department dept) {
		sql = "INSERT INTO departments \r\n" + 
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
			pstmt.setString(1, dept.getDepartmentName());
			if(dept.getManagerId() != 0) {
				pstmt.setInt(2, dept.getManagerId());
			}else {
				pstmt.setNull(2, Types.INTEGER); 
			}
			
			if(dept.getLocationId() != 0) {
				pstmt.setInt(3, dept.getLocationId());
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
	
	public static void delete(int departmentId) {
		sql = "DELETE departments \r\n"
			+ "WHERE department_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver).newInstance();

			con = DriverManager.getConnection(url, username, password);
			
			//SQL 전처리
			pstmt = con.prepareStatement(sql);
			
			if(departmentId != 0) {
				pstmt.setInt(1, departmentId);
			}
			int count = pstmt.executeUpdate(); // 매개변수X
			System.out.println(count + "행이 삭제되었습니다.");
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
	public static void main(String[] args) {
//		create("KOSTA2", 0, 0);
		delete(370);
	}
}

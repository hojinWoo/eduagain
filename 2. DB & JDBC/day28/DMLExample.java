import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//권장 방법 XXX!!!
public class DMLExample {
	static String driver = "oracle.jdbc.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String username = "hr";
	static String password = "hr";

	public static void main(String[] args) {
		create("KOSTA", 0, 0);
	}

	public static void create(String departmentName, int managerId, int locationId) {
		
		String mId = "NULL";
		if(managerId != 0) {
			mId = String.valueOf(managerId);
		}
		String lId = "NULL";
		if(locationId != 0) {
			lId = String.valueOf(locationId);
		}
		
		String sql = "INSERT INTO departments \r\n" + 
				"            (department_id, \r\n" + 
				"             department_name, \r\n" + 
				"             manager_id, \r\n" + 
				"             location_id) \r\n" + 
				"VALUES     (departments_seq.NEXTVAL, \r\n" + 
				"           '" + departmentName + "', \r\n" + 
				"           " + mId + ", \r\n" + 
				"           " + lId + ")";

		Connection con = null;
		Statement stmt = null;
		try {
			Class.forName(driver).newInstance();

			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);// 실행 시 default는 auto commit이 되기 때문에 변경 가능
			stmt = con.createStatement();
			
			int count = stmt.executeUpdate(sql); // 변경된 행의 개수
			System.out.println(count + "행이 추가되었습니다.");
			con.commit(); // commit을 원할 시 가능
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				// rollback 가능
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

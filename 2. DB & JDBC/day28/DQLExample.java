import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DQLExample {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		String driver = "oracle.jdbc.OracleDriver";

		Class.forName(driver).newInstance();
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "hr";
		String password = "hr";
		
		String sql = "SELECT e.employee_id     id, \r\n" + 
				"       e.last_name       name, \r\n" + 
				"       e.salary          salary, \r\n" + 
				"       TO_CHAR(e.hire_date, 'YYYY-MM-DD HH24:MI:SS')   hiredate, \r\n" + 
				"       d.department_name dname \r\n" + 
				"		FROM   employees e \r\n" + 
				"       	JOIN departments d \r\n" + 
				"         		ON e.department_id = d.department_id";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		con = DriverManager.getConnection(url, username, password);
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		int id = 0;
		String name = null;
		int salary = 0;
		String hiredate = null; //Date는 Oracle의 toChar에서 결정된다. Date로 가져오지 않는다.
		String dname = "";
		while (rs.next()) {
			id = rs.getInt("id");
			name = rs.getString("name");
			salary = rs.getInt("salary");
			hiredate = rs.getString("hiredate");
			dname = rs.getString("dname");
			System.out.println(id + " : " + name + " : " + salary+ " : " + hiredate+ " : " + dname);
		}
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (con != null)
			con.close();
	}
}

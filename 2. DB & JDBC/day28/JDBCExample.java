import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

public class JDBCExample {
	public static void main(String[] args) {
		//1. JDBC Driver loading(create Object) 정적 생성
//		Driver driver = new OracleDriver();
		
		//정적으로 생성하면 문제가 생긴다. 따라서 동적 생성이 필요*************
		//2. Class 클래스를 이용한 동적 객체 생성 (안의 내용만 변수처리)
		String driver = "oracle.jdbc.OracleDriver";
//		Class.forName(driver).newInstance(); //명시적으로 써 주는 것이 더 좋다.
		//이미 내부적으로 Oracle Driver에서 newInstance가 default로 호출이 되어 있기 떄문에  굳이 안 써도 되기는 한다. (cf. 문자열로 인한 예외 처리 필요)
		try {
			Class.forName(driver);
//			System.out.println("JDBC Dvier 생성 완료");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "hr";
		String password = "hr";
		
		String sql = "SELECT employee_id, last_name, salary FROM employees";
		//2. DBMS 연결(JDBC - Connection, Statement, ResultSet)
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, username, password); 
//			System.out.println("DBMS 연결 완료");
//			System.out.println(con);//oracle.jdbc.driver.T4CConnection
			
			//3. SQL 서버 전송 및 결과집합 수신
			stmt = con.createStatement();
//			System.out.println(stmt); //oracle.jdbc.driver.OracleStatementWrapper
			rs = stmt.executeQuery(sql);
//			System.out.println(rs); //oracle.jdbc.driver.OracleResultSetImpl
			
			//ResultSet 구성 요소 : 가장 위에 있는 것을 BOF(Before Of File) + 내용 + EOF(End)
			//next method를 통해 이동 및 boolean으로 반환
			int employee_id = 0;
			String last_name = null;
			int salary = 0;
			while(rs.next()) {
				employee_id = rs.getInt("employee_id"); //rs.getInt(1)
				last_name = rs.getString("last_name");
				salary = rs.getInt("salary");
				System.out.println(employee_id + " : " + last_name + " : " + salary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
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

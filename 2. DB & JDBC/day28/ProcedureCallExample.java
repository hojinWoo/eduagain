import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class ProcedureCallExample {
	static String driver = "oracle.jdbc.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String username = "hr";
	static String password = "hr";
	
	public static void callProcedure() {
		Connection con = null;
		CallableStatement cstmt = null;
		
		String sql = "{call getemployee(?, ?, ?, ?)}";
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
			cstmt = con.prepareCall(sql);
			
			cstmt.setInt(1, 110);
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.INTEGER);
			
			cstmt.execute();
			
			int employeeId = cstmt.getInt(2);
			String name = cstmt.getString(3);
			int salary = cstmt.getInt(4);
			
			System.out.println(employeeId+"\t"+name+"\t"+salary);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(cstmt!=null) cstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {}
		}
	}
	public static void main(String[] args) {	
		callProcedure();
	}
}

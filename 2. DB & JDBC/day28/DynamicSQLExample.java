/**
 * 동적 SQL 활용
 * @author hojin
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class DynamicSQLExample {
	static String driver = "oracle.jdbc.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String username = "hr";
	static String password = "hr";
	
	public static void executeSQL(String sql) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(sql);
			boolean exist = pstmt.execute(); //true : DQL(ResultSet이 존재), false : DML (ResultSetX)
			if(exist) {
				rs = pstmt.getResultSet();
				//어떤 구조인지 알 수 없기 때문에 rs의 스키마를 알아야 한다.
				ResultSetMetaData rsmt = rs.getMetaData();
				for (int i = 1; i <= rsmt.getColumnCount(); i++) {
					System.out.print(rsmt.getColumnName(i)+"\t");
				}
				System.out.println();
				while(rs.next()) {
					for (int i = 1; i <= rsmt.getColumnCount(); i++) {
						if(rsmt.getColumnType(i) == 2) {
							//int
							System.out.print(rs.getInt(i)+"\t");
						}else if(rsmt.getColumnType(i) == 12) {
							//String
							System.out.print(rs.getString(i)+"\t");
						}
					}
					System.out.println();
				}				
			}else {
				int count = pstmt.getUpdateCount();
				System.out.println(count+"행이 변경되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String sql = "Select * \r\n" + 
				"From departments";
		executeSQL(sql);
	}
}

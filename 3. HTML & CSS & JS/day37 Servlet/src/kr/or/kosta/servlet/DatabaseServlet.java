package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 현재는 서블릿으로 DB를 연결하지 않지만 예제 차원으로 실습
 */
public class DatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//예전 20년 전 방식.. db연동
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "hr";
	String password = "hr";

	String sql = "select employee_id, salary, last_name from employees";
	Connection con;
//	connection은 한 번만 하기 때문에 init()에서 처리
	@Override
	public void init() throws ServletException {
		//DB연동
		try {
			Class.forName(driver);	//newInstance()는 생략 가능
			con = DriverManager.getConnection(url, username, password);
//			System.out.println(con);
		} catch (Exception e) {
			//예외처리 필요
			e.printStackTrace();
		}

		//이후에 lifecycle 개념이 생기고 현재는 다 사용X
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//db연동
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title></title>");
		out.println("</head>");
		out.println("<body style = 'font-size:20pt'>");

		out.println("<table border = '1' width='50%'>");
		try {
			int id = 0;
			int salary = 0;
			String last_name = null;
			while(rs.next()) {
				id = rs.getInt("employee_id");
				salary = rs.getInt("salary");
				last_name = rs.getString("last_name");
				out.println("<tr>");
				out.println("<td>" + id + "</td><td>" + salary + "</td><td>" + last_name + "</td>");
				out.println("</tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</table>");

		out.println("</body>");
		out.println("</html>");

	}
	@Override
	public void destroy() {
		if(con!= null) {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

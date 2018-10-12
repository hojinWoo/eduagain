package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp2.BasicDataSource;

import kr.or.kosta.dao.JdbcUserDao;
import kr.or.kosta.dao.User;

/**
 * package dao 연동, connection pooling
 */
public class DatabaseServlet2 extends HttpServlet {

	JdbcUserDao dao;
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER_ID = "hr";
	private static final String USER_PW = "hr";

	@Override
	public void init() throws ServletException {
		//dao를 자동으로 생성해주는 Factroy를 만들어주면 좋다. (여기는 factory 적용 전)
		dao = new JdbcUserDao();
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DRIVER_NAME);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(USER_ID);
		dataSource.setPassword(USER_PW);
		dataSource.setInitialSize(5);
		dataSource.setMaxTotal(10);
		dataSource.setMaxIdle(7);

		dao.setDatasource(dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> list = null;
		try {
			System.out.println("전체 test");
			 list = dao.listAll();
		}catch (Exception e) {
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

		for (User user : list) {
			System.out.println(user);
		}

		out.println("</body>");
		out.println("</html>");
	}
}

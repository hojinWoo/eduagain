package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet3 버전부터는 annotatation을 지원.
 * 환경을 이제 annotation으로 잡는다.
 */
@WebServlet(value = "/hello.do")	//tag처럼 생각하면 된다(value는 생략 가능), 그러나 서블릿이 예전거라서 잘 사용 안하고 web.xml에 등록한다
public class HelloServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 웹 클라이언트로 동적 출력하고자 하는 데이터 생성 (factory method pattern)
		Calendar now = Calendar.getInstance();
		String nowStr = String.format("%1$tF %1$tT", now);

		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title></title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>오늘은" + nowStr + "입니다...22</h2>");
		out.println("</body>");
		out.println("</html>");
	}

}

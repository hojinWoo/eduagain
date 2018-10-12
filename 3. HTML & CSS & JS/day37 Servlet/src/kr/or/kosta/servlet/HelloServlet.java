package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet2
 */
public class HelloServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 웹 클라이언트로 동적 출력하고자 하는 데이터 생성 (factory method pattern)
		Calendar now = Calendar.getInstance();
		String nowStr = String.format("%1$tF %1$tT", now);

		// 이벤트 등록처럼 서버에 등록이 필요 > web.xml

		// 응답 메시지의 헤더에 컨텐츠 유형 설정
		response.setContentType("text/html; charset=utf-8");

		// 브라우저에 전달 (스트리밍은 resp에 자동적으로 다 만들어져 있기 때문에 component만 만들면 된다)
		PrintWriter out = response.getWriter();
		out.println("<html>"); // browser가 해석하기 좋게 HTML으로 동적으로 보내는 것이 더 좋다. 하지만 길다..
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title></title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>오늘은" + nowStr + "입니다...</h2>"); // 동적 data 출력
		//ServletContextServlet.java
//	out.println("<h2>오늘은" + getServletContext().getAttribute("message") + "입니다...</h2>");

		//HttpSessionServlet.java
		out.println("<h2>" + req.getSession().getAttribute("userName") + "입니다...</h2>");
		out.println("</body>");
		out.println("</html>");

	}

}

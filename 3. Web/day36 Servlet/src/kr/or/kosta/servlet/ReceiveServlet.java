package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet2
 */
public class ReceiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	service방식으로 만들어서 하면 둘 다 가능하지만 구조가 망가지기 때문에 service method를 쓰는 것이 아니라
//	method를 따로 만들어서 get, post를 둘 다 지원하도록 설정

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();

		//post방식의 인코딩 지원
		request.setCharacterEncoding("utf-8");

		//요청 파라미터 수신
		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");

		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");

		String teams = request.getParameter("teams");

		String gender = request.getParameter("gender");

		String[] hobbys = request.getParameterValues("hobby");

		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String name = (String) paramNames.nextElement();
			String value = request.getParameter(name);
			System.out.println(name + " : "+ value);
		}
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title></title>");
		out.println("</head>");
		out.println("<body style = 'font-size:20pt'>");
		out.println("<h2>user : " + id + "</h2>");
		out.println("<h2>pw : "   + pw + "</h2>");
		out.println("<h3>선택된 팀 : " + teams + "</h3>");
		out.println("<h3>성별 : " + gender + "</h3>");
		out.println("<h3>이름 : " + firstName + "</h3>");
		out.println("<h3>성 : " + lastName + "</h3>");
		if(hobbys != null) {
			for (String hobby : hobbys) {
				out.println("<h3>취미 : " + hobby + "</h3>");
			}
		}

		out.println("</body>");
		out.println("</html>");
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();

		//요청 파라미터 수신
		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");

		String teams = request.getParameter("teams");

		String gender = request.getParameter("gender");

		String[] hobbys = request.getParameterValues("hobby");

		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String name = (String) paramNames.nextElement();
			String value = request.getParameter(name);
			System.out.println(name + " : "+ value);
		}


		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title></title>");
		out.println("</head>");
		out.println("<body style = 'font-size:20pt'>");
		out.println("<h2>user : " + id + "</h2>");
		out.println("<h2>pw : "   + pw + "</h2>");
		out.println("<h3>선택된 팀 : " + teams + "</h3>");
		out.println("<h3>성별 : " + gender + "</h3>");
		if(hobbys != null) {
			for (String hobby : hobbys) {
				out.println("<h3>취미 : " + hobby + "</h3>");
			}
		}

		out.println("</body>");
		out.println("</html>");*/
		process(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
}

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
public class HTTPServletResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();
//		response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //400 error 강제로 내기

		String name = request.getParameter("name");

		if(name != null && name.trim().length() != 0) {
			if(name.equals("hh")) {
				//blacklist
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);	//403
			}

		}

		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title></title>");
		out.println("</head>");
		out.println("<body style = 'font-size:20pt'>");
		out.println("<ul>");
		out.println("name : " + name);
		out.println("</ul>");
		out.println("</body>");
		out.println("</html>");

		//Dispatch : 브라우저가 자동으로 다른 URL 요청***********
//		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);	//301
//		response.setHeader("Location", "/servlet/hello");

//위에 두줄을 한 줄로 처리 가능
//		response.sendRedirect("/servlet/hello");

	}

}

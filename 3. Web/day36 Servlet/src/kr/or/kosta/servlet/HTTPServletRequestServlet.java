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
public class HTTPServletRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();
		
		String clientIp = request.getRemoteAddr();
		String method   = request.getMethod();
		String uri		= request.getRequestURI();
		String protocol = request.getProtocol();
		String query 	= request.getQueryString();
		String userName 	= request.getParameter("name");	//get, post방식으로 온 것을 받을 때
		
		//기존 등록된 이름
		String applicationName = request.getContextPath();
		String servletName = request.getServletPath();
		
		Enumeration<String> headerNames = request.getHeaderNames();
		
		out.println("<html>"); 
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title></title>");
		out.println("</head>");
		out.println("<body style = 'font-size:20pt'>");
		out.println("<ul>"); 
		out.println("<li> IP : " + clientIp + "</li>"); 
		out.println("<li> method : " + method + "</li>"); 
		out.println("<li> uri : " + uri + "</li>"); 
		out.println("<li> protocol : " + protocol + "</li>"); 
		out.println("<li> query : " + query + "</li>"); 
		
		while (headerNames.hasMoreElements()) {
			String name = (String) headerNames.nextElement();
			String value = request.getHeader(name);
			out.println("<li>"+ name + " : " + value + "</li>"); 
		}
		
		out.println("<li> name : " + userName + "</li>"); 
		out.println("<li> applicationName : " + applicationName + "</li>"); 
		out.println("<li> servletName : " + servletName + "</li>"); 
		
		
		out.println("</ul>"); 
		out.println("</body>");
		out.println("</html>");

	}

}

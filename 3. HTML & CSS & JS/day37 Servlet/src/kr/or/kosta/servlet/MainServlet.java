package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String id  = null;
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				System.out.println(cookie.getName() + " : " + cookie.getValue());
				if(cookie.getName().equals("loginId")) {
					id = cookie.getValue();
				}
			}
		}

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n");
		out.println("<html>\r\n");
		out.println("<head>\r\n");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/basic.css\">\r\n");
		out.println("</head>\r\n");
		out.println("<body>\r\n");
		out.println("\r\n");
		out.println("<div class=\"header\">\r\n");
		out.println("  <h1>My Website</h1>\r\n");
		out.println("  <p>Resize the browser window to see the effect.</p>\r\n");
		out.println("</div>\r\n");
		out.println("\r\n");
		out.println("<div class=\"topnav\">\r\n");
		out.println("  <a href=\"#\">Link</a>\r\n");
		out.println("  <a href=\"#\">Link</a>\r\n");
		out.println("  <a href=\"#\">Link</a>\r\n");
		out.println("  <a href=\"#\" style=\"float:right\">Link</a>\r\n");
		out.println("</div>\r\n");
		out.println("\r\n");
		out.println("<div class=\"row\">\r\n");
		out.println("  <div class=\"leftcolumn\">\r\n");
		out.println("    <div class=\"card\">\r\n");
		out.println("      <h2>TITLE HEADING</h2>\r\n");
		out.println("      <h5>Title description, Dec 7, 2017</h5>\r\n");
		out.println("      <div class=\"fakeimg\" style=\"height:200px;\">Image</div>\r\n");
		out.println("      <p>Some text..</p>\r\n");
		out.println(
				"      <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>\r\n");
		out.println("    </div>\r\n");
		out.println("    <div class=\"card\">\r\n");
		out.println("      <h2>TITLE HEADING</h2>\r\n");
		out.println("      <h5>Title description, Sep 2, 2017</h5> \r\n");
		out.println("      <div class=\"fakeimg\" style=\"height:200px;\">Image</div>\r\n");
		out.println("      <p>Some text..</p>\r\n");
		out.println(
				"      <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>\r\n");
		out.println("    </div>\r\n");
		out.println("  </div>\r\n");
		out.println("  \r\n");
		out.println("  <div class=\"rightcolumn\">\r\n");
		out.println("    <div class=\"card\">\r\n");
		out.println("      <div>\r\n");
		if(id == null || id.equals("")) {
			out.println("        <form action=\"/servlet/login.do\" method = \"post\">");
			out.println("			<input type=\"text\" id=\"userid\" name=\"userid\" placeholder=\"Identifier...\">");
			out.println("			<input type=\"password\" id=\"userpw\" name=\"userpw\" placeholder=\"Password...\">");
			out.println("			<input type=\"submit\" value=\"Login\">");
		}else {
			out.println(			id + "님이 로그인 중입니다.");
			out.println(" 			<button onclick = \"location.href = 'login.do';\">Logout</button>");
		}

		out.println("        </form>\r\n");
		out.println("      </div>\r\n");
		out.println("      \r\n");
		out.println("    </div>\r\n");
		out.println("    \r\n");
		out.println("    <div class=\"card\">\r\n");
		out.println("      <h3>Popular Post</h3>\r\n");
		out.println("      <div class=\"fakeimg\"><p>Image</p></div>\r\n");
		out.println("      <div class=\"fakeimg\"><p>Image</p></div>\r\n");
		out.println("      <div class=\"fakeimg\"><p>Image</p></div>\r\n");
		out.println("    </div>\r\n");
		out.println("    <div class=\"card\">\r\n");
		out.println("      <h3>Follow Me</h3>\r\n");
		out.println("      <p>Some text..</p>\r\n");
		out.println("    </div>\r\n");
		out.println("  </div>\r\n");
		out.println("</div>\r\n");
		out.println("\r\n");
		out.println("<div class=\"footer\">\r\n");
		out.println("  <h2>Footer</h2>\r\n");
		out.println("</div>\r\n");
		out.println("\r\n");
		out.println("</body>\r\n");
		out.println("</html>\r\n");

	}
}

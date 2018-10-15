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
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// logout
		response.setContentType("text/html");
		System.out.println("logout1");
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("loginId")) {
				System.out.println("logout2");
				cookie.setMaxAge(0);	//expire, Delete cookie
				cookie.setPath("/");
				response.addCookie(cookie);
				break;
			}
		}
		response.sendRedirect("index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//login
		String id = request.getParameter("userid");
		String pwd = request.getParameter("userpw");
		Cookie cookie = new Cookie("loginId", id);

		//UserDao를 이용한 회원 가입여부 체크 필요 (여기서는 생략)
		cookie.setPath("/");
		response.addCookie(cookie);

		response.sendRedirect("index.html");

		//Servlet 간 data 주고 받는 방식
//		1. Servlet Context(Map), 톰캣 구동 시 한 개 생성, method :  setAttr, getAttr, removeAttr (전역 개념이기 때문에 모든 서블릿들이 다 공유 - 동기화 문제로 잘 사용X)
//		2. Session(Map),	요청한 브라우저의 수 만큼 생성 - HttpSession (ex. 쇼핑몰 장바구니)
//		3. request(Map), 모든 브라우저들마다 지역변수로 request, response 객체 생성되고  method :  setAttr, getAttr, removeAttr 존재. 보내고 받고 나면 life cycle이 죽는다
//							Dispatcher >> forwarding 시에만 죽지 않고 살아서 공유된다.
	}
}

package kr.or.kosta.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//브라우저 요청 들어올 시
//클라이언트 상태 정보를 유지
public class HttpSessionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();

		String name = "agdcre";

		//기존에 있다면 만들어진 것이 return
		HttpSession session =  request.getSession();

		System.out.println("session" + session.isNew());

		session.setAttribute("userName", name);

		response.sendRedirect("hello.do");

	}
}

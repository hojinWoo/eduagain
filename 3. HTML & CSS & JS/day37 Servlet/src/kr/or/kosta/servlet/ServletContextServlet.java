package kr.or.kosta.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet이 시작될 때 가장 먼저 실행되는 최상위 Servlet
//서블릿 컨테이너 환경 정보를 제공하며, 컨테이너에 의해 관리되는 서블릿들의 데이터 공유를 위해 제공된다.
public class ServletContextServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();

		//상황 가정
		String message = "서블릿 간의 데이터 공유입니다.";

		System.out.println(context.getServerInfo());	//Apache Tomcat/8.0.53
		System.out.println(context.getContextPath());	//servlet
														//web application 이름. 꼭 기억하기!

		context.setAttribute("message", message);
//		response.sendRedirect("hello.do");

		//web.xml에서 미리 등록한 경우 값 받아오기
		String location = context.getInitParameter("Location");
		System.out.println(location);

	}
}

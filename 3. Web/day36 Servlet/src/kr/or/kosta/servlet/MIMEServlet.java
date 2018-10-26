package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * MIME(Multipurpose Internet Mail Extension)
 * Servlet implementation class MIMEServlet
 */
public class MIMEServlet extends HttpServlet {
//	class의 속성이나 method가 변경 후 새로 저장 시 충돌나지 않게 하기 버전 구분을 위해 사용(직렬화 마다 버전 확인)
	private static final long serialVersionUID = 1L;

//	항상 service, init method는 default
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=utf-8"); //클라이언트와 서버가 주고받는 것의 데이터 종류를 (ex. text)mime type이라고 한다. ex. html, plain : sub type
		// Content-Type:text/plain; charset=utf-8
		PrintWriter out = response.getWriter();
		out.println("일반적인 텍스트");
	
	}

}

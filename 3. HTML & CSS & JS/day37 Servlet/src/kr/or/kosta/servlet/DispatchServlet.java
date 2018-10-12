package kr.or.kosta.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatchServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//디스패치 기술
		RequestDispatcher rd =  request.getRequestDispatcher("/hello.do");	//자신의 container안에 있는 것만, 외부는 안 된다.

		//포워드 > URL 노출이 되지 않는다. (Model1 >> Model2 개발방법)
//		rd.forward(request, response);

		//포워드에 반대방법, 해당 실행 결과를 여기로 가져오기 (직접 만든 것이 아니기 때문에 header 설정 필요)

		rd.include(request, response);
	}
}

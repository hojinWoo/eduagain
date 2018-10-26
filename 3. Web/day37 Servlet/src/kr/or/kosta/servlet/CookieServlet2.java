package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//쿠키는 text data만 가능 , Object 저장 X
//한글 자체 저장을 못하기 때문에 encoding 필수
public class CookieServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count = 0;

		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for (Cookie cookie2 : cookies) {
				if(cookie2.getName().equals("count")) {
					count = Integer.parseInt(cookie2.getValue());
					System.out.println(count);
					break;
				}
			}
		}
		count++;
		Cookie cookie = new Cookie("count", String.valueOf(count));;
		//초단위, 쿠키가 삭제되는 날짜
		cookie.setMaxAge(60*60*24*30);	//한 달간 지정, 값이 없는 경우 브라우저가 실행되는 경우에만
		//쿠키가 유효하게 사용될 수 있는 URL 도메인, 생략 시 쿠키를 설정한 도메인
		//countC.setDomain("/");

		//쿠키가 유효하게 사용될 수 있는 URL path, 생략 시 쿠키를 설정한 문서의 path
		//cookie.setPath("/");	//모든 위치에서 다 사용

		//쿠키는 응답 헤더에 넣는다 > 브라우저에 저장
		response.addCookie(cookie);

		response.setContentType("text/html; charset=utf-8");


		//브라우저에 전달 (스트리밍은 resp에 자동적으로 다 만들어져 있기 때문에 component만 만들면 된다)
		PrintWriter out = response.getWriter();
		out.println("<html>"); //browser가 해석하기 좋게 HTML으로 동적으로 보내는 것이 더 좋다. 하지만 길다..
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title></title>");
		out.println("</head>");
		out.println("<body>");

		out.println("<h2>방문횟수 : " + count + "번</h2>");
		out.println("<h3>" + "</h3>");
		out.println("</body>");
		out.println("</html>");

	}
}

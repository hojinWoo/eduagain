package kr.or.kosta.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//쿠키는 text data만 가능 , Object 저장 X
//한글 자체 저장을 못하기 때문에 encoding 필수
public class CookieServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Session은 server쪽에 부하가 많이 걸리기 때문에 잘 사용X
		String id = "lion";

		//한글 인코딩
		id = URLEncoder.encode(id, "utf-8");

		//쿠키관련 세팅
		Cookie cookie = new Cookie("cucu", id);

		//초단위, 쿠키가 삭제되는 날짜
		cookie.setMaxAge(60*60*24*30);	//한 달간 지정, 값이 없는 경우 브라우저가 실행되는 경우에만
		//쿠키가 유효하게 사용될 수 있는 URL 도메인, 생략 시 쿠키를 설정한 도메인
		//countC.setDomain("/");

		//쿠키가 유효하게 사용될 수 있는 URL path, 생략 시 쿠키를 설정한 문서의 path
		cookie.setPath("/");	//모든 위치에서 다 사용

		//쿠키는 응답 헤더에 넣는다 > 브라우저에 저장
		response.addCookie(cookie);

		response.sendRedirect("hello.do");
	}
}

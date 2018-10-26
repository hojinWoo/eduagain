<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.blog.user.dao.User"%>
<%@ page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@ page import="kr.or.kosta.blog.user.domain.UserJdbcDaoFactory"%>
<%@ page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@ page import="javax.servlet.http.Cookie" %>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String pw = request.getParameter("pw");
String saveId = request.getParameter("saveId");
DaoFactory factory = (DaoFactory)application.getAttribute("factory");
UserDao dao = factory.getUserDao();

User user = dao.certify(id, pw);
Cookie[] cookies = request.getCookies();
if(user == null){
	if(request.getHeader("referer").equals("http://localhost/")){
		response.sendRedirect("../index.jsp?login=false");
	}else{
		response.sendRedirect(request.getHeader("referer")+"?login=false");	
	}
}else{
	Cookie cookie = new Cookie("id",id);
	cookie.setPath("/");
	response.addCookie(cookie);
	if(saveId != null){
		//로그인 저장 쿠키 생성
		System.out.print(saveId);
		Cookie cookie2 = new Cookie("saveId",id);
		cookie2.setPath("/");
		response.addCookie(cookie2);
	}else{
		//로그인 저장 쿠키 삭제
		for(Cookie cookie2 : cookies){
			if(cookie2.getName().equals("saveId")){
				cookie2.setPath("/");
				cookie2.setMaxAge(0);
				response.addCookie(cookie2);
			}
		}
	}
	if(request.getRequestURI().equals("user/login.jsp")){
		response.sendRedirect("../index.jsp");
	}else{
		response.sendRedirect(request.getHeader("referer"));
	}
}
%>

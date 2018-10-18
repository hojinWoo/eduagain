<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.blog.user.dao.User"%>
<%@ page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@ page import="kr.or.kosta.blog.user.domain.UserJdbcDaoFactory"%>
<%@ page import="kr.or.kosta.blog.DaoFactory"%>
<%@ page import="javax.servlet.http.Cookie" %>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String pw = request.getParameter("pw");
DaoFactory factory = new UserJdbcDaoFactory();
UserDao dao = factory.getUserDao();
User user = dao.certify(id, pw);
if(user != null){
	Cookie cookie = new Cookie("id",id);
	cookie.setPath("/");
	response.addCookie(cookie);
}
//response.sendRedirect("../index.jsp");
response.sendRedirect(request.getHeader("referer"));
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@ page import="kr.or.kosta.blog.user.domain.UserJdbcDaoFactory"%>
<%@ page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="user" class="kr.or.kosta.blog.user.dao.User"/>
<jsp:setProperty property="*" name="user"/>
<%
DaoFactory factory = (DaoFactory)application.getAttribute("factory");
UserDao dao = factory.getUserDao();
if(dao.create(user)){
	response.sendRedirect("signupResult.jsp");
}else{
	response.sendRedirect(request.getHeader("referer")+"?email=false");	
}
%>
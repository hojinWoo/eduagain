<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@ page import="kr.or.kosta.blog.user.domain.UserJdbcDaoFactory"%>
<%@ page import="kr.or.kosta.blog.DaoFactory"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="user" class="kr.or.kosta.blog.user.dao.User" scope="request"/>
<jsp:setProperty property="*" name="user"/>
<%
DaoFactory factory = new UserJdbcDaoFactory();
UserDao dao = factory.getUserDao();
dao.create(user);
response.sendRedirect("signupResult.jsp");
%>
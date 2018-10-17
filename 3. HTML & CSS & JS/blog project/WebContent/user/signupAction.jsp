<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.pattern.UserDao"%>
<%@ page import="kr.or.kosta.pattern.JdbcDaoFactory"%>
<%@ page import="kr.or.kosta.pattern.DaoFactory"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="user" class="kr.or.kosta.pattern.User" scope="request"/>
<jsp:setProperty property="*" name="user"/>
<%
DaoFactory factory = new JdbcDaoFactory();
UserDao dao = factory.getUserDao();
dao.create(user);
%>
<!-- 가입결과 화면 jsp로 디스패치 -->
<jsp:forward page="signupResult.jsp"/>
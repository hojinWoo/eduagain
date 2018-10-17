<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.pattern.UserDao"%>
<%@ page import="kr.or.kosta.pattern.JdbcDaoFactory"%>
<%@ page import="kr.or.kosta.pattern.DaoFactory"%>
<% request.setCharacterEncoding("utf-8"); %>
<%
String readId = request.getParameter("bid");
DaoFactory factory = new JdbcDaoFactory();
UserDao dao = factory.getUserDao();
if(dao.read(readId) == null){
	request.setAttribute("cid", readId);
	System.out.println("아이디 사용 가능");
}else{
	request.setAttribute("cid", "!!");
	System.out.println("아이디 이미 존재");
}
%>
<jsp:forward page="signup.jsp"/>

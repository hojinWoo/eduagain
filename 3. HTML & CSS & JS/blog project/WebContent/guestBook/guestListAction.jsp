<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.blog.guest.dao.Guest"%>
<%@ page import="java.util.List"%>
<%@ page import="kr.or.kosta.blog.guest.dao.GuestDao"%>
<%@ page import="kr.or.kosta.blog.guest.domain.GuestJdbcDaoFactory"%>
<%@ page import="kr.or.kosta.blog.DaoFactory"%>
<%@ include file="../jsp/cookie.jsp" %>
<%
String cookieId = "";
if(loginId != null){
	cookieId = loginId;
}
DaoFactory factory = new GuestJdbcDaoFactory();
GuestDao dao = factory.getGuestDao();
System.out.println("ccid : "+cookieId);
List<Guest> guests = dao.listAll(cookieId);
%>
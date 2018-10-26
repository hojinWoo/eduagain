<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.blog.guest.dao.Guest"%>
<%@ page import="java.util.List"%>
<%@ page import="kr.or.kosta.blog.guest.dao.GuestDao"%>
<%@ page import="kr.or.kosta.blog.guest.domain.GuestJdbcDaoFactory"%>
<%@ page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@ include file="../jsp/loginId.jsp" %>
<%
String cookieId = "";
if(loginId != null){
	cookieId = loginId;
}
DaoFactory factory = (DaoFactory)application.getAttribute("factory");
GuestDao dao = factory.getGuestDao();
List<Guest> guests = dao.listAll(cookieId);
%>
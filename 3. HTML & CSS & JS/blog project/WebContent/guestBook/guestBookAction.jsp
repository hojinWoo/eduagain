<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.blog.guest.dao.GuestDao"%>
<%@ page import="kr.or.kosta.blog.guest.domain.GuestJdbcDaoFactory"%>
<%@ page import="kr.or.kosta.blog.DaoFactory"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="guest" class="kr.or.kosta.blog.guest.dao.Guest" scope="request"/>
<jsp:setProperty property="*" name="guest"/>
<%
DaoFactory factory = new GuestJdbcDaoFactory();
GuestDao dao = factory.getGuestDao();
System.out.println(dao);
System.out.println(guest);
dao.create(guest);
response.sendRedirect("../guestBook/guestBook.jsp");
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.blog.guest.dao.GuestDao"%>
<%@ page import="kr.or.kosta.blog.guest.domain.GuestJdbcDaoFactory"%>
<%@ page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="guest" class="kr.or.kosta.blog.guest.dao.Guest"/>
<jsp:setProperty property="*" name="guest"/>
<%
DaoFactory factory = (DaoFactory)application.getAttribute("factory");
GuestDao dao = factory.getGuestDao();
System.out.println(dao);
System.out.println(guest);
dao.create(guest);
response.sendRedirect("../guestBook/guestBook.jsp");
%>
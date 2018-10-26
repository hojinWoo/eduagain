<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%!
public void jspInit() {
	DaoFactory daoFactory = new JdbcDaoFactory();
   getServletContext().setAttribute("factory", daoFactory);
}
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.blog.article.dao.Article"%>
<%@ page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@ page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="article" class="kr.or.kosta.blog.article.dao.Article"/>
<%
	DaoFactory factory = (DaoFactory)application.getAttribute("factory");
	ArticleDao dao = factory.getArticleDao();
	dao.delete(request.getParameter("article_id"));
	response.sendRedirect("../board/board.jsp");
%>
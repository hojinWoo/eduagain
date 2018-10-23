<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@ page import="kr.or.kosta.blog.factory.DaoFactory"%>

<%
	request.setCharacterEncoding("utf-8");
//	System.out.println(request.getParameter("subject"));
//	System.out.println(request.getParameter("writer"));
//	System.out.println(request.getParameter("passwd"));
//	System.out.println(request.getParameter("content"));
%>
<jsp:useBean id="article" class="kr.or.kosta.blog.article.dao.Article"/>
<jsp:setProperty property="*" name="article"/>
<%
	DaoFactory factory = (DaoFactory)application.getAttribute("factory");
	ArticleDao dao = factory.getArticleDao();
	if(dao.create(article)){
		response.sendRedirect("../board/board.jsp");
	}else{
		System.out.println("error");
		response.sendRedirect("../index.jsp");
	}
%>
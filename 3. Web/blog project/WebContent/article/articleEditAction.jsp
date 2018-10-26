<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.blog.article.dao.Article"%>
<%@ page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@ page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="article" class="kr.or.kosta.blog.article.dao.Article"/>
<jsp:setProperty property="*" name="article"/>
<%
	DaoFactory factory = (DaoFactory)application.getAttribute("factory");
	ArticleDao dao = factory.getArticleDao();
	//System.out.println(request.getParameter("subject"));
	//System.out.println(request.getParameter("content"));
	
	if(dao.update(article)){
		response.sendRedirect("../board/board.jsp");
	}else{
		System.out.println("error");
		response.sendRedirect("../index.jsp");
	}
%>
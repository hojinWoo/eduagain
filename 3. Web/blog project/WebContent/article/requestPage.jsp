<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.article.dao.Article"%>
<%@page import="kr.or.kosta.blog.common.PageBuilder"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@page import="kr.or.kosta.blog.common.Params"%>
<% 
request.setCharacterEncoding("utf-8");

int listSize = 10; int pageSize = 10;

//선택페이지 수신
String requestPage = request.getParameter("page");
if(requestPage == null || requestPage.equals("")){
requestPage = "1";
}

//검색 요청일 경우 파라메터 수신
String searchType = request.getParameter("searchType");
String searchValue = request.getParameter("searchValue");
if(searchType == null || searchType.equals("") ||searchValue == null ){
	searchType = null;
	searchValue = null;
}

//요청파라메터 포장
Params params = new Params(Integer.parseInt(requestPage), listSize, pageSize, searchType, searchValue);
System.out.println(params);
DaoFactory factory = (DaoFactory)application.getAttribute("factory");
ArticleDao dao = factory.getArticleDao();
List<Article> list = dao.listByPage(params);

//페이징 처리에 필요한 검색 개수 DB조회
int rowCount = dao.countBySearch(params);

//PageBuilder를 이용하여 페이징 계산
PageBuilder pageBuilder = new PageBuilder(params, rowCount);
pageBuilder.build();
%>
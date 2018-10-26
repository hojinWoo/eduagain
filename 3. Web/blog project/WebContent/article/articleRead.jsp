<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@ include file="../jsp/loginId.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="article" class="kr.or.kosta.blog.article.dao.Article"/>
<%
	DaoFactory factory = (DaoFactory)application.getAttribute("factory");
	ArticleDao dao = factory.getArticleDao();
	String articleId = request.getParameter("article_id");
	dao.countUp(articleId);
	article = dao.read(articleId);
	boolean isDelete = dao.isdelete(articleId);
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>게시글 보기</title>
    <!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../css/main.css" rel="stylesheet">
  </head>
  <body>
    <!-- navigation -->
	<jsp:include page="../include/navigator.jsp"/>
	<!-- /navigation -->
    <!-- Page Content -->
    <div class="container">
      <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-md-8">

          <h1 class="my-4">
          <strong>게시글 보기</strong></h1>
          <%if(!isDelete && loginId != null){%>
          <div class="row justify-content-end">
          	<input type="button" class="btn btn-primary active" onclick="location.href='../article/reArticleNew.jsp?article_id=<%=article.getArticle_id()%>'" value = "답변 게시글 쓰기">
          </div>
          <%}%>
          <br>
			<div class="card text-center" style="border: none;">
			<div class="card-body">
				<form action="../article/articleEdit.jsp" method="post">
				<table class="table form-control table-borderless">
				<colgroup>
					<col width='15%' />
					<col width='35%' />
					<col width='15%' />
					<col width='35%' />
				</colgroup>
				<tbody>
				<tr>
					<th>글 제목</th>
					<td colspan="3"><input class="form-control" readonly="readonly" name="subject" type="text" value="<%=article.getSubject()%>"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name = "writer" class="form-control" readonly="readonly" value="<%=article.getWriter() %>"></td>
					<th>작성일</th>
					<td><input type="text" name = "regdate" class="form-control" readonly="readonly" value="<%=article.getRegdate() %>"></td>
				</tr>
				
				<tr>
					<th>아이피</th>
					<td><input type="text" name = "ip" class="form-control" readonly="readonly" value="<%=article.getIp()%>.xxx.xxx">
					</td>
					<th>조회수</th>
					<td><input type="text" name = "hitcount" class="form-control" readonly="readonly" value="<%=article.getHitcount() %>">
					</td>
				</tr>
				</tbody>
				</table>
				<textarea class="form-control" rows="10" name="content" disabled="disabled"><%=article.getContent() %></textarea>	
				<br>
				<div class="row justify-content-center">
				<%if(loginId!= null && loginId.equals(article.getWriter())){%>
					<input type="hidden" name="article_id" value = <%=article.getArticle_id() %>>
					<%if(!isDelete){ %>
					<div class="col-md-3">
					<input type="submit" class="btn btn-primary form-control" value = "게시글 수정">
					</div>
					<%} %>
				<%} %>
					<div class="col-md-3">
					<input type="button" class="btn btn-primary form-control" value = "목록으로 돌아가기" onclick="history.back()">
					</div>
				</div>								
				</form>
				</div>
			</div>
        </div>
        <!-- Sidebar Widgets Column -->
		<jsp:include page="../include/sidebar.jsp"/>
        <!-- Sidebar Widgets Column -->
      </div>
      <!-- /.row -->
    </div>
    <!-- /.container -->
    <!-- Footer -->
	<jsp:include page="../include/footer.jsp"/>
    <!-- Bootstrap core JavaScript -->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  </body>
</html>

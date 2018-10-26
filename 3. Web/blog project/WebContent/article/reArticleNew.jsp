<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../jsp/loginId.jsp" %>
<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%
	request.setCharacterEncoding("utf-8");
	if(request.getParameter("article_id") == null){
		response.sendRedirect("../index.jsp");
	}
%>
<jsp:useBean id="article" class="kr.or.kosta.blog.article.dao.Article"/>
<%
	DaoFactory factory = (DaoFactory)application.getAttribute("factory");
	ArticleDao dao = factory.getArticleDao();
	article = dao.read(request.getParameter("article_id"));
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>답변 게시글</title>
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
          <strong>답변 게시글 쓰기</strong></h1>
			<div class="card text-center" style="border: none;">
			<div class="card-body">
				<form action="../article/reArticleNewAction.jsp" method="post">
				<input type="hidden" name="group_no" value = <%=article.getGroup_no()%>>
				<input type="hidden" name="level_no" value = <%=article.getLevel_no()%>>
				<input type="hidden" name="order_no" value = <%=article.getOrder_no()%>>
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
					<td colspan="3"><input class="form-control" name="subject" maxlength="100" type="text" value="[RE] <%=article.getSubject() %>" required></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name = "writer" class="form-control" readonly="readonly" value="<%=loginId %>">
					</td>
					<th>비밀번호</th>
					<td><input type="password" name = "passwd" maxlength="8" class="form-control" placeholder="비밀번호를 작성해주세요" required>
					</td>
				</tr>
				</tbody>
				</table>
				<textarea class="form-control" rows="10" maxlength="4000" name="content"></textarea>	
				<br>
				<div class="row justify-content-center">
					<div class="col-md-3">
					<input type="submit" class="btn btn-primary form-control" value = "게시글 등록">
					</div>
					<div class="col-md-3">
					<input type="button" class="btn btn-primary form-control" value = "취소" onclick="location.href='../board/board.jsp'">
					</div>
				</div>								
				</form>
				</div>
			</div>
        </div>
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

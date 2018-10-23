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
<!-- 비밀번호 확인 필요 -->
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>게시글 수정</title>
    <!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../css/main.css" rel="stylesheet">
    <script type="text/javascript">
    
    function isCheckPw(){
    	<%if(article.getPasswd()!= null){%>
	    	var pw = <%= article.getPasswd() %>
	    	var cpw = document.getElementById('passwd').value;
	    	if(pw != cpw){
	    		document.getElementById('wrongPw').innerHTML = '비밀번호가 틀렸습니다.';
	    		return false;
	    	}
    	<%}%>
    }
    
    function removeArticle(){
    	<%if(article.getPasswd()!= null){%>
    	var pw = <%= article.getPasswd() %>
    	var cpw = document.getElementById('passwd').value;
    	if(pw != cpw){
    		document.getElementById('wrongPw').innerHTML = '비밀번호가 틀렸습니다.';
    	}else{
    		window.location.href='../article/deleteArticleAction.jsp?article_id=<%=article.getArticle_id()%>';
    	}
	<%}%>
    }

    </script>
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
          <strong>게시글 수정</strong></h1>
			<div class="card text-center" style="border: none;">
			<div class="card-body">
				<form action="../article/articleEditAction.jsp" onsubmit="return isCheckPw();" method="post">
				<input type="hidden" name="article_id" value = <%=article.getArticle_id()%>>
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
					<td colspan="3"><input class="form-control" name="subject" maxlength="100" type="text" value="<%=article.getSubject() %>" required></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name = "writer" class="form-control"  readonly="readonly" value="<%=article.getWriter() %>">
					</td>
					<th>비밀번호</th>
					<td><input type="password" id= "passwd" name = "passwd" maxlength="8" class="form-control" placeholder="비밀번호를 작성해주세요" required>
					<p id="wrongPw" style="color: red"></p>
					</td>
				</tr>
				</tbody>
				</table>
				<textarea class="form-control" rows="10" maxlength="4000" name="content"><%=article.getContent() %></textarea>	
				<br>
				<div class="row justify-content-center">
					<div class="col-md-3">
					<input type="submit" class="btn btn-primary form-control" value = "게시글 수정">
					</div>
					<div class="col-md-3">
          				<input type="button" class="btn btn-primary active" onclick="removeArticle();" value = "게시글 삭제">
          				
          			</div>
					<div class="col-md-3">
					<input type="button" class="btn btn-primary form-control" value = "취소" onclick="location.href='../article/articleRead.jsp?<%=article.getArticle_id()%>'">
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

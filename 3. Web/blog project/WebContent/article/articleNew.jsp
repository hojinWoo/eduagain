<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../jsp/loginId.jsp" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>게시글 쓰기</title>
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
          <strong>게시글 쓰기</strong></h1>
			<div class="card text-center" style="border: none;">>
			<div class="card-body">
				<form action="../article/articleNewAction.jsp" method="post">
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
					<td colspan="3"><input class="form-control" name="subject" type="text" maxlength="100" placeholder="제목을 작성해주세요" required></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name = "writer" class="form-control" readonly="readonly" value="<%=loginId %>">
					</td>
					<th>비밀번호</th>
					<td><input type="password" name = "passwd" class="form-control" maxlength="8" placeholder="비밀번호를 작성해주세요" required>
					</td>
				</tr>
				</tbody>
				</table>
					<textarea class="form-control" rows="10" name="content" maxlength="4000"></textarea>	
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

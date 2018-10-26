<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>회원가입</title>
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
      <div class="row justify-content-center">
        <!-- Blog Entries Column -->
        <div class="col-md-6">
        <div class="center">
			<h2>회원가입이 완료되었습니다!!</h2><br>
			<h3>다시 로그인 해주세요</h3>
        	<img class="img-fluid rounded" src="../img/success.gif" alt="">
        	
			<div class="row justify-content-center">
				<div class="col-4">
					<input type="button" class="btn btn-primary" value="로그인하기" onclick="location.href='../user/login.jsp'">
				</div>
				<div class = "col-1">
					<label></label>
				</div>
				<div class="col-4">
					<input type="button" class="btn btn-primary" value="홈으로" onclick="location.href='../index.jsp'">
				</div>
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

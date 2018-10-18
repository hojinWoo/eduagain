<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Hojin Blog</title>
    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/main.css" rel="stylesheet">
  </head>
  <body>
    <!-- navigation -->
	<jsp:include page="include/navigator.jsp"/>
	<!-- /navigation -->
    <!-- Page Content -->
    <div class="container">
      <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-md-8">

          <h1 class="my-4">
          <strong>사라있다 블로그</strong><br>
            <small>bootstrap을 이용한 개인 블로그</small>
          </h1>


		<!-- Blog Post -->
          <div class="card mb-4">
            <div class="card-body">
              <img class="img-fluid rounded" src="img/sana.jpg" alt="">
            </div>
          </div>
          
          <!-- Blog Post -->
          <div class="card mb-4">
            <div class="card-body">
              <img class="img-fluid rounded" src="img/messi.jpg" alt="">
            </div>
          </div>
          
          <!-- Blog Post -->
          <div class="card mb-4">
            <div class="card-body">
              <img class="img-fluid rounded" src="img/iu.gif" alt="">
            </div>
          </div>
        </div>
        <!-- Sidebar Widgets Column -->
		<jsp:include page="include/sidebar.jsp"/>
        <!-- Sidebar Widgets Column -->
      </div>
      <!-- /.row -->
    </div>
    <!-- /.container -->
    <!-- Footer -->
	<jsp:include page="include/footer.jsp"/>
    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  </body>
</html>

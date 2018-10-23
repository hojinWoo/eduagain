<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../guestBook/guestListAction.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>방명록</title>
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
          <strong>방명록</strong><br>
          </h1>
          <br><br>
          <!-- Post Write -->
          <div class="card mb-4">
            <h5 class="card-header">방명록을 작성해주세요~~</h5>
            <div class="card-body">
            	<form class= "row" action="guestBookAction.jsp" method="post">
            	<div class="col-md-9">
            	<% 
              	if(loginId != null){
              	%>
	            	<input type="hidden" id= "user_id" name="user_id" value="<%=loginId%>">
            		<textarea class="form-control" rows="3" id = "contents" maxlength="400" name="contents" placeholder="방명록 남겨주세요^^"></textarea>
              	<%}else{%>
              		<textarea class="form-control" rows="3" maxlength="400" disabled="disabled">로그인  후 작성이 가능합니다.</textarea>
              	<%} %>
              	</div>
              	<div class="col-md-3 col align-self-center">
              	<% 
              	if(loginId != null){
              	%>
            		<input class="btn btn-primary" type="submit" value = "방명록 등록">
              	<%} %>
              	</div>
				</form>
            </div>
          </div>
          <br><br>
		<%for(Guest guest : guests){ %>
			<!-- Blog Post -->
          <div class="card mb-4">
            <div class="card-body">
              <h2 class="card-title"><%=guest.getUser_id() %></h2>
              <pre class="card-text"><%=guest.getContents() %></pre>
            </div>
            <div class="card-footer text-muted">
              <%=guest.getRegdate()%>
            </div>
          </div>
		
		<%} %>

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

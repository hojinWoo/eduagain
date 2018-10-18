<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../jsp/saveId.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login</title>
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
			<%
		if(loginId == null){
		%>
		<div class="card mb-4">
		<h5 class="card-header">로그인</h5>
		<div class="card-body">
			<form action="../user/loginAction.jsp" method="post">
			<div class = "row">
				<label class="col-md-4">아이디</label>
				<% if(saveId == null){ %>
					<input type="text" class="form-control col-md-8" name = "id" placeholder="아이디를 입력해주세요..">
				<%}else{ %>
					<input type="text" class="form-control col-md-8" name = "id" value = "<%=saveId%>">
				<%}%>
			</div>
			<br>
			<div class = "row">
				<label class="col-md-4">비밀번호</label>
				<input type="password" class="form-control col-md-8"  id = "pw" name = "pw"  placeholder="비밀번호를 입력해주세요..">
			</div>
			<br>
			<div class = "row justify-content-end align-items-center">
			<% if(saveId == null){ %>
				<input type = "checkbox" name = "saveId" >
				<%}else{ %>
				<input type = "checkbox" name = "saveId" checked="checked" >
				<%} %>
				<label style="text-align: right">아이디 저장</label>
			</div>
			<br>	
				<div class="row justify-content-around">
					<input class="btn btn-secondary" type="submit" value = "로그인">
				</form>
					<input type="button" class="btn btn-secondary " value="회원가입" onclick="location.href='../user/signup.jsp'">
			</div>
		<%}else{ response.sendRedirect("../index.jsp");}%>
          
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
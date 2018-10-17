<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.pattern.UserDao"%>
<%@ page import="kr.or.kosta.pattern.JdbcDaoFactory"%>
<%@ page import="kr.or.kosta.pattern.DaoFactory"%>
<!DOCTYPE html>
<html lang="en">
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
      <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-md-8">
        <div class="center">
			<form class="form-checkId" action="checkIdAction.jsp" method="post">
		      <h1 class="h3 mb-3 font-weight-normal">회원가입</h1><br>
		      <div class="row">
		      <label class = "col-md-3" for="id">아이디</label>
		      <%
		      String id = null;
		      System.out.println("받은거 :"+request.getParameter("cid"));
		    		  if(request.getParameter("cid")!=null){
		    			  if(!request.getParameter("cid").equals("!!")){
		    				  id = request.getParameter("cid");
		    				  System.out.println("asd" + id);
		    				  %>
		     					<input type="text" name="bid" class="col-md-6" value="<%=id%>" required autofocus>
		    				  <%
		    			  }
		    		  }else{
		    			  %>
					    	  <input type="text" name="bid" class="col-md-6" placeholder="아이디를 입력해주세요" required autofocus>
		    			  <%
		    		  }
		      %>
			  <label class= "col-md-1"></label>
			  <input type="submit" class="btn btn-sm btn-info active" aria-pressed="true" value="중복확인"></button>

			  </div>
			  </form>
			  <div class = "row">
			  <label class="col-md-3"></label>
			  <% 
			  if(id != null){
				  	if (id.equals("!!")) {System.out.println("사용 불가능!!!!");%>
					 <label class="col-md-6"><small style="color: red">사용 불가능한 아이디입니다.</small></label>
				<%}else{System.out.println("사용 가능!!!!");%>
					<label class="col-md-6"><small style="color: red">사용 가능한 아이디입니다.</small></label>
				<%}}else{%><br>
				<%}%>
			  </div>
			  
		  <form class="form-signin" action="signupAction.jsp" method="post">
		  		<%if(id != null) {%>
		  			<input type="hidden" id = "id" name="id" value="<%=id%>">
		  		<%} %>	
			  <div class="row">
		      <label class = "col-md-3" for="passwd" class="sr-only">비밀번호</label>
		      <input type="password" id="passwd" name="passwd" class="col-md-6" placeholder="비밀번호를 입력해주세요" required>
		       </div>
		      <br>
		      <div class="row">
		      <label class = "col-md-3" for="chekPassword" class="sr-only">비밀번호 확인</label>
		      <input type="password" id="chekPassword" class="col-md-6" placeholder="비밀번호를 한 번 더 입력해주세요" required>
			   </div>
			  <br>
			  <div class="row">
		      <label class = "col-md-3" for="name" class="sr-only">이름</label>
		      <input type="text" id="name" name="name" class="col-md-6" placeholder="아이디를 입력해주세요" required autofocus>
		  	   </div>
		  	   <br>
		  	  <div class="row">
		      <label class = "col-md-3" for="email" class="sr-only">이메일</label>
		      <input type="email" id = "email" name="email" class="col-md-6" placeholder="이메일을 입력해주세요" required >
		      </div>
		      <br><br>
		      <div class = "row justify-content-md-center">
		      	<button class="btn btn-sm btn-secondary active btn-block col-md-4" type="submit" aria-pressed="true">Sign up</button>
		      </div>
		    </form>
		    <div style="float: right;">
		    <p class=" mt-5 mb-3 text-muted">&copy; 2018-2018</p>
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

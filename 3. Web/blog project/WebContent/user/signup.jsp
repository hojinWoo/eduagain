<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@ page import="kr.or.kosta.blog.user.domain.UserJdbcDaoFactory"%>
<%@ page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%
request.setCharacterEncoding("utf-8");
%>
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
    
    <script type="text/javascript">
    /* 한글 입력 방지 */
    function fn_not_han(e){
    	var event = e || window.event;
    	if(!(event.keyCode >=37 && event.keyCode<=40) ){
    	  event.value = event.value.replace(/[^a-z0-9]/gi,'');
    	}
    }
    /* 비밀번호 체크 */
    function checkIDandPw(){
    	<%if(request.getParameter("cid")==null || request.getParameter("cid").equals("!!")){ %>
    		document.getElementById('checkMustID').innerHTML = ' 중복체크를 눌러주세요.';
    		return false;
    	<%} %>
    	var pw = document.getElementById("passwd").value;
    	var cpw = document.getElementById("chekPassword").value;
    	if(pw != cpw){
    		document.getElementById('wrongPw').innerHTML = ' 비밀번호가 틀렸습니다.';
    		return false;
    	}
    	return true;
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
        <div class="center">
			<form class="checkId" action="checkIdAction.jsp" method="post">
		      <h1 class="h3 mb-3 font-weight-normal">회원가입</h1><br>
		      <div class="row">
		      <label class = "col-md-3" for="id">아이디</label>
		      <%
		      /* 아이디 중복체크 결과 */
		      String id = null;
    		  if(request.getParameter("cid")!=null){
    			  id = request.getParameter("cid");
    			  if(!(id.equals("!!"))){
    				  %>
     					<input type="text" name="bid" class="col-md-6" onkeyup="fn_not_han(this)" value="<%=id%>" maxlength="12" required autofocus>
    				  <%
    			  }else{
    				  %>
			    	  <input type="text" name="bid" class="col-md-6" onkeyup="fn_not_han(this)" maxlength="12" placeholder="아이디를 입력해주세요" required autofocus>
    			  	<%
    			  }
    		  }else{
    			  %>
			    	  <input type="text" name="bid" class="col-md-6" onkeyup="fn_not_han(this)" maxlength="12" placeholder="아이디를 입력해주세요" required autofocus>
    			  <%
    		  }
		      %>
			  <label class= "col-md-1"></label>
			  <input type="submit" class="btn btn-sm btn-info active" aria-pressed="true" value="중복확인"></button>
			  </div>
			  </form>
			  <div class = "row">
			  <label class="col-md-3"></label>
			  <label id="checkMustID" style="color: red"><small></small></label>
			  <% 
			  if(id != null){
				  	if (id.equals("!!")) {%>
					 <label class="col-md-6"><small style="color: red">사용 불가능한 아이디입니다.</small></label>
				<%}else{%>
					<label class="col-md-6"><small style="color: red">사용 가능한 아이디입니다.</small></label>
				<%}}else{%><br>
				<%}%>
			  </div>
		  	<form class="form-signin" action="../user/signupAction.jsp" onsubmit="return checkIDandPw();" method="post">
		  		<%if(id != null) {%>
		  			<input type="hidden" id = "id" name="id" value="<%=id%>">
		  		<%} %>	
			  <div class="row">
		      <label class = "col-md-3" for="passwd" class="sr-only">비밀번호</label>
		      <input type="password" id="passwd" name="passwd" class="col-md-6" maxlength="12" placeholder="비밀번호를 입력해주세요" required>
		       </div>
		      <br>
		      <div class="row">
		      <label class = "col-md-3" for="chekPassword" class="sr-only">비밀번호 확인</label>
		      <input type="password" id="chekPassword" class="col-md-6" maxlength="12" placeholder="비밀번호를 한 번 더 입력해주세요" required>
				<br>
				<label id="wrongPw" style="color: red"><small></small></label>
			   </div>
			  <br>
			  <div class="row">
		      <label class = "col-md-3" for="name" class="sr-only">이름</label>
		      <input type="text" id="name" name="name" class="col-md-6" maxlength="6" placeholder="이름을 입력해주세요" required autofocus>
		  	   </div>
		  	   <br>
		  	  <div class="row">
		      <label class = "col-md-3" for="email" class="sr-only">이메일</label>
		      <input type="email" id = "email" name="email" class="col-md-6" maxlength="30" placeholder="이메일을 입력해주세요" required ><br>
		      </div>
		      <% 
			  if(request.getParameter("email") != null){
				  	if (request.getParameter("email").equals("false")) {%>
				  	<div>
				  	 <label class="col-md-3"></label>
					 <label class="col-md-6"><small style="color: red">이메일이 중복됩니다.</small></label>
					</div>
				<%}%>
			  <%}%>
		      <br><br>
		      <div class = "row justify-content-md-center">
		      	<input class="btn btn-sm btn-secondary active btn-block col-md-4" type= "submit" value = "Sign up">
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

<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<!-- Sidebar Widgets Column -->
<%
boolean islogin = false;
Cookie[] cookies = request.getCookies();
if(cookies!=null){
	for(Cookie cookie2: cookies){
		if(cookie2.getName().equals("id")){
			islogin = true;
		}
	}
}
%>
<div class="col-md-4">
	<!-- Search Widget -->
	<div class="card my-4">
		<%
		if(!islogin){
		%>
		<h5 class="card-header">로그인</h5>
		<div class="card-body">
			<form action="../user/loginAction.jsp" method="post">
			<div class = "row">
				<label class="col-md-4">아이디</label>
				<input type="text" class="form-control col-md-8" id = "id" name = "id" placeholder="아이디를 입력해주세요..">
			</div>
			<br>
			<div class = "row">
				<label class="col-md-4">비밀번호</label>
				<input type="password" class="form-control col-md-8"  id = "pw" name = "pw"  placeholder="비밀번호를 입력해주세요..">
			</div>
			<br>	
				<span class="input-group-btn">
					<input style="float: right;" class="btn btn-secondary" type="submit" value = "로그인">
				</span>
			</form>
		<%}else{%>
			<form action="../user/logoutAction.jsp" method="get">
				<span class="input-group-btn">
					<input style="float: right;" class="btn btn-secondary" type="submit" value = "로그아웃">
				</span>
			</form>
			</div>
		<%} %>
	</div>
</div>
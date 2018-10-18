<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ include file="../jsp/cookie.jsp" %>
<!-- Sidebar Widgets Column -->
<div class="col-md-4">
	<!-- Search Widget -->
		<%
		if(loginId == null){
		%>
		<div class="card my-4">
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
				<div class="row justify-content-around">
					<input class="btn btn-secondary" type="submit" value = "로그인">
				</form>
					<input type="button" class="btn btn-secondary " value="회원가입" onclick="location.href='../user/signup.jsp'">
			</div>
		<%-- <%}else{%>
		<h5 class="card-header"><%=loginId %>님 환영합니다.</h5>
		<div class="card-body">
			<% 
			String uri = request.getRequestURI();
			System.out.println(uri);
			if(uri.equals("/board/board.jsp")){%>
			<form action="../board/createPost.jsp" method="get">
				<div class="row justify-content-center">
				<br>
				<input class="btn btn-primary" type="submit" value = "게시글 쓰기">
				</div>
			</form>
			<%}%>
			<br>
			<form action="../user/logoutAction.jsp" method="get">
			<span class="input-group-btn">
					<input style="float: right;" class="btn btn-secondary" type="submit" value = "로그아웃">
					</span>
			</form>
			</div> --%>
		<%} %>
	</div>
</div>
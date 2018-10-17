<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<%
boolean islogin = false;
Cookie[] cookies = request.getCookies();
if(cookies!=null){
	for(Cookie cookie: cookies){
		if(cookie.getName().equals("id")){
			islogin = true;
		}
	}
}

%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="../index.jsp">호진 블로그</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="../index.jsp">홈</a></li>
				
				<li class="nav-item"><a class="nav-link" href="#">방명록</a></li>
				<li class="nav-item"><a class="nav-link" href="#">자유게시판</a></li>
				<%
				if(islogin){
				%>
					<li class="nav-item"><a class="nav-link" href="../user/logoutAction.jsp">로그아웃</a></li>
				<%}else{ %>
					<li class="nav-item"><a class="nav-link" href="../user/signup.jsp">회원가입</a></li>
				<%}%>
			</ul>
		</div>
	</div>
</nav>
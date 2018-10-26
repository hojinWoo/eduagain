<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="user" class="kr.or.kosta.blog.user.dao.User"/>
<jsp:setProperty property="*" name="user"/>
<%
Cookie[] cookies = request.getCookies();
for(Cookie cookie : cookies){
	if(cookie.getName().equals("id")){
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
//response.sendRedirect("../index.jsp");
response.sendRedirect(request.getHeader("referer"));
%>

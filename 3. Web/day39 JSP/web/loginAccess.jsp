<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<%
String id = request.getParameter("userid");
String pw = request.getParameter("userpw");

/* if (id==null){
	Cookie[] cookies = request.getCookies();
	for(Cookie cookie2 : cookies){
		if(cookie2.getName().equals("id")){
			cookie2.setPath("/");
			cookie2.setMaxAge(0);
			response.addCookie(cookie2);
		}
	}
}else{
	Cookie cookie = new Cookie("id",id);
	cookie.setPath("/");
	response.addCookie(cookie);
} */
if(id != null){
	session.setAttribute("id", id);
	session.setAttribute("pw", pw);
}else{
	//session.invalidate();
	session.removeAttribute("id");
}
response.sendRedirect("index.jsp");
%>

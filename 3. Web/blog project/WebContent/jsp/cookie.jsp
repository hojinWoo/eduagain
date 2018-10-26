<%@ page contentType="text/html; charset=utf-8"%>
<%
String loginId = null;
Cookie[] cookies = request.getCookies();
if(cookies!=null){
	for(Cookie cookie2: cookies){
		if(cookie2.getName().equals("id")){
			loginId = cookie2.getValue();
			break;
		}
	}
}
%>
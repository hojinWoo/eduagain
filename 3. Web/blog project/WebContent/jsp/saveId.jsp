<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../jsp/loginId.jsp" %>
<%
String saveId = null;
if(cookies!=null){
	for(Cookie cookie2: cookies){
		if(cookie2.getName().equals("saveId")){
			saveId = cookie2.getValue();
			break;
		}
	}
}
%>

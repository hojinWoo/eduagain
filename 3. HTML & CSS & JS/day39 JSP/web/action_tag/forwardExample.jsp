<%@ page contentType="text/html; charset=utf-8"%>
<%
int age = 20;
%>
<jsp:forward page="/hello.jsp">
	<jsp:param value="hh" name="id"/>
	<jsp:param value="<%=age%>" name="age"/>
</jsp:forward>

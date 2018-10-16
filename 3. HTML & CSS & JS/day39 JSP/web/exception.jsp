<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>예외처리</title>
</head>
<body>
<!-- 예전 예외처리 방식, 이렇게 하지 않는다 -->
<%
try{
	String name = null;
	name.length();
}catch(NullPointerException e){
	%>
	예외 발생 : <%=e.toString()%>
<%
}
%>
</body>
</html>

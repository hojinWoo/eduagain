<%@ page contentType="text/html; charset=utf-8"%>
<!-- 예전 지정방식 -->
<%-- <%@ page errorPage="errorHandlingPage.jsp" %> --%>
<!-- web.xml (container)에 등록해서 지정 가능 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>예외처리</title>
</head>
<body>
<!-- 예외처리하는 servlet을 따로 만드는 식 (forwarding) exception을 넘겨서 처리한다. -->
<%
String name = null;
name.length();
//out.println(10/0);
%>
</body>
</html>

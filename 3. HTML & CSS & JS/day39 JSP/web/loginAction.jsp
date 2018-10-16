<%@ page contentType="text/html; charset=utf-8"%>
<%
/* post방식 > encoding */
request.setCharacterEncoding("utf-8");
String firstName = request.getParameter("firstname");
String lastName = request.getParameter("lastname");
String country = request.getParameter("country");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
firstName : <%=firstName %><br>
lastName : <%=lastName %><br>
country : <%=country %><br>
</body>
</html>

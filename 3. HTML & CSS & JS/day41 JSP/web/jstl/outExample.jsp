<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String message = "hello <test> jstl";
request.setAttribute("message", message);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>JSTL 출력</title>
</head>
<body>
${message},												<!-- EL -->
<c:out value="${message}" default="test message"/>		<!-- JSTL, 동적 출력 시 EL 사용 -->
<!-- 만약 출력문에 tag 등 들어가 있는 경우  > escape처리를 자동으로 해 준다.-->
<!-- 대부분 그냥 EL 태그 사용 -->

</body>
</html>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>jsp가 제공하는 4개의 스코프(컨텍스트) 객체들 test</title>
</head>
<body>
현재 페이지 정보 : <%=pageContext.getAttribute("msg")%><br>
request 정보 : <%=request.getAttribute("msg")%><br>
session 정보 : <%=session.getAttribute("msg")%><br>
application 정보 : <%=application.getAttribute("msg")%><br>
</body>
</html>

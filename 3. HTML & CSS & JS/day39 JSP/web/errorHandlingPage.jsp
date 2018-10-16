<%@ page contentType="text/html; charset=utf-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
<!-- Exception 객체가 받아서 처리(isErrorPage가 true일 때만 사용 가능) -->
아래와 같은 error가 발생하였습니다.<br>
<%=exception.toString()%>
</body>
</html>

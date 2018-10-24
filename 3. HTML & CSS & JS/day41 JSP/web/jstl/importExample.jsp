<%@ page language="java" contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>import</title>
</head>
<body>
<%-- <c:import url="https://www.naver.com"></c:import> --%>

<c:import url="https://www.naver.com" var="output"></c:import>

asdasda
<c:out value="${output}"></c:out>

</body>
</html>


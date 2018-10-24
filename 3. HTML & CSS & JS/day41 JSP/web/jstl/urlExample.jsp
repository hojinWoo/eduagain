<%@ page language="java" contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>url</title>
</head>
<body>
<!-- url 객체로 저장 -->
<c:url var = "url" value = "https://www.naver.com">
	<c:param name="name" value = "aa"></c:param>
</c:url>

<a href = "${url}">이동</a>
</body>
</html>


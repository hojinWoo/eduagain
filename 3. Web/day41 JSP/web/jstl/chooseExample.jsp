<%@ page language="java" contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:set var="score" value="80" scope="request"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>choose</title>
</head>
<body>

<c:choose>
   <c:when test="${score >= 90 }">��</c:when>
   <c:when test="${score >= 80 }">��</c:when>
   <c:when test="${score >= 70 }">��</c:when>
   <c:when test="${score >= 60 }">��</c:when>
   <c:otherwise>��</c:otherwise>
</c:choose>

</body>
</html>


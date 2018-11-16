<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${title}</title>
</head>
<body>
<c:forEach var="employee" items="${list}">
<table border="1" width="50%">
<tr>
	<td>${employee.employeeId}</td>
	<td>${employee.firstName}</td>
	<td>${employee.lastName}</td>
	<td>${employee.email}</td>
</tr>
</table>
</c:forEach>
</body>
</html>
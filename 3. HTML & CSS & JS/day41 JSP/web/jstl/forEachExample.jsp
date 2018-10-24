<%@page import="kr.or.kosta.pattern.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 확장 for문 사용 위해 임시 생성 -->
<%
List<String> teams = new ArrayList<String>();
teams.add("barcelona");
teams.add("chelsea");
teams.add("MU city");
teams.add("dortmund");

request.setAttribute("teams", teams);

/* 주로 Bean 객체 저장 */
List<User> users = new ArrayList<User>();
users.add(new User("aa","asdas","pwd","add@ss","agg"));
users.add(new User("bb","bsdas","pwd","bdd@ss","bgg"));
users.add(new User("cc","csdas","pwd","cdd@ss","cgg"));
users.add(new User("dd","dsdas","pwd","ddd@ss","dgg"));

request.setAttribute("users", users);

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>JSTL FOREACH</title>
</head>
<body>

<c:forEach var = "i" begin = "1" end = "10" step="2">
${i} : hello <br>
</c:forEach>

<table border = "1"> 
<c:forEach var = "i" begin = "2" end = "9">
	<tr>
	<c:forEach var = "j" begin = "1" end = "9">
		<td>${i} * ${j} = ${i*j}</td>
	</c:forEach>
	</tr>
</c:forEach>
</table>

<!-- 확장 for문 -->
<select>
<c:forEach var="team" items="${teams}">	
	<option>${team}</option>
</c:forEach>
</select>

<table border="1">
	<tr>
		<th>번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>비밀번호</th>
		<th>이메일</th>
		<th>가입일자</th>
	</tr>
	
	<c:choose>
		<c:when test="${not empty users}">
			<c:forEach var="user" items="${users}" varStatus="status"> <!-- varStatus : loop시마다 몇 번 돌고 있는 지 상태 정보 -->	
			<tr>
			<!-- getter method가 있어야 된다. -->
				<th>${status.count}</th> <!-- index는 0부터 시작 -->
				<th>${user.id}</th>
				<th>${user.name}</th>
				<th>${user.passwd}</th>
				<th>${user.email}</th>
				<th>${user.regdate}</th>
			</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="5">사용자가 존재하지 않습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>
	
</table>

<%
String ssn = "123456-1234567";
request.setAttribute("ssn", ssn);
%>
<c:forTokens var = "token" items="${ssn}" delims="-">
${token }<br>
</c:forTokens>

</body>
</html>
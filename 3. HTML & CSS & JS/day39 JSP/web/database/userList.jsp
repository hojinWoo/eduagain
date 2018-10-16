<%@page import="kr.or.kosta.pattern.User"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosta.pattern.UserDao"%>
<%@page import="kr.or.kosta.pattern.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.pattern.DaoFactory"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
DaoFactory factory = new JdbcDaoFactory();
UserDao dao = factory.getUserDao();
List<User> list = dao.listAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
<table border="1" width="50%">
<thead>
	<tr>
		<th>아이디 </th>
		<th>이름</th>
		<th>이메일 </th>
		<th>가입일자 </th>
	</tr>
	</thead>
	<tbody>
	<%
		for(User user : list){
	%>
	<tr>
	<%-- useBean을 써야 getProperty 사용 가능 --%>
		<td><%=user.getId() %></td>
		<td><%=user.getName() %></td>
		<td><%=user.getEmail() %></td>
		<td><%=user.getRegdate() %></td>
	</tr>
	<%
		}
	%>
	</tbody>
</table>
</body>
</html>

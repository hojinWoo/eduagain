<!-- Containser에게 전달 -->
<%@ page contentType="text/html; charset=utf-8" %>
<%-- <%!
/* instance 변수 (private도 가능) */
int count;
/* JSP는 한 번더 상속을 받아서 구현하기 때문에 HttpJSPBase에서 init을 Override해야 한다
//서버마다 이름이 다를 수는 있음
public void jspInit(){}
public void jspDestroy(){}
public void jspService(~~~){}
*/
//선언하는 init부분 Override, 그러나 life cycle을 신경쓰지 않기 때문에 사용하는 경우는 없다.
public void jspInit(){
	count = 0;
}
public void jspDestroy(){
	count = 0;
}
%> --%>
<!-- include 지시어 -->
<%@ include file='jspf/common.jspf' %>
<!DOCTYPE html>
<html>
<head>
<!-- browser에게 전달 -->
<meta charset="EUC-KR">
<title>Gugudan</title>
</head>
<body>
<h2>구구단</h2>
<table border='1' width = '80%'>
<%
	for(int i = 2; i < 10; i++){
		//1. 여기서 out.println() 사용 가능하지만 좋지 않은 방법
		//2. 출력문 사용<%=>도 사용X
		%>
		<tr>
		<%
		for(int j = 2; j < 10; j++){
		%>
			<td><%=i%> * <%=j%> = <%=i*j%> </td>
		<%
		}
		%>
		</tr>
<%
	}
%>
</table>
<!-- 방문 횟수 출력 (life cycle), 요청 시다마 service안 method 호출 -->
<h2>Init이 죽기 전 <%=++count%>번 호출했습니다</h2>
</body>
</html>

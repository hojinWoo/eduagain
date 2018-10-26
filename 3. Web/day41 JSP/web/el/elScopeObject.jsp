<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EL 스코프(디폴트) 객체 11개</title>

</head>
<body>
<!-- pageContext 기본 객체에 저장된 속성<키, 값> 매핑을 저장한 Map 객체-->
page : ${pageScope} <br><br>
<!-- request 기본 객체에 저장된 속성<키, 값> 매핑을 저장한 Map 객체 -->
<!-- request에 있는 protoco, method들은 pageContext를 사용해야 한다 -->
request : ${requestScope} <br><br>
<!-- session 기본 객체에 저장된 속성<키, 값> 매핑을 저장한 Map 객체 -->
session : ${sessionScope} <br><br>
<!-- application 기본 객체에 저장된 속성<키, 값> 매핑을 저장한 Map 객체 -->
<%--application : ${applicationScope} <br><br> --%>

<!-- 
요청 파라미터의 <파라미터이름, 값> 매핑을 저장한 Map 객체
request.getParameter(“파라미터이름”)의 결과와 동일
 -->
${param.name},	<!--/url?name=aa, 전달 값이 없는 경우 null이 아닌 빈 문자열 -->
${param["name"]} <br><br>

<!-- 
요청 파라미터의 <파라미터이름, 값배열> 매핑을 저장한 Map 객체
request.getParameterValues(“파라미터이름”)의 결과와 동일
-->
${paramValues.hobby[0]},	<!-- /url?name=aa&hobby=df&hobby=zz -->
${paramValues.hobby[1]} <br><br>


<!-- 
요청 정보의 <헤더이름, 값> 매핑을 저장한 Map 객체
request.getHeader(“헤더이름”)의 결과와 동일 
-->
${header['user-agent']}<br><br>

<!-- 
요청 정보의 <헤더이름, 값배열> 매핑을 저장한 Map 객체
request.getHeaders(“헤더이름”)의 결과와 동일
 -->
<%--${headerValues[''][] } --%>	<!-- 배열로 return -->

<!--  
web.xml의 <context-param>의 초기파라미터 <이름, 값> 매핑을 저장한 Map 객체
application.getInitParameter(“파라미터이름”)의 결과와 동일
 -->
<%-- initParam --%>
 
<!-- 
<쿠키이름, Cookie> 매핑을 저장한 Map 객체
request.getCookies()의 결과와 동일
-->
쿠키이름 : ${cookie.loginId.name }
쿠키 값 : ${cookie.loginId.value }	
<!-- 비어있는 경우 empty 연산자 사용 -->
${empty cooke.loginId}<br><br>
								
<!-- 
jsp의 pageContex와 동일한 자바 빈객체 , 모든 객체와 연결되어 있다.
request, session 등 정보를 가지고 있다. ('.')으로 접근 가능
-->
<%=request.getMethod() %>
${pageContext.request.method }

</body>
</html>
<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EL 스코프 객체 테스트</title>
</head>
<body>
<%
// 테스트를 위한 Scope객체에 데이터 저장!!
String today = String.format("%1$tF %1$tT", Calendar.getInstance());
request.setAttribute("today", today);

session.setAttribute("id", "bangry");

String[] names = {"김기정", "박기정", "최기정"};
%>

<%-- request.getAttribute("today") --%>
${requestScope.today}<br>
${today}<br>

<%-- pageContext.findAttribute("id") --%>
${id}<br>

<!-- EL로 지역변수 접근하는 것 X -->
${names[0]}, ${names[1]}, ${names[2]}<br>


<jsp:useBean id="dog" class="el.Dog" scope="page" />
<jsp:setProperty value="치얀" property="name" name="dog"/>

<jsp:useBean id="student" class="el.Student" scope="page" />
<jsp:setProperty value="이야" property="name" name="student"/>
<jsp:setProperty value="${dog}" property="dog" name="student"/>	<!-- 설정 시 EL 필요 -->
<%--
<<getProperty에서 안되는 것을 보완>>
<jsp:getProperty property="id" name="student"/>
<jsp:getProperty property="name" name="student"/>
<jsp:getProperty property="dog" name="student"/>
--%>
<!-- student의 dog name을 출력하고 싶지만 toString이 호출된다 -->
<jsp:getProperty property="name" name="dog"/>

${student.name}, 
${student.dog.name},
${student.setName("스크")}
${student.name},  <br>

<!-- EL에서 class method 직접 호출은 X -->
<!-- TLD -->


</body>
</html>
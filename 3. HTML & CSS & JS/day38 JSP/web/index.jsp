<!--지시어  -->
<%@ page import="java.util.Calendar"%>
<%@ page  contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>hello jsp</title>
</head>
<body>
<!-- jsp코드를 .class로 변환
	workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/work/Catalina/localhost/jsp/org/apache/jsp/
 -->
<!-- html과 JSP의 인코딩은 다르다 -->
<h2>JSP 테스트 페이지..</h2>
<!-- browser가 아닌 web contents에서 실행된다. -->
<% String msg = "jsp 실행과정입니다";
	out.println(msg);
	Calendar today = Calendar.getInstance();
	out.println(today.toString());
%>
</body>
</html>

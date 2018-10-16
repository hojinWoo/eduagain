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
<%
	/* 지역코드 밑으로 들어간다. (지역변수) Service method 밑에 있다. Service밑에 생성*/
	String msg = "jsp 실행과정입니다";
	out.println(msg);
	Calendar today = Calendar.getInstance();
	out.println(today.toString());

%>

<!-- Instance 변수로 하고 싶을 시에 '선언문' 필요  (잘 사용X)-->
<%!
	int temp = 50;
	/* 여기서는 대신 출력이 불가능 (out은 지역변수이기 때문에 불가능) */
	public void printMessage(String tt){
		System.out.println(tt);
	}
%>

<!-- '출력문(표현식)' out.print 대용으로 사용, 동적으로 변수 출력 시 사용 -->
<%
//	지역변수 선언
	String message = "let's break";
//사용 X
/* out.println(message); */

%>
메세지 : <%= message %>입니다	<!-- out.println(message)으로 변환 -->

<!-- Context Object (Scope, Map) -->
<!--1. page context
	2. request
	3. session
	4. application -->


</body>
</html>

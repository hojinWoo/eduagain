<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>jsp가 제공하는 4개의 스코프(컨텍스트) 객체들</title>
</head>
<body>
<%
/* 지역변수로 저장이 아닌 context에 저장해서 더 넓게 사용 가능 (현재 클래스에서만 사용 가능)*/
pageContext.setAttribute("msg", "test jsp by pageContext");
%>
현재 페이지 정보 : <%=pageContext.getAttribute("msg")%><br>
<%
/* request로 통해 공유 가능 (포워드 필수) */
request.setAttribute("msg", "test jsp by requet");
/* 항상 '/'부터 시작해야 한다 */
//application.getRequestDispatcher("/scopeObject2.jsp").forward(request, response);
%>
<%
/* session : 같은 브라우저에서 (다른 창으로도) 사용 가능(하지만 먼저 실행이 필수) */
session.setAttribute("msg", "test jsp by session");
%>
<%
/* application 전역으로 공유(잘 사용X) 다른 브라우저에서도 사용 가능*/
application.setAttribute("msg", "test jsp by application");
%>
</body>
</html>

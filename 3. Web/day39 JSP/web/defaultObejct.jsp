<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>jsp가 제공하는 9개의 디폴트 객체들</title>
</head>
<body>
<!-- name	type -->
<!-- pageContext, session, exception은 다른 곳에 -->
<%
/* request		HttpServletRequest */
String id = request.getParameter("id");
/* response		HttpServletResponse */
//response.sendRedirect("hellojsp.jsp");
%>
아이디 : <%=id%><br>
<%
/* out			JspWriter(잘 사용X) */
out.println("<h4>출력</h4>");
%>
<!--application	ServletContext -->
WAS에 등록된 웹애플리케이션 이름 : <%=application.getContextPath()%><br>
WAS정보 : <%=application.getServerInfo()%><br>
<!--config		ServletConfig  -->
<!--jsp도 web.xml에 등록 가능 (옵션, 선택사항임)  -->
location : <%=config.getInitParameter("location")%><br>
<!--page		Object  -->
현재 페이지 : <%=this %><br>
현재 페이지 : <%=toString() %><br>
<!--Exception	Throwable  -->
<!--일반 jsp에서 사용X  -->
<%-- 예외 : <%=exception%> --%>
</body>
</html>

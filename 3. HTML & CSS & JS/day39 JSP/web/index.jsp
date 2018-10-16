<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<%--
	Cookie[] cookies = request.getCookies();
	String id = null;
	for(Cookie cookie : cookies){
		if(cookie.getName().equals("id"))
			id = cookie.getValue();
	}
--%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/basic.css">
</head>
<body>
<div class="header">
  <h1>My Website</h1>
  <p>Resize the browser window to see the effect.</p>
</div>
<%-- navigator 시작 (jsp 주석으로 사용하기 > 노춡X) --%>
<jsp:include page="/include/navigator.jsp"></jsp:include>
<%-- navigator 종료 --%>
<div class="row">
  <div class="leftcolumn">
    <div class="card">
      <h2>TITLE HEADING</h2>
      <h5>Title description, Dec 7, 2017</h5>
      <div class="fakeimg" style="height:200px;">Image</div>
      <p>Some text..</p>
      <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
    </div>
    <div class="card">
      <h2>TITLE HEADING</h2>
      <h5>Title description, Sep 2, 2017</h5>
      <div class="fakeimg" style="height:200px;">Image</div>
      <p>Some text..</p>
      <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
    </div>
  </div>
  <%-- 사이드메뉴 시작 --%>
  <jsp:include page="/include/aside.jsp"></jsp:include>
  <%-- 사이드메뉴 종료 --%>
</div>
<%-- footer 시작 --%>
<jsp:include page="/include/footer.jsp"></jsp:include>
<%-- footer 종료 --%>
</body>
</html>

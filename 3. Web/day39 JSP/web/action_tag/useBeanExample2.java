<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.jsp.Account"%>
<%
//Class.forName("kr.or.kosta.jsp.Account").newInstance();	//new보다 동적 생성 사용
%>
<%-- useBeanExample에서 생성된 것을 검색해서 찾아옴 --%>
<jsp:useBean id="account" class="kr.or.kosta.jsp.Account" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
계좌번호 : <%=account.getAccountNum() %><br>
예금주명 : <jsp:getProperty property="accountOwner" name="account"/><br>
잔액 	  : <jsp:getProperty property="restMoney" name="account"/><br>
비밀번호 : <jsp:getProperty property="passwd" name="account"/><br>
<%-- useBean에서 검색할 때 getAttribute가 아닌 findAttribute로 검색한다 --%>
</body>
</html>

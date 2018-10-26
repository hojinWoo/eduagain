<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.jsp.Account"%>
<%
//Class.forName("kr.or.kosta.jsp.Account").newInstance();	//new보다 동적 생성 사용
%>
<%-- useBean은 동적생성 + 추가적인 기능. 디폴트 생성자 호출(useBean은 디폴트 생성자 필수)
     useBean은 무조건 생성이 아닌 key값을 통해 scope 객체 중 순서대로 찾아보는 역할
     (pageContext > request > .. >class.forName) 재사용! 없는 경우 생성 후 page(pageContext, default)에 저장--%>
<jsp:useBean id="account" class="kr.or.kosta.jsp.Account" scope="request"></jsp:useBean>

<jsp:setProperty property="accountNum" name="account" value="1111-2222"/>
<jsp:setProperty property="accountOwner" name="account" value="asd"/>
<jsp:setProperty property="passwd" name="account" value="1122"/>
<jsp:setProperty property="restMoney" name="account" value="100000"/>

<jsp:forward page="/action_tag/useBeanExample2.jsp"></jsp:forward>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
<%-- set, get을 자바 코드로 하는 것이 아닌 setproperty, getproperty로 설정 --%>
계좌번호 : <%=account.getAccountNum() %><br>
예금주명 : <jsp:getProperty property="accountOwner" name="account"/><br>
잔액 	  : <jsp:getProperty property="restMoney" name="account"/>
</body>
</html>

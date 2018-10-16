<%@ page contentType="text/html; charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="account" class="kr.or.kosta.jsp.Account" scope="request"/>
<%-- 하나씩 하는 것이 아니라 한 번에 넣을 수 있다. 그래서 name값이 중요!! (원래 DAO를 통해 생성) --%>
<jsp:setProperty property="*" name="account"/>
<%-- name 값이 일치하지 않은 경우 --%>
<jsp:setProperty property="accountNum" param="num" name="account"/>
<jsp:forward page="/action_tag/useBeanExample2.jsp"></jsp:forward>

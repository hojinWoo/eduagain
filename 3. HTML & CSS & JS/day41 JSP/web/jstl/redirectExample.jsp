<%@ page language="java" contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%-- <c:redirect url = "https://www.daum.net"></c:redirect> --%>
<c:redirect url = "https://www.daum.net">
	<c:param name="name" value="aa"></c:param>
</c:redirect>

<!-- url�� path���� ��� �ϰ� context�� web Application �̸� ���� �ʿ�-->
<c:redirect url="/user/list.jsp" context="/jsp"></c:redirect>
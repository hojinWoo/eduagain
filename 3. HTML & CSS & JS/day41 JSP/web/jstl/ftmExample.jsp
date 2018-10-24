<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--참고 사이트
http://noritersand.tistory.com/256
 --%>
<fmt:formatNumber value="1700600"/>
<!-- 1,700,600 -->

<fmt:formatNumber value="1700600" type="number"/>
<!-- 1,700,600 -->

<fmt:formatNumber value="1700600" type="number" groupingUsed="false"/>
<!-- 1700600 -->

<fmt:formatNumber value="1700600" type="currency" groupingUsed="true"/>
<!-- ￦1,700,600 -->

<fmt:formatNumber value="1670400" type="currency"  currencySymbol="&"/>
<!-- &1,670,400 -->

<fmt:formatNumber value="0.5" type="percent"/>
<!-- 50% -->

<fmt:formatNumber value="999" minIntegerDigits="5" minFractionDigits="2"/>
<!-- 00,999.00 -->

<fmt:formatNumber value="9876543.61" pattern=".000" />
<!-- 9876543.610 -->

<fmt:formatNumber value="9876543.612345" pattern="#,#00.0#"/>
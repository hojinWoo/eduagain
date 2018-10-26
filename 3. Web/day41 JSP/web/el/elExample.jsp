<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EL 연습</title>

</head>
<body>
<!-- JSP 대신 EL 사용으로 출력 가능 -->
${"EL"}
${'EL'}
${10}
${null}
${true}<br>

<%=10 + "20" %>, ${10+'20'}, ${10 * '20'}	<br>

${true && false }, ${true and false }, ${true or false } <br> 

<!-- empty는 null, 빈 문자열, 빈 컬렉션 체크 -->
${empty null}, ${empty "" }	



</body>
</html>
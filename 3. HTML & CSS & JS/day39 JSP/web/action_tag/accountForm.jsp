<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}
input[type=submit], input[type=button] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
input[type=submit]:hover {
    background-color: #45a049;
}
h3{
	text-align: center;
}
div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
    width: 40%;
    margin: 10px auto;
}
</style>
</head>
<body>
<h3>계좌등록화면</h3>
<div>
	<%-- name 값이 중요! --%>
  <form name = "accountForm" action="accountAction.jsp" method="post">
    <label for="accountNum">계좌번호</label>
    <input type="text" id = "accountNum" name="num" placeholder="계좌번호 입력..">
    <label for="accountOwner">예금주</label>
    <input type="text" id = "accountOwner" name="accountOwner" placeholder="예금주 입력..">
    <label for="passwd">비밀번호</label>
    <input type="password" id = "passwd" name="passwd" placeholder="비밀번호 입력..">
    <label for="restMoney">금액</label>
    <input type="text" id = "restMoney" name="restMoney" placeholder="입금금액 입력..">
	<input type="submit" value="계좌 생성">
  </form>
</div>
</body>
</html>

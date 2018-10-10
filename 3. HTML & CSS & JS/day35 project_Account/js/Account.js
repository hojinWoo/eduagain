
function Account(accountNum, accountOwner, passwd, restMoney){
	this.accountNum = accountNum;
	this.accountOwner = accountOwner;
	this.passwd = passwd;
	this.restMoney = restMoney;
}
/*
 * 은닉화가 없기 때문에 get set을 만들지 않음
 */
/**
 * @return 계좌번호
 */
Account.prototype.getAccountNum = function(){
	return this.accountNum;
}
/**
 * @return 계좌주인 
 */
Account.prototype.getAccountOwner = function(){
	return this.accountOwner;
}
/**
 * @return	잔액
 */
Account.prototype.getRestMoney = function(){
	return this.passwd;
}
/**
 * @param restMoney 잔액 설정하기
 */
Account.prototype.setRestMoney = function(restMoney){
	this.restMoney  = restMoney;
}
/**
 * 돈 입금하기
 * @param money	입금하는 금액
 * @return		총 금액
 * @throws AccountException
 */
Account.prototype.deposit = function(money){
	if(money <= 0){
		Alert('입금하고자 하는 금액은 음수일 수 없습니다.');
		return;
	}
	this.restMoney += money;
	return restMoney;
}
/**
 * 돈 출금하기
 * @param money	출금하고자 하는 금액
 * @return
 * @throws AccountException
 */
Account.prototype.withdraw = function(money){
	if(money <= 0){
		Alert('출금하고자 하는 금액은 음수일 수 없습니다.');
		return;
	}
	if(moeny > this.restMoney){
		Alert('잔액이 부족합니다.');
		return;
	}
	this.restMoney -= money;
	return restMoney;
}
/**
 * 비밀번호 확인하기
 * @param passwd	비밀번호
 * @return	기존 비밀번호 일치 여부
 */
Account.prototype.checkPasswd = function(passwd){
	return this.passwd == passwd;
}
/*
 * toString
 */
Account.prototype.toString = function(){
	var output = '';
	for ( var key in this) {
		if(key != 'toString'){
			output += key + ' = ' + this[key]+"\t";
		}
	}
	return output;
}
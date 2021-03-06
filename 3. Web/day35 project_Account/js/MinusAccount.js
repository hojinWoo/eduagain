/**
 * 생성자 생성 (Account 상속)
 */
function MinusAccount(accountNum, accountOwner, passwd, restMoney, borrowMoney){
	Account.call(this, accountNum, accountOwner, passwd, restMoney);
	this.borrowMoney = borrowMoney;
}

MinusAccount.prototype = new Account('', '', 0, 0);

MinusAccount.prototype.getBorrowMoney = function(){
	return this.borrowMoney;
}
MinusAccount.prototype.setBorrowMoney = function(borrowMoney){
	this.borrowMoney = borrowMoney;
}

/**
 * @Override
 */
MinusAccount.prototype.deposit = function(money){
	Account.prototype.setRestMoney(Account.prototype.getRestMoney.call(this) + money);
	return Account.prototype.getRestMoney.call(this);
}
/**
 * @Override
 * 남은금액 return
 */
MinusAccount.prototype.getRestMoney = function(money){
	return Account.prototype.getRestMoney.call(this) - this.borrowMoney;
}

MinusAccount.prototype.toString = function(){
	var output = '';
	for ( var key in this) {
		if(key != 'toString'){
			output += key + ' = ' + this[key]+"\t";
		}
	}
	return output;
}

delete MinusAccount.prototype.accountNum;
delete MinusAccount.prototype.accountOwner;
delete MinusAccount.prototype.passwd;
delete MinusAccount.prototype.restMoney;

MinusAccount.prototype.constructor = MinusAccount;

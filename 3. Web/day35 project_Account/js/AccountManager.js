/**
 * 생성자 생성
 */
function AccountManager(){
	this.accounts=[];
}

/**
 * @return 가지고 있는 모든 Account객체들의 집합
 */
AccountManager.prototype.getAccounts = function() {
	return this.accounts;
}
/**
 * 계좌 추가
 * @param money	출금하고자 하는 금액
 */
AccountManager.prototype.add = function(account){
	for (key in this.accounts) {
		if (this.accounts[key].getAccountNum() == account.getAccountNum()) {
			return false;
		}
	}
	this.accounts.push(account);
	return true;
}
/**
 * 계좌 리스트
 * @param num 0: 전체, 1: account, 2:minuAccount
 */
AccountManager.prototype.list = function(num){
	var output = [];
	if(num == 0){
		for (key in this.accounts) {
			output.push(this.accounts[key]);
		}
	}else if(num == 1){
		for (key in this.accounts) {
			if(!(this.accounts[key] instanceof MinusAccount)){
				output.push(this.accounts[key]);
			}
		}
	}else{
		for (key in this.accounts) {
			if(this.accounts[key] instanceof MinusAccount){
				output.push(this.accounts[key]);
			}
		}
	}
	return output.sort();
}
/**
 * 계좌 번호로 검색하기
 * @param 	계좌번호
 * return 배열
 */
AccountManager.prototype.get = function(accountNum) {
	var output = [];
	for (key in this.accounts) {
		if (this.accounts[key].getAccountNum() == accountNum) {
			output.push(this.accounts[key]);
			return output;
		}
	}
	return output;
}
/**
 * 계좌 이름으로 검색하기
 * @param 	계좌이름
 * return 배열
 */
AccountManager.prototype.search = function(accountOwner){
	var output = []
	for (key in this.accounts) {
		if (this.accounts[key].getAccountOwner() == accountOwner) {
			output.push(this.accounts[key]);
		}
	}
	return output;
}
/**
 * 계좌번호 삭제하기
 * @param 	계좌번호
 * return 성공여부
 */
AccountManager.prototype.remove = function(accountNum){
	for (key in this.accounts) {
		if (this.accounts[key].getAccountNum() == accountNum) {
			this.accounts.splice(key, 1);
			return true;
		}
	}
	return false;
}
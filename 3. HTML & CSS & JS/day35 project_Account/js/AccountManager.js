function AccountManager(){
	this.accounts=[];
}

/**
 * @return 가지고 있는 모든 Account객체들의 집합
 */
AccountManager.prototype.getAccounts = function() {
	return this.accounts;
}

AccountManager.prototype.add = function(account){
	for (key in this.accounts) {
		if (this.accounts[key].getAccountNum() == account.getAccountNum()) {
			return false;
		}
	}
	this.accounts.push(account);
	return true;
}
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
AccountManager.prototype.search = function(accountOwner){
	var output = []
	for (key in this.accounts) {
		if (this.accounts[key].getAccountOwner() == accountOwner) {
			output.push(this.accounts[key]);
		}
	}
	return output;
}
AccountManager.prototype.remove = function(accountNum){
	for (key in this.accounts) {
		if (this.accounts[key].getAccountNum() == accountNum) {
			this.accounts.splice(key, 1);
			return true;
		}
	}
	return false;
}
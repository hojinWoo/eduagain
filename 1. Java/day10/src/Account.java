/**
 * Definition of a modeling class for everyday abstracting objects Design and
 * analysis ex) bank account
 */
class Account {
	// static init block
	static {
//    System.out.println("Start static init block");

	}

	// Class(Static) variable
	public final static String bankName = "hana";

	// instance variable
	private String accountNum;
	private String accountOwner;
	private int passwd;
	private long restMoney;

	// default Constructor
	public Account() {
		this(null, null);
	}

	// Constructor Overloading
	public Account(String accountNum, String accountOwner) {
		this(accountNum, accountOwner, 0, 0);
	}

	// Constructor
	public Account(String accountNum, String accountOwner, int passwd, long restMoney) {
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
		this.passwd = passwd;
		this.restMoney = restMoney;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public int getPasswd() {
		return passwd;
	}

	public void setPasswd(int passwd) {
		this.passwd = passwd;
	}

	public void setRestMoney(long restMoney) {
		this.restMoney = restMoney;
	}

	// instance method //example
	public long deposit(long money) throws AccountException {
		if (money <= 0) {
			throw new AccountException("입금하고자 하는 금액은 음수일 수 없습니다.", -3);
		}
		restMoney += money;
		return restMoney;
	}

	public long withdraw(long money) throws AccountException  {
		if (money <= 0) {
			throw new AccountException("출금하고자 하는 금액은 음수일 수 없습니다.", -1);
		}
		if (money > restMoney) {
			throw new AccountException("잔액이 부족합니다.", -2);
		}
		restMoney -= money; // Not check validate this
		return restMoney;
	}

	public long getRestMoney() {
		return restMoney;
	}

	public boolean checkPasswd(int passwd) {
		return this.passwd == passwd;
	}

	public String toString() {
		return getAccountNum() + " " + getAccountOwner() + " **** " + getRestMoney();
	}

	// class(static) method
	public static int sum(int a, int b) {
		return a + b;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if (obj instanceof Account) {
			flag = toString().equals(obj.toString());
		}
		return flag;
	}


	@Override
	public int hashCode() {	
		return Integer.parseInt(accountNum);
//		return 1; //이렇게만 해도 가능해진다. 
		//Object의 hash알고리즘과 관련.
		//memory의 주소를 기반으로 hashCode를 만든다.
	}	
}

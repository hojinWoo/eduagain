package kr.or.kosta.jsp;
/*
 * JavaBean
 */
public class Account {
	public static final String BANK_NAME = "Hana Bank";
	private String accountNum;
	private String accountOwner;
	private int passwd;
	private long restMoney;
	public Account(){
		this(null, null);
	}

	public Account(String accountNum, String accountOwner){
		this(accountNum, accountOwner, 1111);
	}

	public Account(String accountNum, String accountOwner, int passwd){
		this(accountNum, accountOwner, passwd, 0);

	}

	public Account(String accountNum, String accountOwner, int passwd, long restMoney){
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
		this.passwd = passwd;
		this.restMoney = restMoney;
	}

	public String getAccountNum(){
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

	// 인스턴스 메소드
	public long deposit(long money){
		return restMoney += money;
	}

	public long withdraw(long money){
		return restMoney -= money;
	}

	public long getRestMoney(){
		return restMoney;
	}

	public boolean checkPasswd(int pw){
		return passwd == pw;
	}

	@Override
	public String toString() {
		return  String.format("%-9s", "입출금계좌") + String.format("%-17s", accountNum) + String.format("%-6s", accountOwner) + String.format("%-7s", "*****")  + String.format("%,14d원", restMoney);
	}
  
	@Override
	public boolean equals(Object obj) {
		return toString().equals(obj.toString());
	}
}

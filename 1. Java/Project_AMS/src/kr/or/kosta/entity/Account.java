package kr.or.kosta.entity;
/**
 * 
 * @author hojin
 *
 */
public class Account {
	// Class(Static) variable
	public final static String bankName = "KOSTA 은행";

	private String accountNum;	 //계좌번호
	private String accountOwner; //계좌주인
	private int passwd;			 //비밀번호
	private long restMoney;		 //잔액
	
	/**
	 * Default Constructor
	 */
	public Account() {
		this(null, null);
	}

	/**
	 * Constructor
	 * @param accountNum
	 * @param accountOwner
	 */
	public Account(String accountNum, String accountOwner) {
		this(accountNum, accountOwner, 0, 0);
	}

	/**
	 * Constructor
	 * @param accountNum
	 * @param accountOwner
	 * @param passwd
	 * @param restMoney
	 */
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

	/**
	 * 돈 입금하기
	 * @param money
	 * @return
	 * @throws AccountException
	 */
	public long deposit(long money) throws AccountException {
		if (money <= 0) {
			throw new AccountException("입금하고자 하는 금액은 음수일 수 없습니다.", -3);
		}
		restMoney += money;
		return restMoney;
	}

	/**
	 * 돈 출금하기
	 * @param money
	 * @return
	 * @throws AccountException
	 */
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

	/**
	 * 잔액 조회
	 * @return
	 */
	public long getRestMoney() {
		return restMoney;
	}

	/**
	 * 비밀번호 확인하기
	 * @param passwd
	 * @return
	 */
	public boolean checkPasswd(int passwd) {
		return this.passwd == passwd;
	}

	/* (non-Javadoc)
	 * 은행의 것을 String으로 return하기 위해 재사용
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "입출금\t"+getAccountNum() + "\t" + getAccountOwner() + "\t" + getRestMoney();
	}
	

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if (obj instanceof Account) {
			flag = toString().equals(obj.toString());
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {	
		return Integer.parseInt(accountNum);
//		return 1; //이렇게만 해도 가능해진다. 
		//Object의 hash알고리즘과 관련.
		//memory의 주소를 기반으로 hashCode를 만든다.
	}	
}

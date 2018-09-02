package kr.or.kosta.entity;
/**
 * Account 계좌 객체
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
	 * Account Constructor
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

	/**
	 * @return 계좌번호
	 */
	public String getAccountNum() {
		return accountNum;
	}

	/**
	 * @param accountNum 계좌번호 설정
	 */
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	/**
	 * @return 계좌주인 
	 */
	public String getAccountOwner() {
		return accountOwner;
	}

	/**
	 * @param accountOwner	계좌주인 설정
	 */
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	/**
	 * @return	비밀번호
	 */
	public int getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd	비밀번호 설정
	 */
	public void setPasswd(int passwd) {
		this.passwd = passwd;
	}

	/**
	 * @param restMoney 잔액 설정하기
	 */
	public void setRestMoney(long restMoney) {
		this.restMoney = restMoney;
	}

	/**
	 * 돈 입금하기
	 * @param money	입금하는 금액
	 * @return		총 금액
	 * @throws AccountException
	 */
	public long deposit(long money) throws AccountException {
		//혹시 음수가 들어온 경우 exception 처리 (이번 프로젝트에는 음수(-)자체를 사전에 입력할 수 없다)
		if (money <= 0) {
			throw new AccountException("입금하고자 하는 금액은 음수일 수 없습니다.", -3);
		}
		restMoney += money;
		return restMoney;
	}

	/**
	 * 돈 출금하기
	 * @param money	출금하고자 하는 금액
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
	 * @return	잔액
	 */
	public long getRestMoney() {
		return restMoney;
	}

	/**
	 * 비밀번호 확인하기
	 * @param passwd	비밀번호
	 * @return	기존 비밀번호 일치 여부
	 */
	public boolean checkPasswd(int passwd) {
		return this.passwd == passwd;
	}

	/* (non-Javadoc)
	 * 은행의 것을 String으로 return하기 위해 재사용
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		//출력 형식 맞추기
		return String.format("입출금\t%-20s%-10s%,15d", getAccountNum(), getAccountOwner(), getRestMoney());
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
	}	
}
package kr.or.kosta.entity;
/**
 * Account를 상속받은 마이너스 계좌 객체
 * @author hojin
 *
 */
public class MinusAccount extends Account{

	private long borrowMoney; //대출금액

	/**
	 * Default constructor
	 */
	public MinusAccount() {
		super();
	}

	/**
	 * @param accountNum
	 * @param accountOwner
	 */
	public MinusAccount(String accountNum, String accountOwner) {
		super(accountNum, accountOwner);
	}

	/**
	 * MinusAccount Constructor
	 * @param accountNum
	 * @param accountOwner
	 * @param passwd
	 * @param restMoney
	 * @param borrowMoney
	 */
	public MinusAccount(String accountNum, String accountOwner, int passwd, long restMoney, long borrowMoney) {
		super(accountNum, accountOwner, passwd, restMoney);
		this.borrowMoney = borrowMoney;
	}
	
	/**
	 * @return 대출금액
	 */
	public long getBorrowMoney() {
		return borrowMoney;
	}

	/**
	 * 대출금액 설정하기
	 * @param borrowMoney 대출금액
	 */
	public void setBorrowMoney(long borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	/* (non-Javadoc)
	 * @see kr.or.kosta.entity.Account#deposit(long)
	 * 추가로 금액을 인출한 경우
	 */
	@Override
	public long deposit(long money) {
		super.setRestMoney(super.getRestMoney()+money);
		return super.getRestMoney();
	}

	/* (non-Javadoc)
	 * @see kr.or.kosta.entity.Account#getRestMoney()
	 * 남은 금액에서 현재 빌린 금액의 차액
	 */
	@Override
	public long getRestMoney() {
		return super.getRestMoney()-getBorrowMoney();
	}
	
	@Override
	public String toString() {
		return String.format("마이너스\t%-20s%-10s%,15d%,15d", getAccountNum(), getAccountOwner(), getRestMoney(), borrowMoney);
	}
}
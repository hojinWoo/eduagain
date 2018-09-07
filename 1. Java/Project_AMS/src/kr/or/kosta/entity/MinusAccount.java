package kr.or.kosta.entity;
/**
 * Account�� ��ӹ��� ���̳ʽ� ���� ��ü
 * @author hojin
 *
 */
public class MinusAccount extends Account{

	private long borrowMoney; //����ݾ�

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
	 * @return ����ݾ�
	 */
	public long getBorrowMoney() {
		return borrowMoney;
	}

	/**
	 * ����ݾ� �����ϱ�
	 * @param borrowMoney ����ݾ�
	 */
	public void setBorrowMoney(long borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	/* (non-Javadoc)
	 * @see kr.or.kosta.entity.Account#deposit(long)
	 * �߰��� �ݾ��� ������ ���
	 */
	@Override
	public long deposit(long money) {
		super.setRestMoney(super.getRestMoney()+money);
		return super.getRestMoney();
	}

	/* (non-Javadoc)
	 * @see kr.or.kosta.entity.Account#getRestMoney()
	 * ���� �ݾ׿��� ���� ���� �ݾ��� ����
	 */
	@Override
	public long getRestMoney() {
		return super.getRestMoney()-getBorrowMoney();
	}
	
	@Override
	public String toString() {
		return String.format("���̳ʽ�\t%-20s%-10s%,15d%,15d", getAccountNum(), getAccountOwner(), getRestMoney(), borrowMoney);
	}
}
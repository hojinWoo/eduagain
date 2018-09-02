package kr.or.kosta.entity;
/**
 * 
 * @author hojin
 *
 */
public class Account {
	// Class(Static) variable
	public final static String bankName = "KOSTA ����";

	private String accountNum;	 //���¹�ȣ
	private String accountOwner; //��������
	private int passwd;			 //��й�ȣ
	private long restMoney;		 //�ܾ�
	
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
	 * �� �Ա��ϱ�
	 * @param money
	 * @return
	 * @throws AccountException
	 */
	public long deposit(long money) throws AccountException {
		if (money <= 0) {
			throw new AccountException("�Ա��ϰ��� �ϴ� �ݾ��� ������ �� �����ϴ�.", -3);
		}
		restMoney += money;
		return restMoney;
	}

	/**
	 * �� ����ϱ�
	 * @param money
	 * @return
	 * @throws AccountException
	 */
	public long withdraw(long money) throws AccountException  {
		if (money <= 0) {
			throw new AccountException("����ϰ��� �ϴ� �ݾ��� ������ �� �����ϴ�.", -1);
		}
		if (money > restMoney) {
			throw new AccountException("�ܾ��� �����մϴ�.", -2);
		}
		restMoney -= money; // Not check validate this
		return restMoney;
	}

	/**
	 * �ܾ� ��ȸ
	 * @return
	 */
	public long getRestMoney() {
		return restMoney;
	}

	/**
	 * ��й�ȣ Ȯ���ϱ�
	 * @param passwd
	 * @return
	 */
	public boolean checkPasswd(int passwd) {
		return this.passwd == passwd;
	}

	/* (non-Javadoc)
	 * ������ ���� String���� return�ϱ� ���� ����
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format("�����\t%-20s%-10s%,15d", getAccountNum(), getAccountOwner(), getRestMoney());
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
//		return 1; //�̷��Ը� �ص� ����������. 
		//Object�� hash�˰���� ����.
		//memory�� �ּҸ� ������� hashCode�� �����.
	}	
}
/**
 * Account를 확장한 마이너스 계좌
 * @author hojin
 *
 */
public class MinusAccount extends Account{

	private long borrowMoney;

	public MinusAccount() {
		super();
	}

	public MinusAccount(String accountNum, String accountOwner) {
		super(accountNum, accountOwner);
	}

	public MinusAccount(String accountNum, String accountOwner, int passwd, long restMoney, long borrowMoney) {
		super(accountNum, accountOwner, passwd, restMoney);
		this.borrowMoney = borrowMoney;
	}
	
	public long getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(long borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	@Override
	public long deposit(long money) {
		super.setRestMoney(super.getRestMoney()+money);
		return super.getRestMoney();
	}

	@Override
	public long getRestMoney() {
		return super.getRestMoney()-getBorrowMoney();
	}
	
	public static void main(String[] args) {
		MinusAccount minusAccount = new MinusAccount();
		System.out.println(minusAccount.getBorrowMoney());
		
		MinusAccount minusAccount2 = new MinusAccount("1231-1234-2222", "minus", 4321, 0, 1000000);
		minusAccount2.deposit(100000);
		System.out.println(minusAccount2.getRestMoney());
		
	}
}

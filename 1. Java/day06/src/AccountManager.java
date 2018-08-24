/**
 * 배열을 이용한 은행계좌 관리
 * @author hojin
 *
 */
public class AccountManager {
	
	private Account[] accounts;
	private int count;
	
	public AccountManager() {
		this(0);
	}
	public AccountManager(int size) {
		accounts = new Account[size];
		count = -1;
	}
	
	public Account[] getAccounts() {
		return accounts;
	}
	public void setAccounts(Account[] accounts) {
		this.accounts = accounts;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * Account 객체를 accounts 배열에 순서대로 넣는다.
	 * @param account Account 객체
	 */
	public void add(Account account) {
		accounts[++count] = account;
	}
	
	/** 실제 들어있는 개수의 크기만큼만 Account 객체 반환
	 * @return 실제 null이 아닌 Account들의 배열
	 */
	public Account[] list() {
		Account[] acc = new Account[count+1];
		for (int i = 0; i < (count+1); i++) {
			acc[i] = accounts[i];
		}
		return acc;
	}
	public Account get(String accountNum) {
		for (int i = 0; i < (count+1); i++) {
			if(accountNum.equals(accounts[i].getAccountNum())) {
				return accounts[i];
			}
		}
		return null;
	}
	public Account[] search(String name) {
		Account[] acc = new Account[count+1];
		int tmp = 0;
		for (int i = 0; i < (count+1); i++) {
			if (name.equals(accounts[i].getAccountOwner())) {
				acc[tmp++] = accounts[i];
			}
		}
		Account[] acc2 = new Account[tmp];
		for (int i = 0; i < tmp; i++) {
			acc2[i] = acc[i];
		}
		return acc2;
	}
	public boolean remove(String accountNum) {
		for (int i = 0; i < (count+1); i++) {
			if(accountNum.equals(accounts[i].getAccountNum())) {
				for (int j = i; j < count; j++) {
					accounts[j] = accounts[j+1];
				}
				break;
			}
		}
		count--;
		return false;
	}
}

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * Vector을 이용한 효율적인 은행계좌 관리
 * @author hojin
 *
 */
public class AccountManager {
	
	private Vector<Account> accounts;
	
	public AccountManager() {
		this(0);
	}
	public AccountManager(int capacity) {
		accounts = new Vector<Account>(capacity, 5); //5개씩 늘어나는 것으로 설정
	}
	
	public Vector getAccounts() {
		return accounts;
	}
	public void setAccounts(Vector<Account> accounts) {
		this.accounts = accounts;
	}
	
	/**
	 * Account 객체를 accounts 배열에 순서대로 넣는다.
	 * @param account Account 객체
	 */
	public void add(Account account) {
		accounts.addElement(account);
	}
	
	public List list() {
//		return accounts;
		List list = new ArrayList(accounts.size());
		Enumeration e = accounts.elements();
		while (e.hasMoreElements()) {
			Object account = e.nextElement();
			list.add(account);
		}
		return list;
		
	}
	public Account get(String accountNum) {
		//accounts.contains(object); //전체비교가 아니기 때문에 사용X
		Enumeration e = accounts.elements();
		while (e.hasMoreElements()) {
			Account account = (Account) e.nextElement();
			boolean eq = account.getAccountNum().equals(accountNum);
			if(eq) {
				return account;
			}
		}
		return null;
	}
	public List search(String name) {
		List list = new ArrayList();
		Enumeration e = accounts.elements();
		while (e.hasMoreElements()) {
			Account account = (Account) e.nextElement();
			if(account.getAccountOwner().equals(name)) {
				list.add(account);
			}
		}
		return list;
	}
	public boolean remove(String accountNum) {
		Enumeration e = accounts.elements();
		while (e.hasMoreElements()) {
			Account account = (Account) e.nextElement();
			if(account.getAccountNum().equals(accountNum)) {
				return accounts.removeElement(account);
			}
		}
		return false;
	}
}

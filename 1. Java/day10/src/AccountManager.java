import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * Hashtable을 이용한 효율적인 은행계좌 관리
 * @author hojin
 *
 */
public class AccountManager {
	
	private Hashtable<String, Account> accounts;
	
	public AccountManager() {
		this(0);
	}
	public AccountManager(int capacity) {
		accounts = new Hashtable<String, Account>();
	}
	
	public Hashtable<String, Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Hashtable<String, Account> accounts) {
		this.accounts = accounts;
	}

	public void add(Account account) {
		//중복체크
		if(accounts.contains(account)) {
			System.out.println("이미 계좌가 존재합니다..");
			return;
		}
		accounts.put(account.getAccountNum(), account);
	}
	
	public Enumeration<Account> list() {
		return accounts.elements();
		
	}
	public Account get(String accountNum) {
		return accounts.get(accountNum);
	}
	public List search(String name) {
		List<Account> list = new ArrayList<>();
		Enumeration<Account> e = accounts.elements();
		while(e.hasMoreElements()) {
			Account ac = e.nextElement();
			if(ac.getAccountOwner().equals(name)) {
				list.add(ac);
			}
		}
		//계좌번호 기준 정렬
		Collections.sort(list, new NumberComparator());
		return list;
	}
	public boolean remove(String accountNum) {
		return accounts.remove(accountNum) != null;
		
	}
}

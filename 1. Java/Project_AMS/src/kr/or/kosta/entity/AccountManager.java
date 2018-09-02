package kr.or.kosta.entity;
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

	public boolean add(Account account) {
		//계좌번호 중복체크
		if(accounts.containsKey(account.getAccountNum())) {
			return false;
		}else{
			accounts.put(account.getAccountNum(), account);
			return true;
		}
	}
	
	public Enumeration<Account> list() {
		return accounts.elements();
		
	}
	
	public List list(int num) {
		List<Account> list = new ArrayList<>();
		Enumeration<Account> e = accounts.elements();
		
		if(num==0) {
			while(e.hasMoreElements()) {
				Account ac = e.nextElement();
				list.add(ac);
			}
		}else if(num == 1) {
			//입출금 계좌
			while(e.hasMoreElements()) {
				Account ac = e.nextElement();
				if(!(ac instanceof MinusAccount)) {
					list.add(ac);
				}
			}
		}else {
			//출금 계좌
			while(e.hasMoreElements()) {
				Account ac = e.nextElement();
				if(ac instanceof MinusAccount) {
					list.add(ac);
				}
			}
			
		}
		Collections.sort(list, new NumberComparator());
		return list;
			
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
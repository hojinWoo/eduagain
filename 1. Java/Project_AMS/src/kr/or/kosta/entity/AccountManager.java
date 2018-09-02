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
	
	/**
	 * Hashtable을 이용하여 Account 객체들을 관리
	 */
	private Hashtable<String, Account> accounts;
	
	/**
	 * Default constructor
	 */
	public AccountManager() {
		this(0);
	}
	/**
	 * @param capacity
	 */
	public AccountManager(int capacity) {
		accounts = new Hashtable<String, Account>();
	}
	
	/**
	 * @return 가지고 있는 모든 Account객체들의 집합인 Hashtable
	 */
	public Hashtable<String, Account> getAccounts() {
		return accounts;
	}
	/**
	 * @param accounts Account객체들의 집합을 설정
	 */
	public void setAccounts(Hashtable<String, Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @param account 추가하고자 하는 Account 객체
	 * @return	중복체크를 통해 계좌 등록 성공 여부
	 */
	public boolean add(Account account) {
		//계좌번호 중복체크
		if(accounts.containsKey(account.getAccountNum())) {
			return false;
		}else{
			accounts.put(account.getAccountNum(), account);
			return true;
		}
	}
	
	
	/**
	 * @return	현재 Account 객체들을 List로 return
	 */
	public Enumeration<Account> list() {
		return accounts.elements();
		
	}
	
	/**
	 * Override
	 * @param num	0:전체 계좌, 1:입출금 계좌, 2:마이너스 계좌
	 * @return	각각의 맞는 계좌를 return
	 */
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
		//계좌번호에 맞는 오름차순 정렬 후 return
		Collections.sort(list, new NumberComparator());
		return list;
			
	}
	
	/**
	 * @param accountNum	계좌번호
	 * @return	일치하는 계좌번호를 가진 Account 객체 리턴
	 */
	public Account get(String accountNum) {
		return accounts.get(accountNum);
	}
	/**
	 * @param name	계좌주인
	 * @return	일치하는 계좌주인를 가진 Account 객체들을 List로 리턴
	 */
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
	
	/**
	 * @param accountNum	계좌번호
	 * @return	일치하는 계좌주인를 가진 Account 객체의 삭제 여부
	 */
	public boolean remove(String accountNum) {
		return accounts.remove(accountNum) != null;
		
	}
}
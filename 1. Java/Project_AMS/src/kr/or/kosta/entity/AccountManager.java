package kr.or.kosta.entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * Hashtable�� �̿��� ȿ������ ������� ����
 * @author hojin
 *
 */
public class AccountManager {
	
	/**
	 * Hashtable�� �̿��Ͽ� Account ��ü���� ����
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
	 * @return ������ �ִ� ��� Account��ü���� ������ Hashtable
	 */
	public Hashtable<String, Account> getAccounts() {
		return accounts;
	}
	/**
	 * @param accounts Account��ü���� ������ ����
	 */
	public void setAccounts(Hashtable<String, Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @param account �߰��ϰ��� �ϴ� Account ��ü
	 * @return	�ߺ�üũ�� ���� ���� ��� ���� ����
	 */
	public boolean add(Account account) {
		//���¹�ȣ �ߺ�üũ
		if(accounts.containsKey(account.getAccountNum())) {
			return false;
		}else{
			accounts.put(account.getAccountNum(), account);
			return true;
		}
	}
	
	
	/**
	 * @return	���� Account ��ü���� List�� return
	 */
	public Enumeration<Account> list() {
		return accounts.elements();
		
	}
	
	/**
	 * Override
	 * @param num	0:��ü ����, 1:����� ����, 2:���̳ʽ� ����
	 * @return	������ �´� ���¸� return
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
			//����� ����
			while(e.hasMoreElements()) {
				Account ac = e.nextElement();
				if(!(ac instanceof MinusAccount)) {
					list.add(ac);
				}
			}
		}else {
			//��� ����
			while(e.hasMoreElements()) {
				Account ac = e.nextElement();
				if(ac instanceof MinusAccount) {
					list.add(ac);
				}
			}
			
		}
		//���¹�ȣ�� �´� �������� ���� �� return
		Collections.sort(list, new NumberComparator());
		return list;
			
	}
	
	/**
	 * @param accountNum	���¹�ȣ
	 * @return	��ġ�ϴ� ���¹�ȣ�� ���� Account ��ü ����
	 */
	public Account get(String accountNum) {
		return accounts.get(accountNum);
	}
	/**
	 * @param name	��������
	 * @return	��ġ�ϴ� �������θ� ���� Account ��ü���� List�� ����
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
		//���¹�ȣ ���� ����
		Collections.sort(list, new NumberComparator());
		return list;
	}
	
	/**
	 * @param accountNum	���¹�ȣ
	 * @return	��ġ�ϴ� �������θ� ���� Account ��ü�� ���� ����
	 */
	public boolean remove(String accountNum) {
		return accounts.remove(accountNum) != null;
		
	}
}
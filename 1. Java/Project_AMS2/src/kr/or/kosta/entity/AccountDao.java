package kr.or.kosta.entity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * 파일을 이용한 은행계좌 관리
 * @author hojin
 *
 */
public class AccountDao {
	
	private static final String FILE_PATH = "AccountTest.txt";
	
	private RandomAccessFile file;

	//저장된 Account의 수
	private int recordCount;
	
	/**
	 * Default Constructor
	 * @throws IOException
	 */
	public AccountDao() throws IOException{
		//파일 읽기
		file = new RandomAccessFile(FILE_PATH, "rw");
		
		//파일 안에 있는 Account객체의 수 읽기
		if (file.length() != 0) {
			recordCount = file.readInt();
		}
	}
	
	/**
	 * @return 저장된 Account의 수
	 */
	public int getRecordCount() {
		return recordCount;
	}
	
	public void add(Account account) throws IOException {
		file.seek(recordCount);
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
		return null;
		
	}
}

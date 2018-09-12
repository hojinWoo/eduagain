package kr.or.kosta.entity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * 파일을 이용한 은행계좌 관리
 * 
 * @author hojin
 *
 */
public class AccountDao {

	// 저장 파일 경로
	private static final String FILE_PATH = "AccountTest.txt";

	private RandomAccessFile file;

	// 레코드 수 저장을 위한 파일 컬럼 사이즈 고정
	private static final int RECORD_COUNT_LENGTH = 4;

	// 레코드(계좌번호, 예금주명, 비밀번호, 입금금액, 대출금액) 저장을 위한 컬럼 사이즈 고정
	private static final int ACCOUNT_LENGTH = 30; // 계좌번호(30바이트)
	private static final int OWNER_LENGTH = 20; // 예금주명(20바이트)
	private static final int PASSWD_LENGTH = 4; // 비밀번호(4바이트)
	private static final int DEPOSIT_LENGTH = 8; // 입금금액(8바이트)
	private static final int LOAN_LENGTH = 8; // 대출금액(8바이트)

	// 계좌정보 저장을 위한 레코드 사이즈 : 70바이트(총 합)
	public static final int RECORD_LENGTH = ACCOUNT_LENGTH + OWNER_LENGTH + PASSWD_LENGTH + DEPOSIT_LENGTH
			+ LOAN_LENGTH;

	// 저장된 Account의 수
	private int recordCount;

	/**
	 * Default Constructor
	 * 
	 * @throws IOException
	 */
	public AccountDao() throws IOException {
		// 파일 읽기
		file = new RandomAccessFile(FILE_PATH, "rw");

		// 파일 안에 있는 Account객체의 수 읽기
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

	public void add(Account account) throws IOException, AccountException {
		// 파일의 마지막 레코드 끝으로 파일 포인터 이동
		file.seek((recordCount * RECORD_LENGTH) + RECORD_COUNT_LENGTH);

		// 새로운 레코드(계좌) 추가
		// 30 + 20 + 4 + 8 + 8
		String accountNum = account.getAccountNum();
		List<Account> list = list();
		for (Account account2 : list) {
			if (account2.getAccountNum().equals(account.getAccountNum())) {
				JOptionPane.showMessageDialog(null, "이미 등록된 계좌입니다.", "경고", JOptionPane.ERROR_MESSAGE);
				throw new AccountException("이미 등록된 계좌입니다.", -100);
			}
		}

		String acacountOwner = account.getAccountOwner();
		int passwd = account.getPasswd();
		long deposit = account.getRestMoney();
		if (account instanceof MinusAccount) {
			MinusAccount minusAccount = (MinusAccount) account;
			deposit = minusAccount.getRestMoney();
		}
		long loan = 0;
		if (account instanceof MinusAccount) {
			MinusAccount minusAccount = (MinusAccount) account;
			loan = minusAccount.getBorrowMoney();
		}

		int charCount = accountNum.length();
		// 30바이트로 계좌번호 저장
		for (int i = 0; i < (ACCOUNT_LENGTH / 2); i++) {
			file.writeChar((i < charCount ? accountNum.charAt(i) : ' '));
		}

		charCount = acacountOwner.length();
		// 20바이트로 이름 저장
		for (int i = 0; i < (OWNER_LENGTH / 2); i++) {
			file.writeChar((i < charCount ? acacountOwner.charAt(i) : ' '));
		}

		// 4바이트로 비밀번호 저장
		file.writeInt(passwd);

		// 8바이트로 예금금액 저장
		file.writeLong(deposit);

		// 8바이트로 대출금액 저장
		file.writeLong(loan);

		// 레코드 저장 후 파일포인터를 파일의 처음으로 이동시켜
		// 등록된 계좌 수 증가시키기
		file.seek(0);
		file.writeInt(++recordCount);
	}

	public List<Account> list() throws IOException {
		List<Account> list = new ArrayList<Account>();
		for (int i = 0; i < recordCount; i++) {
			file.seek((i * RECORD_LENGTH) + RECORD_COUNT_LENGTH);
			Account account = read(i);
			list.add(account);
		}
		return list;

	}

//	public List list(int num) {
//		List<Account> list = new ArrayList<>();
//		Enumeration<Account> e = accounts.elements();
//		
//		if(num==0) {
//			while(e.hasMoreElements()) {
//				Account ac = e.nextElement();
//				list.add(ac);
//			}
//		}else if(num == 1) {
//			//입출금 계좌
//			while(e.hasMoreElements()) {
//				Account ac = e.nextElement();
//				if(!(ac instanceof MinusAccount)) {
//					list.add(ac);
//				}
//			}
//		}else {
//			//출금 계좌
//			while(e.hasMoreElements()) {
//				Account ac = e.nextElement();
//				if(ac instanceof MinusAccount) {
//					list.add(ac);
//				}
//			}
//			
//		}
//		Collections.sort(list, new NumberComparator());
//		return list;
//			
//	}
	
	private Account read(int index) throws IOException {
		Account account = null;

		String accountNum = "";
		file.seek((index * RECORD_LENGTH) + RECORD_COUNT_LENGTH);
		for (int i = 0; i < (ACCOUNT_LENGTH / 2); i++) {
			accountNum += file.readChar();
		}
		accountNum = accountNum.trim();

		String accountOwner = "";
		for (int i = 0; i < (OWNER_LENGTH / 2); i++) {
			accountOwner += file.readChar();
		}
		accountOwner = accountOwner.trim();

		int passwd = 0;
		passwd = file.readInt();

		long deposit = 0;
		deposit = file.readLong();

		long loan = 0;
		loan = file.readLong();

		if (loan != 0) {
			account = new MinusAccount(accountNum, accountOwner, passwd, deposit, loan);
		} else {
			account = new Account(accountNum, accountOwner, passwd, deposit);
		}
		return account;
	}


	public Account get(String accountNum) throws AccountException, IOException {
		List<Account> list = list();
		boolean flag = false;
		for (Account account : list) {
			if (accountNum.equals(account.getAccountNum())) {
				flag = true;
				return account;
			}
		}

		if (accountNum.length() < 1) {

			JOptionPane.showMessageDialog(null, "계좌번호를 입력해주세요.", "경고", JOptionPane.ERROR_MESSAGE);
			throw new AccountException("계좌번호를 입력해주세요.", -300);

		} else if (!flag && accountNum.length() > 0) {

			JOptionPane.showMessageDialog(null, "해당 계좌가 존재하지 않습니다.", "경고", JOptionPane.ERROR_MESSAGE);
			throw new AccountException("존재하지 않는 계좌입니다.", -200);

		}

		return null;

	}

	public boolean remove(String accountNum) throws AccountException, IOException {
		// 삭제할 계좌의 index 찾기
		int findIdx = 0;
		int number = 0;
		List<Account> list = list();
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			Account account = read(i);
			if (account.getAccountNum().equals(accountNum)) {
				findIdx = i;
				flag = true;
				break;
			}
			number++;

		}

		if (flag) {
			for (int i = findIdx; i < recordCount - 1; i++) {
				Account account = read(i + 1);
				file.seek((i * RECORD_LENGTH) + RECORD_COUNT_LENGTH);

				int charCount = account.getAccountNum().length();
				for (int j = 0; j < (ACCOUNT_LENGTH / 2); j++) {
					file.writeChar((j < charCount ? account.getAccountNum().charAt(j) : ' '));
				}

				charCount = account.getAccountOwner().length();
				for (int j = 0; j < (OWNER_LENGTH / 2); j++) {
					file.writeChar((j < charCount ? account.getAccountOwner().charAt(j) : ' '));
				}

				file.writeInt(account.getPasswd());

				if (account instanceof MinusAccount) {
					MinusAccount minusAccount = (MinusAccount) account;
					file.writeLong(minusAccount.getRestMoney());
				} else {
					file.writeLong(account.getRestMoney());
				}

				if (account instanceof MinusAccount) {
					MinusAccount minusAccount = (MinusAccount) account;
					file.writeLong(minusAccount.getBorrowMoney());
				} else {
					file.writeLong(0);
				}
			}
			// 레코드 저장 후 파일포인터를 파일의 처음으로 이동시켜
			// 등록된 계좌 수 감소시키기
			file.seek(0);
			file.writeInt(--recordCount);

		}

		// 계좌 유효성
		if (accountNum.length() < 1) {
			JOptionPane.showMessageDialog(null, "계좌번호를 입력해주세요.", "경고", JOptionPane.ERROR_MESSAGE);
			throw new AccountException("계좌번호를 입력해주세요.", -300);
		} else if (number == list.size() && accountNum.length() > 0) {
			JOptionPane.showMessageDialog(null, "해당 계좌가 존재하지 않습니다.", "경고", JOptionPane.ERROR_MESSAGE);
			throw new AccountException("존재하지 않는 계좌입니다.", -200);
		}

		return flag;

	}

	// 스트림 닫기
	public void close() {
		try {
			if (file != null)
				file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

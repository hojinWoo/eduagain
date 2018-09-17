/**
 * 은행 계좌 관리 애플리케이션
 * @author hojin
 *
 */
public class AMS {

	public static void main(String[] args) {
		AccountManager manager = new AccountManager(100);
		//객체 생성
		manager.add(new Account("111-222-333", "우호진", 1111, 100000));
		manager.add(new Account("333-222-666", "박지성", 1112, 400000));
		manager.add(new Account("444-777-111", "김연아", 1113, 300000));
		manager.add(new Account("666-444-222", "손흥민", 1114, 200000));
		manager.add(new Account("446-224-123", "박지성", 1115, 100000));
		
		//up casting
		manager.add(new MinusAccount("999-999-222", "대출이", 1231, 10, 1000000));
		manager.add(new MinusAccount("999-999-223", "대출삼", 1232, 10, 1202323));
		manager.add(new Account("446-234-125", "박지성", 1116, 200000));
		
		Account[] list = manager.list();
		for (Account account : list) {
			if (account instanceof MinusAccount) {
				System.out.println("마이너스 계좌 : " + account.toString());
			}else {
				System.out.println("입출금 계좌    : " + account.toString());
			}
		}
	}
}

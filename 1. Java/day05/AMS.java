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
		
		//전체 계좌 출력
		System.out.println("\n전체 계좌 출력");
		Account[] accountArray = manager.list();
		
		for (Account account : accountArray) {
			System.out.println(account.toString());
		}
		//계좌 조회
		System.out.println("\n계좌 조회");
		System.out.println(manager.get("444-777-111").toString());
		
		//예금주 명 검색 후 출력
		System.out.println("\n예금주 명 검색 후 출력");
		accountArray = manager.search("박지성");
		for (Account account : accountArray) {
			System.out.println(account.toString());
		}
		
		//삭제
		System.out.println("\n삭제");
		manager.remove("666-444-222");
		accountArray = manager.list();
		
		for (Account account : accountArray) {
			System.out.println(account.toString());
		}
	}
}

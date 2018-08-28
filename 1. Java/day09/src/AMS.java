import java.util.List;

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
//		manager.add(new MinusAccount("999-999-222", "대출이", 1231, 10, 1000000));
//		manager.add(new MinusAccount("999-999-223", "대출삼", 1232, 10, 1202323));
		manager.add(new Account("446-234-125", "박지성", 1116, 200000));
		
		System.out.println("==============전체계좌목록==============");
		List list = manager.list();
		if(!list.isEmpty()) {
			for (Object object : list) {
				System.out.println(object); //toString 호출됨.
			}
		}else {
			System.out.println("계설된 계좌가 없습니다.");
		}
		System.out.println("==============계좌조회==============");
		Account acc = manager.get("111-222-333");
		if(acc!=null) {
			System.out.println(acc);
		}else {
			System.out.println("계좌가 존재하지 않습니다.");
		}
		
		System.out.println("==============계좌검색==============");
		List searchList = manager.search("박지성");
		if(!searchList.isEmpty()) {
			for (Object object : searchList) {
				System.out.println(object);
			}
		}else {
			System.out.println("계좌가 존재하지 않습니다.");
		}
	}
}

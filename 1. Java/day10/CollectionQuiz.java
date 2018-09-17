import java.util.HashSet;
import java.util.Set;

public class CollectionQuiz {
	public static void main(String[] args) {
		//Set : 순서없지만 중복 데이터 허용X
		//그렇기 때문에 넣기 전에 검색을 한다.
		
		Set<Account> set = new HashSet<>();
		Account ac1 = new Account("113", "ho", 1234,10000);
		Account ac2 = new Account("114", "hddo", 1234,10000);
		Account ac3 = new Account("113", "ho", 1234,10000);
		
		
		set.add(ac1);
		
		
		
		set.add(ac2);
		set.add(ac3);
		
		System.out.println(set.size()); // 원래라면 중복되기 때문에 2개가 나와야 하지만 3개가 들어간다..		
										// hint : Object에 힌트가 있다.
		///code 수정 필요					// Account에서 hashCode를 Override해서 만들어줘야 한다.
										// ****** HashCode가 같은 경우 2차로 equals를 호출 하게 된다!!!! *******
										// String의 경우 내용이 같은 경우 HashCode가 다 같게 나온다 (Override했기 때문)
										// 그러나, Object의 경우 무조건 HashCode가 다르다.
		String str1 = "hhh";
		String str2 = "ddd";
		String str3 = "hhh";
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		System.out.println(str3.hashCode());
	}
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionExample {
	public static void main(String[] args) {
		//Comparator<? super T> : 객체에는 기준이 없기 때문에 대소구분?이 되지 않는다. 그렇기 때문에 Interface를 통해 기준을 규격해야 한다. 
		//Generic이  특정 type을 했을 때 지정된 부모 객체를 전부 받을 수 있다(범위가 넓어짐)
		// ? : 모두
//		Collections.sort(List<T> list, Comparator<? super T> c);
		
		List<Account> list = new ArrayList<>();
		list.add(new Account("12312", "AAA", 1234, 10000));
		list.add(new Account("11144", "BBB", 4321, 20000));
		list.add(new Account("51234", "CCC", 1212, 40000));
		
		//정렬 기준 설정 (ex. 은행계좌, NumberComparator 객체 생성)
		Collections.sort(list, new NumberComparator());
		for (Account account : list) {
			System.out.println(account);
		}
		System.out.println();
		
		//정렬 기준설정(ex. 잔액 기준)
		Collections.sort(list, new MoneyComparator());
		for (Account account : list) {
			System.out.println(account);
		}
	}
}

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * List는 순서를 통해 데이터를 관리하기 위한 규약(인터페이스)이다.
 * Set과 달리 요소가 순차적으로 관리되며, 중복을 허용하는 특징을 가진다.
 * ArrayList, Vector, LinkedList가 List 인터페이스를 구현한 대표적 클래스이다
 * 
 * @author hojin
 *
 */
public class ListExample {
	public static void main(String[] args) {
		
		List list =  new ArrayList();
		
		list.add("손흥민");
		list.add("바나나");
		list.add(100); //Object obj = 1000;
		list.add(new Integer(100));
		list.add(Calendar.getInstance());
		list.add("황의조");
		
		Set boddari = new HashSet();
		boddari.add("AAAA");
		boddari.add("BBBB");
		boddari.add("CCCC");
		
		list.addAll(boddari);
		
		list.add(0, "hojin");
		System.out.println(list.get(0));
		System.out.println(list.remove(0));
		System.out.println(list.set(0, "박지성"));
		
		System.out.println(list.size());
		
		List l = list.subList(0, 3);
		
		for (Object object : l) {
			System.out.println(object);
		}
	}
}

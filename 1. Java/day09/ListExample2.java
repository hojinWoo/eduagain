import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * List는 순서를 통해 데이터를 관리하기 위한 규약(인터페이스)이다.
 * Set과 달리 요소가 순차적으로 관리되며, 중복을 허용하는 특징을 가진다.
 * ArrayList, Vector, LinkedList가 List 인터페이스를 구현한 대표적 클래스이다
 * Vector는 확장 for문을 사용하면 안 된다. (비동기식 처리이기 때문)
 * 
 * @author hojin
 *
 */
public class ListExample2 {
	public static void main(String[] args) {
		
		Vector list =  new Vector(); //default로 생성 시 인자가 2개
									//(size, ) 용량이 배수로 늘어남..
									//(size, count) count만큼 늘어남 (개수 늘어나는 것을 조절 가능)
		
		list.add("손흥민");
		list.add("바나나");
		list.add(100); //Object obj = 1000;
		list.add(new Integer(100));
		list.add(Calendar.getInstance());
		list.add("황의조");
		
		System.out.println("당겨진 갯수: "+list.size());
		System.out.println("비어있는지 여부: "+list.isEmpty());
		
		System.out.println(list.elementAt(0));
		System.out.println(list.removeElement("바나나"));
		
		//Vector는 비동기식 처리이므로 확장 for문을 사용할 수 없다(동기식)
		Enumeration e = list.elements();
		while(e.hasMoreElements()) {
			Object object = e.nextElement();
			System.out.println(object);
		}
		
		
	}
}

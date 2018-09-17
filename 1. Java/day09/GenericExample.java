import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenericExample {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("라이언");
		list.add("이홍기");
		list.add("류세은");
		
//		list.add(12331);
//		list.add(Calendar.getInstance());
		
		//Iterator도 generic으로 되어있다.
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			String string = (String) iter.next();
			System.out.println(string);
		}
		//String이기 때문에 foreach도 가능
		for (String string : list) {
			System.out.println(string);
		}
		
	}
}

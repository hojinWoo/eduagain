import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample {
	public static void main(String[] args) {
		Set<String> set = new TreeSet<String>();
		set.add("asd");
		set.add("123123");
		set.add("나얼");
		set.add("마모");
		set.add("12123");
		set.add("ㄴㅇㄹㄴㅇ");
		set.add("ㅁㅁㅁ");
		set.add("333");
		for (String string : set) {
			System.out.println(string);
		}
	}
}

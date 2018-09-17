import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapExample {
	public static void main(String[] args) {
		//HashMap
		Map<String, String> map = new HashMap<>();
		map.put("123123", "man");
		map.put("123456", "num");
		map.put("111111", "www");
		map.put("414141", "hos");
		map.put("555555", "eee");
		
		//막 저장하면 안된다. 저장하기 전 key가 있는 지 검색이 필요하다. 중복체크
		if(map.containsKey("111111")) {
			System.out.println("존재하는 키입니다");
		}else {
			map.put("111111", "AAA");
		}
		System.out.println(map.get("111111"));
		
		Set<String> keyList = map.keySet();
		for (String string : keyList) {
			System.out.println(string);
		}
		
		Collection<String> key = map.values();
		for (String string : key) {
			System.out.println(string);
		}
	}
}

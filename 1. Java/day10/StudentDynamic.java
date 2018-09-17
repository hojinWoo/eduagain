import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 동적 객체 정의
 * @author hojin
 *
 */
public class StudentDynamic {
	Map<String, Object> prop;

	public StudentDynamic() {
		prop = new HashMap<String, Object>();
	}

	public StudentDynamic(Map<String, Object> prop) {
		super();
		this.prop = prop;
	}
	
	public Map<String, Object> getProp() {
		return prop;
	}

	public void setProp(Map<String, Object> prop) {
		this.prop = prop;
	}
	
	public void setProperty(String key, Object value) {
		prop.put(key, value);
	}
	
	public Object getProperty(String key) {
		return prop.get(key);
	}
	
	public static void main(String[] args) {
		//동적 생성
		StudentDynamic student = new StudentDynamic();
		student.setProperty("ssn", "123123");
		student.setProperty("name", "AAA");
		
		StudentDynamic student2 = new StudentDynamic();
		student2.setProperty("ssn", "234234");
		student2.setProperty("name", "BBB");
		student2.setProperty("address", "korea");
		
		System.out.println(student.toString());
		
		
		Map<String, String> env = System.getenv(); //지금은 사용 못하더라도 나중에 많이 사용된다. ,, OS의 환경변수와 값을 다 가져올 수 있다.
		System.out.println(env);
		Set<String> keyset = env.keySet();
		for (String name : keyset) {
			String value = System.getenv(name);
			System.out.println(name + "=" + value);
		}
	}
	
}

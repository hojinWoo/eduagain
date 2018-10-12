import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

// 예전 사용 방법
public class PropertiesExample {
	//실제로는 main이 아니라 static 초기화 블록에서 미리 시작된다.
	static {
		String path = "config.properties";
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		prop.put("last", "lastValue" );
		System.out.println(prop.getProperty("name1"));
		prop.elements();
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {


		//확장자명은 지켜야 한다.
		String path = "config.properties";	//환경정보 저장

		Properties prop = new Properties(); //memory에 빈 Map이 생성

		prop.load(new FileInputStream(path));	//config.properties에 있는 data가 들어온다
		prop.put("last", "lastValue" );//파일 자동 저장 (commit)

		System.out.println(prop.getProperty("name1"));

		prop.elements();
	}
}

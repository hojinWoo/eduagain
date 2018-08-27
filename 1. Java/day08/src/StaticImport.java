import static java.lang.System.out;
import static java.lang.Math.*;

public class StaticImport {
	public static void main(String[] args) {
		//생략 가능
		System.out.println("피곤안 월요일............");
		out.println("피곤안 월요일............");
		
//		System.out.println(Math.min(23, 12));
		out.println(min(123,23));
		
		
	}
}

import java.util.StringTokenizer;

/**
 * Stringbuffer class
 * StringTokenizer
 * @author hojin
 *
 */
public class StringBufferTest {
	public static void main(String[] args) {
	     StringBuffer sb = new StringBuffer();
	     sb.append("Java");
	     System.out.println(sb.toString());
	     sb.append("Programming");
	     System.out.println(sb.toString());
	     sb.replace(0, 4, "C++");
	     System.out.println(sb);
	     sb.reverse();
	     System.out.println(sb);
	     String str = sb.substring(11);
	     System.out.println(str);

		
		String date = "2018-08-23";
		StringTokenizer strt = new StringTokenizer(date, "-");
		System.out.println(strt.countTokens()); //토큰 개수 반환
		
		while(strt.hasMoreTokens()) {
			String token = strt.nextToken();
			System.out.println(token);
		}
		
		StringTokenizer st3 = new StringTokenizer("2012-3-13", "-", true);
		while (st3.hasMoreTokens()) {// 토큰 존재 시
			System.out.println(st3.nextToken());
		}
	}
}

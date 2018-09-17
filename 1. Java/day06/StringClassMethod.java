/**
 * java.lang.String class의 주요 method
 * @author hojin
 *
 */
public class StringClassMethod {
	public static void main(String[] args) {
		String str1 = "test for class";
		String str2 = "Practice for class";
		// 주요 method
		System.out.println(str1.length());
		
		System.out.println(str1.equals(str2));
		
		// 대소문자 구분 없이 문자열이 같은 지 비교
		System.out.println(str1.equalsIgnoreCase(str2));
		
		//주어진 위치부터 마지막까지 반환
		System.out.println(str1.substring(5));
		
		//문자열을 합친 새로운 String 객체 반환
		System.out.println(str1.concat(str2));
		
		//기존의 char을 새로우 char로 모두 바꾸어준다
		String str3 = str1.replace('s', 'd');
		System.out.println(str3);
		
		System.out.println(str1.toUpperCase());
		
		System.out.println(str1.charAt(3));
		
		//주어진 문자가 존재하는 마지막 위치 반환. 없을 경우 -1
		System.out.println(str2.lastIndexOf('c'));
		
		//시작과 끝 나이 공백 제거
		System.out.println(" test ".trim());
		
		int num = 123123131;
		System.out.println(String.valueOf(num).length());
	}
}

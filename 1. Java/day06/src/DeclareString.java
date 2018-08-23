/**
 * 명시적 생성과 묵시적 생성 차이
 * @author hojin
 *
 */

public class DeclareString {
	public static void main(String[] args) {
		//명시적 생성
		String str1 = new String("Java Programming");
		String str2 = new String("Java Programming");
		if (str1 == str2) {
			System.out.println("same Reference");
		}else {
			System.out.println("difference Reference");
		}
		
		
		//묵시적 생성
		//StringPool에서 검색 후 존재하지 않을 경우 새로 생성
		String str3 = "Java Programming";
		String str4 = "Java Programming";
		if (str3 == str4) {
			System.out.println("same Reference");
		}else {
			System.out.println("difference Reference");
		}
		
		System.out.println(str1 == str3);
		System.out.println(str2 == str3);
		System.out.println(str2.equals(str3));
		
		//String 인스턴스 변경 불가 (자체적으로 변경이 아닌 쓰레기 객체가 양산..)
		//문자 가공 시 StringBuffer클래스를 사용하여 문자열을 수정/변경
		str4 = str4 + " study";
		System.out.println(str4);
	}
}

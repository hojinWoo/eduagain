
/** 
 * 단위테스트를 위해 Application 클래스 정의(JUnit을 사용하기 전)
 */
public class SomeServiceTest {
	
	public static void main(String[] args) {
//		예전에는 직접 생성...
		SomeServiceImpl service = new SomeServiceImpl();
		int sum = service.sum(10, 20);
		System.out.println(sum);
	}

}


public class ExceptionExample {
	public void testMethod() {
		String message = null;
		try {
			System.out.println(message.length()); //New NullPointerException by JVM
			
		} catch (NullPointerException e) {
			System.out.println("Error... 인스턴스 생성 안 됨.");
			System.out.println(e.getMessage());
			e.printStackTrace(); //call stack에 있는 것 출력해준다 (비동기식 처리)

		} finally {
			System.out.println("null..null..");
		}
		
		try {
			System.out.println(10/0);
		} catch (ArithmeticException e) {
			System.out.println("Error... 수식 오류");
		}
		
		try {
			int[] array = {1,2,3};
			System.out.println(array[3]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error... 배열 생성 실수");
		}
	}
	
	//예외 간접처리
	public void testMethod2() throws NullPointerException, ArithmeticException, ArrayIndexOutOfBoundsException{
		String message = null;
		System.out.println(message.length());
		System.out.println(10/0);
		int[] array = {1,2,3};
		System.out.println(array[3]);
	}

	public static void main(String[] args) {
		System.out.println("프로그램 시작됨 by JVM...");
		ExceptionExample example = new ExceptionExample();
		example.testMethod();
		try {
			example.testMethod2();
		} catch (Exception e) {
			System.out.println("method에서 예외처리");
		}
		
		System.out.println("프로그램 종료...");
	}
}


public class OuterClass {
	
	class InnerClass{
		public void foo() {
			System.out.println("foo 호출됨..");
		}
	}
	
	static class StaticInnerClass {
		public void bar() {
			System.out.println("bar 호출");
		}
	}
	
	public static void main(String[] args) {		
		//Innerclass를 쓰고 싶다면 무조건 outerclass를 생성하고 만들어야 한다
		OuterClass.InnerClass innerClass = new OuterClass().new InnerClass();
		innerClass.foo();
		
		//StaticInnerClass는 바로 접근 가능
		OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();
		staticInnerClass.bar();
	}
	
	//Enum class도 독립적으로 사용하기보다 내부 클래스로 많이 사용한다.
	enum Direction{
		A, B, C;
	}
}

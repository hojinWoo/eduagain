/**
 * 컴파일 시 알려주는 용도
 * @author hojin
 *
 */
public class AnnotationExample {
	
	@Deprecated
	public void some() {
		System.out.println("somesome");
	}
	
	@Override
	public String toString() {
		return "annotation test";
	}
	
	public void myMethod() {
		System.out.println("어노테이션 테스트");
	}
	
	
	
	public static void main(String[] args) {
		AnnotationExample anno = new AnnotationExample();
		anno.some(); 
	}
}

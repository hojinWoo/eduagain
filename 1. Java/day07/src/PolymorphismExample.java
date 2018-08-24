/**
 * 다형성 예제
 * @author hojin
 *
 */
public class PolymorphismExample {
	public static void main(String[] args) {
		Shape[] shapes = new Shape[10];
		
		//클래스 형변환(Up casting)
		shapes[0] = new Shape();
		shapes[1] = new Circle(10, 10, 20);
		shapes[2] = new Rectangle(20,3, 13,21);
		
		System.out.println(shapes[0].toString());
		System.out.println(shapes[1].toString());
		System.out.println(shapes[2].toString());
		
//		shapes[2].getWidth(); //undefined for the type Shape
		//추가 속성이나 메소드 접근 위해 Down casting 필요
		Rectangle rectangle = (Rectangle)shapes[2];
		System.out.println(rectangle.getWidth());
		
		System.out.println(shapes[0] instanceof Shape);
		System.out.println(shapes[1] instanceof Circle);
		System.out.println(shapes[2] instanceof Rectangle);
		
	}
}


public class ShapeApp {

	public static void main(String[] args) {
		//Shape shape = new Shape(12.5, 35.7);
		//shape.draw();
		
		Circle circle = new Circle(15.0, 15.0, 30.0);
		circle.draw();
		System.out.println("원의 둘레 : " + circle.getLength());
		System.out.println("원의 면적 : " + circle.getArea());
		
		Rectangle rectangle = new Rectangle(23.0, 12.4, 23.5, 12.5);
		rectangle.draw();
		System.out.println("직사각형의 둘레 : " + rectangle.getLength());
		System.out.println("직사각형의 면적 : " + rectangle.getArea());
		
		//System.out.println(shape);
		//System.out.println(shape.toString());
		
		System.out.println(circle.toString());
	}
}

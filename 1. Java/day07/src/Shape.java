/**
 * 모든 도형의 공통적인 속성과 기능 정의
 * @author hojin
 *
 */
public class Shape {
	private double x, y;
	
	public Shape() {
		this(0.0, 0.0);
	}
	public Shape(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void draw() {
		System.out.println(x + ", "+y+"의 도령힙니다");
	}
	public double getLength() {
		return 0.0;
	}
	public double getArea() {
		return 0.0;
	}
	@Override
	public String toString() {
		return "Shape [x=" + x + ", y=" + y + "]";
	}
}

/**
 * 모든 도형의 공통적인 속성과 기능 정의 - 추상 클래스
 * @author hojin
 *
 */
public abstract class Shape {
	protected double x, y;
	
	//추상메소드
	//서브클래스가 반드시 구현해야 할 수직적 규악
	public abstract void draw();
	public abstract double getLength();
	public abstract double getArea();

}

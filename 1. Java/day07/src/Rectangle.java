
public class Rectangle extends Shape{
	private double width;
	private double height;
	
	public Rectangle() {
		this(0.0, 0.0, 0.0, 0.0);
	}

	public Rectangle(double x, double y, double width, double height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}
	

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public void draw() {
		System.out.println(getX()+", "+getY()+", "+getWidth()+", "+getHeight()+"의 직사각형입니다.");
	}

	@Override
	public double getLength() {
		return 2*(getWidth()+getHeight());
	}

	@Override
	public double getArea() {
		return getWidth()*getHeight();
	}

	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", height=" + height + ", getX()=" + getX() + ", getY()=" + getY()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
	
	
	
	
}





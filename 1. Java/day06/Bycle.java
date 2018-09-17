// Compiler가 extends Object를 자동적으로 추가한다
public class Bycle /*extends Object*/{
	
	private int id;
	private String brand;
	public Bycle() {
		this(0,null);
	}
	
	public Bycle(int id, String brand) {
		this.id = id;
		this.brand = brand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void running() {
		System.out.println("자전거가 달린다");
	}
	
}

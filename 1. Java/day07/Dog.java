
public class Dog extends Animal {

	protected boolean loyalty;
	
	public Dog() {
		this(null, 0, false);
	}
	
	public Dog(String name, int age, boolean loyalty) {
		this.name = name;
		this.age = age;
		this.loyalty = loyalty;
	}

	@Override
	public void sleep() {
		System.out.println("강아지가 잔다");
	}

	@Override
	public void eat() {
		System.out.println("강아지가 먹음");
	}
	public static void main(String[] args) {
		Animal animal = new Dog("개", 2, true);
		animal.sleep();
	}
}

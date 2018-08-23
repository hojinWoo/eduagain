
public class inheritanceExample {
	public static void main(String[] args) {
		Bycle bycle = new Bycle(10, "삼천리");
		
		System.out.println(bycle.getBrand());
		
		MountainBycle mountainBycle = new MountainBycle(20, "삼천리", "카본", true);
		
		System.out.println(mountainBycle.getFrame());
		
		//오버라이딩
		bycle.running();
		mountainBycle.running();
	}
}

import java.util.Random;

public class RandomExample {
	public static void main(String[] args) {
		Random random = new Random();
		// 자바 데이터타입별 난수 발생
		System.out.println(random.nextBoolean());
		System.out.println(random.nextInt()); // -21~~ ~ 21~~~
		System.out.println(random.nextInt(100)); // 0~99
		System.out.println(random.nextDouble());

		// 로또 번호 생성
		for (int i = 0; i < 6; i++) {
			System.out.println(random.nextInt(45) + 1);
		}

	}
}

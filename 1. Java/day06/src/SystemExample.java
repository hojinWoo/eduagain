/**
 * System은 OS를 추상화해서 만들어진 객체
 * @author hojin
 *
 */
public class SystemExample {
	public static void main(String[] args) {
		System.out.println("Program start");
		
//		System.exit(0); //system shutdown

		//Garbage Collector : 우선순위가 낮다
		System.gc(); //실행 -> garbage 수거, 그러나 바로 동작X
		
		//성능 비교
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000000; i++) {
			for (int j = 0; j < 1000000; j++) {
				
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		
		System.out.println("Program finish");
	}
}

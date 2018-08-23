import java.util.Date;

public class DateExample {
	public static void main(String[] args) {
		Date today = new Date();
		System.out.println(today.getDay());//@deprecated : 권장X
		System.out.println(today.toString());
		System.out.println(today.toLocaleString());
		
		//1970년 1월1일부터 지금까지의 ms(1s = 1000ms)를 반환
		System.out.println(today.getTime());
	}
}

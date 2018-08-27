import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

public class FormatterExample {

	public static void main(String[] args) {
		int number = 1234567;
		Formatter formatter = new Formatter();
		// Formatter format(String format,Object... args)
		// format : "%[출력인자순서$][출력옵션(-, +, (,,..)][출력자리수][.소수점이하자리수]출력데이터유형"
		// args : 포맷팅 하고자 하는 가변인자
		String formatedString = null;
		formatedString = formatter.format("%d", number).toString();
		System.out.println(formatedString);
		formatter.close();
		
		formatter = new Formatter();
		// (기본)우측정렬 후 정수로 출력
		System.out.println(formatter.format("%1$d", number));
		formatter.close();
		
		formatter = new Formatter();
		// 20자리확보하고, 3자리단위 콤마 찍고, 부호달고, 좌측정렬 후 정수로 포맷
		System.out.println(formatter.format("%,+-20d", number));
		formatter.close();
		
		double height = 23454.34343434356;
		formatter = new Formatter();
		// 20자리확보하고, 3자리단위 콤마 찍고, 부호달고, 좌측정렬 후 소수점 이하 2자리 실수로 포맷
		System.out.println(formatter.format("%,+-20.2f", height));
		formatter.close();
		
		// 도스콘솔 전용
		System.out.printf("%,+-10d\n", 198745);
		System.out.printf("%1$,-10d와 %2$,10d\n", 1000, 2000);

		// String 클래스의 클래스메소드 활용
		String fs = String.format("%,20.2f\n", 198745.678);
		System.out.print(fs);

		System.out.println("********************************************");
		//jdk 1.5 이전
		double num = 123124.234;
		NumberFormat nf = new DecimalFormat("#,###.##");
		System.out.println(nf.format(num));
		
		nf = NumberFormat.getInstance();
		System.out.println(nf.format(num));
		
		nf = NumberFormat.getCurrencyInstance();
		System.out.println(nf.format(num));
		
		nf = NumberFormat.getCurrencyInstance(Locale.US);
		System.out.println(nf.format(num));
		
		DateFormat df = DateFormat.getInstance();
		System.out.println(df.format(new Date()));
		
		df = new SimpleDateFormat("yyyy-MM-dd(E) hh:mm:ss");
		System.out.println(df.format(new Date()));

		
	}

}

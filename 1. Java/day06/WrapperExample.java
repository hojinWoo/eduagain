/**
 * wrapper class
 * 
 * @author hojin
 *
 */
public class WrapperExample {
	public static void main(String[] args) {
		char[] data = { 'A', 'a', '4', ' ', '#' };
		for (int i = 0; i < data.length; i++) {
			if (Character.isUpperCase(data[i])) {
				System.out.println(data[i] + "은 대문자이다.");
			} else if (Character.isLowerCase(data[i])) {
				System.out.println(data[i] + "은 소문자이다.");
			} else if (Character.isDigit(data[i])) {
				System.out.println(data[i] + "은 숫자이다.");
			} else if (Character.isSpaceChar(data[i])) {
				System.out.println(data[i] + "은 공백문자이다.");
			}

		}

		Integer integer1 = new Integer(10);
		Integer integer2 = new Integer("10");
		int i1 = integer1.intValue();
		int i2 = integer2.intValue();
		System.out.println("두수의 합: " + (i1 + i2));

		String str = "3578";
		int i3 = Integer.parseInt(str);

		System.out.println("10진수를 2진수로:" + Integer.toBinaryString(i3));
		System.out.println("10진수를 8진수로:" + Integer.toOctalString(i3));
		System.out.println("10진수를 16진수로:" + Integer.toHexString(i3));

		str = "123.45";
		Double d1 = new Double(str);
		double result = d1.doubleValue() / 5;
		System.out.println(result);

		System.out.println("NaN 체크 : " + Double.isNaN(result));
		System.out.println("무한대 체크 : " + Double.isInfinite(result));
		System.out.println("double 최대값 : " + Double.MAX_VALUE);
		System.out.println("double 최소값 : " + Double.MIN_VALUE);

	}
}

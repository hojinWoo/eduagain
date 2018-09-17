/**
 * java FileReader [읽고자 하는 파일] [읽고자 하는 파일명]
 * @author hojin
 *
 */
public class FileReader {
	 public static void main(String[] args) {
		 //java.lang.ArrayIndexOutOfBoundsException : 실행하면서 값을 저장하지 않았기 때문
		if(args.length != 2) {
			System.out.println("error");
			return;
		}
		String fileName1 = args[0];
		String fileName2 = args[1];
		System.out.println(fileName1);
		System.out.println(fileName2);
		
	}
}

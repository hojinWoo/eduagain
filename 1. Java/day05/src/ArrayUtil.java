import java.util.Arrays;

/**
 * 배열 관련 공통 기능
 * @author hojin
 *
 */
public class ArrayUtil {
	//alt+shift+j : method에 대한 주석 달기
	
	
	/**
	 * @param arr	복사하고자 하는 원본 배열
	 * @param inc	증가치
	 * @return		복사된 배열
	 */
	public static int[] Arcopy(int[] arr, int inc) { //call by Reference
		int[] array = new int[arr.length + inc];		
		for (int i = 0; i < arr.length; i++) {
			array[i] = arr[i];
		}
		return array;
	}

	
	//배열 정렬
	/**
	 * @param arr
	 */
	public  static void Arsort(int[] arr) { //call by Reference(because argument is array) 
		Arrays.sort(arr);					// 주소를 줬기 때문에 공유된다.
	}
	
	public static void main(String[] args) {
		int[] arr = {21,3,4,12,4,5};
		int[] arr2 = Arcopy(arr, 4);
		arr2[1] = 6;
		System.out.println(arr[1]);
		System.out.println(arr2[1]);
		
		Arsort(arr);
		for (int i : arr) {
			System.out.print(i+" ");
		}
	}
}

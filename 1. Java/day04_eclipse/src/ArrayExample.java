import java.util.Arrays;

/*
 * 1차원 배열 선언, 생성, 초기화
 * @author hojin
 */
public class ArrayExample {
	public static void main(String[] args) {
		int[] array = new int[5];
		Arrays.fill(array, 2);
//		System.out.println(array[0]);
		array[2] = 3;
//		System.out.println(array[2]);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println();
		//at once
//		int[] p = new int[] {5,4,3,2,1};
		int[] pp = {4,5,1,2,3};
		for (int i = 0; i < pp.length; i++) {
			System.out.print(pp[i]+" ");
		}
		System.out.println();
		for (int i : pp) {
			System.out.print(i+" ");
		}
		
		System.out.println("\nMission1 : Array copy");
		//array copy
		int[] array3 = {3,1,9,2,5};
		int[] array4 = new int[7];
		for (int i = 0; i < array3
				.length; i++) {
			array4[i] = array3[i];
			System.out.print(array4[i]+" ");
		}
		
		System.out.println("\nMission2 : lotto");
		//array sort
		int[] lottos = {4,22,13,44,24};
		//Arrays.sort(lottos);
		int temp;
		for (int i = 0; i < lottos.length; i++) {
			for (int j = i; j < lottos.length-1; j++) {
				if (lottos[j+1] < lottos[j]) {
					temp = lottos[j+1];
					lottos[j+1] = lottos[j];
					lottos[j] = temp;
				}
			}
		}
		for (int i : lottos) {
			System.out.print(i+ " ");
		}
	}
}

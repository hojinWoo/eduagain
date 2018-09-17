/**
 * 다차원배열 테스트
 * @author hojin
 *
 */
public class ArrayExample2 {

	public static void main(String[] args) {
		
		char[][] arr =  new char[10][10];
		
		arr[0][0] = 'A';
		arr[9][9] = 'W';
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
		//int[][] arr2 = new int[][] {{1,2,3}, {6,5,4}, {2,8,1}}; //3*3행렬 초기화
		int[][] arr2 = {{1,2,3}, {6,5,4}, {2,8,1}};	
		
		int[][] gugudan = new int[8][9]; //2~9 
		for (int i = 0; i < gugudan.length; i++) {
			for (int j = 0; j < gugudan[i].length; j++) {
				gugudan[i][j] = (i+2)*(j+1);
				System.out.print((i+2) +" * "+(j+1) +" = " + gugudan[i][j]+ "  ");
			}
			System.out.println();
		}
	}
}

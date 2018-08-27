
public class VarargsExample {
     // 가변 인자를 받아 덧셈 처리
     public static int sum(int... arg){
          int sum = 0;
          for (int i : arg) {
               sum += i;
          }
          return sum;		
     }
     
     public static void main(String[] args) {
          System.out.println(sum(55, 40));
          System.out.println(sum(55, 40, 23, 23, 34, 343, 3453, 343, 55));
     }
}

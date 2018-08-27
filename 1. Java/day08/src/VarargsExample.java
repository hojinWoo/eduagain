
public class VarargsExample {
     // 가변 인자를 받아 덧셈 처리 (오버로딩은 안 된다, 내부적으로 배열 처리 되기 때문)
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

import java.util.Scanner;

public class Gugudan{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.println("input your number");
    int num = sc.nextInt();
    for(int i = 1; i<10; i++){
      for(int j=2;j<=num;j++){
        System.out.print(j + " * " + i +" = " + i*j +"\t");
      }
      System.out.println();
    }
  }
}

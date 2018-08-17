import java.util.Scanner;
public class Statement{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    System.out.println("input your score");
    int score = sc.nextInt();
    if(score < 100){ //Whether the line is only one, wish to write {} in Test
      System.out.println(score);
      System.out.println("you are failed..");
    }
    if((score % 2) == 0){
      System.out.println("your score is even");
    }else{
      System.out.println("your score is odd");
    }
  }
}

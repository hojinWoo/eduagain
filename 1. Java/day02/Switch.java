import java.util.Scanner;
public class Switch{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.println("input number 1 to 5");
    int x = sc.nextInt();
    switch(x){
      case 1:
        System.out.println("your input is "+x);
        break;
      case 2:
        System.out.println("your input is "+x);
        break;
      case 3:
        System.out.println("your input is "+x);
        break;
      case 4:
        System.out.println("your input is "+x);
        break;
      case 5:
        System.out.println("your input is "+x);
        break;
      default:
        System.out.println("you write wrong number");
    }

    int y = 10, z = 2;
    String opp = "+";
    switch(opp){
      case "+":
        System.out.println(y + z);
        break;
      case "-":
        System.out.println(y - z);
        break;
      case "*":
        System.out.println(y * z);
        break;
      case "/":
        System.out.println(y / z);
        break;
      default:
        System.out.println("no operator");
    }
  }
}

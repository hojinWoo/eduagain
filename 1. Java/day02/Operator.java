public class Operator{
  public static void main(String[] args){
    int x=50, y=20;

    System.out.println("Result : " + x + y); //connect operator
    System.out.println("Result : " + (x + y)); //priority operator
    System.out.println("Result : " + (x % y)); //module

    x+=y;
    System.out.println(x);

    //casting
    double weight = 67.5;
    System.out.println((int)weight); //down casting

    System.out.println(x++); //print 70 and up to 71
    System.out.println(++x); //up to 72 and print 72

    //bit Operator
    int xx = 10;
    System.out.println(xx * 2 * 2 * 2);
    int yy = 10;
    System.out.println(yy << 3);

    //Ternary operator
    int a = 12, b = 5, c = 13, max;
    max = (a>b) ? a : b;
    max = (max>c) ? max : c;
    System.out.println(max);
  }
}

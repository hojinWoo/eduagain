public class Method{
  //instance method
  static void printMessage(String msg1, String msg2){
    System.out.println("msg is "+ msg1+" "+msg2);
    return;
  }

  static int sum(int x, int y, int z){
    return x+y+z;
  }

  //entry point
  //class(static) method
  public static void main(String[] args){
    printMessage("so", "calm..");
    System.out.println(sum(3,5,6));
  }
}

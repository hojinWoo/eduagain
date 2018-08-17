public class Return{
  public static void main(String[] args){
    System.out.println("Program start ..");
    int t = 0;
    while(t < 10){
      System.out.println("ss");
      if(t == 5){
        return; // end main
      }
      t++;
    }

    System.out.println("Program end ..");
  }
}

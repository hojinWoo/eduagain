public class While{
  public static void main(String[] args){
    int i = 0;
    while(i < 10){
      System.out.println("reply");
      i++;
    }

    i=1;
    int odd=0, even=0;
    while(i<=100){
      if(i%2==0){
        even+=i;
      }else{
        odd+=i;
      }
      i++;
    }
    System.out.println("odd sum is " + odd);
    System.out.println("even sum is " + even);
  }
}

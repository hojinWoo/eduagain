public class For{
  public static void main(String[] args){
    for(int i = 0; i < 10; i++){
      System.out.println("fighting");
    }
    //Ex1
    for(int i = 1;i <= 5;i++){
      for(int j = 1; j<=i;j++){
        System.out.print("*");
      }
      System.out.println();
    }

    System.out.println();

    //Ex2
    for(int i = 5;i >= 1;i--){
      for(int j = 1; j<=i;j++){
        System.out.print("*");
      }
      System.out.println();
    }

    System.out.println();

    //Ex3
    for(int i = 5; i>=1; i--){
      for(int j = 1; j < i; j++){
        System.out.print(" ");

      }
      for(int j = i; j <=5; j++){
        System.out.print("*");
      }
      System.out.println();
    }

    System.out.println();

    //Ex4
    for(int i = 1; i<=5; i++){
      for(int j = 1; j < i; j++){
        System.out.print(" ");

      }
      for(int j = i; j <=5; j++){
        System.out.print("*");
      }
      System.out.println();
    }

    System.out.println();

    //Ex5
    for(int i=1;i<=5;i++){
      for(int j = 5-i; j>0; j--){
        System.out.print(" ");
      }
      for(int j = 1;j<=2*i-1;j++){
        System.out.print("*");
      }
      for(int j = 5-i; j>0; j--){
        System.out.print(" ");
      }
      System.out.println();
    }

    System.out.println();

    //Ex6
    for(int i = 1; i<10; i++){
      for(int j=2;j<10;j++){
        System.out.print(j + " * " + i +" = " + i*j +"\t");
      }
      System.out.println();
    }
  }
}

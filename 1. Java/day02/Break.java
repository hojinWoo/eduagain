public class Break{
  public static void main(String[] args){
    for(int i = 0;i < 10;i++){
      if(i == 5) break;
      if(i==3) continue;
      System.out.println("print : " + i);
    }

    //in java, there is no 'go to'
    GG: //label
    for(int i = 0; i < 10; i++){
      for(int j = 0; j < 10; j++){
        System.out.print(i+", "+j+"\t");
        if(j == 6) {
          break GG;
        }
      }
      System.out.println();
    }
  }
}

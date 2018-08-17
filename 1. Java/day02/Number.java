public class Number{
  public static void main(String[] args){
    //cf. byte vs short vs integer vs long
    //JVM default num is integer(4byte)
    long money = 500000; //not equal datatype, so implicit type casting
    long money1 = 500000L; //Recommendation

    System.out.println(money);
    System.out.println(money1);

    //JVM default float is double(8byte)
    //float pi = 3.141592; //compile error, incompatible types
    float PI = 3.141592f; // f or F
    double dd = 152.12331;
    System.out.println(PI);
    System.out.println(dd);
  }
}

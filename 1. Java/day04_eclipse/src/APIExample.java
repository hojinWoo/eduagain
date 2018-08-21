/*
 * Standard API
 * C:\Program Files\Java\jdk1.8.0_181\jre\lib\rt.jar(Runtime)
 */

class APIExample{
  public static void main(String[] args){
    String str = new String("It is Java.."); //명시적 생성
    str = "Java"; //묵시적 생성
    int length = str.length();
    System.out.println(length);

    System.out.println(str.charAt(0));
  }
}

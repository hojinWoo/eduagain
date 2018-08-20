import java.util.Scanner;

class AccountExample{
  public static void main(String[] args){
    System.out.println("Start Account Application");
    // Class = Data Type form everyday Object, 4Byte, not pass by value

    //Create new Object(instance) from class
    Account account = new Account("111-222-333","aa",123123,100000); //account : Reference variable (like a Pointer)
    //assgin memory (copy and paste)

    //User instance attribute and method
    //if object's access modifier is private then you cannot access
    // account.accountNum = "111-222-333";
    // account.accountOwner = "aa";
    // account.passwd = 123123;
    // account.restMoney = 100000;

    //highest : '.' //lowest : '='
    //Scenario
    System.out.println("Enter your password");
    Scanner sc = new Scanner(System.in);
    int pw = sc.nextInt();
    if(account.checkPasswd(pw)){
      account.deposit(50000);
      System.out.println("After deposit :"+ account.getRestMoney());
    }else{
      System.out.println("error password!!");
    }

    Account account1 = new Account();
    System.out.println(account1.getRestMoney());


    System.out.println("End Account Application");
  }
}

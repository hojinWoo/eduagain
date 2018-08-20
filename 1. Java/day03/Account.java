/**
* Definition of a modeling class for everyday abstracting objects
* Design and analysis
* ex) bank account
*/

class Account{
  //declare instance variable ((expand Union in C)), automatic init
  private String accountNum; //String ==> class
  private String accountOwner; //class
  private int passwd;
  private long restMoney;

  //default Constructor
  public Account(){
    //init
    this(null, null); //default : JVM init

    // accountNum = null;
    // accountOwner = null;
    // passwd = 0;
    // restMoney = 0L;
  }
  //Constructor Overloading
  public Account(String accountNum, String accountOwner){
    // this.accountNum = accountNum;
    // this.accountOwner = accountOwner;
    // this();
    this(accountNum, accountOwner, 0, 0);
  }
  //Constructor
  public Account(String accountNum, String accountOwner, int passwd, long restMoney){
    this.accountNum = accountNum;
    this.accountOwner = accountOwner;
    this.passwd = passwd;
    this.restMoney = restMoney;
  }

  //instance method
  public long deposit(long money){
    //example
    restMoney += money;
    return restMoney;
  }
  public long withdraw(long money){
    //example
    restMoney -= money; //Not check validate this
    return restMoney;
  }
  public long getRestMoney(){
    //example
    return restMoney;
  }
  public boolean checkPasswd(int passwd){
    //example
    return this.passwd == passwd;
  }
}

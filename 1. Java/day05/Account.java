/**
 * Definition of a modeling class for everyday abstracting objects
 * Design and analysis
 * ex) bank account
 */
class Account{
  //static init block
  static {
//    System.out.println("Start static init block");

  }

  //Class(Static) variable
  public final static String bankName = "hana";

  //instance variable
  private String accountNum;
  private String accountOwner;
  private int passwd;
  private long restMoney;

  //default Constructor
  public Account(){
    this(null, null);
  }
  //Constructor Overloading
  public Account(String accountNum, String accountOwner){
    this(accountNum, accountOwner, 0, 0);
  }
  //Constructor
  public Account(String accountNum, String accountOwner, int passwd, long restMoney){
    this.accountNum = accountNum;
    this.accountOwner = accountOwner;
    this.passwd = passwd;
    this.restMoney = restMoney;
  }

  //instance method //example
  public long deposit(long money){
    restMoney += money;
    return restMoney;
  }
  public String getAccountNum() {
	return accountNum;
}
public void setAccountNum(String accountNum) {
	this.accountNum = accountNum;
}
public String getAccountOwner() {
	return accountOwner;
}
public void setAccountOwner(String accountOwner) {
	this.accountOwner = accountOwner;
}
public int getPasswd() {
	return passwd;
}
public void setPasswd(int passwd) {
	this.passwd = passwd;
}
public void setRestMoney(long restMoney) {
	this.restMoney = restMoney;
}
public long withdraw(long money){
    restMoney -= money; //Not check validate this
    return restMoney;
  }
  public long getRestMoney(){
    return restMoney;
  }
  public boolean checkPasswd(int passwd){
    return this.passwd == passwd;
  }
  
  public String toString() {
	  return getAccountNum()+" "+ getAccountOwner()+" **** "+getRestMoney();
  }
  
  //class(static) method
  public static int sum(int a, int b){
    return a+b;
  }
}

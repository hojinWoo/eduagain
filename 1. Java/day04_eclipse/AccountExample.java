class AccountExample{
  public static void main(String[] args){
    System.out.println("Start Account Application");

    //System.out.println(Account.bankName);
    //Account.bankName = "hana bank"; //cannot assign a value to final variable
    System.out.println(Account.bankName);
    System.out.println(Account.sum(2,3));


    System.out.println("End Account Application");
  }
}

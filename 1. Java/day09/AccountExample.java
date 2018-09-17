/**
 * 프로그램 실행을 위한 어플리케이션 클래스 정의
 */

class AccountExample {
   public static void main(String[] args) {
      System.out.println("은행계좌 어플리케이션 시작됨");
      // I. 생성 따로 초기화 따로
      // I-1. 클래스로부터 객체(인스턴스) 생성
      /*
       * Account account; account = new Account(); // .class 파일이 복사되어 메모리에 올라감(4byte에
       * 주소값 저장)
       */

      // I-2. 인스턴스의 속성과 기능 사용
      /*
       * account.accountNum = "1111-2222-3333"; account.accountOwner = "최재민";
       * account.restMoney = 100000; account.passwd = 1234;
       */

      // II. 생성과 초기화 동시에 - 생성자 활용
      Account account;
      account = new Account("1111-2222-3333", "최재민", 1234, 100000);

      // 비밀번호 체크
      int passwd = 1234;
      boolean result = account.checkPasswd(passwd);
      if (result) {
         // 입금
         long money = 0;
         long restMoney;
         try {
            restMoney = account.deposit(-500);
            System.out.println("입금 후 잔액: " + restMoney);
         } catch (AccountException e) {
            System.out.println(e.getMessage());
         }

         // 출금
         money = 20000;
//         restMoney = account.withdraw(돈);
//         System.out.println("출금 후 잔액: " + restMoney);
      } else {
         System.out.println("비밀번호를 확인해주세요.");
      }

      // 또 다른 계좌 생성
      Account account2 = new Account();
      // 인스턴스 변수의 경우 JVM에 의해 자동 초기화됨.
      account2.setAccountNum("1111-3333-4444");
      System.out.println(account2.getAccountNum());
      System.out.println(account2.getAccountOwner()); // null
      System.out.println(account2.getRestMoney()); // 0
      System.out.println(account2.getPasswd()); // 0

      // 홍길동 계좌 생성
      Account account3;
      account3 = new Account("2222-3333-4444", "홍길동");

      // Account.BANKNAME = "Hana Bank";
      System.out.println(Account.bankName);

      System.out.println(Account.sum(30, 20));

      // System.out.println(account.accountNum);
      System.out.println("은행계좌 어플리케이션 종료됨");
   }
}
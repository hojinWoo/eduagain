import java.util.Scanner;

/**
 * Reference 배열 
 * @author hojin
 *
 */
public class ArrayExample4 {

	public static void main(String[] args) {
//		Account account = new Account("111-222-333", "우호진", 1111, 100000);
		Account[] accounts = new Account[10];
		int index = 0;
		accounts[index] = new Account("111-222-333", "우호진", 1111, 100000);
		index++;

		accounts[index] = new Account("333-222-666", "박지성", 1112, 400000);
		index++;
		
		accounts[index] = new Account("444-777-111", "김연아", 1113, 300000);
		index++;
		
		accounts[index] = new Account("666-444-222", "손흥민", 1114, 200000);
		index++;
		
		//전체 계좌 목록 출력
		
		
		for (Account account : accounts) {
//			System.out.println(account); //hashcode
//			if(account != null) {
//				System.out.print(account.getAccountNum()+" "+ account.getAccountOwner()+" **** "+account.getRestMoney()+"\n");
//			} //this is not OOP code
		}
		
		for (int i = 0; i < index; i++) {
			System.out.println(accounts[i].toString());
		}
		
		//Searching by AccountNumber
		System.out.print("검색하실 계좌번호를 입력하세요 : ");
		Scanner sc = new Scanner(System.in);
		String num = sc.nextLine();
		Account ac = null;
		for (int i = 0; i < index; i++) {
			if(num.equals(accounts[i].getAccountNum())) {
				ac = accounts[i];
				break;
			}
		}
		if(ac ==null) {
			System.out.println("검색된 계좌가 없습니다");
		}else {
			System.out.println("검색하신 계좌는 다음과 같습니다: " + ac.toString());
		}
		
	}
}

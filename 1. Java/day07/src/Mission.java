
public class Mission {
	public static void main(String[] args) {
		Account account1 = new Account("111-222-333", "우호진", 1111, 100000);
		Account account2 = new Account("111-222-333", "우호진", 1111, 100000);
		Account account3 = new Account("111-222-334", "우호진", 1111, 100000);

		System.out.println(account1 == account2); //false
		System.out.println(account2.equals(account1)); //false
		System.out.println(account3.equals(account1)); //false
		
		
	}
}

import java.util.Enumeration;
import java.util.Hashtable;

public class MapExample2 {
	public static void main(String[] args) {
		//HashTable
		Hashtable<String, Account> set = new Hashtable<>();
		
		Account account1 = new Account("1111", "www", 1111, 1000);
		Account account2 = new Account("2222", "sdfsd", 1234, 10000);
		
		set.put(account1.getAccountNum(), account1);
		set.put(account2.getAccountNum(), account2);
		
		System.out.println(set.get("1111"));
		
		//동기화처리
		Enumeration<String> e =  set.keys();
		while(e.hasMoreElements()) {
			String key = e.nextElement();
			System.out.println(key);
		}
		
		Enumeration<Account> e2 = set.elements();
	}
}

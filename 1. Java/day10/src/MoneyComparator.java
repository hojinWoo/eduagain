import java.util.Comparator;

public class MoneyComparator implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		return (int) (o2.getRestMoney() - o1.getRestMoney());
	}

}

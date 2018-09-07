package kr.or.kosta.entity;
import java.util.Comparator;

public class NumberComparator implements Comparator<Account>{

	/*계좌를 계좌번호에 맞게 오름차순 정렬하기 위해 comparator 사용*/
	@Override
	public int compare(Account o1, Account o2) {
		//o1 > o2 : positive
		//o1 = o2 : 0
		//o1 < o2 : negative
		return o1.getAccountNum().compareTo(o2.getAccountNum());
	}

}
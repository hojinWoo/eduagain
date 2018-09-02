package kr.or.kosta.entity;
import java.util.Comparator;

public class NumberComparator implements Comparator<Account>{

	//추상 메소드, 정렬 기준 제시 (ex. 계좌번호)
	@Override
	public int compare(Account o1, Account o2) {
		//o1 > o2 : positive
		//o1 = o2 : 0
		//o1 < o2 : negative
		
		//String에서도 compareTo를 사용 가능
		//같이 크기를 기준으로 return값이 위와 같다
		return o1.getAccountNum().compareTo(o2.getAccountNum());
	}

}
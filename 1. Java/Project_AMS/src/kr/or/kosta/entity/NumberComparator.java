package kr.or.kosta.entity;
import java.util.Comparator;

public class NumberComparator implements Comparator<Account>{

	//�߻� �޼ҵ�, ���� ���� ���� (ex. ���¹�ȣ)
	@Override
	public int compare(Account o1, Account o2) {
		//o1 > o2 : positive
		//o1 = o2 : 0
		//o1 < o2 : negative
		
		//String������ compareTo�� ��� ����
		//���� ũ�⸦ �������� return���� ���� ����
		return o1.getAccountNum().compareTo(o2.getAccountNum());
	}

}
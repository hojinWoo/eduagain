package kr.or.kosta.bin;

import kr.or.kosta.boundary.MainFrame;
import kr.or.kosta.entity.*;

/**
 * ���� ���� ���� ���ø����̼�
 * @author hojin
 *
 */
public class AMS {

	public static void main(String[] args) {
		//AccountManager ����
		AccountManager accountManager = new AccountManager(); 
		//ȭ���� ����� ���� frame�����
		MainFrame frame = new MainFrame("KOSTA AMS - ����ȭ��");
		//frame�� ������ AccountManager���
		frame.setAccountManager(accountManager);
		frame.setAll(); //seContens, eventRegist, pack
		frame.setVisible(true); //ȭ�鿡 ���̵��� ����
	}
}

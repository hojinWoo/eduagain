package kr.or.kosta.bin;
import java.awt.Frame;
import java.util.Enumeration;
import java.util.List;

import kr.or.kosta.boundary.MainFrame;
import kr.or.kosta.entity.*;

/**
 * ���� ���� ���� ���ø����̼�
 * @author hojin
 *
 */
public class AMS {

	public static void main(String[] args) {
		AccountManager accountManager = new AccountManager(); 
		MainFrame frame = new MainFrame("KOSTA AMS - ����ȭ��");
		frame.setAccountManager(accountManager);
		frame.setAll(); //seContens, eventRegist, pack
		frame.setVisible(true);
	}
}

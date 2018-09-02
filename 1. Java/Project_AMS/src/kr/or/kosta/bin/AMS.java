package kr.or.kosta.bin;
import java.awt.Frame;
import java.util.Enumeration;
import java.util.List;

import kr.or.kosta.boundary.MainFrame;
import kr.or.kosta.entity.*;

/**
 * 은행 계좌 관리 애플리케이션
 * @author hojin
 *
 */
public class AMS {

	public static void main(String[] args) {
		AccountManager accountManager = new AccountManager(); 
		MainFrame frame = new MainFrame("KOSTA AMS - 메인화면");
		frame.setAccountManager(accountManager);
		frame.setAll(); //seContens, eventRegist, pack
		frame.setVisible(true);
	}
}

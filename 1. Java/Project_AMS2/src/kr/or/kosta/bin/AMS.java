package kr.or.kosta.bin;
import java.awt.Frame;
import java.io.IOException;
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

	public static void main(String[] args) throws IOException {
		AccountDao dao = new AccountDao();
		MainFrame frame = new MainFrame("KOSTA AMS - 메인화면");
		frame.setAccountManager(dao);
		frame.setContents();
		frame.eventRegist();
		frame.pack();
		frame.setVisible(true);
	}
}

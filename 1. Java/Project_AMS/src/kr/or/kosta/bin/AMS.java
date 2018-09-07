package kr.or.kosta.bin;

import kr.or.kosta.boundary.MainFrame;
import kr.or.kosta.entity.*;

/**
 * 은행 계좌 관리 애플리케이션
 * @author hojin
 *
 */
public class AMS {

	public static void main(String[] args) {
		//AccountManager 생성
		AccountManager accountManager = new AccountManager(); 
		//화면을 만들기 위해 frame만들기
		MainFrame frame = new MainFrame("KOSTA AMS - 메인화면");
		//frame에 만들어둔 AccountManager등록
		frame.setAccountManager(accountManager);
		frame.setAll(); //seContens, eventRegist, pack
		frame.setVisible(true); //화면에 보이도록 설정
	}
}

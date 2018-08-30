/**
 * 마우스 eventListener를 통해서 이벤트 처리 하기
 * 다중구현
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.border.Border;

public class UserFrame extends Frame implements MouseListener, WindowListener{
//	String title; //실제로는 존재하기 때문에 필요
	
	Button ebutton, wbutton, sbutton, nbutton, cbutton; //동서남북 + 중앙
	
	public UserFrame() {
		this("noTitle");
	}

	public UserFrame(String title){
		super(title);
		this.ebutton = new Button("East");
		this.wbutton = new Button("West");
		this.sbutton = new Button("South");
		this.nbutton = new Button("North");
		this.cbutton = new Button("Center");
	}
	//화면 배치
	public void setContents() {
		//layout을 교체할 수 있다.
		this.setLayout(new FlowLayout());
		add(ebutton, BorderLayout.EAST);
		add(wbutton, BorderLayout.WEST);
		add(sbutton, BorderLayout.SOUTH);
		add(nbutton, BorderLayout.NORTH);
		add(cbutton, BorderLayout.CENTER);
		
		//이벤트 소스에 이벤트리스너 연결
//		ebutton.addMouseListener(this); //직접 처리하기 때문에 여기서 처리.
		
	}
	//이벤트 등록
	public void eventRegist() {
		ebutton.addMouseListener(this);
		wbutton.addMouseListener(this);
		sbutton.addMouseListener(this);
		nbutton.addMouseListener(this);
		cbutton.addMouseListener(this);
		
		addWindowListener(this);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("마우스 클릭");
		
		//call back method : 자동으로 호출
		Object eventSource = e.getSource();
		Button button = (Button)eventSource;
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b = (int)(Math.random()*256);
		button.setBackground(new Color(r, g, b));
		
		//eventSource 구분
		if(eventSource == ebutton) {
			System.out.println("East클릭");
		}else if(eventSource == wbutton) {
			System.out.println("west클릭");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered clicked... ");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited clicked... ");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed clicked... ");

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased clicked... ");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// X 버튼 눌렀을 떄
		setVisible(false);
		dispose(); //상속받음, awt는 OS에서 resource를 받아오는 것이기 때문에 resource 반납
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("windowOpened..");
	}
	
	public static void main(String[] args) {
		UserFrame frame = new UserFrame("This is Title..");
		frame.setSize(1000,800);
		frame.setContents();
		frame.eventRegist();
		frame.setVisible(true);
	}
}

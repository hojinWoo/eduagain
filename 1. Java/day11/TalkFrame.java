import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


/**
 * CardLayout 적용 Frame
 * @author hojin
 *
 */
public class TalkFrame extends Frame implements WindowListener{
	
	LoginPanel loginPanel;
	MainPanel mainPanel;
	
	Panel cardPanel;
	CardLayout cardLayout;
	
	
	public TalkFrame() {
		this("이름없음");
	}
	
	public TalkFrame(String title) {
		super(title);
		//양방향으로 접근하기 위해서 바꿔야 함, 생성자 default를 가져야 한다.
		loginPanel = new LoginPanel(this);
		mainPanel = new MainPanel(this);
		
		cardPanel = new Panel();
		cardLayout = new CardLayout();
		
	}
	
	// 화면 배치
	public void setContents() {
		cardPanel.setLayout(cardLayout);
		cardPanel.add("Login", loginPanel);
		cardPanel.add("Main", mainPanel);
		
		//Queue로 만들어져 있기 때문에 새로 바꿀 때 이전에 있는 Layout을 없애면 된다.
		add(cardPanel, BorderLayout.CENTER);
		
		//card 교체
//		cardLayout.show(cardPanel, "Main");
	}
	
	public void setCenter() {
		//Runtime.getRuntime().exec(command);
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (dim.width - getSize().width)/2;
		int y = (dim.height - getSize().height)/2;
		setLocation(x, y);
	}
	
	//캡슐화를 위한 작업
	public void changeCard(String name) {
		cardLayout.show(cardPanel, name);
	}
	
	private void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	
	//이벤트 등록
	public void registerEvent() {
		addWindowListener(this);
	}
	
	
	public static void main(String[] args) {
		TalkFrame frame = new TalkFrame("Kotalk");
		frame.setContents();
		frame.setSize(300, 500);
		frame.setCenter();
		frame.registerEvent();
		frame.setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	//dispose()는 자원을 반납
	//자원이 잘 반납 되었을 때 호출되는 것.
	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("windowClosed Well..."); //for Debug
	}

	@Override
	public void windowClosing(WindowEvent e) {
		finish();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}

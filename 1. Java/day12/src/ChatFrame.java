import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;

/**
 * 한글 안 깨지는 법 : [Run configuration]- [Arguments] - [VM Arguments]에 "-Dfile.encoding=MS949" 입력
 */

public class ChatFrame extends Frame{
//	String title; //실제로는 존재하기 때문에 필요
	
	//화면 만들 때 쓰는 것이기 때문에 굳이 private 안 해도 된다.
	Label serverL;
	TextField serverTF, inputTF;
	Button connectBt, sendBt;
	TextArea messageTA;
	List userList;
	
	public ChatFrame() {
		this("noTitle");
	}

	public ChatFrame(String title){
		super(title);
		serverL = new Label("Server");
		serverTF = new TextField(10); //10 column
		inputTF = new TextField(10);
		connectBt = new Button("PRESS CONNECT");
		sendBt = new Button("PRESS SEND");
		messageTA = new TextArea(10, 20); //row, column
		userList = new List(20); //row
		
	}
	//화면 배치
	public void setContents() {
		//component를 활성화, 비활성화 가능
//		connectBt.setEnabled(false);
		
		Panel northP = new Panel(); 
		northP.setLayout(new BorderLayout());
		northP.add(serverL, BorderLayout.WEST);
		northP.add(serverTF, BorderLayout.CENTER);
		northP.add(connectBt, BorderLayout.EAST);
		
		Panel southP = new Panel();
		southP.setLayout(new BorderLayout());
		southP.add(inputTF, BorderLayout.CENTER);
		southP.add(sendBt, BorderLayout.EAST);
		
		add(northP, BorderLayout.NORTH);
		add(messageTA, BorderLayout.CENTER);
		add(userList,  BorderLayout.EAST);
		add(southP, BorderLayout.SOUTH);
		
		this.setBackground(Color.BLUE); //전체 적용 되는 것이 아니다
		setColorAll(Color.RED, Color.BLACK); //add 한 후로 색을 변경해야
		
//		connectBt.setBackground(new Color(176, 127, 162));
		connectBt.setBackground(Color.cyan);
		connectBt.setForeground(Color.MAGENTA);
		connectBt.setFont(new Font("궁서", Font.BOLD, 15));
		
		//먼저 전체 사이즈를 알아야 한다.
//		setLocation(400, 100); //set Frame 위치 (pixel, pixel) 실행 될 때 위치를 지정할 수 있다
	}
	
	private void setCenter() {
		//Runtime 안에 전체 사이즈를 알 수 있다. 추상클래스
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//		System.out.println(dim); //java.awt.Dimension[width=1920,height=1080]
		
		int x = (dim.width - getSize().width)/2; //or getSize().width
		int y = (dim.height - getSize().height)/2; 
		setLocation(x,y);
		
//		Runtime.getRuntime(); //factory method
	}
	
	private void setColorAll(Color fg, Color bg) {
		Component[] components = getComponents();
		for (Component component : components) {
			//Panel에 들어있는 것들은 추가적으로 또 추출해서 색을 변경해야 한다.
			if(component instanceof Panel) { 
				Component[] cs = ((Panel) component).getComponents();
				for (Component component2 : cs) {
					component2.setForeground(fg);
					component2.setBackground(bg);
				}
			}else {
				component.setForeground(fg);
				component.setBackground(bg);
			}
		}
	}
	
	public void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	
	public void eventRegist() {
		addWindowListener(new ExitHandler(this));
	}
	
	public static void main(String[] args) {
		ChatFrame frame = new ChatFrame("This is Title..");
		frame.setSize(500,600);
//		frame.setBounds(x, y, width, height); //한 번에 size와 location 설정 가능
		frame.setContents();
		frame.setCenter();
		frame.eventRegist();
		frame.setVisible(true);
	}
}

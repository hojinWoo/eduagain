package awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

public class ChatFrame extends Frame{
//	String title; //실제로는 존재하기 때문에 필요
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
		//BorderLayout을 사용하려면 Panel을 꼭 같이 써야 한다.
		Panel northP = new Panel(); //default : FlowLayout
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
		
	}
	
	public static void main(String[] args) {
		ChatFrame frame = new ChatFrame("This is Title..");
		frame.setSize(500,600);
		frame.setContents();
		frame.setVisible(true);
		
	}
}

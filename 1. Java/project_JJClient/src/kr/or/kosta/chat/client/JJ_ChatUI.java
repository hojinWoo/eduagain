package kr.or.kosta.chat.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import kr.or.kosta.chat.common.Protocol;

public class JJ_ChatUI extends Frame implements WindowListener{

	LoginPanel loginPanel;
	WaitingPanel waitingPanel;
	RoomPanel roomPanel;
	
	Panel cardPanel;
	CardLayout cardLayout;
	
	JJ_ChatClient client;

	public JJ_ChatUI() {
		this("noname");
	}
	public JJ_ChatUI(String title) {
		super(title);
		loginPanel = new LoginPanel(this);
		waitingPanel = new WaitingPanel(this);
		roomPanel = new RoomPanel(this);
		loginPanel.eventRegist();
		waitingPanel.eventRegist();
		
		cardPanel = new Panel();
		cardLayout = new CardLayout();
	}
	
	
	public void setContents() {
		cardPanel.setLayout(cardLayout);
		cardPanel.add("login", loginPanel);
		cardPanel.add("waiting", waitingPanel);
		cardPanel.add("room", roomPanel);
		
		add(cardPanel, BorderLayout.CENTER);
	}
	public void setCenter() {
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (dim.width - getSize().width)/2;
		int y = (dim.height - getSize().height)/2;
		setLocation(x, y);
	}
	
	public void changeCard(String name) {
		cardLayout.show(cardPanel, name);
		if(name.equalsIgnoreCase("login")) {
			this.setTitle("로그인");
		}else if(name.equalsIgnoreCase("waiting")) {
			this.setTitle("대기실");
		}else {
			this.setTitle("대화방");
		}
	}
	
	public void sendMessage(String message) {
		client.sendMessage(message);
	}
	
	public void eventRegist() {
		addWindowListener(this);
	}
	
	public void process(String message) {
		String[] token = message.split(Protocol.DELEMETER);
		switch (Integer.parseInt(token[0])) {
		case Protocol.SC_LOGIN_RESULT:
			loginPanel.sc_checkNickName(token[2]);
			break;
			
		default:
			break;
		}
	}
	
	public void setChatClient(JJ_ChatClient client) {
		this.client = client;
	}
	
	public void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	public void connect() {
		try {
			if(client.getSocket() == null) {
				System.out.println("connect");
				client.connectServer();
				// 최초 연결 메시지 전송
				client.receiveMessage();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "연결 실패", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		finish();
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		
	}
}

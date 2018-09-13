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

	String key_nickName;
	
	public JJ_ChatUI() {
		this("noname");
	}
	public JJ_ChatUI(String title) {
		super(title);
		key_nickName = "";
		loginPanel = new LoginPanel(this);
		waitingPanel = new WaitingPanel(this);
		roomPanel = new RoomPanel(this);
		loginPanel.eventRegist();
		waitingPanel.eventRegist();
		roomPanel.eventRegist();
		
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
	
	public void setKey_nickName(String key_nickName) {
		this.key_nickName = key_nickName;
	}
	
	public String getKey_nickName() {
		return key_nickName;
	}
	
	public void sendMessage(String message) {
		client.sendMessage(message);
	}
	
	public void eventRegist() {
		addWindowListener(this);
	}
	
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}
	
	public void process(String message) {
		String[] token = message.split(Protocol.DELEMETER);
		switch (Integer.parseInt(token[0])) {
		case Protocol.SC_LOGIN_RESULT:
			loginPanel.sc_checkNickName(token[2]);
			break;
		case Protocol.SC_UPDATE_WAITINGROOM_USER_LIST:
			String nickNames = token[1];
			waitingPanel.sc_getWaiting(nickNames);
			roomPanel.sc_getWaiting(nickNames);
			break;
		case Protocol.SC_UPDATE_CHATROOM_LIST:
			waitingPanel.sc_getRoomInfo(token[1]);
			break;
		case Protocol.SC_ROOM_NAME_RESULT:
			waitingPanel.sc_checkRoomName(token[1]);
			break;
		case Protocol.SC_ROOM_CREATE_RESULT:
			waitingPanel.sc_checkCreateRoom(token[1]);
			break;
		case Protocol.SC_GETROOM_INFO_RESULT:
			waitingPanel.sc_getRoomUserInfo(token[1]);
			break;
		case Protocol.SC_REQUEST_ENTER_CHATROOM_RESULT:
			if(token[1].equalsIgnoreCase("SUCCESS")){
				waitingPanel.sc_enterRoom();
				roomPanel.setRoomName(waitingPanel.getRoomName());
			}else {
				waitingPanel.sc_failEnterRoom();
			}
			break;
		case Protocol.SC_ROOM_CHAT_RESULT:
			roomPanel.uploadMessage(token[1], token[2], token[3]);
			break;
		case Protocol.SC_ALL_CHAT_RESULT:
			waitingPanel.uploadMessage(token[1], token[2], token[3]);
			break;
		case Protocol.SC_UPDATE_ROOM_USER:
			roomPanel.sc_getRoomUserInfo(token[1]);
			break;
		case Protocol.SC_SECRET_CHAT_ROOM_RESULT:
			roomPanel.uploadWisperMsg(token[1], token[2], token[3]);
			break;
		case Protocol.SC_SECRET_CHAT_WAIT_RESULT:
			waitingPanel.uploadWisperMsg(token[1], token[2], token[3]);
			break;
		case Protocol.SC_INVITE_RESULT:
			String roomName = token[2];
			int sc_invite = JOptionPane.showConfirmDialog(null, token[1]+"님이 [" + roomName + "]방에 초대하였습니다\n참여하시겠습니까?", "방초대", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(sc_invite == 0) {
				sendMessage(Protocol.CS_INVITE_ISACCEPT + Protocol.DELEMETER + roomName +Protocol.DELEMETER + "ACCEPT");
			}else {
				sendMessage(Protocol.CS_INVITE_ISACCEPT + Protocol.DELEMETER + roomName +Protocol.DELEMETER + "REJECT");
			}
			break;
		case Protocol.SC_INVITE_ISACCEPT_RESULT:
			if(token[1].equalsIgnoreCase("SUCCESS")){
				waitingPanel.sc_enterRoom();
				roomPanel.setRoomName(waitingPanel.getRoomName());
			}else {
				waitingPanel.sc_failEnterRoom();
			}
			break;
		case Protocol.SC_LEAVEROOM_RESULT:
			roomPanel.sc_leaveRoom();
			break;
		default:
			break;
		}
	}
	
	public void setChatClient(JJ_ChatClient client) {
		this.client = client;
	}
	
	public void finish() {
		sendMessage(Protocol.CS_DISCONNECT+Protocol.DELEMETER);
		client.stopClient();
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
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		
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

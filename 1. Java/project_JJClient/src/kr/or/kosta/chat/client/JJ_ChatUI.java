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

	String key_nickName;	//연결하는 nickname 기록
	
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
	
	/**
	 * cardPanel에 보이는 panel 바꾸기
	 * @param name 원하는 카드 이름
	 */
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
		/** 로그인 체크 */
		case Protocol.SC_LOGIN_RESULT:
			loginPanel.sc_checkNickName(token[2]);
			break;
		/** 대기실 유저 업데이트 */
		case Protocol.SC_UPDATE_WAITINGROOM_USER_LIST:
			String nickNames = token[1];
			waitingPanel.sc_getWaiting(nickNames);
			roomPanel.sc_getWaiting(nickNames);
			break;
		/** 대기실 업데이트 */
		case Protocol.SC_UPDATE_CHATROOM_LIST:
			waitingPanel.sc_getRoomInfo(token[1]);
			break;
		/** 채팅방 중복확인 */	
		case Protocol.SC_ROOM_NAME_RESULT:
			waitingPanel.sc_checkRoomName(token[1]);
			break;
		/** 채팅방 생성 결과 */
		case Protocol.SC_ROOM_CREATE_RESULT:
			waitingPanel.sc_checkCreateRoom(token[1]);
			break;
		/** 채팅방 참여 인원 목록*/
		case Protocol.SC_GETROOM_INFO_RESULT:
			waitingPanel.sc_getRoomUserInfo(token[1]);
			break;
		/** 채팅방입장결과 */
		case Protocol.SC_REQUEST_ENTER_CHATROOM_RESULT:
			if(token[1].equalsIgnoreCase("SUCCESS")){
				waitingPanel.sc_enterRoom();
				roomPanel.setRoomName(waitingPanel.getRoomName());
			}else {
				waitingPanel.sc_failEnterRoom();
			}
			break;
		/** 방에서 채팅하기 */
		case Protocol.SC_ROOM_CHAT_RESULT:
			roomPanel.uploadMessage(token[1], token[2], token[3]);
			break;
		/** 대기실 전체채팅  */
		case Protocol.SC_ALL_CHAT_RESULT:
			waitingPanel.uploadMessage(token[1], token[2], token[3]);
			break;
		/** 대기실 유저 변경 시 */
		case Protocol.SC_UPDATE_ROOM_USER:
			roomPanel.sc_getRoomUserInfo(token[1]);
			break;
		/** 귓속말 결과(receiver 채팅방에 있는 경우) */
		case Protocol.SC_SECRET_CHAT_ROOM_RESULT:
			roomPanel.uploadWisperMsg(token[1], token[2], token[3]);
			break;
		/** 귓속말 결과(receiver 대기실에 있는 경우) */
		case Protocol.SC_SECRET_CHAT_WAIT_RESULT:
			waitingPanel.uploadWisperMsg(token[1], token[2], token[3]);
			break;
		/** 방에 초대하기 요청 */
		case Protocol.SC_INVITE_RESULT:
			String roomName = token[2];
			int sc_invite = JOptionPane.showConfirmDialog(null, token[1]+"님이 [" + roomName + "]방에 초대하였습니다\n참여하시겠습니까?", "방초대", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(sc_invite == 0) {
				sendMessage(Protocol.CS_INVITE_ISACCEPT + Protocol.DELEMETER + roomName +Protocol.DELEMETER + "ACCEPT");
			}else {
				sendMessage(Protocol.CS_INVITE_ISACCEPT + Protocol.DELEMETER + roomName +Protocol.DELEMETER + "REJECT");
			}
			break;
		/** 방에 초대받아서 들어갈 경우 */
		case Protocol.SC_INVITE_ISACCEPT_RESULT:
			if(token[1].equalsIgnoreCase("SUCCESS")){
				waitingPanel.sc_enterRoom();
				roomPanel.setRoomName(waitingPanel.getRoomName());
			}else {
				waitingPanel.sc_failEnterRoom();
			}
			break;
		/** 방에서 나가는 경우*/
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

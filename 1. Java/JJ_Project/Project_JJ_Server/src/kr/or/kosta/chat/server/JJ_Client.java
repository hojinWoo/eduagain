package kr.or.kosta.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosta.common.Protocol;

/**
 * 
 * @author 최재민
 *
 */
public class JJ_Client extends Thread {

	// 필드 
	private Socket socket; // client와 통신하기 위한 socket 
	private boolean running; // 현재상태 thread 생성하면 true
	private BufferedReader in; // client로부터 받아오는 값 
	private PrintWriter out; // client로 내보내는 값 
	private String nickName; // 여러 client 객체를 구분하기 위한 key인 닉네임
	JJ_ChatServer chatServer; // 서버  

	// default 생성자 
	public JJ_Client() {
	}

	// nickName을 받는 생성자(nickName이 여러 client Thread를 구분할 수 있음)
	public JJ_Client(String nickName) {
		this.nickName = nickName; 
	}

	// socket과 server를 전달 받는 생성자 
	public JJ_Client(Socket socket, JJ_ChatServer server) throws IOException {
		this.socket = socket; // 전달받은 socket으로 초기화 
		this.chatServer = server; // 전달받은 서버를 연결 
		in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // in 초기화 
		out = new PrintWriter(socket.getOutputStream(), true); // out 초기화 
		running = true; // 생성했기 때문에 running true 

	}
	
	// getter 메소드 
	public Socket getSocket() {
		return socket;
	}

	public boolean isRunning() {
		return running;
	}

	public BufferedReader getIn() {
		return in;
	}

	public PrintWriter getOut() {
		return out;
	}

	public String getNickName() {
		return nickName;
	}

	/*
	 * 실행 시작
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() { // 처음 시작 
		receiveMessage(); // client로부터 message 전달 받음 
	}

	/**
	 * 메시지 수신
	 */
	public void receiveMessage() { 
		String clientMessage = null;

		while (running) {
			try {
				clientMessage = in.readLine(); // client로부터 전달받은 메시지를 읽어들임 
				System.out.println("[Debug] : 클라이언트 수신 데이터: " + clientMessage); 
				process(clientMessage); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 처리
	 */
	public void process(String clientMessage) { 
		String[] tokens = clientMessage.split(Protocol.DELEMETER); // Delemeter를 기준으로 끊어서 tokens라는String 배열에 넣어줌 
		int protocol = Integer.parseInt(tokens[0]); // String으로 들어온 protocol 숫자를 int로 형변환 
		switch (protocol) { // protocol이 몇번인지에 따라 case 나눔 
		case Protocol.CS_LOGIN: // 로그인 화면에서 닉네임 중복체크 
			nickName = tokens[1]; // client에서 protocol 다음에 nickName을 전달해줌 
			/** 닉네임 중복체크 */
			if (chatServer.isExistNickName(nickName)) { // 서버에서 중복체크 
				sendMessage(Protocol.SC_LOGIN_RESULT + Protocol.DELEMETER + nickName + Protocol.DELEMETER + "FAIL");
			} else {
				sendMessage(Protocol.SC_LOGIN_RESULT + Protocol.DELEMETER + nickName + Protocol.DELEMETER + "SUCCESS");
			}
			break;

		case Protocol.CS_ENTER:
			/** 입장 시 사용자 리스트에 추가 */
			chatServer.addClient(this);
			
			/* 대기자 리스트 업데이트 **/
			updateWaitingClientList();
			
			/** 채팅방 리스트 업데이트 */
			updateChatRoomList();
			break;

		/** 새로운 채팅방 생성 - 채팅방 이름 중복체크 */
		case Protocol.CS_ROOM_NAME:
			// tokens[1] : chatRoomName
 			if (chatServer.checkChatRoomName(tokens[1])) { 
				sendMessage(Protocol.SC_ROOM_NAME_RESULT + Protocol.DELEMETER + "Fail");
			} else {
				sendMessage(Protocol.SC_ROOM_NAME_RESULT + Protocol.DELEMETER + "SUCCESS");
			}
			break;

		/** 새로운 채팅방 생성 */
		case Protocol.CS_ROOM_CREATE:
			String[] chatRoomInfo = tokens[1].split(Protocol.DELEMETER2);

			// chatRoomInfo[0]: 새로운 채팅방 이름
			// chatRoomInfo[1]: 새로운 채팅방 방장
			// chatRoomInfo[2]: 새로운 채팅방 최대참여자수
			chatServer.addChatRoom(chatRoomInfo[0], chatRoomInfo[1], Integer.parseInt(chatRoomInfo[2]));
			
			/** 방장 채팅방에 추가하기 */
			chatServer.addRoomOwnerToChatRoom(chatRoomInfo[0], chatRoomInfo[1]);
			sendMessage(Protocol.SC_ROOM_CREATE_RESULT + Protocol.DELEMETER + "SUCCESS");
			updateChatRoomClientList(chatRoomInfo[0], 1);
			/** 채팅방 리스트 업데이트 */
			updateChatRoomList();
			break;
			
		/** 채팅방 입장 */
		case Protocol.CS_REQUEST_ENTER_CHATROOM:
			// tokens[1]: nickName
			// tokens[2]: roomName
			if (chatServer.addClientToChatRoom(tokens[1], tokens[2])) {
				sendMessage(Protocol.SC_REQUEST_ENTER_CHATROOM_RESULT + Protocol.DELEMETER + "SUCCESS");
				updateChatRoomClientList(tokens[2], 1);
			} else {
				sendMessage(Protocol.SC_REQUEST_ENTER_CHATROOM_RESULT + Protocol.DELEMETER + "Fail");
			}
			break;

		/** 해당 채팅방 받아오기 */
		case Protocol.CS_GETROOM_INFO:

			/** 해당 채팅방 client 리스트 업데이트 */
			// tokens[1]: roomName
			updateChatRoomClientList(tokens[1], 0);
			updateWaitingClientList();
			break;

		case Protocol.CS_ROOM_CHAT:
			// tokens[1]: nickName
			// tokens[2]: message
			chatServer.startRoomChat(tokens[1], tokens[2]);
			break;

		/** 귓속말하기 */
		case Protocol.CS_SECRET_CHAT:
			// CS로부터 protocol, sender, receiver, message 전달받음
			chatServer.startSecretChat(tokens[1], tokens[2], tokens[3]);
			// CS한테 잘 전달해쓴지 전달결과 전해주면 됨
			break;

		/** 초대하기-1: sender가 receiver에게 초대한다는 사실 전달하기 */
		case Protocol.CS_INVITE:
			// protocol, sender, receiver
			chatServer.inviteReceiver(tokens[1], tokens[2]);
			break;

		/** 초대하기-2: receiver가 Accept를 했는지 Reject를 했는지 그 결과 전달하기 */
		case Protocol.CS_INVITE_ISACCEPT:
			// protocol, roomName, result
			if (tokens[2].equalsIgnoreCase("ACCEPT")) {
				if (chatServer.addClientToChatRoom(this.getNickName(), tokens[1])) {
					sendMessage(Protocol.SC_INVITE_ISACCEPT_RESULT + Protocol.DELEMETER + "SUCCESS");
					updateChatRoomClientList(tokens[1], 1);
					updateWaitingClientList();
				} else {
					sendMessage(Protocol.SC_INVITE_ISACCEPT_RESULT + Protocol.DELEMETER + "Fail");
				}
			}
			break;

		/** 방 나가기 */
		case Protocol.CS_LEAVEROOM:
			// tokens[1] : nickName
			String roomName = chatServer.getUserChatRoomName(tokens[1]);
			boolean flag = chatServer.removeChatRoom(tokens[1]); // 채팅방 없애기
			if(flag != true) {
				//방에서 나가는 경우
				chatServer.leaveRoom(tokens[1]);
				updateChatRoomClientList(roomName, 1);
			}
			sendMessage(Protocol.SC_LEAVEROOM_RESULT + Protocol.DELEMETER);
			updateChatRoomList();
			break;

		/** 전체 채팅하기 */
		case Protocol.CS_ALL_CHAT:
			// nickName, message
			chatServer.sendAllMessage(tokens[1], tokens[2]);
			break;

			/** 로그아웃 */
		case Protocol.CS_LOGOUT:
			chatServer.removeClient(this);
			updateWaitingClientList();
			break;

		/** 종료하기 */
		case Protocol.CS_DISCONNECT:
			boolean flag2 = chatServer.isUserHasRoom(this.getNickName());
			if (flag2) {
				String roomName2 = chatServer.getUserChatRoomName(this.getNickName());
				boolean flag3 = chatServer.removeChatRoom(this.getNickName()); // 채팅방 없애기
				if (flag3 != true) {
					// 방에서 나가는 경우
					chatServer.leaveRoom(this.getNickName());
					updateChatRoomClientList(roomName2, 1);
				}
				sendMessage(Protocol.SC_LEAVEROOM_RESULT + Protocol.DELEMETER);
				updateChatRoomList();
			}
			/** 사용자 리스트에서 삭제 */
			chatServer.removeClient(this);
			updateWaitingClientList();
			running = false;
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 채팅방 내 client 리스트 업데이트
	 * 
	 * @param chatRoomName
	 * @param flag
	 */
	public void updateChatRoomClientList(String chatRoomName, int flag) {
		if (flag == 0) {
			sendMessage(
					Protocol.SC_GETROOM_INFO_RESULT + Protocol.DELEMETER + chatServer.getAtiveChatRoom(chatRoomName));
		} else if (flag == 1) {
			chatServer.sendToSpecificRoom(chatRoomName);
		}
	}

	/**
	 * 대기자 리스트 업데이트
	 * 
	 */
	public void updateWaitingClientList() {
		List<String> allClientList = new ArrayList<>();
		allClientList = chatServer.getAllClientList();
		chatServer.sendAllMessage(Protocol.SC_UPDATE_WAITINGROOM_USER_LIST + Protocol.DELEMETER + allClientList);
	}

	/**
	 * 채팅방 리스트 업데이트
	 */
	public void updateChatRoomList() {
		List<JJ_ChatRoom> activeChatRoomList = new ArrayList<>();
		activeChatRoomList = chatServer.getActiveChatRoomList();
		chatServer.sendAllMessage(Protocol.SC_UPDATE_CHATROOM_LIST + Protocol.DELEMETER + activeChatRoomList);
	}

	public void sendList(List list) {
		if (out != null) {
			out.println(list);
		}
	}

	/**
	 * 메시지 전송
	 */
	public void sendMessage(String message) {
		if (out != null) {
			out.println(message);
		}
	}

}

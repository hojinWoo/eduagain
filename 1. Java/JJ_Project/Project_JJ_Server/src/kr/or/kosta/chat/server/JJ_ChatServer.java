package kr.or.kosta.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import kr.or.kosta.common.Protocol;

/**
 * 
 * @author 최재민
 *
 */
public class JJ_ChatServer {
	private static final int PORT = 8888; // sever의 port번호 

	private boolean running; // 현재상태 
	private ServerSocket serverSocket; // sevevr의 Socket 
	private Hashtable<String, JJ_Client> clients; // server에서 clients 관리 
	private JJ_WaitingRoom waitingRoom; // chatRoom을 관리하는 waitingRoom 
	private static int roomNumber = 3; 

	Date today = null;
	private SimpleDateFormat simpleDateFormat = null;

	public JJ_ChatServer() {

		today = new Date();
		simpleDateFormat = new SimpleDateFormat("MM.dd HH:mm");

		clients = new Hashtable<>();
		clients.put("소시지", new JJ_Client("소시지"));
		clients.put("별", new JJ_Client("별"));
		clients.put("사탕", new JJ_Client("사탕"));
		clients.put("치약", new JJ_Client("치약"));
		clients.put("물통", new JJ_Client("물통"));

		waitingRoom = new JJ_WaitingRoom(this);

	}

	public static int getPort() {
		return PORT;
	}

	public boolean isRunning() {
		return running;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public Hashtable<String, JJ_Client> getClients() {
		return clients;
	}

	public JJ_WaitingRoom getWaitingRoom() {
		return waitingRoom;
	}

	/**
	 * 닉네임 생성 조건에 맞는 닉네임인지 확인
	 * 
	 * @param nickName
	 * @return
	 */
	public boolean isValidNickName(String nickName) {
		return false;
	}

	/**
	 * Server를 시작
	 * 
	 * @throws IOException
	 * 
	 */
	public void startUp() throws IOException { 
		Socket socket;
		try {
			serverSocket = new ServerSocket(PORT); // port번호로 serverSocket 열어줌 
		} catch (IOException e) {
			throw new IOException("[" + PORT + "] 포트 충돌이 일어났습니다. ");
		}
		running = true;

		System.out.println("JMG[" + PORT + "] 포트 시작");

		while (running) {
			try {
				socket = serverSocket.accept();
				JJ_Client client = new JJ_Client(socket, this);
				client.start();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Client 추가(입장)
	 * 
	 * @param client
	 * @return
	 */
	public void addClient(JJ_Client client) {
		clients.put(client.getNickName(), client);
	}

	/**
	 * 닉네임 중복확인
	 * 
	 * @param nickName
	 * @return
	 */
	public boolean isExistNickName(String nickName) { // nickName을 전달 받아 clients 중에서 해당하는 nickName이 있는지 rerturn
		return clients.containsKey(nickName);
	}

	/**
	 * 전체 사용자 수 반환
	 * 
	 * @return
	 */
	public int getAllClientCount() {
		return clients.size();
	}

	/**
	 * 전체 사용자 리스트 반환
	 * 
	 * @return
	 */
	public List<String> getAllClientList() {

		List<String> list = new ArrayList<String>();
		String nickName = null;
		Enumeration<JJ_Client> e = clients.elements();
		while (e.hasMoreElements()) {
			JJ_Client client = e.nextElement();
			nickName = client.getNickName();
			list.add(nickName);
		}
		return list;
	}

	
	/**
	 * 채팅방 이름 중복확인
	 * 
	 * @param chatRoomName
	 * @return
	 */
	public boolean checkChatRoomName(String chatRoomName) {
//		if(waitingRoom.getChatRooms().containsKey(chatRoomName)) {
		if (waitingRoom.getChatRoomNames().contains(chatRoomName)) {
			return true; // 이미 존재하는 채팅방 이름이 있음
		}
		return false; // 채팅방 이름 사용 가능함
	}

	/**
	 * 종료
	 * 
	 * @param client
	 */
	public void removeClient(JJ_Client client) {
		// 채팅방에 참여되어 있으면 방에서도 없애기
		JJ_ChatRoom chatRoom = waitingRoom.getChatRoom(client.getNickName());
		if (chatRoom != null) {
			chatRoom.removeClient(client.getNickName());
		}
		clients.remove(client.getNickName());

	}

	/**
	 * 현재 활성화된 채팅방 리스트 반환
	 * 
	 * @return
	 */
	public List<JJ_ChatRoom> getActiveChatRoomList() {
		List<JJ_ChatRoom> activeChatRoomList = new ArrayList<>();
		activeChatRoomList = waitingRoom.getActiveChatRoomList();
		return activeChatRoomList;
	}

	/**
	 * 채팅방 추가
	 * 
	 * @param newChatRoomName
	 * @param newChatRoomOwner
	 * @param newChatRoomCapacity
	 */
	public void addChatRoom(String newChatRoomName, String newChatRoomOwner, int newChatRoomCapacity) {
		waitingRoom.addChatRoom(++roomNumber, newChatRoomName, newChatRoomOwner, newChatRoomCapacity);
	}

	/**
	 * 해당 채팅방의 client 리스트 반환
	 */
	public List<String> getActiveChatRoomClientList(String nickName) {
		List<String> clientList = new ArrayList<>();
		clientList.addAll(waitingRoom.getActiveChatRoomClientList(nickName));
		return clientList;
	}

	/**
	 * 해당 채팅방 정보 반환
	 * 
	 * @param roomName
	 */
	public List<String> getAtiveChatRoom(String roomName) {
		// client 리스트 반환
//		List<String> list = new ArrayList<>(); 
//		list.addAll(waitingRoom.getActiveChatRoomClientList(roomName));
		return waitingRoom.getActiveChatRoomClientList(roomName);
	}

	/**
	 * 해당 채팅방에 Client 추가하기
	 * 
	 * @param nickName
	 * @param roomName
	 */
	public boolean addClientToChatRoom(String nickName, String roomName) {
		if (waitingRoom.addClientToChatRoom(nickName, roomName)) {
			return true;
		}
		return false;

	}

	/**
	 * 방장 채팅방에 추가하기
	 */
	public void addRoomOwnerToChatRoom(String roomName, String roomOwner) {
		waitingRoom.chatRooms.get(roomName).addClient(roomOwner);
	}

	/**
	 * 모든 클라이언트에게 이벤트 보내기
	 */
	public void sendAllMessage(String message) {
		Enumeration<JJ_Client> e = clients.elements();
		while (e.hasMoreElements()) {
			JJ_Client client = e.nextElement();
			client.sendMessage(message);
		}
	}

	/**
	 * 채팅시작하기
	 * 
	 * @param nickName
	 * @param message
	 */
	public void startRoomChat(String nickName, String message) {
		// nickName을 활용해서 해당 채팅방을 찾아야 함
		JJ_ChatRoom chatRoom = waitingRoom.getChatRoom(nickName);
		// 그 채팅방에 있는 사람들을 찾아야 함
		List<String> roomUsers = chatRoom.getClients();
		// 그 사람들한테 message를 뿌려야 함
		for (String nickNames : roomUsers) {
			clients.get(nickNames).sendMessage(Protocol.SC_ROOM_CHAT_RESULT + Protocol.DELEMETER + nickName
					+ Protocol.DELEMETER + message + Protocol.DELEMETER + simpleDateFormat.format(today));
		}
		// List<String> roomUser = waitingRoom.getChatUserName(nickName);
		// 들어온 nickName이 있는 채팅방 찾기
//		waitingRoom.getActiveChatRoomName();
		// 채팅 입력 필요!!
//		for (String string : roomUsers) {
//			clients.get(string).sendMessage(Protocol.SC_ROOM_CHAT_RESULT + Protocol.DELEMETER + nickName + Protocol.DELEMETER + message);
//		}

		// 그 채팅방에 있는 사람들이 누구인지 찾기
		// 그 사람들한테 들어온 message 보내주기
	}

	/**
	 * 대기방 말고 특정 채팅방에만 userList 업데이트 해주기
	 * 
	 * @param nickName
	 */
	public void sendToSpecificRoom(String chatRoomName) {

		List<String> users = waitingRoom.getActiveChatRoomClientList(chatRoomName);
		for (String userList : users) {
			clients.get(userList).sendMessage(Protocol.SC_UPDATE_ROOM_USER + Protocol.DELEMETER + users);
		}
	}

	/**
	 * 귓속말하기
	 * 
	 * @param sender
	 * @param receiver
	 * @param message
	 */
	public void startSecretChat(String sender, String receiver, String message) {
		// receiver가 채팅방 안에 있는지, 밖에 있는지 판단
		// receiver의 client 객체 찾기
		JJ_Client receiverC = clients.get(receiver);
		if (waitingRoom.getChatRoom(receiver) != null) { // 채팅방 안에 있는 경우
			// receiver한테 sender로부터 message가 왔다고 얘기하기
			receiverC.sendMessage(Protocol.SC_SECRET_CHAT_ROOM_RESULT + Protocol.DELEMETER + sender + Protocol.DELEMETER
					+ message + Protocol.DELEMETER + simpleDateFormat.format(today));
		} else { // 채팅방 밖에 있는 경우
			receiverC.sendMessage(Protocol.SC_SECRET_CHAT_WAIT_RESULT + Protocol.DELEMETER + sender + Protocol.DELEMETER
					+ message + Protocol.DELEMETER + simpleDateFormat.format(today));
		}

	}

	/**
	 * 초대하기
	 */
	public void inviteReceiver(String sender, String receiver) {
		// 초대받는 대상 찾기
		JJ_Client receiverC = clients.get(receiver);
		// receiver한테 초대 전하기(보내야할 것: protocol, sender, roomName)
		JJ_ChatRoom room = waitingRoom.getChatRoom(sender);
		receiverC.sendMessage(
				Protocol.SC_INVITE_RESULT + Protocol.DELEMETER + sender + Protocol.DELEMETER + room.getRoomName());
	}

	/**
	 * 방 나가기
	 * 
	 * @param nickName
	 */
	public void leaveRoom(String nickName) {
		// nickName에 해당하는 chatRoom 찾기
		JJ_ChatRoom chatRoom = waitingRoom.getChatRoom(nickName);
		// chatRoom에서 nickName 제거하기
		chatRoom.removeClient(nickName);

	}

	/**
	 * 전체 채팅
	 * 
	 * @param nickName
	 * @param message
	 */
	public void sendAllMessage(String nickName, String message) {
		// 모든 client 찾기
		Enumeration<JJ_Client> clientList = clients.elements();
		// 모든 client에게 message 전달하기
		while (clientList.hasMoreElements()) {
			JJ_Client client = clientList.nextElement();
			client.sendMessage(Protocol.SC_ALL_CHAT_RESULT + Protocol.DELEMETER + nickName + Protocol.DELEMETER
					+ message + Protocol.DELEMETER + simpleDateFormat.format(today));

		}
	}

	/**
	 * 방 없애기
	 * 
	 * @param nickName
	 */
	public boolean removeChatRoom(String nickName) {
		// 채팅방 안에 있는 다른 사람이 있는지 체크
		JJ_ChatRoom chatRoom = waitingRoom.getChatRoom(nickName);
		int chatRoomSize = chatRoom.getClientCount(); // 해당 chatRoom의 사용자 수 반환

		//방장인 경우
		if (chatRoom.getRoomOwner().equals(nickName)) {
			if(chatRoomSize == 1) {
				waitingRoom.removeChatRoom(chatRoom);
				return true;
			}
			// 있으면, 남아있는 사람 중 아무나를 방장으로 set
			List<String> chatRoomNickNames = chatRoom.getClients();// chatRoom 안의 client 반환
			chatRoom.setRoomOwner(chatRoomNickNames.get(1));
		}
		return false;
		
	}
	
	/**
	 * @param nickName
	 * @return 방에 들어가 있는 지
	 */
	public boolean isUserHasRoom(String nickName) {
		return (waitingRoom.getChatRoom(nickName) != null);
	}
	
	public String getUserChatRoomName(String nickName) {
		return waitingRoom.getChatRoom(nickName).getRoomName();
	}

}

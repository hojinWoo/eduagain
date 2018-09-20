package kr.or.kosta.chat.server;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * 
 * @author 최재민
 *
 */
public class JJ_WaitingRoom {

	JJ_ChatServer server; // 서버와 연결 
	Hashtable<String, JJ_ChatRoom> chatRooms; // 채팅방 관리 

	// default 생성자 
	public JJ_WaitingRoom() {

	}

	// 서버를 전달받았을 때의 생성자 
	public JJ_WaitingRoom(JJ_ChatServer server) {
		this.server = server; // 서버 초기화 
		chatRooms = new Hashtable<>(); // 채팅방 관리 초기화 

		// 더미데이터 
		chatRooms.put("멍텅구리1", new JJ_ChatRoom(1, "멍텅구리1", "소시지", 6)); // 방번호, 방이름, 방장, 수용가능인원
		chatRooms.put("멍텅구리2", new JJ_ChatRoom(2, "멍텅구리2", "사탕", 3)); 
		chatRooms.put("멍텅구리3", new JJ_ChatRoom(3, "멍텅구리3", "별", 5));
		
		chatRooms.get("멍텅구리1").addClient("소시지"); // 멍텅구리1이라는 방에 client 추가(소시지, 치약, 물통)
		chatRooms.get("멍텅구리1").addClient("치약");
		chatRooms.get("멍텅구리1").addClient("물통");

		chatRooms.get("멍텅구리2").addClient("사탕");
		
		chatRooms.get("멍텅구리3").addClient("별");
		
		chatRooms.get("멍텅구리1").setRoomOwner("소시지"); // 멍텅구리1 방의 방장: 소시지 
		chatRooms.get("멍텅구리2").setRoomOwner("사탕");
		chatRooms.get("멍텅구리3").setRoomOwner("별");
	}

	/**
	 * 현재 활성화 된 채팅방 반환
	 * 
	 * @return
	 */
	public List<JJ_ChatRoom> getActiveChatRoomList() { 
		List<JJ_ChatRoom> chatRoomList = new ArrayList<>();
		Enumeration<JJ_ChatRoom> e = chatRooms.elements(); // HashTable에 있는 채팅방을 chatRoomList에 추가 
		while (e.hasMoreElements()) {
			JJ_ChatRoom chatRoom = e.nextElement();
			chatRoomList.add(chatRoom);
		}
		if (chatRoomList.size() != 0) {
			return chatRoomList; // 현재 활성화된 chatRoomList를 반환 
		}
		return null;
	}

	/**
	 * 채팅방 이름 반환
	 * 
	 * @return
	 */
	public List<String> getChatRoomNames() {
		List<String> chatRoomNameList = new ArrayList<>();
		Enumeration<String> e = chatRooms.keys(); // 채팅방 이름으로 검색 
		while (e.hasMoreElements()) {
			String keys = e.nextElement();
			chatRoomNameList.add(keys);
		}
		return chatRoomNameList;
	}

	/**
	 * 채팅방 객체 반환
	 * 
	 * @return
	 */
	public Hashtable<String, JJ_ChatRoom> getChatRooms() {
		return chatRooms;
	}


	/**
	 * 방장 설정하기  
	 * 
	 * @param nickName
	 */
	public void setChatRoomOwner(String nickName) {
		chatRooms.get(nickName).setRoomOwner(nickName); 
	}

	
	
	

	/**
	 * 새로운 채팅방 추가
	 * 
	 * @param newChatRoomNumber
	 * @param newChatRoomName
	 * @param newChatRoomOwner
	 * @param newChatRoomCapacity
	 */
	public void addChatRoom(int newChatRoomNumber, String newChatRoomName, String newChatRoomOwner,
			int newChatRoomCapacity) {
		chatRooms.put(newChatRoomName,
				new JJ_ChatRoom(newChatRoomNumber, newChatRoomName, newChatRoomOwner, newChatRoomCapacity));
	}
	
	/**
	 * nickName으로 chatRoom 찾기 
	 * 
	 * @param nickName
	 * @return
	 */
	public JJ_ChatRoom getChatRoom(String nickName) {
		JJ_ChatRoom tempRoom = null;
		Enumeration<JJ_ChatRoom> tempRooms = chatRooms.elements();
		while (tempRooms.hasMoreElements()) {
			JJ_ChatRoom jj_ChatRoom = (JJ_ChatRoom) tempRooms.nextElement();
			if(jj_ChatRoom.getClientByNickname(nickName)) {
				tempRoom = jj_ChatRoom;
				break;
			}
		}
		return tempRoom;
	}
	
	/**
	 * client가 들어있는 방의 유저들을 return
	 */
	public List<String> getChatUserName(String nickName) {
		//해당 방의 유저를 return
		return getChatRoom(nickName).getClients();
		
	}
	
	/**
	 * 해당 채팅방 client 리스트 보여주기
	 * 
	 * @param roomName
	 */
	public List<String> getActiveChatRoomClientList(String roomName) {
//		List<String> clientNickNamelist = new ArrayList<>();
		JJ_ChatRoom chatRoom = chatRooms.get(roomName);
//		clientNickNamelist.addAll(chatRoom.getClientNickNames());
		return chatRoom.getClients(); 
	}
	
	/**
	 * 채팅방에 Client 입장
	 */
	public boolean addClientToChatRoom(String nickName, String roomName) {
		if(chatRooms.get(roomName).getClientCount() <  chatRooms.get(roomName).getRoomCapacity()) {
			chatRooms.get(roomName).addClient(nickName);
			return true; 
		}
		return false; 
	}

	/**
	 * 방 없애기 
	 * 
	 * @param chatRoom
	 */
	public void removeChatRoom(JJ_ChatRoom chatRoom) {
		chatRooms.remove(chatRoom.getRoomName());
	}
	
	
}

package kr.or.kosta.chat.server;

import java.util.ArrayList;
import java.util.List;

import kr.or.kosta.common.Protocol;

/**
 * 
 * @author 최재민
 *
 */
public class JJ_ChatRoom {

	private int roomNum; // 방 번호
	private String roomName; // 방 이름 
	private String roomOwner; // 방장
	private int roomCapacity; // 한 방당 최대 수용 가능한 인원 
	private List<String> clients; // 방 안에 있는 clients 리스트로 저장  

	// 디폴트 생성자 
	public JJ_ChatRoom() {
	}

	public JJ_ChatRoom(int roomNum, String roomName, String roomOwner, int roomCapacity) {
		this.roomNum = roomNum; // 생성 시 초기화 
		this.roomName = roomName;
		this.roomOwner = roomOwner;
		this.roomCapacity = roomCapacity;
		clients = new ArrayList<>(); // 방 안에 있는 clients도 마찬가지로 생성 시 초기화 
	}

	// getter, setter 메소드 
	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomOwner() {
		return roomOwner;
	}

	public void setRoomOwner(String roomOwner) { // 방장으로 지정 
		this.roomOwner = roomOwner;
	}

	public int getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(int roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	/**
	 * 방 안에 있는 사람 수 리턴하기
	 * 
	 * @return
	 */
	public int getClientCount() {
		return clients.size(); 
	}

	@Override
	public String toString() {
		return roomNum + Protocol.DELEMETER2 + roomName + Protocol.DELEMETER2 + roomOwner + Protocol.DELEMETER2
				+ roomCapacity + Protocol.DELEMETER2 + clients.size();
	}// 채팅 방에 대한 정보를 리턴할 때는 delemeter2를 사용해 일반적인 return과 구분 

	/**
	 * 해당 채팅방 client 리스트 반환하기
	 * 
	 * @return
	 */
	public List<String> getClients() { 
		return clients; 
	}

	/**
	 * 채팅방에 client 추가하기
	 */
	public void addClient(String nickName) {
		clients.add(nickName);
	}

	/**
	 * 해당 nickName을 가진 client 찾기 
	 * 
	 * @param nickName
	 * @return
	 */
	public boolean getClientByNickname(String nickName) {
		return clients.contains(nickName); 
	}

	/**
	 * 로그아웃 하거나 종료한 client 채팅방에서 삭제하기 
	 */
	public void removeClient(String nickName) { 
		clients.remove(nickName); 
	}
	
}

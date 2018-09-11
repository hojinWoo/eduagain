package kr.or.kosta.chat.common;
/**
 * 메시지 규격
 * SC : server to client, CS: client to Server
 * @author hojin
 *
 */
public interface Protocol {

	public static final String DELEMETER = "☆☆"; // 기본 구분자
	public static final String DELEMETER2 = "＠＠"; // 채팅방 리스트 반환 시 사용하는 구분자

	public static final int CS_LOGIN = 1000; // 닉네임 중복체크
	public static final int SC_LOGIN_RESULT = 1001; // 닉네임 중복체크 결과 반환
	public static final int CS_ENTER = 1002; // 입장 시 사용자 리스트에 추가

	public static final int SC_UPDATE_WAITINGROOM_USER_LIST = 2000; // 대기자 리스트 업데이트
	public static final int SC_UPDATE_CHATROOM_LIST = 2001; // 채팅방 리스트 업데이트

	public static final int CS_UPDATE_CHATROOM_USER = 2001; // 채팅방 클릭
	public static final int SC_UPDATE_CHATROOM_USER_LIST = 2001; // 해당 채팅방 참가자 업데이트

	public static final int CS_ROOM_NAME = 2002; // 채팅방 이름 중복체크
	public static final int SC_ROOM_NAME_RESULT = 2003; // 채팅방 이름 중복체크 결과 반환
	public static final int CS_ROOM_CREATE = 2004; // 새로운 채팅방 생성

	public static final int CS_MULTI_CHAT = 3000; // 다중 채팅 시작
	public static final int SC_MULTI_CHAT_RESULT = 3001; // 다중 채팅 결과 반환

	public static final int CS_DISCONNECT = 4000; // 종료 요청
	public static final int SC_DISCONNECT_RESULT = 4001; // 종료
}

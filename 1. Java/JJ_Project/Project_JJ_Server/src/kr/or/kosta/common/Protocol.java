package kr.or.kosta.common;

public interface Protocol {

	public static final String DELEMETER = "☆☆"; 		// 기본 구분자
	public static final String DELEMETER2 = "＠＠"; 	// 채팅방 리스트 반환 시 사용하는 구분자

	public static final int CS_LOGIN = 1000; 			// 닉네임 중복체크
	public static final int SC_LOGIN_RESULT = 1001; 	// 닉네임 중복체크 결과 반환
	public static final int CS_ENTER = 1002; 			// 입장 시 사용자 리스트에 추가

	public static final int SC_UPDATE_WAITINGROOM_USER_LIST = 2000; // 대기자 리스트 업데이트
	public static final int SC_UPDATE_CHATROOM_LIST = 2001; // 채팅방 리스트 업데이트
	public static final int CS_ROOM_NAME = 2002; // 채팅방 이름 중복체크
	public static final int SC_ROOM_NAME_RESULT = 2003; // 채팅방 이름 중복체크 결과 반환
	public static final int CS_ROOM_CREATE = 2004; // 새로운 채팅방 생성
	public static final int SC_ROOM_CREATE_RESULT = 2005;// 새로운 채팅방 생성 결과 반환
	public static final int CS_GETROOM_INFO = 2006; // 방에 참여한 인원 요청
	public static final int SC_GETROOM_INFO_RESULT = 2007; // 방에 참여한 인원 결과 반환 
	public static final int CS_REQUEST_ENTER_CHATROOM = 2008; // 방에 참여 요청
	public static final int SC_REQUEST_ENTER_CHATROOM_RESULT = 2009; // 방에 참여 요청 결과 반환 

	public static final int CS_ROOM_CHAT = 3000; // 다중 채팅 시작
	public static final int SC_ROOM_CHAT_RESULT = 3001; // 다중 채팅 결과 반환


	public static final int CS_SECRET_CHAT = 3004; // 귓속말 하기 
	public static final int SC_SECRET_CHAT_ROOM_RESULT = 3005;    // 채팅방에 있는 경우 귓속말 보내기
    public static final int SC_SECRET_CHAT_WAIT_RESULT = 3006;    // 대기방에 있는 경우 귓속말 보내기
	
    public static final int SC_UPDATE_ROOM_USER = 3007; // 채팅방의 유저 업데이트 
	
	public static final int CS_INVITE= 3008; // 맨 처음에 sender가 receiver에게 초대 요청하기
	public static final int SC_INVITE_RESULT= 3009; // receiver에게 sender로부터 초대 받았다고 전하기 
	public static final int CS_INVITE_ISACCEPT=3010; // receiver는 초대 요청에 응할(Accept)수도 있고 거절(Reject)할 수도 있음, 그 결과를 반환하기 
	public static final int SC_INVITE_ISACCEPT_RESULT=3011; // Accept하거나 Reject하는 결과를 sender에게 보내주기
	
	
	public static final int CS_LEAVEROOM = 3012; //방 나가기
    public static final int SC_LEAVEROOM_RESULT = 3013; //방 나가기 결과
    
	public static final int CS_ALL_CHAT = 3002; // 전체 채팅 시작
	public static final int SC_ALL_CHAT_RESULT = 3003; // 전체 채팅 시작


	public static final int CS_DISCONNECT = 4000; // 종료 요청
	public static final int SC_DISCONNECT_RESULT = 4001; // 종료
	public static final int CS_LOGOUT = 4002; // 로그아웃

}

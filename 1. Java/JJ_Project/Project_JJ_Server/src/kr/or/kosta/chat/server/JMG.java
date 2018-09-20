package kr.or.kosta.chat.server;

public class JMG {

	public static void main(String[] args) {

		JJ_ChatServer chatServer = new JJ_ChatServer(); // 서버 생성 
		try {
			chatServer.startUp(); // 서버 시작 
		} catch (Exception e) {
			System.out.println(e.getMessage()); // 오류 발생 시 메시지 받기-"[" + PORT + "] 포트 충돌이 일어났습니다. "
		}
	}

}

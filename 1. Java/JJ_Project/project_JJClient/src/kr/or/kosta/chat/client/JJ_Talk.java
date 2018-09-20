package kr.or.kosta.chat.client;

public class JJ_Talk {
	public static void main(String[] args) {
		JJ_ChatUI frame = new JJ_ChatUI("로그인");
		frame.setContents(); // 배치
		frame.setSize(400, 500);
		frame.setCenter();
		frame.eventRegist();
		frame.setVisible(true);
		
		JJ_ChatClient client = new JJ_ChatClient(frame);
		
		frame.setChatClient(client);
	}
}

package kr.or.kosta.chat.client;

public class KoTalk {

	public static void main(String[] args) {
		
		ChatFrame frame = new ChatFrame("Kotalk");
		
		frame.setContents(); // 배치
		frame.setSize(400, 500);
		frame.setCenter();
		frame.eventRegist();
		frame.setVisible(true);
		
		ChatClient chatClient = new ChatClient(frame);
		
		frame.setChatClient(chatClient);
	}
}
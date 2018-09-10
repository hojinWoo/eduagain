package kr.or.kosta.chat.common;
/**
 * 메시지 규격
 * SC : server to client, CS: client to Server
 * @author hojin
 *
 */
public interface Protocol {

	public static final String DELEMETER = "☆☆";
	
	
	public static final int CONNECT = 1000;
	public static final int CONNECT_RESULT = 1001;
	public static final int CONNECT_ALERT = 1002;
	public static final int CURRENT_USER = 1003;
	
	
	public static final int MULTICHAT = 2000; //chatting message
	public static final int DISCONNECT = 3000;
}

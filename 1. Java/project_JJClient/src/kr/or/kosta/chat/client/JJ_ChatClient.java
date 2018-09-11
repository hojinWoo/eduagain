package kr.or.kosta.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import kr.or.kosta.chat.common.Protocol;

public class JJ_ChatClient {
	
	public static final String SERVER = "192.168.0.117";
	public static final int PORT = 8888;
	
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	private boolean running;

	private JJ_ChatUI frame;
	
	public JJ_ChatClient() {}
	
	public JJ_ChatClient(JJ_ChatUI frame) {
		this.frame = frame;
	}
	
	public void connectServer() throws Exception {
		try {
			
			socket = new Socket(SERVER, PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			running = true;

		}catch (Exception e) {
			throw new Exception("서버를 찾을 수 없습니다..");
		}
		
	}
	
	public void sendMessage(String message) {
		if(out != null) {
			out.println(message);
			System.out.println("[Debug] : Send Message:  "+message);
		}
	}
	
	public void receiveMessage() {
		new Thread() {
			@Override
			public void run() {
				while (running) {
					String serverMessage = null;
					try {
						serverMessage = in.readLine();
						System.out.println("[Debug] : Receive Message: " + serverMessage);
						frame.process(serverMessage);
					} catch (IOException e) {
						System.out.println("네트워크가 단절되었습니다..");
						break;
					}
				}

				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
					}
				}
			}

		}.start();
	}
	
	public Socket getSocket() {
		return socket;
	}

}

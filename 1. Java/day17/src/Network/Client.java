package Network;
/**
 * 쓰레드를 활용한 클라이언트의 데이터 수신 및 처리
 * @author hojin
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	private boolean flag = false;
	
	public Client(Socket socket) throws IOException {
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
		flag = true;
	}
	public void recieve() {
		while(flag) {
			String clientMessage = null;
			try {
				clientMessage = in.readLine();
				System.out.println("받은 메시지 : " + clientMessage);
				if(clientMessage.equalsIgnoreCase("quit")) {
					break;
				}
				out.println(clientMessage);
				out.flush();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(out != null) {
			out.close();
		}
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}
	
	@Override
	public void run() {
		recieve();
	}
}

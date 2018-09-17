package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocketExample {
	
	private static final int port = 7777;
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;
		boolean running = true;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println(port + "포트에서 서버 실행");
			while(running) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+" client와 연결");
				in = socket.getInputStream();
				out = socket.getOutputStream();
				
//				int data = in.read();
//				System.out.println("client 수신 데이터 : " + data);
//				
//				//echo server
//				out.write(data);
				
				PrintWriter pw = new PrintWriter(out); //autoflush
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				Scanner sc = new Scanner(System.in);
				boolean flag = true;
				
				while(flag) {
					String clientMessage = br.readLine();
					System.out.println("받은 메시지 : " + clientMessage);
					if(clientMessage.equalsIgnoreCase("quit")) {
						break;
					}
//					String msg = sc.nextLine();
					pw.println(clientMessage);
					pw.flush();
				}
				
				br.close();
				pw.close();
				
				out.close();
				in.close();
				socket.close();
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//serverSocket은 닫으면 안 된다.
		}
	}
}

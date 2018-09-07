package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketExample {
//	public static final String ip = "www.naver.com";
//	public static final String domain = "115.91.131.92";
	
//	public static final String domain = "localhost";
	public static final String domain = "192.168.0.117";
	public static final int port = 7777;
	
	
	public static void main(String[] args) {
		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;
		try {
//			Socket socket = new Socket(InetAddress.getByName(ip), port); //ip추출
			socket = new Socket(domain, port); //ip추출
			System.out.println("-----------서버 연결 시작-----------");
			
			//Byte Stream
			in = socket.getInputStream();
			out = socket.getOutputStream();
			
//			out.write(10);
//			int data = in.read();
//			System.out.println("server로부터 받은 데이터 : " + data);
			
			PrintWriter pw = new PrintWriter(out, true); //autoflush
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			Scanner sc = new Scanner(System.in);
			while(true) {
				String msg = sc.nextLine();
				pw.println(msg);
				pw.flush();
				if(msg.equalsIgnoreCase("quit")) {
					break;
				}
				System.out.println("서버에서 다시 수신된 에코 데이터 : " + br.readLine());
			}
			
			br.close();
			pw.close();
			
		} catch (UnknownHostException e) {
			System.out.println("잘못된 사이트 주소입니다...");
		} catch (IOException e) {
			System.out.println("서버를 연결할 수 없습니다...");
		} finally {
			//socket만 닫아도 자동적으로 안에서 생성되었기 때문에 닫힘
//			try {
//				out.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				in.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			try {
				//이렇게 하면 말 없이 socket을 끊어버림.
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}

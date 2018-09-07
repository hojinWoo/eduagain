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

public class Server {
	
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
				Client client = new Client(socket);
				client.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

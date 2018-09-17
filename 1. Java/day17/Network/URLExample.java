package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

public class URLExample {
	public static void main(String[] args) {
		String urlString = "http://finance.daum.net:80"; //web server(program) 접근
		
		try {
			URL url = new URL(urlString); //parsing이 가능해진다
			System.out.println(url.getProtocol());
			System.out.println(url.getHost());
			
			//통신
			InputStream in = url.openStream(); //프로토콜이 https인 경우 error가 된다.
//			System.out.println(in);
//			System.out.println(in.read());
			
			//배열로 읽기
//			byte[] buffer = new byte[1024];	//network에서는 packet단위로 읽는 것이 가장 현명(1Kbyte)
//			int count = 0;
//			while ((count = in.read(buffer)) != -1) {
//				//실제 데이터로 출력
//				String text = new String(buffer, 0, count);
//				System.out.println(text);
//			}
			
			//문자 스트림
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String txt = null;
			while((txt = br.readLine()) != null) {
				System.out.println(txt);
			}
//			OutputStreamWriter osw = new OutputStreamWriter(System.out);
//			int i = 0;
//			while ((i = isr.read()) != -1) {
//				osw.write((char)i);
//			}
			
			
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, "서버를 찾을 수 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}

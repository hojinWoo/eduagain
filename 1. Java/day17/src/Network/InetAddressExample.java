package Network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * DNS와의 통신을 통해 IP 와 Domain 제공
 * @author hojin
 *
 */
public class InetAddressExample {
	public static void main(String[] args) throws UnknownHostException {
		//InetAddress는 예전에 있던 방식.. 4와 6버전 통합
		InetAddress ia = InetAddress.getLocalHost(); //factory method
		System.out.println(ia.getHostAddress());
	
		//getByName : domain 이름
		InetAddress ia2 = InetAddress.getByName("www.naver.com"); //실제 IP를 return
		System.out.println(ia2.getHostAddress());
		
		InetAddress[] ias = InetAddress.getAllByName("www.naver.com");
		for (InetAddress inetAddress : ias) {
			System.out.println(inetAddress.toString());
		}
	}
}

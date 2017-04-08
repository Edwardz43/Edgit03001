package tw.org.iii.ed;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class C40811 {

	public static void main(String[] args) {
		
		try {
			InetAddress[] ips = InetAddress.getAllByName("www.apple.com");
			for(InetAddress ip : ips){
			System.out.println(ip.getHostAddress());
			}
		} catch (UnknownHostException e) {
			System.out.println(e.toString());
		}		
	}
}

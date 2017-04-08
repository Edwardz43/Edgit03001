package tw.org.iii.ed;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class C40814 {

	public static void main(String[] args) {
		try {
				Socket socket = new Socket(InetAddress.getByName("1"), 9999);
				
				OutputStream out = socket.getOutputStream();
				out.write("哈囉, 布萊德".getBytes());
				out.flush();
				out.close();
				
				socket.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
	}
}

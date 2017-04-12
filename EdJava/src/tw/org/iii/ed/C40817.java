package tw.org.iii.ed;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
//TCP
public class C40817 {
	public static void main(String[] args){
		try {
			ServerSocket server = new ServerSocket(9999);
			Socket socket = server.accept();
			
			String urip = socket.getLocalAddress().getHostAddress();
			
			BufferedOutputStream bout = new BufferedOutputStream(
					new FileOutputStream("./dir2/iii.jpg"));
			BufferedInputStream bin = new BufferedInputStream(socket.getInputStream());
			byte[] buf = new byte[4096];int len;
			while((len = bin.read(buf))!=-1){
				bout.write(buf, 0, len);
			}
			bin.close();
			bout.flush();
			bout.close();
			
			server.close();
			System.out.println("收到"+urip+"的來信");
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
	}
}

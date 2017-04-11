package tw.org.iii.ed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class C40815 {
	public static void main(String[] args){
		for(;;){
		try {
			ServerSocket server = new ServerSocket(9999);
			
			Socket socket = server.accept();
			System.out.println(socket.getInetAddress().getHostAddress());
			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			
			int i;String line;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
			br.close();
			server.close();
			//System.out.println("ok");
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		}
		
	}
}

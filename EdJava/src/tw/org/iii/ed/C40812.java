package tw.org.iii.ed;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class C40812 {

	public static void main(String[] args) {
		// UDP / TCP
		String data = "有人在嗎 , 布萊德";
		byte[] sendData = data.getBytes();
		for(int i=0;i<10;i++){
			try {
				DatagramSocket socket = new DatagramSocket();
				DatagramPacket packet = new DatagramPacket(sendData, sendData.length,
						InetAddress.getByName("127.0.0.1"), 8888);
				socket.send(packet);
				socket.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
}

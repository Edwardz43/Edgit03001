package tw.org.iii.ed;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class C40806 {

	public static void main(String[] args) {
		try {
			DataInputStream din = new DataInputStream(new FileInputStream("./dir1/data1.dat"));
			int a = din.readInt();
			int b = din.readInt();
			String c1 = din.readUTF();
			String c2 = din.readUTF();
			din.close();
			System.out.println("=>" +a+":"+b+":"+c1+":"+c2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

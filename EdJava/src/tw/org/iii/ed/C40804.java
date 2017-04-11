package tw.org.iii.ed;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class C40804 {

	public static void main(String[] args) {
		int a = 1, b = 2; String c = "Brad"; String d = "資策會";
		try {
			FileOutputStream fout = new FileOutputStream("./dir1/data1.dat");
			DataOutputStream dout = new DataOutputStream(fout);
			dout.writeInt(a);
			dout.writeInt(b);
			dout.writeUTF(c);
			dout.writeUTF(d);
			fout.flush();
			fout.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

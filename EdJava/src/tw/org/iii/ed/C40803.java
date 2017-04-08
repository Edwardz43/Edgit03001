package tw.org.iii.ed;

import java.io.File;
import java.io.FileOutputStream;

public class C40803 {

	public static void main(String[] args) {
		String data = "Hello , Brad\nLine1\nLine2";
		File outFile = new File("./dir1/ed.txt");
		try {
			FileOutputStream fout = new FileOutputStream(outFile, true);
			fout.write(data.getBytes());
			fout.flush();
			fout.close();
		} catch (Exception e) {
			System.out.println(e.toString());;
		}
	}
}

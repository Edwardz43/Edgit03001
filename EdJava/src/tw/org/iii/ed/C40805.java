package tw.org.iii.ed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class C40805 {

	public static void main(String[] args) {
		// 1. read
		long start = System.currentTimeMillis();
		File readFile = new File("./dir1/brad.jpg");
		File saveFile = new File("./dir2/brad.jpg");
		// 2. write
		try {
			FileOutputStream fout = new FileOutputStream(saveFile);
			FileInputStream fin = new FileInputStream(readFile);
			int b;
			while((b = fin.read())!=-1){
				fout.write(b);
			}
			fin.close();
			fout.flush();
			fout.close();
			System.out.println("finish");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		System.out.println(System.currentTimeMillis()-start);
	}
}

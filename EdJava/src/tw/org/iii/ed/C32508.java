package tw.org.iii.ed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class C32508 {
	public static void main(String[] args) {
		File file = new File("./dir1/file1.txt");
		try{
			FileInputStream fin =
					new FileInputStream(file);
			byte[] buf = new byte[(int)file.length()];
			int len=fin.read(buf);
			System.out.print(new String(buf,0,len));
	
			fin.close();
		}catch(FileNotFoundException ee){
			System.out.println(ee.toString());
		}catch(IOException ee){
			System.out.println(ee.toString());
		}
	}
}

package tw.org.iii.ed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class C32506 {
	public static void main(String[] args) {
		File file = new File("./dir1/file1.txt");
		try{
			FileInputStream fin =
					new FileInputStream(file);
			int temp;
			while((temp=fin.read())!=-1){
				System.out.print((char)temp);
			}
	
			fin.close();
		}catch(FileNotFoundException ee){
			System.out.println(ee.toString());
		}catch(IOException ee){
			System.out.println(ee.toString());
		}
	}
}

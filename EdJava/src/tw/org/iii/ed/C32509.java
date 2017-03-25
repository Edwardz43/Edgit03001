package tw.org.iii.ed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class C32509 {
	public static void main(String[] args) {
		File file = new File("./dir1/file1.txt");
		try {
			FileReader reader = 
					new FileReader(file);
			int temp;
			while((temp=reader.read())!= -1){
				System.out.print((char)temp);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

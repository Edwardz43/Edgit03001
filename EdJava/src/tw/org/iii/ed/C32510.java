package tw.org.iii.ed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class C32510 {
	public static void main(String[] args) {
		File file = new File("./dir1/Book1.csv");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String temp;
			while((temp=br.readLine())!=null){
				System.out.println(br.readLine());
			}
			br.close();			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

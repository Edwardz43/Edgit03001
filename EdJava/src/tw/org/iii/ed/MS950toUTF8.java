package tw.org.iii.ed;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MS950toUTF8 {
	public static void convert(File src, File dst) {
		try {
			FileInputStream fis = new FileInputStream(src); 
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, "MS950"));
			StringBuffer sb = new StringBuffer();
			String tmp;
			while((tmp = br.readLine()) != null) {
				sb.append(tmp+"\n");
				System.out.println(tmp);
			}
			FileOutputStream fout = new FileOutputStream(dst);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout, "UTF-8"));
			bw.write(sb.toString());
			bw.flush();
			bw.close();
			//System.out.println(sb.toString());
		} catch (Exception e) {e.toString();}
	}
}

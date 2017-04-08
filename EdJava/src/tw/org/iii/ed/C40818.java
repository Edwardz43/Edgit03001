package tw.org.iii.ed;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class C40818 {

	public static void main(String[] args) {
		try {
			URL url = new URL(
					"http://thesuperslice.com/wp-content/uploads/2013/01/Michael-Jordan-by-Enterbay-01.jpg");
			URLConnection conn = url.openConnection();
			conn.connect();
			InputStream in = conn.getInputStream();
			
			BufferedOutputStream bout = new BufferedOutputStream(
					new FileOutputStream("./dir2/iii.jpg"));
			BufferedInputStream bin = new BufferedInputStream(in);
			byte[] buf = new byte[4096];int len;
			while((len = bin.read(buf))!=-1){
				bout.write(buf, 0, len);
			}
			bin.close();
			bout.flush();
			bout.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

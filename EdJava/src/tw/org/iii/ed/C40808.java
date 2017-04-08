package tw.org.iii.ed;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

//序列化
public class C40808 {

	public static void main(String[] args) {
		Student s1 = new Student("Brad", 50, 30, 24);
		Student s2 = new Student("Andy", 70, 80, 94);
		try {
			ObjectOutputStream oout = new ObjectOutputStream(
					new FileOutputStream("./dir1/ed.object"));
			oout.writeObject(s1);
			oout.writeObject(s2);
			oout.flush();
			oout.close();
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

package tw.org.iii.ed;

import java.io.FileInputStream;
import java.io.ObjectInputStream;


//序列化
public class C40809 {

	public static void main(String[] args) {
		try{
			ObjectInputStream oin = new ObjectInputStream(
					new FileInputStream("./dir1/ed.object"));
			Object obj1 = oin.readObject();
			Object obj2 = oin.readObject();
			oin.close();
			Student s1 = (Student)obj1;
			Student s2 = (Student)obj2;
			System.out.println(s1.getNanme()+":"+s1.sum()+":"+s1.avg());
			System.out.println(s2.getNanme()+":"+s2.sum()+":"+s2.avg());
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

}

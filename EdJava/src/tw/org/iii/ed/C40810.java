package tw.org.iii.ed;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class C40810 {

	public static void main(String[] args) {
		C408103 obj = new C408103();
		//序列
		try {
			ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream("./dir1/C408101"));
			oout.writeObject(obj);
			oout.flush();
			oout.close();
			System.out.println("ok1");
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
		//解序
		try{
			ObjectInputStream oin = new ObjectInputStream(new FileInputStream("./dir1/C408101"));
			C408103 obj2 = (C408103)oin.readObject();
			oin.close();
		}catch(Exception ee){
			System.out.println(ee.toString());
		}
	}
}
class C408101  implements Serializable{
	C408101(){System.out.println("C408101()");}
}
class C408102 extends C408101 {
	C408102(){System.out.println("C408102()");}
}
class C408103 extends C408102 {
	C408103(){System.out.println("C408103()");}
}

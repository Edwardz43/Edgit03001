package tw.org.iii.ed;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

public class C32505 {

	public static void main(String[] args) {
	new C325051().m1();	
	}
}
class C325051{
	void m1(){
		try{
			System.out.println("do something");
			//throw new Exception();
			return;
		}catch(Exception ee){
			System.out.println("catch");
		}finally{
			System.out.println("ok");
		}
		System.out.println("end");
	}
}
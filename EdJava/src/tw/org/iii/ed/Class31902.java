package tw.org.iii.ed;

public class Class31902 {

	public static void main(String[] args) {
		//Class3190201 c2 = new Class3190201();
		Class3190201 c3 = new Class3190203();
		Class3190201 c4 = new Class3190202();
		//c2.m1();
	    //c2.m2();
	    //System.out.println(c2.b);
		myf1(c4);
		myf1(c3);
	}
	static void myf1(Class3190201 c){
		c.m1();
	}

}
class Class3190201{
	int a;
	Class3190201(){a++; System.out.println("1");}
	void m1(){
		System.out.println("m1");
	}
}

class Class3190202 extends Class3190201{
	int b;
	Class3190202(){a++; System.out.println("2"); }
	void m1(){
		System.out.println("m11;b="+ b);
	}
	void m2(){
		System.out.println("m2");
	}
}

class Class3190203 extends Class3190201{
	
}
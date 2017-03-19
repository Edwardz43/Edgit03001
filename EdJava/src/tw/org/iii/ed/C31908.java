package tw.org.iii.ed;

public class C31908 {

	public static void main(String[] args) {
		C319081 c1 = new C319082();
		C319082 c2 = (C319082)c1;
		C319081 c3 = new C319083();
		C319082 c4 = (C319082)c3;
	}
}
class C319081{
	void m1 (){System.out.println("C319081:m1();");}
}
class C319082 extends C319081{
	void m1 (){System.out.println("C319082:m1();");}
	void m2 (){System.out.println("C319082:m2();");}
}
class C319083 extends C319081{
	void m1 (){System.out.println("C319083:m1();");}
	void m2 (){System.out.println("C319083:m2();");}
}

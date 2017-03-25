package tw.org.iii.ed;

public class C31904 {
	public static void main(String[] args) {
		C319041 c1 = new C319042();
		c1.m1();
	}
}
class C319041{
	C319043 m1(){return new C319043();}
	String m2(){return "";}
}
class C319042 extends C319041{
	C319044 m1(){return new C319044();}
}
class C319043{}
class C319044 extends C319043{
	public C319044() {
		System.out.println("C319044:m1();");
	}
}
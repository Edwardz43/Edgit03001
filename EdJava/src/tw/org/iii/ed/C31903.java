package tw.org.iii.ed;

public class C31903 {

	public static void main(String[] args) {
		C319031 c1 = new C319032();
		C319031 c2 = new C319033();
		c1.m2();
		c2.m2();
	}
}

abstract class C319031{
	C319031(){System.out.println("C319031();");}
	void m1(){System.out.println("m1();");}
	abstract void m2();
}
class C319032 extends C319031{
	void m2(){System.out.println("C319032:m2();");}
}

class C319033 extends C319031{
	void m2(){System.out.println("C319033:m2();");}
}
abstract class C319034 extends C319031{
	void m3(){}
	abstract void m4();
}
abstract class C319035{
	void m1(){}
}
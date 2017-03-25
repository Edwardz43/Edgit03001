package tw.org.iii.ed;
public class C32501 {
	public static void main(String[] args) {
//		C325011 obj1 = new C325011();
//		C325011.C3250111 obj2 = obj1.new C3250111();
//		obj2.M1();
//		C325011.m3();
		C325011.C3250112 obj3 = new C325011.C3250112();
	}
}
class C325011{
	C325011(){System.out.println("C325011");}
	void M1(){System.out.println("C325011:m1();");}
	void M2(){System.out.println("C325011:m2();");}
	static void m3(){}
	class C3250111{
		C3250111(){System.out.println("C3250111");}
		void M1(){
			System.out.println("C3250111");
			C325011.this.M1();	
		}	
	}
	static class C3250112{
		C3250112(){System.out.println("C3250112");}
		void M1(){
			System.out.println("C3250112:m1()");	
		}
	}
}



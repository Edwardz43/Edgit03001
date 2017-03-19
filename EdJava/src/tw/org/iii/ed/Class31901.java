package tw.org.iii.ed;

public class Class31901 {

	public static void main(String[] args) {
		//Class319013 c1 = new Class319013();
		//Class319013 c2 = new Class319013();
		Class319013.m1();
	}

}

class Class319011{
	static{System.out.println("s1");}
	Class319011(){System.out.println("Class319011gg");}
	Class319011(int a){System.out.println("Class319011");}
}

class Class319012 extends Class319011{
	static{System.out.println("s2");}
	Class319012(int a){
		//super();
		System.out.println("Class319012");
	}
}

class Class319013 extends Class319012{
	static{System.out.println("s3");}
	Class319013(){
		super(4);
		System.out.println("Class319013");
	}
	static void  m1(){
		System.out.println("m1();");
	}
}

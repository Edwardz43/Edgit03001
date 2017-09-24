package tw.org.iii.ed;

public class Question02 {
	public static void main(String[] args) {	
		byte a = 0;
		m(Math.min(a, a));
		m(Math.min(a, 1));
		m(Math.min(a, 1l));
	}
	
	static void m(byte b) {
		System.out.println("byte");
	}
	
	static void m(int i) {
		System.out.println("int");
	}
	
	static void m(long l) {
		System.out.println("long");
	}
}

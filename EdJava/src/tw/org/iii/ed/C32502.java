package tw.org.iii.ed;
// Exception 
public class C32502 {
	public static void main(String[] args) {
		int a = 10, b = 3;
		int[] c = {1,2,3,4};
		try{
			System.out.println(a/b);
			try{
				System.out.println(c[4]);
			}catch(Exception e){
				System.out.println("9487");
			}
		}catch(ArithmeticException ae){
			System.out.println("87");
		}catch(ArrayIndexOutOfBoundsException ee){
			System.out.println("xx");
		}catch (RuntimeException re){
			System.out.println("ok");
		}
		System.out.println("Hello World");
	}
}

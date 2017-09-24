package tw.org.iii.ed;

public class Question05 {
	public static void main(String[] args) {
		boolean b1 = false?false:true?false:true?false:true;
		boolean b2 = (false?false:true)?false:true?false:true;
		boolean b3 = ((false?false:true)?false:true)?false:true;
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
	}
}

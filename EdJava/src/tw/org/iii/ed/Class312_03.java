package tw.org.iii.ed;

public class Class312_03 {

	public static void main(String[] args) {
		String s1 = new String();
		byte[] b1 = {97,98,99,100,101,102,103,104,105};
		String s2 = new String(b1);
		String s3 = new String(b1 , 3 ,3);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		
		String s4 = "abcdefg";
		
		char c1 = s2.charAt(3);
		System.out.println(c1);
		System.out.println("abcdefgh".charAt(3));
		System.out.println(s4.indexOf('a'));
		
	}

}

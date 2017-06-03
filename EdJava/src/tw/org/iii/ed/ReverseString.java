package tw.org.iii.ed;
/*
Write a function that takes a string as input and returns the string reversed.
Example:
Given s = "hello", return "olleh".
*/

public class ReverseString {

	public static void main(String[] args) {
		String s = "hello";
		System.out.println(reverseString(s));
	}
	
	
	 public static String reverseString(String s) {
		 StringBuffer res = new StringBuffer();
		 return res.append(s).reverse().toString();
	 }
}


package tw.org.iii.ed;

public class Palindromes{
	public static void main (String[] arg){
		System.out.println(checkPalindromes("abcdcba"));
		System.out.println(checkPalindromes("abccba"));
		System.out.println(checkPalindromes("abcdc "));
    }
	static boolean checkPalindromes(String s){
		for(int i=0; i<s.length();i++){
			if(s.charAt(i)!=s.charAt(s.length()-i-1)){
				return false;
			}
		}
		return true;
	}
}

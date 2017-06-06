package tw.org.iii.ed;

public class PalindromeNumber {

	public static void main(String[] args) {
		System.out.println(checkPalindromes(12345));
		System.out.println(checkPalindromes(2002));
		System.out.println(checkPalindromes(-1000000001));
	}
	static boolean checkPalindromes(int x){
		if(x < 0) return false;
		String temp = "" + x;
		for(int i=0; i<temp.length()/2;i++){
			if(temp.charAt(i)!=temp.charAt(temp.length()-i-1)){
				return false;
			}
		}
		return true;
	}
}


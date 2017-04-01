package tw.org.iii.ed;
/*
Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, like "Google".

Otherwise, we define that this word doesn't use capitals in a right way.
*/
public class DetectCapital{
	public static void main(String[] args){
		String s = "USA";
		System.out.println(detect(s));
		String s1 = "Usa";
		System.out.println(detect(s1));
		String s2 = "usa";
		System.out.println(detect(s2));
		String s3 = "uSA";
		System.out.println(detect(s3));
	}
	static boolean detect (String s){
			return s.matches("[A-Z]*|[a-z]*|[A-Z][a-z]*");	
	}
}

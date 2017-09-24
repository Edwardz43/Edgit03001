package tw.org.iii.ed;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class RansomNote {
	public static void main(String[] args) {
		String str1 = "axvvb";
	    String str2 = "vvaaxxb";
	    boolean bool = new Solution().canConstruct(str1, str2);
	    System.out.println(bool);
	}
}

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
    	int[] charArray = new int[26];
    	for(int i = 0; i < magazine.length(); i++) {
    		charArray[magazine.charAt(i) - 'a']++;
    	}
    	
    	for(int i = 0; i < ransomNote.length(); i++) {
    		if(--charArray[ransomNote.charAt(i) - 'a'] < 0) {
    			return false;
    		}
    	}
    	return true;
    	
    }
}

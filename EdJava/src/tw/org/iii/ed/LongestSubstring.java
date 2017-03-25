package tw.org.iii.ed;

import java.util.HashMap;

import javax.swing.JOptionPane;

/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. 

Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 
 */
public class LongestSubstring {  
	 
	public static void main(String[] args){
		String s =JOptionPane.showInputDialog("Enter string :");
		JOptionPane.showMessageDialog(null, "Your number :"+s+"\nAnswer :"
		+lengthofLongestSubstring(s));
	}
	static int lengthofLongestSubstring(String s){
		HashMap<Character, Integer> map = new HashMap<>();
		int max=0;
		for(int i=0, j=0; i<s.length();i++){	
			if(map.containsKey(s.charAt(i))){
				j=Math.max(j, map.get(s.charAt(i))+1);
			}
			map.put(s.charAt(i), i);
			max = Math.max(max,i-j+1);
		}
		return max;
	}
}  


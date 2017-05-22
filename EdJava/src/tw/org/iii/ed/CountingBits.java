package tw.org.iii.ed;

/*
Given a non negative integer number num. 
For every numbers i in the range 0 ≤ i ≤ num 
calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].


Follow up:
It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */


public class CountingBits {

	public static void main(String[] args) {
	// 5: 0=>0  1=>1  2=>10  3=>11  4=>100  5=>101 6=>110
		int[] n = countBits(64);
		
		
		for(int i=0; i< n.length; i++) System.out.print(n[i] + ",");
	}
	
	public static int[] countBits(int num) {
		int[] result = new int[num+1];
		for (int i =1; i<=num; i++)result[i] = result[i/2] + (i % 2); 
		return result;
		
    }

}


package tw.org.iii.ed;

/*Given an array of integers,
 * every element appears twice except for one. 
 * Find that single one.
 */

public class SingleNumber{
	public static void main(String[] args){
		int[] input = new int[]{1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1};
		isSingleNumber(input);
	}
	static void isSingleNumber(int[] n){
		int result=0;
		for(int v: n) result^=v;
		System.out.println(result);
	}
}

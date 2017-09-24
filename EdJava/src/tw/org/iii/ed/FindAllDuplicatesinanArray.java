package tw.org.iii.ed;

import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicatesinanArray {
	
	public static void main(String[] args) {
		List<Integer> res = findDuplicates(new int[] {4,3,2,7,8,2,3,1, 10, 9, 11, 12, 12, 13, 14});
		for(int n : res) {
			System.out.println(n);
		}
	
	}
	public static List<Integer> findDuplicates(int[] nums) {
        long startTime = System.currentTimeMillis();
		List<Integer> res = new ArrayList<>();
        int n = nums.length;
        boolean[] check = new boolean[n];
        for (int i = 0; i < nums.length; ++i) {
           if(check[nums[i] - 1]) {
        	   res.add(nums[i]);   
           }else {
        	   check[nums[i] - 1] = true;
           }
        }
        System.out.println("time : "+(System.currentTimeMillis() - startTime));
        return res;
    }
}


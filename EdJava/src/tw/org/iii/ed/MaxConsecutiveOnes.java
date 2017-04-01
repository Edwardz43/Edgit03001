package tw.org.iii.ed;

public class MaxConsecutiveOnes{
	public static void main(String[] args){
		int[] input = {1,1,0,1,1,1,0,1,1,1,1,0,0,1,0,1,1,0,1,1,1,1,1,0,1,0,1};
		System.out.println(countCon(input));
	}
	static int countCon(int[] input){
		int count = 0, j=0;
		for(int v : input) j = Math.max(j,count=v==0?0:++count);
		return j;
	}
}


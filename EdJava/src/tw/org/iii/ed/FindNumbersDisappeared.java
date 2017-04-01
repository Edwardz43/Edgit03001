package tw.org.iii.ed;

public class FindNumbersDisappeared{
	public static void main(String[] args){
		int[] input = new int[]{4,3,2,7,8,2,3,1};
		disPear(input);
	}
	static void disPear(int[] input){
		boolean[] isRepeat = new boolean[input.length];
		for(int i=0;i<isRepeat.length;i++){
			isRepeat[input[i]-1]=true;
		}
		for(int i=0;i<isRepeat.length;i++){
			System.out.print(isRepeat[i]==false?i+1+" ":"");
		}
	}
}

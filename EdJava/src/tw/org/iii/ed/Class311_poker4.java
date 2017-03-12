package tw.org.iii.ed;

import java.util.Arrays;

public class Class311_poker4 {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int[] poker = new int[52];
		for(int i=0;i<52;i++){
			poker[i]=i;
		}
		for(int i=0; i<52; i++){		
			int j = (int)(Math.random()*52);
			int temp = poker[j];
			poker[j] = poker[i];
			poker[i] = temp;
		}
		//發牌=> 4players=> 13cards
		int[][] player = new int[4][13];
		for(int i=0;i<52;i++){
			player[i%4][i/4]= poker[i];
		}
		//攤牌
		String[] suits = {"黑桃","紅心","方塊","梅花"};
		String[] symbol = {"A","2","3","4","5","6","7","8","9","10","J","Q","k"};
		for(int[] cards : player){
			//排序
			Arrays.sort(cards);
			for(int card : cards){
				System.out.print(suits[card/13]+symbol[card%13]+"   ");
			}
			System.out.println();
		}
		
		
//	    for(int i=0;i<52;i++){
//	    	System.out.println(poker[i]+1);
//	    }
//		System.out.println("耗時"+(System.currentTimeMillis()-start));
	}
}

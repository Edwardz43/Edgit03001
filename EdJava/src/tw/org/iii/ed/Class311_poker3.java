package tw.org.iii.ed;

public class Class311_poker3 {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		boolean[] check = new boolean[52];
		int temp;int counter=0;
		int[] poker = new int[52];
		for(int i=0; i<52; i++){		
			do{
				temp = (int)(Math.random()*52);
				counter++;
			}while(check[temp]);
		
			poker[i]=temp;
			check[temp] = true;
		}
	    for(int i=0;i<52;i++){
		System.out.println(poker[i]+1);
	    }
		System.out.println("次數"+counter);
		System.out.println("耗時"+(System.currentTimeMillis()-start));
	}
}

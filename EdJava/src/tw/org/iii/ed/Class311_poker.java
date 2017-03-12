package tw.org.iii.ed;

public class Class311_poker {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		final int n = 52;
		int[] poker = new int[n];
		boolean isRepeat; int temp;
		for(int i=0; i<n; i++){

			do{
				temp = (int)(Math.random()*52+1);	
				//檢查機制
				isRepeat = false;
				for(int j=0;j<i;j++){
					if(poker[j]==temp){
						isRepeat = true;
						break;
					}
				}
			}while (isRepeat);
			poker[i]=temp;
			System.out.println(poker[i]);
		}
		//System.out.print(System.currentTimeMillis()-start );
	}
}

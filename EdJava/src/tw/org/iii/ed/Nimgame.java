package tw.org.iii.ed;

public class Nimgame{
	public static void main(String[] args){
		Nimgame(65);
	}
	static void Nimgame(int n){
		if(n % 4 !=0){
			int remain = n%4;
			System.out.print(remain);
			for(int i =0; i<(n-remain)/4;i++){
				System.out.print((i==10)?"\n":",1,3");
				//(1, 3) can replace by (2, 2) or (3, 1)
			}
		}else{
			System.out.println("No way!");
		}
	}
}


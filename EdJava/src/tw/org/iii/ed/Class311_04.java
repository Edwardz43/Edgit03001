package tw.org.iii.ed;

public class Class311_04 {

	public static void main(String[] args) {
		int result;
		for(int k = 0; k < 4; k++ ){
			for(int j = 1; j <= 9; j++){	
				for(int i = 2; i <= 5; i ++){
					int newi = i + k* 4;
					result = newi * j;
					System.out.print(newi + " x " + j + " = " + result +"\t");			
				}
				System.out.println();
				
			}
			System.out.println("-------------------------------------------------------------");
		}	
	}

}

package tw.org.iii.ed;

public class TEST {

	public static void main (String[] args){
		int intScore = (int)(Math.random() * 100 +1);
		System.out.println(intScore);
		if(intScore >= 90){
			System.out.println("A");
		}else if(intScore >= 80){
			System.out.println("B");
		}else if (intScore >= 60) {
			System.out.println("C");
		}else if (intScore >= 60) {
			System.out.println("D");
		}else {
			System.out.println("E");
		} 
	}
}

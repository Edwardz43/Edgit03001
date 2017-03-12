package tw.org.iii.ed;

public class Class312_02 {

	public static void main(String[] args) {
		int a =1;
		int sum = 0;
		while(a<=100){
			sum += a;
			a++;
		}
		System.out.println("1+2+3+...+ ="+sum);
		System.out.println("------");
		sum = 0;
		a = 1;
		for(;a<=100;){
			sum += a;
			a++;
		}
		
		System.out.println("1+2+3+...+ ="+sum);
	}
}

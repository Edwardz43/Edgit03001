package tw.org.iii.ed;

public class Test1 {

	public static void main(String[] args) {
		double a = 784;
		for(int i=0; i<60; i++){
		System.out.println((int)((Math.cos(Math.toRadians(i*6.0-90)))*150));
		}
	}

}

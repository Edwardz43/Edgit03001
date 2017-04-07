package tw.org.iii.ed;

public class DemoDivision {
	public static void main(String[] args) throws Exception {
		division(126, 13);
	}
	static void division(int a, int b){
		double e = Math.log(a) - Math.log(b);
		int q = (int)(Math.floor(Math.pow(Math.E, e)));
		int r = a- b* q;
		System.out.println(a + "/" + b + " = " + q+"....."+r);
	}
}

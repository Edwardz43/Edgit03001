package tw.org.iii.ed;

public class DigitSum {
	public static void main(String[] args) {
		int result = getSum(32574);
		System.out.println(result);
	}
	
	static int getSum(int i) {
		return i < 10 ? i : i % 10 + getSum(i/10);
	}
}

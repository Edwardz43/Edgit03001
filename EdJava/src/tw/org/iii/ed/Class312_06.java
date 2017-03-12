package tw.org.iii.ed;

public class Class312_06 {

	public static void main(String[] args) {
		Bike b1 = new Bike();
		Bike b2 = new Bike();
		System.out.println(b1.getSpeed());
		b1.upSpeed();
		b1.upSpeed();
		b1.upSpeed();
		b1.upSpeed();
		System.out.println(b1.getSpeed());
	}
}

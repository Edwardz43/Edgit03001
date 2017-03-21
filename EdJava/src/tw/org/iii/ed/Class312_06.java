package tw.org.iii.ed;

public class Class312_06 {

	public static void main(String[] args) {
		Bike b1 = new Bike();
		Scooter s1 = new Scooter();
		
		
		
		System.out.println(b1.getSpeed());
		System.out.println(s1.getSpeed());
		b1.upSpeed();
		b1.upSpeed();
		b1.upSpeed();
		b1.upSpeed();
		
	
		s1.upSpeed();
		s1.upSpeed();
		s1.upSpeed(1);
		s1.upSpeed(2);
		System.out.println(b1.getSpeed());
		System.out.println(s1.getSpeed());
	}
}

package tw.org.iii.ed;

public class Scooter extends Bike{
	Scooter(){
		//super();
		System.out.println("Scooter()");
	}
	void upSpeedv2(){
		super.upSpeed();
		speed = (speed<1)?1.5:(speed *3.2);	
	}
}

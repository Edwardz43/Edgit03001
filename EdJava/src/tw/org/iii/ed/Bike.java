package tw.org.iii.ed;

public class Bike extends Object{
	double speed;
	int a;  // a=0
	static int counter;   // b=0
	
	
	Bike(){
		this(0);
		System.out.println("_____");
	}
	
	Bike(int s){
		this(s*1f);
		counter++;
		//System.out.println("gg");
		//s*1f => convert to float 
	}
	Bike(double s){
		speed = s;
		a++;
		counter++;
		
		System.out.println("新車出廠!");
		//System.out.println("cc");
	}
	
	Bike(float s){
		this(s*1.0);
		//System.out.println("kk");
		//s*1.0 => convert to double
	}
	
	// overloading的範例 => 同方法  參數不同  產生不同結果
	void upSpeed(){
		speed = (speed < 1?1:speed * 1.2);
	}

	void upSpeed(int gear){
		speed = (speed < 1?1:speed * (1.2+gear));
	}
	
	void downSpeed(){
		speed *= 0.7;
	}
	
	double getSpeed(){
		return speed;
	}
}

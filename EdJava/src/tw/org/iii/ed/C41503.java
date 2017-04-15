package tw.org.iii.ed;

public class C41503 {

	public static void main(String[] args) {
		Brad711 obj1 = new Brad711();
		obj1.setDaemon(true);
		obj1.start();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {}
		System.out.println("ok");
		return;
	}
}
class Brad711 extends Thread{
	@Override
	public void run() {
		for(int i=0; i<10; i++){
			System.out.println(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
	}
}

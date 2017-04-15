package tw.org.iii.ed;

public class C41502 {

	public static void main(String[] args) {
		Thread2 t2 = new Thread2("A");
		Thread tt2 = new Thread(t2);
		tt2.start();
		
		Thread2 t3 = new Thread2("B");
		Thread tt3 = new Thread(t3);
		tt3.start();
	}
}
class Thread2 implements Runnable{
	String name;
	public Thread2(String name) {
		this.name = name;
	}
	@Override
	public void run(){
		for (int i=0; i<10; i++){
			System.out.println(name +" : "+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

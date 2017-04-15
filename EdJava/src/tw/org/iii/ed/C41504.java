package tw.org.iii.ed;

import java.util.Timer;
import java.util.TimerTask;

public class C41504 {
	private static Timer timer;
	
	public static void main(String[] args) {
		timer = new Timer();
		timer.schedule(new MyTask("A"), 1000, 500);
		timer.schedule(new MyTask("B"), 1800, 300);
		timer.schedule(new MyStop(), 10000);
		System.out.println("main");
	}
	private static class MyStop extends TimerTask{
		@Override
		public void run() {
			timer.cancel();
			}
		}	
}
class MyTask extends TimerTask{
	private String name;
	private int i;
	
	MyTask(String name){
		this.name = name;
	}
	
	@Override
	public void run() {
		System.out.println(name +" :"+ i++);
		
	}
}

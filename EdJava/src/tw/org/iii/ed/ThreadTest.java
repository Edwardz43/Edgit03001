package tw.org.iii.ed;

public class ThreadTest {
	static int x = 0;
	static int n = 5;
	private static Thread2[] t = new Thread2[n];
	private static Thread[] tt = new Thread[n];
	
	public static void main(String[] args) {
			for(int i=0; i<n; i++){
				t[i] = new Thread2("t"+(i+1));
				tt[i] = new Thread(t[i]);
			}
		
			for(int i=0; i<n; i++){
				tt[i].start();
			}
	}
	static class Thread2 implements Runnable{
		String name;
		Thread2(String name){
			this.name = name;
		}
		@Override
		public void run() {
			for(int i=0; i<5 ;i++){
				x = x + 1;
				System.out.println(name + ":" + x);
			}	
		}
	}
}


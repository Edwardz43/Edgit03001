package tw.org.iii.ed;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class MyClock extends JLabel{
	private Timer timer;
	private int i;
	
	public MyClock(){
		timer = new Timer();
		timer.schedule(new MyClockTask(), 0, 1000);
	}
	private class MyClockTask extends TimerTask{
		@Override
		public void run() {
			Calendar now = Calendar.getInstance();
			int hh =now.get(Calendar.HOUR_OF_DAY);
			int mm = now.get(Calendar.MINUTE);
			int ss = now.get(Calendar.SECOND);;
			setText( hh+":"+ mm+":"+ss);
		}
	}
}

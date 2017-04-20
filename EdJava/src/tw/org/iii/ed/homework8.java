package tw.org.iii.ed;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;




public class homework8  extends JFrame{
	private MyPool2 mypool; 
	homework8(){
		setLayout(new BorderLayout());
		mypool=new MyPool2();

		add(mypool,BorderLayout.CENTER);
		
		setSize(350,350);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		new homework8();
	}
}



  class MyPool2 extends JPanel {
	  //在這邊宣告 全區變數
	  private  int yy,mm,ss, sss;
	  //
	  private Timer a;
	  private Time  b;
	  int x=150,y=150;
	 
	  MyPool2(){
		  setBackground(Color.BLACK);
		  a=new Timer();
		  b=new Time();
		  a.schedule(b, 0,10);

	  }
	  
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);			
			g.setColor(Color.WHITE);
			//加上毫秒判斷
			g.drawLine(x,y,(int) (x+100*Math.sin((ss+sss/1000.0)*Math.PI/300)),(int)((y-100*Math.cos((ss+sss/1000.0)*Math.PI/300))));
			g.setColor(Color.BLUE);
			g.drawLine(x, y,(int)(x+80*Math.sin(mm/30.0*Math.PI)), (int)(y-80*Math.cos(mm/30.0*Math.PI)) );
			g.setColor(Color.CYAN);
			g.drawLine(x,y,(int)(x+55*Math.sin( (yy+mm/60.0) * Math.PI/6) ), (int)(y-55*Math.cos( (yy+mm/60.0) * Math.PI/6) ));

		}
		// 畫1-12 和 每秒的刻度
		@Override
		public void paint(Graphics g) {
			String number[] = {"12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" };
			super.paint(g);
			g.setColor(Color.BLUE);
			for(int i=0; i<60; i++){
						g.drawLine((int)(x-115*Math.cos(i*Math.PI/30)),
						(int)(y+115*Math.sin(i*Math.PI/30)),
						(int)(x-120*Math.cos(i*Math.PI/30)),
						(int)(y+120*Math.sin(i*Math.PI/30)) );
			}

			for(int i=0; i<12; i++){
						g.setColor(Color.RED);
						g.drawLine( (int)(x-110*Math.cos(i*Math.PI/6)),
						(int)(x+110*Math.sin(i*Math.PI/6)),
						(int)(x-120*Math.cos(i*Math.PI/6)),
						(int)(y+120*Math.sin(i*Math.PI/6)));
						g.setColor(Color.white); 
						g.drawString(number[i], (int)(x+135*Math.sin(i*Math.PI/6))-5,(int)(x-135*Math.cos(i*Math.PI/6))+5 );
			}
		}

//現在的時間
  class Time extends TimerTask{
	@Override
	public void run() {
		//calendar放在run裡面  這樣時間才會更新
		Calendar now = Calendar.getInstance();
		yy=now.get(Calendar.HOUR_OF_DAY);
		mm=now.get(Calendar.MINUTE);
		ss=now.get(Calendar.SECOND)*10;
		//加一個  抓毫秒
		sss= now.get(Calendar.MILLISECOND)*10;
		//測試用
		System.out.println(yy+":"+mm+":"+ss+":"+sss);
		repaint();
	}
 }	  
 }
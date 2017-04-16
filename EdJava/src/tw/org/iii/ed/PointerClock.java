package tw.org.iii.ed;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class PointerClock extends JPanel{
	private Pointer secPointer, minPointer, hrPointer;
	private Timer timer;
	private int viewW, viewH;
	//LinkedList<Pointer> pointers;
	
	public PointerClock(){
		setSize(800, 640);
		secPointer = new Pointer();
		minPointer = new Pointer();
		hrPointer = new Pointer();
		timer = new Timer();
		//pointers= new LinkedList<Pointer>();
		timer.schedule(secPointer, 1000 , 1000);
		timer.schedule(minPointer, 1000 , 60*1000);
		timer.schedule(hrPointer, 1000 , 60*60*1000);	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		viewW = getWidth(); viewH = getHeight();
		//System.out.println(viewW+"+"+viewH);
		
		//背景色
		g2d.setColor(Color.gray);
		g2d.fillRect(0, 0, viewW, viewH);
		
		//畫圓
		g2d.setStroke(new BasicStroke(10));
		g2d.setColor(Color.black);
		g2d.drawOval(viewW/2-200, viewH/2-200, 400, 400);
		System.out.println(viewW/2+"+"+viewH/2);
		
		//刻度
		g2d.setStroke(new BasicStroke(10));
		g2d.setColor(Color.black);
	
		for(int i=0; i<60; i++){
		g2d.drawLine(392 + (int)((Math.cos(Math.toRadians(i*6.0-90)))*200), 301 + (int)((Math.sin(Math.toRadians(i*6.0-90)))*200)
				, 392 + (int)((Math.cos(Math.toRadians(i*6.0-90)))*180), 301 + (int)((Math.sin(Math.toRadians(i*6.0-90)))*180));
		}
		//指針
		//秒針
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(Color.red);
		g2d.drawLine(viewW/2, viewH/2, secPointer.x, secPointer.y);
		
		//分針
		g2d.setStroke(new BasicStroke(10));
		g2d.setColor(Color.blue);
		g2d.drawLine(viewW/2, viewH/2, minPointer.x, minPointer.y);
		
		//時針
		g2d.setStroke(new BasicStroke(13));
		g2d.setColor(Color.yellow);
		g2d.drawLine(viewW/2, viewH/2, hrPointer.x, hrPointer.y);	
	}
	
	private class Pointer extends TimerTask{
		private int x, y, n;	
		Pointer(){
			x=392; y=151; n=0; 
		}
		
		@Override
		public void run() {
			x = 392 + (int)((Math.cos(Math.toRadians(n*6.0-90)))*150);
			y = 301 + (int)((Math.sin(Math.toRadians(n*6.0-90)))*150);
			System.out.println("x:"+x+","+"y:"+y+", n:"+n);
			n++;
			
			repaint();
		}
		
	}
}

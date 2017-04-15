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
	private Pointer pointer;
	private Timer timer;
	private int viewW, viewH;
	//LinkedList<Pointer> pointers;
	
	public PointerClock(){
		pointer = new Pointer(768/2,602/2);
		timer = new Timer();
		//pointers= new LinkedList<Pointer>();
		timer.schedule(pointer, 0 , 1000);
		
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
		g2d.setColor(Color.orange);
		g2d.drawOval(viewW/2-200, viewH/2-200, 400, 400);
		
		
		//指針
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(Color.red);
		g2d.drawLine(viewW/2, viewH/2, pointer.x, pointer.y);
		
		
	}
	
	private class Pointer extends TimerTask{
		private int x, y, dx, dy;
		
		Pointer(int x, int y){
			
		}
		
		@Override
		public void run() {
			
			repaint();
		}
		
	}
}

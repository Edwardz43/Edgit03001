package tw.org.iii.ed;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class MyPool3 extends JPanel{
	private Ship ship, ship2;
	private Timer timer;
	private int viewW, viewH;
	
	public MyPool3(){
		ship = new Ship(0,0);
		ship2 = new Ship(0,0);
		timer = new Timer();
		timer.schedule(new ViewTask(),0, 30);
		timer.schedule(ship, 200, 10);
		timer.schedule(ship2, 500, 10);
//		addMouseMotionListener(new MyMouseAdapter());
	}
//	private class MyMouseAdapter extends MouseAdapter{
//		@Override
//		public void mouseMoved(MouseEvent e) {
//			requestFocus();
//			ship.x = e.getX()-20; ship.y = e.getY()-20;
//		}
//	}
	
	private class createBall extends TimerTask{
		@Override
		public void run() {
		}
	}
	
	private class ViewTask extends TimerTask{
		@Override
		public void run() {
			repaint();	
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d= (Graphics2D)g;
		
		viewW = getWidth(); viewH=getHeight();
		
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, viewW, viewH);
		
		g2d.setColor(Color.red);
		g2d.fillOval(ship.x, ship.y, 40, 40);
		g2d.setColor(Color.white);
		g2d.fillOval(ship2.x, ship2.y, 40, 40);
		
		g2d.setColor(Color.yellow);
		g2d.drawLine(viewW/2, viewH/2, ship.x+20, ship.y+20);
		g2d.drawLine(viewW/2, viewH/2, ship2.x+20, ship2.y+20);
		
	}
	
	private class Ship extends TimerTask{
		int x, y, dx, dy;
		Ship(int x, int y){
			this.x = x; this.y = y; dx = dy = (int)(Math.random()*3+5);
		}
		@Override
		public void run() {
			if(x < 0 || x + 40 > viewW){
				dx *= -1;
			}
			if(y < 0 || y + 40 > viewH){	
				dy *= -1;
			}
			x += dx;
			y += dy;
			repaint();
		}
	}
}

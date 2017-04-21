package tw.org.iii.ed;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class MyPool2 extends JPanel{
	private Ball ball;
	private Ship ship;
	private Timer timer;
	private int viewW, viewH;
	private int shipX, shipY;
	LinkedList<Ball> balls;
	
	public MyPool2(){
		ship = new Ship(viewW, viewH);
		balls = new LinkedList<>();
		timer = new Timer();
		timer.schedule(new ViewTask(),0, 30);
		timer.schedule(new createBall(), 0 , 300);
		timer.schedule(ship, 200, 100);
		addMouseListener(new MyMouseAdapter());
	}
	private class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mouseEntered(MouseEvent e) {
			shipX = e.getX(); shipY = e.getY();
			System.out.println(e.getX()+","+e.getY());
			
			
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			shipX = e.getX(); shipY = e.getY();
			System.out.println(e.getX()+","+e.getY());
		}
		@Override
		public void mouseExited(MouseEvent e) {
			shipX = e.getX(); shipY = e.getY();
			System.out.println(e.getX()+","+e.getY());
		}
	}
	
	private class createBall extends TimerTask{
		@Override
		public void run() {
			ball = new Ball((int)(Math.random()*viewW), (int)(Math.random()*viewH));
			balls.add(ball);
			timer.schedule(ball,1000, (int)(30 + Math.random()*70));
			repaint();
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
		g2d.fillOval(shipX, shipY, 20, 20);
		
		g2d.setColor(Color.yellow);
		
		try{
			for(Ball ball : balls){
				g2d.fillOval(ball.x, ball.y, 10, 10);
			}
		}catch(Exception ee){}
	}
	
	private class Ball extends TimerTask{
		int x, y, dx, dy;
		Ball(int x, int y){
			this.x = x; this.y = y; 
			dx = (int)(10*Math.random()+1)*(Math.random()+0.5>1?1:-1); 
			dy = (int)(10*Math.random()+1)*(Math.random()+0.5>1?1:-1);
		}
		@Override
		
		public void run() {
			x += dx;
			y += dy;
			repaint();
		}
		
	}
	private class Ship extends TimerTask{
		int x, y;
		Ship(int x, int y){
			this.x = x; this.y = y; 
		}
		@Override
		public void run() {
			x = shipX;
			y = shipY;
			repaint();
		}
		
	}
	
	
}

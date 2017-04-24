package tw.org.iii.ed;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class MyPool2 extends JPanel{
	private Ball ball;
	private Ship ship;
	private Timer timer;
	private int viewW, viewH;
	private LinkedList<Ball> balls;
	private boolean isGameOver;
	
	public MyPool2(){
		ship = new Ship(viewW, viewH);
		balls = new LinkedList<>();
		timer = new Timer();
		timer.schedule(new ViewTask(),0, 30);
		timer.schedule(new createBall(), 0 , 100);
		timer.schedule(ship, 200, 10);
		isGameOver = false;
		addMouseMotionListener(new MyMouseAdapter());
	}
	private class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
			requestFocus();
			ship.x = e.getX(); ship.y = e.getY();
		}
	}
	
	private class createBall extends TimerTask{
		@Override
		public void run() {
			if(isGameOver==false){
				int n = (int)(Math.random()*4);
				switch(n){
				case 1:
					ball = new Ball((int)(Math.random()*viewW),0);
					balls.add(ball);
					timer.schedule(ball,1000, (int)(10 + Math.random()*70));
					repaint();
					break;
				case 2:
					ball = new Ball(0,(int)(Math.random()*viewH));
					balls.add(ball);
					timer.schedule(ball,1000, (int)(10 + Math.random()*70));
					repaint();
					break;
				case 3:
					ball = new Ball(viewW,(int)(Math.random()*viewH));
					balls.add(ball);
					timer.schedule(ball,1000, (int)(10 + Math.random()*70));
					repaint();
					break;
				case 4:
					ball = new Ball((int)(Math.random()*viewW),viewH);
					balls.add(ball);
					timer.schedule(ball,1000, (int)(10 + Math.random()*70));
					repaint();
					break;
				}
			}
		}
	}
	
	private class ViewTask extends TimerTask{
		@Override
		public void run() {
			if(isGameOver==false){
				repaint();
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d= (Graphics2D)g;
		
		viewW = getWidth(); viewH=getHeight();
		
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, viewW, viewH);
		
		g2d.setColor(Color.red);
		g2d.fillOval(ship.x, ship.y, 20, 20);
		
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
			dx = (x == ship.x )? 0 : (x > ship.x ? -10-(int)(Math.random()*10) : 10+(int)(Math.random()*10)); 
			dy = (y == ship.y )? 0 : (y > ship.y ? -10-(int)(Math.random()*10) : 10+(int)(Math.random()*10));
		}
		@Override
		
		public void run() {
			if(isGameOver==false){
				x += dx;
				y += dy;
				repaint();
			}
		}
	}
	private class Ship extends TimerTask{
		int x, y;
		Ship(int x, int y){
			this.x = x; this.y = y; 
		}
		@Override
		public void run() {
			if(isGameOver == false){
				for(Ball ball : balls){
					if(ball.x+10==ship.x-20 && ball.y==ship.y){
						isGameOver = true;
					}else if(ball.x-10==ship.x+20 && ball.y==ship.y){
						isGameOver = true;
					}else if(ball.x==ship.x && ball.y+10==ship.y-20){
						isGameOver = true;
					}else if(ball.x==ship.x && ball.y-10==ship.y+20){
						isGameOver = true;
					} 
				}
				repaint();
			}
		}	
	}
}

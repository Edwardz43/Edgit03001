package tw.org.iii.ed;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
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
		ship = new Ship();
		balls = new LinkedList<>();
		timer = new Timer();
		timer.schedule(new ViewTask(),0, 30);
		timer.schedule(new createBall(), 0 , 80);
		timer.schedule(ship, 200, 10);
		isGameOver = false;
		addMouseMotionListener(new MyMouseAdapter());
	}
	private class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
			requestFocus();
			ship.x = e.getX()-20; ship.y = e.getY()-20;
		}
	}
	
	private class createBall extends TimerTask{
		@Override
		public void run() {
			if(isGameOver==false){
				int n = (int)(Math.random()*4);
				switch(n){
				case 1:
					ball = new Ball((int)(Math.random()*viewW+1),0);
					balls.add(ball);
					ball.start();
					break;
				case 2:
					ball = new Ball(0,(int)(Math.random()*viewH+1));
					balls.add(ball);
					ball.start();
					break;
				case 3:
					ball = new Ball(viewW,(int)(Math.random()*viewH+1));
					balls.add(ball);
					ball.start();
					break;
				case 4:
					ball = new Ball((int)(Math.random()*viewW+1),viewH);
					balls.add(ball);
					ball.start();
					break;
				}
			}
		}
	}
	
	private class ViewTask extends TimerTask{
		@Override
		public void run() {
			if(isGameOver==false){
				for(Ball ball : balls){
					if(ball.x+10==ship.x && ball.y==ship.y){
						isGameOver = true;
					}else if(ball.x-10==ship.x && ball.y==ship.y){
						isGameOver = true;
					}else if(ball.x==ship.x && ball.y+10==ship.y){
						isGameOver = true;
					}else if(ball.x==ship.x && ball.y-10==ship.y){
						isGameOver = true;
					} 
				}
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
		g2d.fillOval(ship.x, ship.y, 40, 40);
		
		g2d.setColor(Color.yellow);
		
		try{
			for(Ball ball : balls){
				g2d.fillOval(ball.x, ball.y, 20, 20);
			}
		}catch(Exception ee){}
		if(isGameOver){
			g2d.setColor(Color.red);
			g2d.setStroke(new BasicStroke(18));
			g2d.drawString("Game Over !", viewW/2-30,viewH/2);
		}
	}
	
	private class Ball extends Thread{
		int x, y, dx, dy;
		Ball(int x, int y){
			this.x = x; this.y = y; 
			dx = (x == ship.x )? 0 : (x > ship.x ? -5-(int)(Math.random()*5) : 5+(int)(Math.random()*5)); 
			dy = (y == ship.y )? 0 : (y > ship.y ? -5-(int)(Math.random()*5) : 5+(int)(Math.random()*5));
		}
		@Override
		
		public void run() {
			if(isGameOver==false){
				while(x >-20 && x < viewW+20 && y >= 0-20 && y < viewH+20){
					x += dx;
					y += dy;
					try {
						Thread.sleep(60);
					} catch (InterruptedException e) {}
					repaint();
				}
			}
		}
	}
	
	
	private class Ship extends TimerTask{
		int x, y;
		
		@Override
		public void run() {
			if(isGameOver == false){
				
				repaint();
			}
		}	
	}
}

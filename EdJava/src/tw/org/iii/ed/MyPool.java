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

public class MyPool extends JPanel{
	private Ball ball;
	private Timer timer;
	private int viewW, viewH;
	LinkedList<Ball> balls;
	
	public MyPool(){
		timer = new Timer();
		timer.schedule(new ViewTask(),0, 10);
		balls = new LinkedList<>();
		addMouseListener(new MyMouseAdapter());
	}
	private class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			Ball ball = new Ball(e.getX(), e.getY());
			balls.add(ball);
			timer.schedule(ball,1000, (int)(30 + Math.random()*30));
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
		
		g2d.setColor(Color.yellow);
		
		for(Ball ball : balls){
			g2d.fillOval(ball.x, ball.y, 10, 10);
		}
	}
	
	private class Ball extends TimerTask{
		int x, y, dx, dy;
		Ball(int x, int y){
			this.x = x; this.y = y; dx = dy = 10;
		}
		@Override
		public void run() {
			if(x < 0 || x + 10 > viewW){
				dx *= -1;
			}
			if(y < 0 || y + 10 > viewH){	
				dy *= -1;
			}
			
			x += dx;
			y += dy;
			repaint();
		}
		
	}
	
}

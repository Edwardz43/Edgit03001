package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MyPool2 extends JPanel{
	private Ball ball;
	private Timer timer;
	private int viewW, viewH;
	private LinkedList<Ball> balls;
	private boolean isGameOver;
	private long startTime, endTime;
	private JPanel menuBar;
	private JButton start;
	private JRadioButton[] btns = new JRadioButton[3];
	private int shipX, shipY;
	
	public MyPool2(){
		setLayout(new BorderLayout());
		
		menuBar = new JPanel();
		menuBar.setLayout(new FlowLayout());
		
		start = new JButton("開始遊戲");
		menuBar.add(start,FlowLayout.LEFT);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startgame();
			}
		});
		
//		for(int i=0;i<3; i++){
//			btns[i] = new JRadioButton();
//			btns[i].setText(""+(i+1));
//			add(btns[i],FlowLayout.LEFT);
//		}
		add(menuBar, BorderLayout.NORTH);
		balls = new LinkedList<>();
		timer = new Timer();
		timer.schedule(new ViewTask(),0, 30);
		timer.schedule(new createBall(), 1000 , 70);
		isGameOver = true;
		addMouseMotionListener(new MyMouseAdapter());
	}
	
	private class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
				shipX = e.getX()-20; shipY = e.getY()-20;	
		}
	}
	
	protected void startgame() {
		isGameOver = false;
		startTime = System.currentTimeMillis();
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
				repaint();
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2d= (Graphics2D)g;
		if(isGameOver){
			g2d.setColor(Color.red);
			g2d.setFont(new Font(null,Font.CENTER_BASELINE, 30));
			g2d.drawString("Game Over !", viewW/2-100,viewH/2);
			g2d.drawString("Record:"+endTime/1000.0, viewW/2-100,viewH/2+30);
			
		}else{
		
		viewW = getWidth(); viewH=getHeight();
		
		g2d.setColor(new Color(219, 255, 255));
		g2d.fillRect(0, 0, viewW, viewH);
		
		g2d.setColor(new Color(91, 91, 174));
		g2d.fillOval(shipX, shipY, 40, 40);
		
		g2d.setColor(new Color(107, 107, 107));
		
		try{
			for(Ball ball : balls){
				if(isGameOver){
					ball.interrupt();
				}
				g2d.fillOval(ball.x, ball.y, 20, 20);
				
			}
		}catch(Exception ee){}
		}
		
	}
	
	private class Ball extends Thread{
		int x, y, dx, dy;
		Ball(int x, int y){
			this.x = x; this.y = y; 
			dx = (x == shipX )? 0 : (x > shipX ? -5-(int)(Math.random()*5) : 5+(int)(Math.random()*5)); 
			dy = (y == shipY )? 0 : (y > shipY ? -5-(int)(Math.random()*5) : 5+(int)(Math.random()*5));
		}
		@Override
		
		public void run() {
			if(isGameOver==false){
				while(x >-20 && x < viewW+20 && y >= 0-20 && y < viewH+20){
					x += dx;
					y += dy;
					if(isGameOver) {
						repaint();
						return;
					}
					if(x+10 >= shipX  && x - 10 <= shipX+10 && y +10 >= shipY && y -10 <= shipY + 10 ){
						isGameOver = true;
						endTime = System.currentTimeMillis()- startTime;
						
						System.out.println("playTime:"+endTime/1000.0);
						
					}
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {}
				}
				repaint();
			}
		}
	}
}

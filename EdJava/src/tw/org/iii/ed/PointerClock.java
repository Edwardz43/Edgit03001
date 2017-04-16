package tw.org.iii.ed;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class PointerClock extends JPanel{
	private Pointer secPointer, minPointer, hrPointer;
	private Timer timer;
	private int viewW, viewH;
	//LinkedList<Pointer> pointers;
	
	public PointerClock(){
		//先預設視窗大小
		setSize(800, 640);
		//抓出銀幕的正中心點  把時鐘置中
		viewW = getWidth()/2; viewH = getHeight()/2;
		
		timer = new Timer();
		//三種針
		secPointer = new Pointer();
		minPointer = new Pointer();
		hrPointer = new Pointer();
		//v1版本  指針一次走0.6度  所以時間間格*10倍 讓指針有走很快的效果 
		timer.schedule(secPointer, 1000 , 100);
		timer.schedule(minPointer, 1000 , 60*100);
		timer.schedule(hrPointer, 1000 , 60*60*100);	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//System.out.println(viewW+"+"+viewH);
		//背景色
		g2d.setColor(Color.gray);
		//填滿 記得*2
		g2d.fillRect(0, 0, viewW*2, viewH*2);
		
		//畫圓
		g2d.setStroke(new BasicStroke(10));
		g2d.setColor(Color.black);
		//圓心位置要置中  半徑就和長的一半相等
		g2d.drawOval(viewW-viewW/2, viewH-viewW/2, viewW, viewW);
		
		//畫刻度
		g2d.setStroke(new BasicStroke(10));
		g2d.setColor(Color.black);
		
		//主要的技巧和指針一樣  每6度就畫一個  長度我是選擇10
		for(int i=0; i<60; i++){
			g2d.drawLine(viewW + cosPointer(i*10, viewW/2), viewH + sinPointer(i*10, viewW/2), 
					viewW + cosPointer(i*10, viewW/2-10), viewH + sinPointer(i*10, viewW/2-10));
		}
		
		//指針期時三根都一樣 只有timertask的地方會不同  之後改版會再修
		//秒針
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.red);
		g2d.drawLine(viewW, viewH, secPointer.x, secPointer.y);
		
		//分針
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(Color.blue);
		g2d.drawLine(viewW, viewH, minPointer.x, minPointer.y);
		
		//時針
		g2d.setStroke(new BasicStroke(10));
		g2d.setColor(Color.yellow);
		g2d.drawLine(viewW, viewH, hrPointer.x, hrPointer.y);	
	}
	
	/*設兩個方法  輸入時間(n) 半徑(r)   就可以回傳指針移動的軌跡(x, y)
	 * 主要就是高中數學的極坐標  任一點坐標可用(r*cosX, r*sinX)來表示  r是半徑  X是角度
	 * 因為時鐘從12點開始算  和一般直角坐標不同  所以+90度
	 * 但程式語言的作標和一般直角坐標上下顛倒  所以變成-90度(就是差180度的概念)
	 * 又因為想讓指針有持續移動的感覺 所一次移動0.6度  也就是加快10倍
	 */ 
	public int cosPointer(int time, int r){
		return (int)((Math.cos(Math.toRadians(time*0.6-90)))*r);
	}
	public int sinPointer(int time, int r){
		return (int)((Math.sin(Math.toRadians(time*0.6-90)))*r);
	}
	
	private class Pointer extends TimerTask{
		private int x, y, n;	
		Pointer(){
			x=viewW; y=viewH; n=0; 
		}
		
		//試作版的  暫時先設個計數器n 每跑一次就累加1 無線迴圈
		@Override
		public void run() {
			x = viewW + cosPointer(n, 150);
			y = viewH + sinPointer(n, 150);
			n++;
			
			repaint();
		}
		
	}
}
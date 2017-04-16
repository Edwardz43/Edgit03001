package tw.org.iii.ed;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

/*時鐘小改版:
 * 1.把麻煩的三角函數打包成方法  看起來比較清爽
 * 2.加入縮放功能  可隨視窗大小縮放
 * 3.加入calendar對時功能 
 */

public class PointerClock extends JPanel{
	private MyClockTask myclock;
	private Timer timer;
	private int viewW, viewH;
	private int W, H;
	private int x, y, hh, mm, ss;
	String number[] = { "12 ", " 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 ", "10 ", "11 " };
	
	public PointerClock(){
		//先預設視窗大小
		setSize(600, 480);
		//抓出銀幕的正中心點  把時鐘置中
		viewW = getWidth()/2; viewH = getHeight()/2;
		
		//設置timer
		timer = new Timer();
		myclock = new MyClockTask();
		
		//因為是根據calendar的時間 所以間隔取多少是無所謂
		timer.schedule(myclock, 1000 , 1000);	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//新增  讓時鐘跟著視窗縮放
		viewW = getWidth()/2; viewH = getHeight()/2;
		
		//背景色
		g2d.setColor(Color.lightGray);
		//填滿 記得*2
		g2d.fillRect(0, 0, viewW*2, viewH*2);
		
		//畫圓
		g2d.setStroke(new BasicStroke(15));
		g2d.setColor(Color.black);
		//圓心位置要置中  半徑就和長的一半相等
		g2d.drawOval((int)(viewW-viewW/2), (int)(viewH-viewW/2), viewW, viewW);
		
		//畫刻度
		g2d.setStroke(new BasicStroke(3));
//		g2d.setColor(Color.black);
		
		//主要的技巧和指針一樣  每6度就畫一個  長度我是選擇10
		for(int i=0; i<60; i++){
			if(i%5==0){
				//5的倍數要長一點
				g2d.drawLine(cosPointer(i, viewW/2), sinPointer(i, viewW/2), 
						cosPointer(i, viewW/2-20), sinPointer(i, viewW/2-20));
			}else{
				g2d.drawLine(cosPointer(i, viewW/2), sinPointer(i, viewW/2), 
					cosPointer(i, viewW/2-10), sinPointer(i, viewW/2-10));
			}
		}
		
		//畫數字
		g2d.setFont(new Font(null, Font.ITALIC, 18));
		g2d.setColor(Color.MAGENTA);
		for(int i=0; i<12; i++){
			//微調位置  讓數字跟刻度對齊
			g2d.drawString(number[i], cosPointer(i*5, viewW/2-40)-9, sinPointer(i*5, viewW/2-40)+7);
		}
		
		//秒針
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.red);
		g2d.drawLine(viewW, viewH, cosPointer(ss, viewW/3), sinPointer(ss, viewW/3));
		
		//分針
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(Color.blue);
		g2d.drawLine(viewW, viewH, cosPointer(mm, viewW/4), sinPointer(mm, viewW/4));
		
		//時針
		g2d.setStroke(new BasicStroke(8));
		g2d.setColor(Color.green);
		g2d.drawLine(viewW, viewH, cosPointer((int)(hh*5+mm*0.5/6), viewW/5), sinPointer((int)(hh*5+mm*0.5/6), viewW/5));	
	}
	
	/*設兩個方法  輸入時間(n) 半徑(r)  就可以回傳指針移動的軌跡(x, y)
	 * 主要就是高中數學的極坐標  任一點坐標可用原點坐標加上(r*cosX, r*sinX)來表示  r是半徑  X是角度
	 * 因為時鐘從12點開始算  和一般直角坐標不同  所以+90度
	 * 但程式語言的作標和一般直角坐標上下顛倒  所以變成-90度(就是差180度的概念)
	 */ 
	public int cosPointer(int time, int r){
		return viewW+(int)((Math.cos(Math.toRadians(time*6.0-90)))*r);
	}
	public int sinPointer(int time, int r){
		return viewH+(int)((Math.sin(Math.toRadians(time*6.0-90)))*r);
	}
		
	
	//calendar拿來玩  
	private class MyClockTask extends TimerTask{
		@Override
		public void run() {
			Calendar now = Calendar.getInstance();
			hh =now.get(Calendar.HOUR);
			mm = now.get(Calendar.MINUTE);
			ss = now.get(Calendar.SECOND);;
			//測試用
			System.out.println( hh+":"+ mm+":"+ss);
			repaint();
		}
	}
}
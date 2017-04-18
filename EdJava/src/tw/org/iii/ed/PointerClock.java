package tw.org.iii.ed;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*時鐘小改版:
 * 1.把麻煩的三角函數打包成方法  看起來比較清爽
 * 2.加入縮放功能  可隨視窗大小縮放
 * 3.加入calendar對時功能 
 * 4.增加指針移動效果明顯化以及秒針尾巴效果
 * 5.更改數字顯示 => 羅馬數字
 * 6.更改變數名稱
 */

public class PointerClock extends JPanel{
	private MyClockTask myclock;
	private Timer timer;
	private int halfW, halfH;
	private int hh, mm, ss, sss;
	private JLabel digiClock;
	String number[] = { "\u2169\u2161", "\u2160", "\u2161", "\u2162", "\u2163", "\u2164", "\u2165",
			"\u2166", "\u2167", "\u2168", "\u2169", "\u2169\u2160" };
	
	public PointerClock(){
		setLayout(new BorderLayout());
		
		//抓出銀幕的正中心點  把時鐘置中
		halfW = getWidth()/2; halfH = getHeight()/2;
		
		//設置timer
		timer = new Timer();
		myclock = new MyClockTask();
		
		//加一個label 等等放數位時鐘
		digiClock = new JLabel();
		add(digiClock, BorderLayout.SOUTH);
		
		//因為是根據calendar的時間 所以間隔取多少是無所謂
		timer.schedule(myclock, 1000 , 1);	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//新增  讓時鐘跟著視窗縮放
		halfW = getWidth()/2; halfH = getHeight()/2;
		
		//背景色
		g2d.setColor(new Color(231, 204, 255));
		//填滿 記得*2
		g2d.fillRect(0, 0, halfW*2, halfH*2);
		
		//畫圓
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(new Color(235, 117, 0));
		
		//圓心位置要置中  
		g2d.drawOval((int)(halfW-halfW/2), (int)(halfH-halfW/2), halfW, halfW);
		
		//畫刻度
		g2d.setStroke(new BasicStroke(3));
//		g2d.setColor(Color.black);
		
		//主要的技巧和指針一樣  每6度就畫一個  長度隨視窗大小變化
		for(int i=0; i<60; i++){
			if(i%5==0){
				//5的倍數要長一點
				g2d.drawLine(cosPointer(i*10, halfW/2.0), sinPointer(i*10, halfW/2.0), 
						cosPointer(i*10, halfW*0.46), sinPointer(i*10, halfW*0.46));
			}else{
				g2d.drawLine(cosPointer(i*10, halfW/2), sinPointer(i*10, halfW/2), 
					cosPointer(i*10, halfW*0.48), sinPointer(i*10, halfW*0.48));
			}
		}
		
		//畫數字
		g2d.setFont(new Font(null, Font.ITALIC, 18));
		g2d.setColor(new Color(65, 129, 129));
		for(int i=0; i<12; i++){
			//微調位置  讓數字跟刻度對齊
			g2d.drawString(number[i], cosPointer(i*50, halfW*0.4)-9, sinPointer(i*50, halfW*0.4)+7);
		}
		
		// 4/18:加上時分秒移動量   讓移動效果明顯
		//時針
		g2d.setStroke(new BasicStroke(8));
		g2d.setColor(Color.green);
		g2d.drawLine(halfW, halfH, cosPointer((hh+mm/60.0)*50, halfW*0.2), sinPointer((hh+mm/60.0)*50, halfW*0.2));	
		
		//分針
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(Color.blue);
		g2d.drawLine(halfW, halfH, cosPointer((mm+ss/60.0)*10, halfW*0.3), sinPointer((mm+ss/60.0)*10, halfW*0.3));
		
		//秒針
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.red);
		g2d.drawLine(halfW, halfH, cosPointer(ss*10+sss*0.01, halfW*0.4), sinPointer(ss*10+sss*0.01, halfW*0.4));
		//新增: 秒針長尾巴
		g2d.drawLine(halfW, halfH, cosPointer((ss*10+sss*0.01)+300, halfW/9), sinPointer((ss*10+sss*0.01)+300, halfW/9));
				
	}
	
	/*設兩個方法  輸入時間(n) 半徑(r)  就可以回傳指針移動的軌跡(x, y)
	 * 主要就是高中數學的極坐標  任一點坐標可用原點坐標加上(r*cosX, r*sinX)來表示  r是半徑  X是角度
	 * 因為時鐘從12點開始算  和一般直角坐標不同  所以+90度
	 * 但程式語言的作標和一般直角坐標上下顛倒  所以變成-90度(就是差180度的概念)
	 * 4/18: 改成每次移動0.6度 讓移動效果更明顯    故所有變數都跟著放大10倍
	 */ 
	public int cosPointer(double time, double r){
		return halfW+(int)((Math.cos(Math.toRadians(time*0.6-90)))*r);
	}
	public int sinPointer(double time, double r){
		return halfH+(int)((Math.sin(Math.toRadians(time*0.6-90)))*r);
	}
		
	
	//calendar拿來玩  
	private class MyClockTask extends TimerTask{
		@Override
		public void run() {
			/*Simple Date Format = > 時間格式化  
			 * a:上下午   h, m, s: 時,分,秒
			 * 然後用Date將其印出   功能和calendar類似
			 */
			SimpleDateFormat sdf = new SimpleDateFormat("a hh:mm:ss");
			Date current = new Date();
			Calendar now = Calendar.getInstance();
			hh =now.get(Calendar.HOUR);
			mm = now.get(Calendar.MINUTE);
			ss = now.get(Calendar.SECOND);;
			sss = now.get(Calendar.MILLISECOND);
			//旁邊印出數位時鐘
			digiClock.setFont(new Font(null, Font.ROMAN_BASELINE, 18));
			digiClock.setText(sdf.format(current));
			repaint();
		}
	}
	//秀出時鐘
	public static void main(String[] args){
		JFrame frame = new JFrame("Clock");
		Clock2 clock = new Clock2();
		frame.add(clock);
		frame.setVisible(true);
		frame.setSize(320, 240);
		frame.setDefaultCloseOperation(3);
	}
}
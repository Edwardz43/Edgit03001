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
	private BGTask bgtask;
	private Timer timer, timerBG;
	private int halfW, halfH, r, sR, mR, hR;
	private int hh, mm, ss, sss;
	private String currentTime="", currentDate="", currentSec="";
	private String number[] = { "12",  " 3", " 6",  " 9" };
	
	public PointerClock(){
		setLayout(new BorderLayout());
		
		//抓出銀幕的正中心點  把時鐘置中
		halfW = getWidth()/2; halfH = getHeight()/2;
		
		//設置timer
		timer = new Timer();
		timerBG= new Timer();
		myclock = new MyClockTask();
		bgtask = new BGTask();
	
		//因為是根據calendar的時間 所以間隔取多少是無所謂
		timer.schedule(myclock, 1000 , 100);
		timerBG.schedule(bgtask, 0, 100);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//新增  讓時鐘跟著視窗縮放
		halfW = getWidth()/2; halfH = getHeight()/2;
		
		//新增:圓與指針的半徑  方便之後修改
		r = Math.min(halfW*3/4,halfH*3/4);
		sR = (int)(0.8*r);mR = (int)(0.6*r);hR = (int)(0.5*r);
		
		//畫圓
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(new Color(57, 59, 167));
		
		//圓心位置要置中  
		g2d.fillOval(halfW-r, halfH-r, r*2, r*2);
		
		//新增: 日期
//		g2d.setColor(new Color(255, 143, 87));
//		g2d.setFont(new Font(null, Font.ITALIC, scaleParam(14)));
//		g2d.drawString(currentDate, getX(354, r*0.6), getY(354, r*0.6));
		
		//新增: 數位時鐘
		g2d.setColor(new Color(0, 235, 0));
		g2d.setFont(new Font(null, Font.CENTER_BASELINE, scaleParam(14)));
		g2d.drawString(currentTime, getX(240, r*0.3), getY(240, r*0.3));
		g2d.setFont(new Font(null, Font.CENTER_BASELINE, scaleParam(16)));
		g2d.drawString(currentSec, getX(230, r*0.6), getY(230, r*0.6));
		
		//畫刻度
		g2d.setStroke(new BasicStroke(3));
		
		//主要的技巧和指針一樣  每6度就畫一個  長度隨視窗大小變化
		for(int i=0; i<60; i++){
			if(i%5==0){
				//5的倍數要長一點
				g2d.setColor(new Color(0, 219, 0));
				g2d.drawLine(getX(i*10, r*0.995), getY(i*10, r*0.995), getX(i*10, r*0.88), getY(i*10, r*0.88));
			}else{
				g2d.setColor(new Color(219, 0, 0));
				g2d.drawLine(getX(i*10, r*0.995), getY(i*10, r*0.995), getX(i*10, r*0.96), getY(i*10, r*0.96));
			}
		}
		
		//畫數字
		g2d.setFont(new Font(null, Font.PLAIN, scaleParam(18)));
		g2d.setColor(new Color(255, 143, 87));
		for(int i=0; i<4; i++){
			//微調位置  讓數字跟刻度對齊
			g2d.drawString(number[i], getX(i*150, r*0.7)-scaleParam(9), getY(i*150, r*0.7)+scaleParam(7));
		}
		
		// 4/18:加上時分秒移動量   讓移動效果明顯
		//時針
		g2d.setStroke(new BasicStroke(scaleParam(3)));
		g2d.setColor(new Color(255, 238, 153));
		g2d.drawLine(halfW, halfH, getX((hh+mm/60.0)*50, hR), getY((hh+mm/60.0)*50, hR));	
		
		//分針
		g2d.setStroke(new BasicStroke(scaleParam(3)));
		//g2d.setColor(new Color(154, 76, 76));
		g2d.drawLine(halfW, halfH, getX((mm+ss/60.0)*10, mR), getY((mm+ss/60.0)*10, mR));
		
		//秒針
		g2d.setStroke(new BasicStroke(scaleParam(3)));
		//g2d.setColor(new Color(154, 76, 76));
		g2d.drawLine(halfW, halfH, getX(ss*10+sss*0.01, sR), getY(ss*10+sss*0.01, sR));
		
		//新增: 秒針長尾巴 
		g2d.drawLine(halfW, halfH, getX((ss*10+sss*0.01)+300, sR/5), getY((ss*10+sss*0.01)+300, sR/5));
		
		//新增: 小小美化 火材棒頭
		int tail2R=8; int tailR=tail2R/2;
		g2d.setColor(new Color(248, 101, 93));
		g2d.fillOval(getX((hh+mm/60.0)*50, hR)-scaleParam(tailR), getY((hh+mm/60.0)*50, hR)-scaleParam(tailR), scaleParam(tail2R), scaleParam(tail2R));
		g2d.fillOval(getX((mm+ss/60.0)*10, mR)-scaleParam(tailR), getY((mm+ss/60.0)*10, mR)-scaleParam(tailR), scaleParam(tail2R), scaleParam(tail2R));
		g2d.fillOval(getX(ss*10+sss*0.01, sR)-scaleParam(tailR), getY(ss*10+sss*0.01, sR)-scaleParam(tailR), scaleParam(tail2R), scaleParam(tail2R));
				
	}
	
	 /*設兩個方法  輸入時間(n) 半徑(r)  就可以回傳指針移動的軌跡(x, y)
	 * 主要就是高中數學的極坐標  任一點坐標可用原點坐標加上(r*cosX, r*sinX)來表示  r是半徑  X是角度
	 * 因為時鐘從12點開始算  和一般直角坐標不同  所以+90度
	 * 但程式語言的作標和一般直角坐標上下顛倒  所以變成-90度(就是差180度的概念)
	 * 4/18: 改成每次移動0.6度 讓移動效果更明顯    故所有變數都跟著放大10倍
	 * 4/20: 微調公式 => cos(X-90)=sin(x)  sin(X-90)= -cos(X)  高中數學:三角函數-餘角公式
	 */
	
	//4/20: 修改方法名稱: => getX, getY
	public int getX(double time, double r){
		return halfW+(int)((Math.sin(Math.toRadians(time*0.6)))*r);
	}
	public int getY(double time, double r){
		return halfH+(int)((Math.cos(Math.toRadians(time*0.6)))*r*-1);
	}
	
	//4/20: 新增:scale參數  方便控制元件的大小
	public int scaleParam(int n){
		return (int)(n*Math.min(getWidth()/320.0 ,getHeight()/240.0));
	}
		
	private class MyClockTask extends TimerTask{
		@Override
		public void run() {
			/*Simple Date Format = > 時間格式化  
			 * a:上下午   h, m, s: 時,分,秒
			 * 然後用Date將其印出   功能和calendar類似
			 */
			SimpleDateFormat sdf = new SimpleDateFormat(" H:mm");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("s");
			Date current = new Date();
			Date current2 = new Date();
			Date current3 = new Date();
			Calendar now = Calendar.getInstance();
			hh =now.get(Calendar.HOUR);
			mm = now.get(Calendar.MINUTE);
			ss = now.get(Calendar.SECOND);;
			sss = now.get(Calendar.MILLISECOND);
			//數位時鐘跟日期
			currentTime = sdf.format(current);
			currentDate = sdf1.format(current2);
			currentSec = sdf2.format(current3);
			repaint();
		}
	}
	
	private class BGTask extends TimerTask{
		@Override
		public void run() {
			repaint();
		}
	}
	
	//秀出時鐘
	public static void main(String[] args){
		JFrame frame = new JFrame("Clock");
		PointerClock clock = new PointerClock();
		frame.add(clock);
		frame.setVisible(true);
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(3);
	}
}